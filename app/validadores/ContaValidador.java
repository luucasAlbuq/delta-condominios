package validadores;

import model.financeiro.Conta;
import exceptions.ValidacaoException;

/**
 * Classe de validacao de {@link Conta}
 * 
 * @author Lucas
 *
 */
public class ContaValidador {
	private final String MSG_ERRO_NULL = "Ocorreu um erro de validacao, objeto ou campo null: ";
	private final String MSG_ERRO_INVALIDO = "Ocorreu um erro de validacao, objeto ou campo invalidos: ";

	private static ContaValidador instance;

	/**
	 * Implementacao do padrao singleton
	 * 
	 * @return ContaValidador
	 */
	public static ContaValidador getInstance() {
		if (instance == null) {
			instance = new ContaValidador();
		}
		return instance;
	}

	/**
	 * Verifica se uma conta e valida
	 * 
	 * @param conta
	 */
	public void validaConta(Conta conta) {
		if (conta == null) {
			throw new ValidacaoException(MSG_ERRO_NULL + "Conta.");
		}
		if (conta.getCodigo() == null) {
			throw new ValidacaoException(MSG_ERRO_NULL + "Codigo de conta.");
		}
		if (conta.getValor() == null) {
			throw new ValidacaoException(MSG_ERRO_NULL + "Valor da conta");
		}
		if (conta.getDataVencimento() == null) {
			throw new ValidacaoException(MSG_ERRO_NULL
					+ "data de vencimento da conta.");
		}
		if (conta.getCodigo().isEmpty()) {
			throw new ValidacaoException(MSG_ERRO_INVALIDO + "Codigo da conta");
		}
		if (conta.getValor().intValue() < 0) {
			throw new ValidacaoException(MSG_ERRO_INVALIDO + "Valor da conta");
		}
		if (conta.getTipoConta() == null) {
			throw new ValidacaoException(MSG_ERRO_NULL + "tipo da conta.");
		}
	}

	/**
	 * Valida um cadastro de uma conta
	 * 
	 * @param conta
	 */
	public void validaCadastro(Conta conta) {
		validaConta(conta);
	}

	/**
	 * Valida a edicao de uma conta.
	 * 
	 * @param conta
	 * @param idConta
	 */
	public void validaAtualizacao(Conta conta, Long idConta) {
		validaConta(conta);
		if (conta.getId() == null) {
			throw new ValidacaoException(MSG_ERRO_INVALIDO
					+ "Nao é possivel editar uma conta nao persistida.");
		}
		if (conta.getId() != idConta) {
			throw new ValidacaoException(MSG_ERRO_INVALIDO
					+ "O id da conta eh diferente do id informado.");
		}
	}
}
