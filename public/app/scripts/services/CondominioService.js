var condominio = angular.module("condominio", []);

condominio.service("CondominioService", ['$state','$http','$resource', function CondominioService($state, $http, $resource) {
	var self = this;
	var CONDOMINIO_URL = '/api/condominio';
	var PESSOA_URL = '/api/pessoa';
	var AVISOS_URL = '/aviso';

	this.getCondominio = function(id) {
		return $http.get(CONDOMINIO_URL + "/" + id);
	}
	
	this.getCondominios = function (sindicoId) {
		return $http.get(CONDOMINIO_URL + (sindicoId ? ("?sindicoId=" + sindicoId) : ""));
	}
	
	this.salvarCondominio = function(condominio) {
		return $http.post(CONDOMINIO_URL, condominio)
	}

	this.editarCondominio = function(condominio) {
		return $http.put(CONDOMINIO_URL + '/' +condominio.id, condominio)
	}
	
	this.getAvisos = function(condominioId) {
		return $http.get(CONDOMINIO_URL + '/' + condominioId + AVISOS_URL);
	}
	
	this.salvarAviso = function(condominioId, aviso) {
		return $http.post(CONDOMINIO_URL + '/' + condominioId + AVISOS_URL, aviso);
	}
	
	this.removerCondominio = function(condominio) {
		return $http.delete(CONDOMINIO_URL + '/' + condominio.id)
	}
}]);