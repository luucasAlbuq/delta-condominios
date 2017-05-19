angular.module('bootstrapModule', ['ui.bootstrap']);
var pessoa = angular.module("pessoa", []);

pessoa.service("PessoaService", ['$http','$resource', function PessoaService($http, $resource) {
	var self = this;
	var PESSOA_URL = '/api/pessoa';
	
	this.salvarPessoa = function(pessoa) {
		return $http.post(PESSOA_URL, pessoa)
	}

	this.getPessoa = function (id) {
		return $http.get(PESSOA_URL + "/" + id);
	}

	this.getAllPessoas = function(){
		return $http.get(PESSOA_URL);
	};
}]);













