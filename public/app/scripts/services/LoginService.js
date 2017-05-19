var login = angular.module("login", []);

/**
 * Serviços da pagina de login
 */
login.service("LoginService", ['ModalMensagemService', '$http','$state', function LoginService(ModalMensagemService, $http, $state) {
	var self = this;
	var URL_ROOT = "/api";
	/**
	 * Faz login no sistema via oauth2
	 */
	this.login = function(username, password) {
		var base64 = window.btoa(username + ":" + password);
		var config = {
				withCredentials: true,
				headers:  {
						'Authorization': 'Basic ' + base64,
				}
		};
		$http.get(URL_ROOT + "/login", config).then(function(success) {
			var token = success.data;
			sessionStorage.token = JSON.stringify(token);
			self.token = token;
			$http.defaults.headers.common['Role'] = token.usuario.role;
			$http.defaults.headers.common['Authorization'] = "Bearer " + token.bearer; 
			$state.go('delta.listar-condominios');
		}, function(fail) {
			ModalMensagemService.erro("Usuário ou senha inválidos.")	
		})
	}
}]);
