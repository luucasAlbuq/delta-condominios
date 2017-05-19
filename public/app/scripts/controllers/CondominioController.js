
// Módulo da Página inicial do delta-condominios
var condominio = angular.module("condominio");

condominio.controller("CondominioController", ['ModalMensagemService', '$sce', 'FileUploader','$state','$stateParams','$http','$resource','CondominioService', 'ContaService', 
	function CondominioController(ModalMensagemService, $sce, FileUploader, $state, $stateParams, $http, $resource, condominioService, contaService, contasPromise) {
	var self = this;
	var dataInicioBalanco;
	var dataFimBalanco;

	var contasFiltradas = [];

	// Promise de contas no resolve do state
	if(contasPromise) {
		contasPromise.then(function(info) {
			self.contas.push.apply(info.data);
		})
	}
	/**
	 * Retorna uma URL confiável para acessar o arquivo de acordo com seu {@code data} (array de bytes)
	 */
	var getTrustedResourceURL = function(data) {
		var file = new Blob([data], {type: 'application/pdf'});
		var fileURL = URL.createObjectURL(file);
		return $sce.trustAsResourceUrl(fileURL);
	}

	this.getSomaBalanco = function () {
		return _.reduce(self.contasFiltradas, function (memo, conta) {
			return memo + conta.valor;
		}, 0);
	}
	
	var constroiGrafico = function () {
		/**
		 * Constrói o gráfico
		 */
		self.tipos = []; 
		self.data = [];
		self.mapaContas = {};
		_.sortBy(self.contas, function(o) { return o.dataPagamento; })
		self.onClick = function() {};
		self.meses = [];
		_.each(self.contas, function(conta) {
			if(conta.pago) {
				var dateM = new Date(conta.dataPagamento);
				var mesString = getMonthString(dateM);
				if(!_.contains(self.meses, mesString)) {
					self.meses.push(mesString);
				}
			}
		});
		
		/**
		 * Seta os tipos das contas e os meses
		 */
		_.each(self.contas, function(conta) {
			var indexOfContaValor = _.indexOf(self.meses, getMonthString( new Date(conta.dataPagamento)));
			if(self.mapaContas[conta.tipoConta]) {
				self.mapaContas[conta.tipoConta][indexOfContaValor] = conta.valor;
			} else {
				self.mapaContas[conta.tipoConta] = [];
				_.each(self.meses, function(k,i) {
					self.mapaContas[conta.tipoConta].push(0);
				});
				self.mapaContas[conta.tipoConta][indexOfContaValor] = conta.valor;
				self.tipos.push(conta.tipoConta);
			}
		})
		
		/**
		 * Seta os dados das contas
		 */
		_.each(self.tipos, function(k){
			self.data.push(self.mapaContas[k]);
		});
	}
	
	/**
	 * Retorna o mês em formato de String
	 */
	var getMonthString = function(date) {
		var locale = "pt-br";
		var month = date.toLocaleString(locale, { month: "long" });
		return month;
	}
	
	var TIPOS_PERMITIDOS_DOCUMENTO = ["application/pdf"];
	/* Uploader */
	this.uploader = new FileUploader(
		{headers: { Authorization: 'Bearer ' + JSON.parse(sessionStorage.token).bearer }, withCredentials: true}
	);
	/**
     * Adiciona um filtro de acordo com os tipos permitidos de upload
     */
    this.uploader.filters.push({
            name:'tipoDeArquivoFilter',
            fn: function(item) {
                    return _.contains(TIPOS_PERMITIDOS_DOCUMENTO, item.type);
            }
    });
    
    self.redicionarRelatorio = function () {
    	$state.go('delta.editar-condominio.relatorio');
    }

    /**
     * Mensagens de falhas de adição de arquivos
     */
    this.uploader.onWhenAddingFileFailed = function(item /*{File|FileLikeObject}*/, filter, options) {
        if (filter.name === 'tipoDeArquivoFilter') {
        	ModalMensagemService.erro("Apenas arquivos do tipo PDF podem ser adicionados");
        }
    };

    this.hasContas = function () {
    	if(self.contas.length === 0 || typeof self.contas === 'undefined'){
    		return false;
    	}else{
    		return true;
    	}
    }

    self.imprimir = function(){
    	window.print();
    }

   /**
	* Gera o banaço de contas de um condomínio
	**/
	self.contasFiltradas = [];

	function apagaArray(array) {
		while(array.length > 0) {
			array.pop();
		}
	}

    self.gerarBalanco = function(){
    	if(typeof self.contas === 'undefined' || self.contas.length === 0){
    		return;
    	}
    	apagaArray(self.contasFiltradas);
    	self.contasFiltradas.push.apply(self.contasFiltradas,_.filter(self.contas, function(conta) {
    		var date = new Date(conta.dataVencimento).getTime();
    		return date >= self.dataInicioBalanco.getTime() && date <= self.dataFimBalanco.getTime();
    	}));
    	self.somaBalanco = self.getSomaBalanco();
    };

	this.conta = {};
	this.contas = [];
	this.condominioId = $stateParams.id;
	if(this.condominioId) {
		condominioService.getCondominio(this.condominioId).then(function(success) {
			self.condominio = success.data;
			self.predio = success.data;
			contaService.getTodasContasDoCondominio(self.condominioId).then(function(sucesso) {
				self.contas.length = 0;
				self.contas.push.apply(self.contas, sucesso.data);
				constroiGrafico();
			})
		});
	}else {
		this.condominio = {};
		this.condominio.predios = [];
	}
	this.condominios = [];

	var user = JSON.parse(sessionStorage.token).usuario;
	if(user.role === 'SINDICO') {
		condominioService.getCondominios(user.id).then(function(sucess) {
			self.condominios.push.apply(self.condominios, sucess.data);
		});
	}else {
		condominioService.getCondominios().then(function(sucess) {
			self.condominios.push.apply(self.condominios, sucess.data);
		});
	}

	this.templateUrl = "views/delta/endereco.html";
	this.templateApartamentoUrl = "views/delta/apartamento.html"
	this.templateContaUrl = "views/delta/contas-pagas.html"
	this.templateBalancoUrl = "views/delta/balanco.html"
	
	/*
	* Salva condomínio no banco de dados.
	* Mostra um uma mensagem com mensagem de sucesso ou falha.
	*/
	this.salvarCondominio = function () {
		ModalMensagemService.confirmacao("criação de condomínio").then(function(resultado) {
			if (resultado) {
				condominioService.salvarCondominio(self.condominio).then(function(data, headers) {
					ModalMensagemService.sucesso("Condominio criado com sucesso");
					$state.go('delta.listar-condominios');
				}, function(data) {
					ModalMensagemService.erro("Falha ao criar condominio");
				});
			}
		})
	}

	/**
	 * Mostra o prédio com o {@code idPredio}	
	 */
	this.mostrarPredio = function (idPredio) {
		$state.go('delta.editar-condominio.editar-predio', {idPredio :idPredio});
	}
	
	/*
	* Remove condomínio no banco de dados.
	* Mostra um alert com mensagem de sucesso ou falha.
	*/
	this.removerCondominio = function() {
		condominioService.removerCondominio(this.condominio).then(function(data, headers) {
			ModalMensagemService.sucesso("Condominio removido com sucesso");
			$state.go('delta.listar-condominios');	
		}, function(data) {
		});	
	}

	/*
	* Editar condomínio no banco de dados.
	* Mostra um alert com mensagem de sucesso ou falha.
	*/
	this.editarCondominio = function () {
		condominioService.editarCondominio(this.condominio).then(function(data, headers) {
			ModalMensagemService.sucesso("Condominio foi alterado com sucesso!");
			$state.go('delta.listar-condominios');
		}, function(data) {
			ModalMensagemService.erro("Falha ao editar condominio");
		});
	}

	/*
	* Mostra dados de um determinado condomínio de acordo com sua id.
	*/
	this.mostrarCondominio = function(condominio, predio) {
		$state.go('delta.editar-condominio', {id: condominio.id});
	}

	/*
	* Verifica se a tela contém utilização de id, se sim, habilita botões da tela de edição, se não, 
	* habilita botão para tela de cadastro de condomínio.
	*/
	this.podeVisualizarBotaoEdicao = function(){
		return _.isUndefined($stateParams.id);
	}
	
	/**
	 * Mostra o modal com o {@code predio} selecionado
	 */
	this.mostrarModalPredio = function(predio) {
		var clonePredio = predio ||_.clone(_.last(this.condominio.predios)) || {};
		this.predioSelecionado = clonePredio;
		delete this.predioSelecionado.id;
		delete this.predioSelecionado.apartamentos;
		if(_.isUndefined(predio)) {
			this.condominio.predios.push(this.predioSelecionado);
		}
	}
	
	/**
	 * Abre o modal de conta fazendo as alterações necessárias
	 */
	this.abreContaModal = function(index) {
		self.conta = self.contas[index];
		// clear array
		while(self.uploader.queue.length > 0) {
			self.uploader.queue.pop();
		}
		// Atualiza o documento
		contaService.getDocumento(this.condominioId, self.conta.id).then(function(info) {
			self.documentoContaSelecionada = getTrustedResourceURL(info.data);
		})
	}
	/**
	 * Salva {@code qtd} a quantidade de apartamentos selecionada no prédio
	 */
	this.salvaApartamentos = function(qtd) {
		this.predioSelecionado.apartamentos = [];
		for(var i = 0; i < parseInt(qtd); i ++) {
			this.predioSelecionado.apartamentos.push(this.apartamentoSelecionado);
		}
	}
	/*
	* Remove elemento de uma lista a partir de um indice passado por parâmetro.
	*/
	this.removeElemento = function(lista, indice) {
		lista.splice(indice, 1);
	}

	/**
	 * Salva a conta
	 */
	this.salvarConta = function() {
		var uploadPDFConta = function () {
			// FIXME Ao mudar de modal o arquivo deve ser setado para undefined
			var PRIMEIRO_ARQUIVO = self.uploader.queue[0];
			if(!_.isUndefined(PRIMEIRO_ARQUIVO)) {
				 self.uploader.onCompleteItem = function(item, response, status, headers) {
                     if (status === 201) {
                    	 ModalMensagemService.sucesso("Upload feito com sucesso !");
                     } else {
                    	 ModalMensagemService.erro("Erro ao realizar o upload da Conta.");
                     }
             };
             PRIMEIRO_ARQUIVO.url = "/api/condominio/" + $stateParams.id + "/conta/" + self.conta.id + "/documento" 
             PRIMEIRO_ARQUIVO.upload();
			}
		}
		if(this.conta.id) {
			contaService.atualizarConta($stateParams.id, this.conta.id, this.conta).then(function(success) {
				ModalMensagemService.sucesso("conta atualizada com sucesso");
				uploadPDFConta();
				constroiGrafico();
			}, function(error) {
				ModalMensagemService.erro("erro ao atualizar conta")
			})
		}else {
			contaService.salvarConta($stateParams.id, this.conta).then(function(success) {
				self.conta = success.data;
				self.contas.push(self.conta);
				uploadPDFConta();
				constroiGrafico();
				ModalMensagemService.sucesso("Conta criada com sucesso ");
			},
			function(error) {
				ModalMensagemService.erro("erro ao criar conta " + error.data);
			});
		}
	}
	/*
	* Salva pessoa no banco de dados.
	* Mostra um alert com mensagem de sucesso ou falha.
	*/
	this.salvarPessoa = function () {
		condominioService.salvarCondominio(this.condominio).then(function(data, headers) {
			ModalMensagemService.sucesso("Pessoa cadastrada com sucesso");
			$state.go('delta.listar-condominios');
		}, function(data) {
			ModalMensagemService.erro("Falha ao cadastrar Pessoa");	
		});
	}	

	this.open = function($event) {
	    $event.preventDefault();
    	$event.stopPropagation();
  	};
  	
  	this.dateOptions = {
    	formatYear: 'yy',
    	startingDay: 1
  	};

  	// Disable weekend selection
  	this.disabled = function(date, mode) {
    	return ( mode === 'day' && ( date.getDay() === 0 || date.getDay() === 6 ) );
  };
  this.dateFormat = "dd/MM/yyyy";
}]);