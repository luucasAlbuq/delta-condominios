package validadores;

import model.condominio.Condominio;
import model.condominio.Predio;
import exceptions.ValidacaoException;

/**
 * Classe de validação de {@link Predio}
 * 
 * @author Bruno
 */
public class PredioValidador {

	private final String MSG_ERRO_NULL = "Ocorreu um erro de validacao, objeto ou campo null: ";
	private final String MSG_ERRO_INVALIDO = "Ocorreu um erro de validacao, objeto ou campo invalidos: ";

	private static PredioValidador instance;

	/**
	 * Implementacao do padrao singleton
	 * 
	 * @return PredioValidador
	 */
	public static PredioValidador getInstance() {
		if (instance == null) {
			instance = new PredioValidador();
		}
		return instance;
	}

	/**
	 * Verifica se um predio é valido.
	 * 
	 * @param predio
	 */
	public void validaPredio(Predio predio) {
		if (predio == null) {
			throw new ValidacaoException(MSG_ERRO_NULL + "Predio.");
		}
		if (predio.getNome() == null) {
			throw new ValidacaoException(MSG_ERRO_NULL + "Nome do predio.");
		}
		if (predio.getNome().trim().isEmpty()) {
			throw new ValidacaoException(MSG_ERRO_INVALIDO + "Nome do predio.");
		}
		if (predio.getQtdAndares() <= 0) {
			throw new ValidacaoException(MSG_ERRO_INVALIDO
					+ "Quantidade de Andares.");
		}
		if (predio.getCondominio() == null) {
			throw new ValidacaoException(MSG_ERRO_NULL
					+ "Condomínio do predio.");
		}
	}

	/**
	 * Verifica se um condominio tem predios validos.
	 * 
	 * @param listaPredios
	 */
	public void validaPredios(Condominio condominio) {
		if (!condominio.getPredios().isEmpty()) {
			for (Predio predio : condominio.getPredios()) {
				validaPredio(predio);
			}
		}
	}

	/**
	 * Valida um cadastro de predio
	 * 
	 * @param predio
	 */
	public void validaCadastro(Predio predio) {
		validaPredio(predio);
		if (predio.getId() != null) {
			throw new ValidacaoException(MSG_ERRO_INVALIDO
					+ "predio já cadastrado.");
		}
	}

	/**
	 * Valida a edição de um predio
	 * 
	 * @param predio
	 * @param idPah
	 */
	public void validaAtualizacao(Predio predio, Long idPath) {
		validaPredio(predio);
		if (predio.getId() == null) {
			throw new ValidacaoException(MSG_ERRO_NULL + "id do predio.");
		}
		if (idPath == null) {
			throw new ValidacaoException(MSG_ERRO_NULL + "idPath.");
		}
		if (predio.getId() != idPath) {
			throw new ValidacaoException(MSG_ERRO_INVALIDO
					+ "Id do predio e do parametro sao diferentes.");
		}
	}
}
