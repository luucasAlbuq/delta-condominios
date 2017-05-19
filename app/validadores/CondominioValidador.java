package validadores;

import model.condominio.Condominio;
import model.condominio.Endereco;
import exceptions.ValidacaoException;

/**
 * Classe de validação de {@link Condominio}
 * 
 * @author Lucas
 *
 */
public class CondominioValidador {
	
	private final String MSG_ERRO_NULL = "Ocorreu um erro de validacao, objeto ou campo null: ";
	private final String MSG_ERRO_INVALIDO = "Ocorreu um erro de validacao, objeto ou campo invalidos: ";
	
	private static CondominioValidador instance;
	
	/**
	 * Implementacao do padrao singleton
	 * 
	 * @return CondominioValidador
	 */
	public static CondominioValidador getInstance(){
		if(instance == null){
			instance = new CondominioValidador();
		}
		return instance;
	}
	
	/**
	 * Verifica se um condomínio é valido.
	 * 
	 * @param condominio
	 */
	public void validaCondominio(Condominio condominio){
		if(condominio == null){
			throw new ValidacaoException(MSG_ERRO_NULL+"Condominio");
		}if(condominio.getTipo() == null){
			throw new ValidacaoException(MSG_ERRO_NULL+"Tipo condominio");
		}if(condominio.getNome() == null ){
			throw new ValidacaoException(MSG_ERRO_NULL+"Nome do condominio");
		}if(condominio.getNome().trim().isEmpty()){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Nome do condominio");
		}if(condominio.getArea() == null){
			throw new ValidacaoException(MSG_ERRO_NULL+"Area do condominio");
		}if(condominio.getArea() <= 0){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Area do condominio");
		}
		validaEndereco(condominio.getEndereco());
	}
	
	/**
	 * Verifica se um endereço é valido.
	 * 
	 * @param Endereco
	 */
	public void validaEndereco(Endereco endereco){
		if(endereco == null){
			throw new ValidacaoException(MSG_ERRO_NULL+"Endereco");
		}if(endereco.getCep() == null){
			throw new ValidacaoException(MSG_ERRO_NULL+"Cep de Endereco");
		}if(endereco.getCep().trim().isEmpty()){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Cep de Endereco");
		}if(endereco.getNomeRua() == null){
			throw new ValidacaoException(MSG_ERRO_NULL+"Rua de Endereco");
		}if(endereco.getNomeRua().trim().isEmpty()){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Rua de Endereco");
		}
	}
	
	/**
	 * Valida um cadastro de condomínio
	 * 
	 * @param condominio
	 */
	public void validaCadastro(Condominio condominio){
		validaCondominio(condominio);
		if(condominio.getId() != null){
			throw new ValidacaoException(MSG_ERRO_INVALIDO + "Condominio ja foi cadastrado anteriormente");
		}
	}
	
	/**
	 * Valida a edição de um condomínio
	 * 
	 * @param condominio
	 * @param idPah
	 */
	public void validaAtualizacao(Condominio condominio, Long idPath){
		validaCondominio(condominio);
		if(condominio.getId() == null){
			throw new ValidacaoException(MSG_ERRO_NULL+"id condominio");
		}if(idPath == null){
			throw new ValidacaoException(MSG_ERRO_NULL+"idPath");
		}if(condominio.getId() != idPath){
			throw new ValidacaoException(MSG_ERRO_INVALIDO
					+ "Id do objeto e do parametro sao diferentes.");
		}
	}
}
