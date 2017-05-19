package exceptions;

/**
 * Exception de validação de objetos. 
 * 
 * @author Lucas
 *
 */
public class ValidacaoException extends RuntimeException{
	
	private static final long serialVersionUID = -1540498810960159547L;

	public ValidacaoException(String mensagemErro){
		super(mensagemErro);
	}
	
	public ValidacaoException(){
		super("Ocorreu um erro de validação");
	}
	
}
