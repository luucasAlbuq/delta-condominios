package services;

import java.util.List;

import model.condominio.Aviso;

/**
 * Interface de serviços de Aviso
 * 
 * @uthor Marcos V. Candeia
 */
public interface AvisoService {

	/**
	 * Cadastra um aviso no condomínio
	 * 
	 * @param aviso
	 *            o aviso a ser cadastrado
	 * @param idCondominio
	 *            o id do condominio no qual se quer cadastrar o aviso
	 */
	void cadastrarAviso(Aviso aviso, Long idCondominio);

	/**
	 * Retorna a lista de avisos de um condomínio com o {@code idCondominio}
	 * 
	 * @param idCondominio
	 *            o id do condomínio
	 * @return a lista de avisos
	 */
	List<Aviso> buscarAvisosDoCondominio(Long idCondominio);
}
