package dao;

import javax.persistence.Query;

import model.usuario.Usuario;

public class UsuarioRepository extends Repository<Usuario> {

	private static UsuarioRepository instance;

	private UsuarioRepository() {
	}

	public static UsuarioRepository getInstance() {
		if (instance == null) {
			instance = new UsuarioRepository();
		}
		return instance;
	}

	public Usuario findUser(String user, String password) {
		String hql = "FROM Usuario u where u.login = :user AND u.senha = :password";
		Query query = getEm().createQuery(hql);
		query.setParameter("user", user);
		query.setParameter("password", password);
		return (Usuario) query.getSingleResult();
	}

	@Override
	public Class<Usuario> getEntityClass() {
		return Usuario.class;
	}
}
