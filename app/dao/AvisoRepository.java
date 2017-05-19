package dao;

import java.util.List;

import model.condominio.Aviso;

/**
 * Provê os serviços de bd para um aviso
 * 
 * @author marcos
 */
public class AvisoRepository extends Repository<Aviso> {

	private static AvisoRepository instance;

	private AvisoRepository() {
	}

	public static AvisoRepository getInstance() {
		if (instance == null) {
			instance = new AvisoRepository();
		}
		return instance;
	}

	@Override
	public Class<Aviso> getEntityClass() {
		return Aviso.class;
	}

	/**
	 * Retorna a lista de avisos de um condomínio
	 */
	public List<Aviso> getAvisosDoCondominio(Long idCondominio) {
		String hql = "FROM Aviso WHERE id = " + idCondominio;
		return getEm().createQuery(hql).getResultList();
	}
}
