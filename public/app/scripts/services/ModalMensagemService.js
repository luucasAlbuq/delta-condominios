var modalMensagem = angular.module("modalMensagem", []);

/**
 * Service de mensagens do sistema
 */
modalMensagem.service("ModalMensagemService", ['ModalService', function ModalMensagemService(ModalService) {
	var self = this;
	
	/**
	 * Mostra uma mensagem de sucesso
	 */
	this.sucesso = function(mensagem) {
	    return ModalService.showModal({
	      templateUrl: "views/delta/modalSucesso.html",
	      controller: "MensagemController",
	      inputs : {
	    	  mensagem: mensagem 
	      }
	    }).then(function(modal) {
		      modal.element.modal();
		      return modal.close;
		});
	}
	
	/**
	 * Mostra a mensagem de confirmação
	 */
	this.confirmacao = function(mensagem) {
	   return ModalService.showModal({
	      templateUrl: "views/delta/modalConfirmacao.html",
	      controller: "MensagemController",
	      inputs : {
	    	  mensagem: mensagem || ""
	      }
	    }).then(function(modal) {
	      modal.element.modal();
	      return modal.close;
	    });
	  };
	  
	/**
	 * Mostra uma mensagem de sucesso
	 */
	this.erro = function(mensagem) {
		return ModalService.showModal({
	      templateUrl: "views/delta/modalErro.html",
	      controller: "MensagemController",
	      inputs : {
	    	  mensagem: mensagem
	      }
	    }).then(function(modal) {
		      modal.element.modal();
		      return modal.close;
		});;
	}
}]);

/**
 * Controla o modal de mensagens de sucesso
 * @param mensagem a mensagem de sucesso a ser exibidos
 */
modalMensagem.controller("MensagemController", function MensagemSucessoController($scope, mensagem, close) {
	// fecha o modal
	$scope.dismiss = function() {
		close();
	}
	
	$scope.close = function(result) {
	 	  close(result, 500); // close, but give 500ms for bootstrap to animate
	 };
	$scope.mensagem = mensagem;
});
