
// Módulo da Página inicial do delta-condominios
var pessoa = angular.module("pessoa");

pessoa.controller("PessoaController", ['ModalMensagemService', '$state','$stateParams','$http','$resource','PessoaService', function PessoaController(ModalMensagemService, $state, $stateParams, $http, $resource, pessoaService) {
	var self = this;

	this.pessoas = [];

	if($stateParams.id) {
		pessoaService.getPessoa($stateParams.id).then(function(sucess) {
			self.pessoa = sucess.data;
		});
	}else {
		this.pessoa = {};
	}

	/*
	* Salva pessoa no banco de dados.
	* Mostra um alert com mensagem de sucesso ou falha.
	*/
	this.salvarPessoa = function () {
		pessoaService.salvarPessoa(this.pessoa).then(function(data, headers) {
			ModalMensagemService.sucesso("Pessoa cadastrada com sucesso");
			$state.go('delta.listar-condominios');
		}, function(data) {
			ModalMensagemService.erro("Falha ao cadastrar uma pessoa");	
		});
	}

	pessoaService.getAllPessoas().then(function(sucess){
		self.pessoas.push.apply(self.pessoas, sucess.data);
	});

}]);