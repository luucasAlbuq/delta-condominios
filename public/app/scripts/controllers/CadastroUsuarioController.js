var usuario = angular.module("usuario");

usuario.controller("CadastroUsuarioController", ['ModalMensagemService', '$state','$stateParams','$http','$resource','UsuarioService', function CadastroUsuarioController(ModalMensagemService, $state, $stateParams, $http, $resource, usuarioService) {
	var self = this;
	this.usuario = {};

	if($stateParams.id) {
		usuarioService.getUsuario($stateParams.id).then(function(sucess) {
			self.usuario = sucess.data;
		});
	}else {
		this.usuario = {};
	}

	/*
	* Salva usuario no banco de dados.
	* Mostra um alert com mensagem de sucesso ou falha.
	*/
	this.salvarUsuario = function () {
		if(self.senhaValidada()){
			if(self.usuario.id) {
				usuarioService.atualizarUsuario(this.usuario, this.usuario.id).then(function(success){
					ModalMensagemService.sucesso("Atualizado com sucesso")
				}, function(fail) {
					ModalMensagemService.erro("Falha ao atualizar")
				});
			} else {
				usuarioService.salvarUsuario(this.usuario).then(function(data, headers) {
					ModalMensagemService.sucesso("Usuário cadastrado com sucesso");
					$state.go('delta.listar-condominios');
				}, function(data) {
					ModalMensagemService.erro("Falha ao cadastrar usuário");	
				});
			}
		}else{
			ModalMensagemService.erro("Senhas não conferem!");
		}

	}

	this.senhaValidada = function () {
		var senha1 = this.usuario.senha;
		var senha2 = this.senha2;
		return (senha1 === senha2);
	}
}]);