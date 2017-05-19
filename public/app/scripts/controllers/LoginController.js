var login = angular.module("login");

/**
 * Controla a página de login do sistema
 */
login.controller("LoginController", [ '$http', '$scope', '$state',
		'LoginService',
		function LoginController($http, $scope, $state, loginService) {
			var URL_ROOT = "/api";
			/**
			 * Faz login no sistema
			 */
			$scope.login = function() {
				loginService.login($scope.username, $scope.password)
				loginService.loginUsername = $scope.username;
			}

			$scope.editarPerfil = function() {
				var id = JSON.parse(sessionStorage.token).usuario.id;
				$state.go("delta.editar-usuario", {id:id});
			}

			/**
			 * Faz logout no sistema
			 */
			$scope.logout = function() {
				delete sessionStorage.token;
				$http.defaults.headers.common['Role'] = null;
				$http.defaults.headers.common['Authorization'] = null;
			}
			if (!_.isUndefined(sessionStorage.token)) {
				$state.go('delta.listar-condominios');
			}
		} ]);