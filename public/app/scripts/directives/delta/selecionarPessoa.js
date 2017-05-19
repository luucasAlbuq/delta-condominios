
	
var selecionarPessoa = angular.module("selecionarCondominio");
/**
 * Diretiva de selecionar pessoa
 */
selecionarPessoa.directive('selecionarPessoa',['PessoaService', function(pessoaService){
   return {
	 templateUrl:'scripts/directives/delta/selecionar-pessoa.html',
	 restrict: 'EA',
	 replace: true,
     scope: {
    	 modelo: '='
     },
     link: function(scope, element, attrs) {
    	scope.pessoas = []
    	pessoaService.getAllPessoas().then(function(sucess){
    		scope.pessoas.push.apply(scope.pessoas, sucess.data);
    	});
     }
   };
}]);
