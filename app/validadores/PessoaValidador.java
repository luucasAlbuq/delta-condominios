package validadores;

import exceptions.ValidacaoException;
import model.condominio.Condominio;
import model.condominio.Pessoa;

/**
 * Classe de validação de {@link Condominio}
 * 
 * @author Bruno
 *
 */
public class PessoaValidador {
	private final String MSG_ERRO_NULL = "Ocorreu um erro de validacao, objeto ou campo null: ";
	private final String MSG_ERRO_INVALIDO = "Ocorreu um erro de validacao, objeto ou campo invalidos: ";
	/**
	 * Regex com o padrão de Strings usadas em um email
	 */
	private static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	private static PessoaValidador instance;
	
	/**
	 * Implementacao do padrao singleton
	 * 
	 * @return PredioValidador
	 */
	public static PessoaValidador getInstance(){
		if(instance == null){
			instance = new PessoaValidador();
		}
		return instance;
	}
	
	/**
	 * Verifica se uma pessoa é valida.
	 * 
	 * @param pessoa
	 */
	public void validaPessoa(Pessoa pessoa){
		if(pessoa == null){
			throw new ValidacaoException(MSG_ERRO_NULL+"Pessoa.");
		}if(pessoa.getNome() == null){
			throw new ValidacaoException(MSG_ERRO_NULL+"Nome de pessoa.");
		}if(pessoa.getNome().trim().isEmpty()){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Nome de pessoa.");
		}if(pessoa.getCpf() == null){
			throw new ValidacaoException(MSG_ERRO_NULL+" CPF de pessoa.");
		}if(pessoa.getCpf().trim().isEmpty()){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"CPF de pessoa.");
		}if(!pessoa.getCpf().trim().matches("[0-9]{11}")){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"CPF de pessoa.");
		}if(pessoa.getEmail() == null){
			throw new ValidacaoException(MSG_ERRO_NULL+" Email de pessoa.");
		}if(pessoa.getEmail().trim().isEmpty()){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Email de pessoa.");
		}if(!pessoa.getEmail().matches(EMAIL_PATTERN)){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Email de pessoa.");
		}if(pessoa.getFone() == null){
			throw new ValidacaoException(MSG_ERRO_NULL+" Fone de pessoa.");
		}if(!pessoa.getFone().trim().matches("[0-9]")){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"CPF de pessoa.");
		}if(pessoa.getFone().trim().isEmpty()){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Fone de pessoa.");
		}
	}
	
	/**
	 * Valida o cadastro de uma pessoa
	 * @param pessoa
	 */
	public void validaCadastro(Pessoa pessoa){
		validaPessoa(pessoa);
		if(pessoa.getId() != null){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Pessoa ja foi persistida anteriormente");
		}
	}
	
	/**
	 * Valida a edicao de uma pessoa.
	 * 
	 * @param pessoa
	 */
	public void validaAtualizacao(Pessoa pessoa){
		validaPessoa(pessoa);
		if(pessoa.getId() == null){
			throw new ValidacaoException(MSG_ERRO_INVALIDO+"Não é possivel editar uma pessoa não persistida.");
		}
	}
	
}
