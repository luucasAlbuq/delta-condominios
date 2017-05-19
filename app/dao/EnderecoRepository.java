package dao;

import model.condominio.Endereco;

public class EnderecoRepository extends Repository<Endereco> {

	private static EnderecoRepository instance;

	private EnderecoRepository() {
	}

	public static EnderecoRepository getInstance() {
		if (instance == null) {
			instance = new EnderecoRepository();
		}
		return instance;
	}

	@Override
	public Class<Endereco> getEntityClass() {
		return Endereco.class;
	}

}
