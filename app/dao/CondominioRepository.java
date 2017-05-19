package dao;

import java.util.List;

import model.condominio.Condominio;

public class CondominioRepository extends Repository<Condominio> {
	private static CondominioRepository instance;

	private CondominioRepository() {
	}

	public static CondominioRepository getInstance() {
		if (instance == null) {
			instance = new CondominioRepository();
		}
		return instance;
	}

	@Override
	public Class<Condominio> getEntityClass() {
		return Condominio.class;
	}

	/**
	 * Retorna todos os condominios que tem relação com o usuário com
	 * {@code userId}
	 */
	public List<Condominio> findAllByUserId(int userId) {
		String hql = "SELECT s.condominio FROM Usuario s WHERE s.id = "
				+ userId;
		return getEm().createQuery(hql).getResultList();
	}
}
