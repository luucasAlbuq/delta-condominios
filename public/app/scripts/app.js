    
'use strict';
/**
 * @ngdoc overview
 * @name sbAdminApp
 * @description
 * # sbAdminApp
 *
 * Main module of the application.
 */
var delta = angular
  .module('sbAdminApp', [
    'oc.lazyLoad',
    'ui.router',
    'ui.bootstrap',
    'ui.utils',
    'angularModalService',
    'ngResource',
    'chart.js',
    'angular-loading-bar',
    'angularFileUpload',
    // Delta dependencias
    'pagInicial',
    'condominio',
    'login',
    'modalMensagem',
    'selecionarCondominio',
    'pessoa',
    'usuario'
  ]);



delta.config(['$stateProvider','$urlRouterProvider','$ocLazyLoadProvider',function ($stateProvider,$urlRouterProvider,$ocLazyLoadProvider) {
	
    $ocLazyLoadProvider.config({
      debug:false,
      events:true,
    });

    $urlRouterProvider.otherwise('/login');

    $stateProvider
      .state("delta", {
        url:'/delta-condominios',
        templateUrl: 'views/delta/main.html',
         resolve: {
            loadMyDirectives:function($ocLazyLoad){
                return $ocLazyLoad.load(
                {
                    name:'sbAdminApp',
                    files:[
                    'scripts/directives/header/header.js',
                    'scripts/directives/header/header-notification/header-notification.js',
                    'scripts/directives/sidebar/sidebar.js',
                    'scripts/directives/sidebar/sidebar-search/sidebar-search.js'
                    ]
                }),
                $ocLazyLoad.load(
                        'bower_components/Chart.js/Chart.min.js'
                    ),
                    $ocLazyLoad.load({
                      name:'chart.js',
                      files:[
                        'bower_components/angular-chart.js/dist/angular-chart.min.js',
                        'bower_components/angular-chart.js/dist/angular-chart.css'
                      ]
                    }),
                    $ocLazyLoad.load({
                        name:'sbAdminApp',
                        files:['scripts/controllers/chartContoller.js']
                    }),
                $ocLazyLoad.load(
                {
                   name:'toggle-switch',
                   files:["bower_components/angular-toggle-switch/angular-toggle-switch.min.js",
                          "bower_components/angular-toggle-switch/angular-toggle-switch.css"
                      ]
                }),
                $ocLazyLoad.load(
                {
                  name:'ngAnimate',
                  files:['bower_components/angular-animate/angular-animate.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngCookies',
                  files:['bower_components/angular-cookies/angular-cookies.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngResource',
                  files:['bower_components/angular-animate/angular-animate.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngSanitize',
                  files:['bower_components/angular-sanitize/angular-sanitize.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngTouch',
                  files:['bower_components/angular-touch/angular-touch.js']
                })
            }
        }
      })
      .state('delta.novo-condominio',{
          templateUrl:'views/delta/cadastrar-condominio.html',
          url:'/novo-condominio',
          controller: 'CondominioController as condominioCtrl'
      })
      
      .state('delta.editar-condominio',{
          templateUrl:'views/delta/cadastrar-condominio.html',
          url:'/editar-condominio/:id',
          controller: 'CondominioController as condominioCtrl',
          resolve: {
        	  contasPromise: ['ContaService','$stateParams', function(ContaService, $stateParams) {
        		  return ContaService.getTodasContasDoCondominio($stateParams.id);
        	  }]
          }
      })
      .state('delta.editar-condominio.avisos',{
          templateUrl:'views/delta/avisos.html',
          url:'/avisos',
          controller: 'AvisosController as avisosCtrl',
          resolve: {
        	  avisosPromise: ['CondominioService','$stateParams', function(CondominioService, $stateParams) {
        		  return CondominioService.getAvisos($stateParams.id);
        	  }]
          }
      })


      .state('delta.editar-condominio.relatorio',{
        templateUrl:'views/delta/relatorio.html',
        url:'/relatorio',
        controller: 'CondominioController as condominioCtrl',
      })

      .state('delta.listar-condominios',{
          templateUrl:'views/delta/listar-condominio.html',
          url:'/listar-condominio',
          controller: 'CondominioController as ListarCondominioCtrl'
      })
      
      .state('delta.editar-condominio.editar-predio',{
          templateUrl:'views/delta/editar-predio.html',
          url:'/predio/:idPredio',
          controller: 'PredioController as predioCtrl'
      })

      .state('delta.nova-pessoa',{
        templateUrl:'views/delta/cadastrar-pessoa.html',
        url:'/pessoa',
        controller: 'PessoaController as pessoaCtrl'
      })

      .state('delta.editar-pessoa',{
        templateUrl:'views/delta/cadastrar-pessoa.html',
        url:'/pessoa/:id',
        controller: 'PessoaController as pessoaCtrl'
      })

      .state('delta.editar-usuario',{
        templateUrl:'views/delta/cadastrar-usuario.html',
        url:'/usuario/:id',
        controller: 'CadastroUsuarioController as usuarioCtrl'
      })

      .state('delta.cadastrar-usuario',{
        templateUrl:'views/delta/cadastrar-usuario.html',
        url:'/usuario',
        controller: 'CadastroUsuarioController as usuarioCtrl'
      })

    // Daqui pra baixo faz parte do template para acessar basta usar /#/dashboard/home
      .state('dashboard', {
        url:'/dashboard',
        templateUrl: 'views/dashboard/main.html',
        resolve: {
            loadMyDirectives:function($ocLazyLoad){
                return $ocLazyLoad.load(
                {
                    name:'sbAdminApp',
                    files:[
                    'scripts/directives/header/header.js',
                    'scripts/directives/header/header-notification/header-notification.js',
                    'scripts/directives/sidebar/sidebar.js',
                    'scripts/directives/sidebar/sidebar-search/sidebar-search.js'
                    ]
                }),
                $ocLazyLoad.load(
                {
                   name:'toggle-switch',
                   files:["bower_components/angular-toggle-switch/angular-toggle-switch.min.js",
                          "bower_components/angular-toggle-switch/angular-toggle-switch.css"
                      ]
                }),
                $ocLazyLoad.load(
                {
                  name:'ngAnimate',
                  files:['bower_components/angular-animate/angular-animate.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngCookies',
                  files:['bower_components/angular-cookies/angular-cookies.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngResource',
                  files:['bower_components/angular-animate/angular-animate.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngSanitize',
                  files:['bower_components/angular-sanitize/angular-sanitize.js']
                })
                $ocLazyLoad.load(
                {
                  name:'ngTouch',
                  files:['bower_components/angular-touch/angular-touch.js']
                })
            }
        }
    })
      .state('dashboard.home',{
        url:'/home',
        controller: 'MainCtrl',
        templateUrl:'views/dashboard/home.html',
        resolve: {
          loadMyFiles:function($ocLazyLoad) {
            return $ocLazyLoad.load({
              name:'sbAdminApp',
              files:[
              'scripts/controllers/main.js',
              'scripts/directives/timeline/timeline.js',
              'scripts/directives/notifications/notifications.js',
              'scripts/directives/chat/chat.js',
              'scripts/directives/dashboard/stats/stats.js'
              ]
            })
          }
        }
      })
      .state('dashboard.form',{
        templateUrl:'views/form.html',
        url:'/form'
    })
      .state('dashboard.blank',{
        templateUrl:'views/pages/blank.html',
        url:'/blank'
    })
      .state('login',{
        templateUrl:'views/pages/login.html',
        url:'/login'
    })
      .state('delta.chart',{
        templateUrl:'views/chart.html',
        url:'/chart',
        controller:'ChartCtrl',
        resolve: {
          loadMyFile:function($ocLazyLoad) {
            return $ocLazyLoad.load(
                'bower_components/Chart.js/Chart.min.js'
            ),
            $ocLazyLoad.load({
              name:'chart.js',
              files:[
                'bower_components/angular-chart.js/dist/angular-chart.min.js',
                'bower_components/angular-chart.js/dist/angular-chart.css'
              ]
            }),
            $ocLazyLoad.load({
                name:'sbAdminApp',
                files:['scripts/controllers/chartContoller.js']
            })
          }
        }
    })
      .state('dashboard.table',{
        templateUrl:'views/table.html',
        url:'/table'
    })
      .state('dashboard.panels-wells',{
          templateUrl:'views/ui-elements/panels-wells.html',
          url:'/panels-wells'
      })
      .state('dashboard.buttons',{
        templateUrl:'views/ui-elements/buttons.html',
        url:'/buttons'
    })
      .state('dashboard.notifications',{
        templateUrl:'views/ui-elements/notifications.html',
        url:'/notifications'
    })
      .state('dashboard.typography',{
       templateUrl:'views/ui-elements/typography.html',
       url:'/typography'
   })
      .state('dashboard.icons',{
       templateUrl:'views/ui-elements/icons.html',
       url:'/icons'
   })
      .state('dashboard.grid',{
       templateUrl:'views/ui-elements/grid.html',
       url:'/grid'
   })
  }]);

delta.run(['$rootScope', function($rootScope) {
	$rootScope.isSindicoLogado = function () {
		if(typeof sessionStorage.token === "undefined") {
			return false;
		}
		var role = JSON.parse(sessionStorage.token).usuario.role;
		return role === "SINDICO";
	};
	
	$rootScope.isAdminLogado = function () {
		if(typeof sessionStorage.token === "undefined") {
			return false;
		}
		var role = JSON.parse(sessionStorage.token).usuario.role;
		return role === "ADMIN";
	};
}]);

////Works for 1.1.x versions. 1.0.x is similar and can be figured out using code comments
delta.factory('httpRequestInterceptor',['$location', function ($location) {
  return {
	 responseError: function (response) {
		 if (response.status === 401) {
			 location.href = "app/login";
         }
         return response;
    }
  };
}]);

/**
 * Interceptador 
 */
delta.config(function ($provide, $httpProvider) {
  
  // Intercept http calls.
  $provide.factory('InterceptadorDeLogin',['$q', '$injector', function ($q, $injector) {
    return {
      // On request success
      request: function (config) {
        return config || $q.when(config);
      },

      // On request failure
      requestError: function (rejection) {
        // console.log(rejection); // Contains the data about the error on the request.
        
        // Return the promise rejection.
        return $q.reject(rejection);
      },

      // On response success
      response: function (response) {
        // console.log(response); // Contains the data from the response.
        
        // Return the response or promise.
        return response || $q.when(response);
      },

      // On response failture
      responseError: function (rejection) {
    	  if(rejection.status === 401) {
    		  $injector.get('$state').go('login');
    		  delete sessionStorage.token;
          }
        // Return the promise rejection.
        return $q.reject(rejection);
      }
    };
  }]);

  // Add the interceptor to the $httpProvider.
  $httpProvider.interceptors.push('InterceptadorDeLogin');

});