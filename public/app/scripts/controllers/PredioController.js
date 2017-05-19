
// Módulo da Página inicial do delta-condominios
var condominio = angular.module("condominio");

condominio.controller("PredioController", ['ModalMensagemService', '$state','$stateParams','$http','$resource','PessoaService',
 function PredioController(ModalMensagemService, $state, $stateParams, $http, $resource,pessoaService) {
	var self = this;

	/*Lista de pessoas cadastradas*/
	this.pessoas = [];
	
	pessoaService.getAllPessoas().then(function(sucess){
		self.pessoas.push.apply(self.pessoas, sucess.data);
	});

	var PREDIO_URL = '/api/condominio' + "/" + $stateParams.id + "/predio";
	if($stateParams.idPredio) {
		$http.get(PREDIO_URL + "/" + $stateParams.idPredio).then(function(sucess) {
			self.predio = sucess.data;
			self.predio.apartamentos = self.predio.apartamentos || [];
		});
	} else {
		self.predio = {};
		self.predio.apartamentos = [];
	}

	/*
	* Editar condomínio no banco de dados.
	* Mostra um alert com mensagem de sucesso ou falha.
	*/
	this.atualizarPredio = function () {
		$http.put(PREDIO_URL +"/" + $stateParams.idPredio, self.predio).then(function(data, headers) {
			ModalMensagemService.sucesso("Predio foi alterado com sucesso!");
			$state.go('delta.listar-condominios');
		}, function(data) {
			ModalMensagemService.erro("Falha ao editar Predio");	
		});
	}

	/*
	* Verifica se a tela contém utilização de id, se sim, habilita botões da tela de edição, se não, 
	* habilita botão para tela de cadastro de condomínio.
	*/
	this.podeVisualizarBotaoEdicao = function(){
		return _.isUndefined($stateParams.idPredio);
	}

	this.mostrarModalPredio = function(apartamento) {
		var cloneApartamento = apartamento || _.clone(_.last(this.predio.apartamentos)) || {};
		this.apartamentoSelecionado = cloneApartamento;
		delete this.apartamentoSelecionado.id;
		if(_.isUndefined(apartamento)) {
			this.predio.apartamentos.push(this.apartamentoSelecionado);
		}
	}
}]);