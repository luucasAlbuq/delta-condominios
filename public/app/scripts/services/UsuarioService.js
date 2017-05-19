var usuario = angular.module("usuario", []);

usuario.service("UsuarioService", ['$http', function UsuarioService($http) {
	var USUARIO_URI = "/api/usuario";
	
	this.salvarUsuario = function(usuario){
		return $http.post(USUARIO_URI, usuario);
	}

	this.atualizarUsuario = function(usuario, id){
		return $http.put(USUARIO_URI + "/" + id, usuario);
	}

	this.getUsuario = function(id){
		return $http.get(USUARIO_URI + "/" + id);
	}
}]);










