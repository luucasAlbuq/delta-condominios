package validadores;

import exceptions.ValidacaoException;
import model.condominio.Apartamento;

/**
 * Classe de validação de {@link Apartamento}
 * 
 * @author Lucas
 *
 */
public class ApartamentoValidador {
	
	private final String MSG_ERRO_NULL = "Ocorreu um erro de validacao, objeto ou campo null: ";
	private final String MSG_ERRO_INVALIDO = "Ocorreu um erro de validacao, objeto ou campo invalidos: ";
	
	private static ApartamentoValidador instance;
	
	/**
	 * Implementacao do padrao singleton
	 * 
	 * @return {@link ApartamentoValidador}
	 */
	public static ApartamentoValidador getInstance(){
		if(instance == null){
			instance = new ApartamentoValidador();
		}
		return instance;
	}
	
	/**
	 * Verifica se um apartamento é valido.
	 * 
	 * @param apartamento
	 */
	public void validaApartamento(Apartamento apartamento){
		if(apartamento == null){
			throw new ValidacaoException(MSG_ERRO_NULL+"Apartamento");
		}if(apartamento.getIdentificacaoApartamento() == null){
			throw new ValidacaoException(MSG_ERRO_NULL+"Identificação de Apartamento");
		}if(apartamento.getIdentificacaoApartamento().isEmpty()){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Identificação de Apartamento");
		}if(apartamento.getAndarApartamento() < 0){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Andar do Apartamento");
		}if(apartamento.getArea() < 0){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Área do Apartamento");
		}if(apartamento.getQtQuartos() < 0){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Quantidade de quartos do Apartamento");
		}if(apartamento.getQtBanheiros() < 0){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Quantidade de banheiros do Apartamento");
		}if(apartamento.getQtVarandas() < 0){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Quantidade de varandas do Apartamento");
		}if(apartamento.getPredio() == null){
			throw new ValidacaoException(MSG_ERRO_NULL+"Apartamento com predio null");
		}if(apartamento.getResponsavel() == null){
			throw new ValidacaoException(MSG_ERRO_NULL+"Responsavel de Apartamento");
		}
			
	}
	
	/**
	 * Valida o reponsavel por um apartamento
	 * @param apartamento
	 */
	public void validaResponsavel(Apartamento apartamento){
		if(apartamento.getResponsavel() != null){
			PessoaValidador.getInstance().validaPessoa(apartamento.getResponsavel());
		}
	}
	
	/**
	 * Valida o cadastro de um apartamento
	 * @param apartamento
	 */
	public void validaCadastro(Apartamento apartamento){
		validaApartamento(apartamento);
		validaResponsavel(apartamento);
		if(apartamento.getId() != null){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Apartamento ja foi persistido anteriormente");
		}
	}
	
	/**
	 * Valida a edicao de um apartamento.
	 * 
	 * @param apartamento
	 */
	public void validaAtualizacao(Apartamento apartamento){
		validaApartamento(apartamento);
		if(apartamento.getId() == null){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Não é possivel editar um apartamento não persistido.");
		}
	}
	
}
