var selecionarCondominio = angular.module("selecionarCondominio", []);
/**
 * Diretiva de selecionar condominio
 */
selecionarCondominio.directive('selecionarCondominio',['CondominioService', function(CondominioService){
   return {
	 templateUrl: 'scripts/directives/delta/selecionar-condominio.html',
	 restrict: 'EA',
	 replace: true,
     scope: {
    	 condominio: '='
     },
     link: function(scope, element, attrs) {
    	 scope.condominios = [];
    	 CondominioService.getCondominios().then(function(info) {
    		 scope.condominios.push.apply(scope.condominios, info.data);
    	 })
     }
   };
}]);
