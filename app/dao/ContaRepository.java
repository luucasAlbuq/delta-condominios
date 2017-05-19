package dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import model.financeiro.Conta;

/**
 * Classe de manipulcao de dados, reponsavel pela persistencia dos dados no BD.
 * 
 * @author Lucas
 *
 */
public class ContaRepository extends Repository<Conta> {

	private static ContaRepository instance;

	private ContaRepository() {
	}

	public static ContaRepository getInstance() {
		if (instance == null) {
			instance = new ContaRepository();
		}
		return instance;
	}

	/**
	 * Retorna as contas de um mes do condominio
	 * 
	 * @param mes
	 * @param idCondominio
	 * @return List<Conta>
	 * @throws ParseException
	 */
	public List<Conta> findContasDoMes(Date mesInicio, Date mesFim,
			Long idCondominio) throws ParseException {
		SimpleDateFormat dataFormatada = new SimpleDateFormat("MM/yyyy");
		Query query = null;
		String hql = "FROM Conta c";
		if (mesInicio != null && mesFim != null) {
			Date mesInicioFormatado = dataFormatada.parse(mesInicio.toString());
			Date mesFimFormatado = dataFormatada.parse(mesFim.toString());
			hql += " WHERE c.dataVencimento between :mesInicioFormatado AND :mesFimFormatado AND c.condominio.id= :idCondominio";
			query = getEm().createQuery(hql);
			query.setParameter("mesInicioFormatado", mesInicioFormatado);
			query.setParameter("mesFimFormatado", mesFimFormatado);
		} else if (mesInicio != null && mesFim == null) {
			Date mesInicioFormatado = dataFormatada.parse(mesInicio.toString());
			hql += " WHERE c.dataVencimento = :mesInicioFormatado AND c.condominio.id = :idCondominio";
			query = getEm().createQuery(hql);
			query.setParameter("mesInicioFormatado", mesInicioFormatado);
		} else {
			hql += " WHERE c.condominio.id = :idCondominio";
			query = getEm().createQuery(hql);
		}
		query.setParameter("idCondominio", idCondominio);
		return query.getResultList();
	}

	@Override
	public Class<Conta> getEntityClass() {
		return Conta.class;
	}

}
