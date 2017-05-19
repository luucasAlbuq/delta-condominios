package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import model.financeiro.BalancoFinanceiro;

/**
 * Classe de manipulcao de dados, reponsavel pela persistencia dos dados no BD.
 * 
 * @author Lucas
 *
 */
public class BalancoFinanceiroRepository extends Repository<BalancoFinanceiro> {

	private static BalancoFinanceiroRepository instance;

	private BalancoFinanceiroRepository() {
	}

	public static BalancoFinanceiroRepository getInstance() {
		if (instance == null) {
			instance = new BalancoFinanceiroRepository();
		}
		return instance;
	}

	@Override
	public Class<BalancoFinanceiro> getEntityClass() {
		return BalancoFinanceiro.class;
	}

	/**
	 * Recupera o balanco financeiro entre uma faixa de tempo de um condominio
	 * 
	 * @param mesInicio
	 * @param mesFim
	 * @param idCondominio
	 * @return balancos
	 * @throws ParseException
	 */
	public List<BalancoFinanceiro> findBalancoFinanceiro(Date mesInicio,
			Date mesFim, Long idCondominio) throws ParseException {
		SimpleDateFormat dataFormatada = new SimpleDateFormat("MM/yyyy");
		Date mesInicioFormatado = dataFormatada.parse(mesInicio.toString());
		Date mesFimFormatado = dataFormatada.parse(mesFim.toString());
		String hql = "FROM BalancoFinanceiro b WHERE to_char(b.referenciaMes,'MM/YYYY') between mesInicioFormatado AND mesFimFormatado AND b.idCondominio= :idCondominio";
		Query query = getEm().createQuery(hql);
		query.setParameter("idCondominio", idCondominio);
		query.setParameter("mesInicioFormatado", mesInicioFormatado);
		query.setParameter("mesFimFormatado", mesFimFormatado);
		return query.getResultList();
	}

}
