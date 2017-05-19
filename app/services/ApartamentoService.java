package services;

import java.util.List;

import model.condominio.Apartamento;

/**
 * Interface de serviços de um apartamento
 * 
 * @author Julio
 *
 */
public interface ApartamentoService {
	
	/**
	 * Persiste um apartamento.
	 * 
	 * @param apartamento
	 * @param idPredio
	 */
	public void cadastrarApartamento(Apartamento apartamento, Long idPredio);
	
	/**
	 * Atualiza um apartamento no BD.
	 * 
	 * @param  {@link Apartamento} apartamento
	 * @param  id
	 */
	public void atualizarApartamento(Apartamento apartamento, Long id);
	
	/**
	 * Recupera um apartamento do BD.
	 * 
	 * @param id do predio
	 * @param id do apartamento
	 * 
	 * @return {@link Apartamento}
	 */
	public Apartamento consultarApartamento(Long idPredio, Long idApartamento);
	
	/**
	 * Recupera todos os apartamentos do sistema.
	 * 
	 * @param numero de paginas
	 * @param itens por pagina
	 * @return List<Apartamento> apartamentos
	 */
	public List<Apartamento> consultarTodosOsApartamentos(int pageNumber, int pageSize);
	
	/**
	 * Remove um apartamento do sistema.
	 * 
	 * @param Long idPredio
	 * @param Long idCondiminio
	 */
	public void removerApartamento(Long idApartamento, Long id);

	/**
	 * Recupera todos os apartamentos de um predio com o {code idPredio}
	 * 
	 * @return List<Apartamento> apartamentos
	 */
	public List<Apartamento> consultarTodosOsApartamentosDoPredio(Long idPredio);
	
}
