package services;

import java.util.Date;
import java.util.List;

import model.condominio.Apartamento;
import model.financeiro.Conta;

/**
 * Interface de servicos de Conta
 * 
 * @author Lucas
 *
 */
public interface ContaService {

	/**
	 * Persiste uma conta.
	 * 
	 * @param conta
	 * @param idCondominio
	 */
	public void cadastrarConta(Conta conta, Long idCondominio);

	/**
	 * Atualiza uma conta no BD.
	 * 
	 * @param {@link conta} conta
	 * @param id
	 */
	public void atualizarConta(Conta conta, Long id);

	/**
	 * Recupera uma conta do BD.
	 * 
	 * @param id
	 *            do predio
	 * @param id
	 *            do apartamento
	 * 
	 * @return {@link Apartamento}
	 */
	public Conta consultarConta(Long idCondominio, Long idConta);

	/**
	 * Recupera todos as contas de um condominio.
	 * 
	 * @param mesInicio
	 * @param mesFim
	 * @return List<Conta> contas
	 */
	public List<Conta> consultarTodasAsContasDoCondominio(Date mesInicio,
			Date mesFim, Long idCondominio);

	/**
	 * Remove uma cona do sistema.
	 * 
	 * @param Long
	 *            idConta
	 */
	public void removerConta(Long idConta);

}
