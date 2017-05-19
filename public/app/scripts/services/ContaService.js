var conta = angular.module("condominio");

conta.service("ContaService", ['$http','$resource', function ContaService($http, $resource) {
	var self = this;
	var CONDOMINIO_URI = '/api/condominio';
	var CONTA_URI = '/conta'
	
	this.getDocumento = function (idCondominio, idConta) {
		return $http.get(CONDOMINIO_URI + "/" + idCondominio + CONTA_URI + "/" + idConta + '/documento', {responseType:'arraybuffer'});
	}
	this.salvarConta = function(idCondominio, conta) {
		return $http.post(CONDOMINIO_URI + "/" + idCondominio + CONTA_URI, conta)
	}

	this.atualizarConta = function(idCondominio, idConta, conta) {
		return $http.put(CONDOMINIO_URI + "/" + idCondominio + CONTA_URI + "/" + idConta, conta);
	}

	this.getConta = function (idCondominio, idConta) {
		return $http.get(CONDOMINIO_URI + "/" + idCondominio + CONTA_URI + "/" + idConta);
	}

	this.getTodasContasDoCondominio = function(idCondominio){
		return $http.get(CONDOMINIO_URI + "/" + idCondominio + CONTA_URI);
	};

}]);

