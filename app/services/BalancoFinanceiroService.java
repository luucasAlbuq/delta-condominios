package services;

import java.util.Date;
import java.util.List;

import model.financeiro.BalancoFinanceiro;

/**
 * Interface de servicos de BalancoFinanceiro
 * 
 * @author Lucas
 *
 */
public interface BalancoFinanceiroService {

	/**
	 * Gera o balanco do mes corrente
	 * 
	 * @param Long
	 *            idCondominio
	 */
	public BalancoFinanceiro gerarBalanco(Long idCondominio);

	/**
	 * Consultar o balanco financeiro entre os meses informados.
	 * 
	 * @param mesIncicio
	 * @param mesFim
	 * @return List<BalancoFinanceiro>
	 */
	public List<BalancoFinanceiro> consultarBalanco(Date mesInicio,
			Date mesFim, Long idCondominio);

	/**
	 * Consultar o balanco financeiro do meses informados.
	 * 
	 * @param mesIncicio
	 * @param mesFim
	 * @return List<BalancoFinanceiro>
	 */
	public List<BalancoFinanceiro> consultarBalanco(Long idCondominio, Date mes);

	/**
	 * Consultar o historico de balancos financeiro de um condominio
	 * 
	 * @param idCondominio
	 * @return List<BalancoFinanceiro>
	 */
	public List<BalancoFinanceiro> consultarHistoricoBalanco(Long idCondominio);
}
