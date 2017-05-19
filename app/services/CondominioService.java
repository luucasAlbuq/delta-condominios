package services;

import java.util.List;

import model.condominio.Condominio;
import model.condominio.Predio;

/**
 * Interface de serviços de um condominio
 * 
 * @author Lucas
 *
 */
public interface CondominioService {

	/**
	 * Persiste um condominio
	 * 
	 * @param condominio
	 */
	public void cadastrarCondominio(Condominio condominio);

	/**
	 * Atualiza um condomínio no BD.
	 * 
	 * @param {@link Condominio} condominio
	 * @param id
	 */
	public void atualizarCondominio(Condominio condominio, Long id);

	/**
	 * Recupera um condominio do BD.
	 * 
	 * @param id
	 *            do condominio
	 * @return {@link Condominio}
	 */
	public Condominio consultarCondominio(Long id);

	/**
	 * Recupera todos os condominios do sistema.
	 * 
	 * @param numero
	 *            de paginas
	 * @param itens
	 *            por pagina
	 * @return List<Condominio> condominios
	 */
	public List<Condominio> getAllCondominio(int pageNumber, int pageSize);

	/**
	 * Recupera todos os predios de um condominio.
	 * 
	 * @param Long
	 *            idCondominio
	 * @return List<Predio> predios
	 */
	public List<Predio> consultarPredios(Long idCondominio);

	/**
	 * Remove um condominio do BD
	 * 
	 * @param Long
	 *            idCondominio
	 */
	public void removerCondominio(Long id);

	/**
	 * Consulta todos os condominios que o sindico com o {@code sindicoId} é
	 * responsável
	 */
	public List<Condominio> consultaCondominiosSindico(int sindicoId);
}
