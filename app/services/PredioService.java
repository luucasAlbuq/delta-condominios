package services;

import java.util.List;

import model.condominio.Predio;

/**
 * Interface de serviços de um predio
 * 
 * @author Lucas
 *
 */
public interface PredioService {
	
	/**
	 * Persiste um predio.
	 * 
	 * @param predio
	 * @param idCondominio
	 */
	public void cadastrarPredio(Predio predio, Long idCondominio);
	
	/**
	 * Atualiza um predio no BD.
	 * 
	 * @param  {@link Predio} predio
	 * @param  id
	 */
	public void atualizarPredio(Predio predio, Long id);
	
	/**
	 * Recupera um predio do BD.
	 * 
	 * @param id do predio
	 * @param id do condominio
	 * 
	 * @return {@link Predio}
	 */
	public Predio getPredio(Long idCondominio, Long idPredio);
	
	/**
	 * Recupera todos os predios do sistema.
	 * 
	 * @param numero de paginas
	 * @param itens por pagina
	 * @return List<Predio> predios
	 */
	public List<Predio> getAllPredios(int pageNumber, int pageSize);
	
	/**
	 * Remove um predio do sistema.
	 * 
	 * @param Long idPredio
	 * @param Long idCondiminio
	 */
	public void removerPredio(Long idCondominio, Long id);

	/**
	 * Recupera todos os predios de um condominio com o {code idCondominio}
	 * 
	 * @return List<Predio> predios
	 */
	List<Predio> getAllPrediosDoCondominio(Long idCondominio);
}
