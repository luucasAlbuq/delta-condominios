// Módulo da Página inicial do delta-condominios
var condominio = angular.module("condominio"); 

condominio.controller("AvisosController", ['ModalMensagemService', '$stateParams', 'CondominioService', function AvisosController(ModalMensagemService, $stateParams, CondominioService, avisosPromise) {
	var self = this;
	self.aviso = {};
	self.avisos = [];
	var adicionaAvisos = function(info) {
		self.avisos.push.apply(self.avisos, info.data);
	};
	
	if(avisosPromise) {
		avisosPromise.then(adicionaAvisos);
	} else{
		CondominioService.getAvisos($stateParams.id).then(adicionaAvisos);
	}
	
	/**
	 * Salva um aviso
	 */
	this.salvarAviso = function () {
		self.aviso.dataPostagem = new Date();
		self.aviso.nomeAutor = JSON.parse(sessionStorage.token).usuario.nome;
		CondominioService.salvarAviso($stateParams.id, self.aviso).then(function(aviso) {
			self.avisos.push(aviso.data);
			ModalMensagemService.sucesso("Aviso salvo com sucesso")
		}, function(err) {
			ModalMensagemService.erro("Falha ao salvar aviso")
		});
	}
}]);
