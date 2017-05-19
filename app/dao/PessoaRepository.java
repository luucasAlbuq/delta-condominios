package dao;

import model.condominio.Pessoa;

public class PessoaRepository extends Repository<Pessoa> {
	private static PessoaRepository instance;

	private PessoaRepository() {
	}

	public static PessoaRepository getInstance() {
		if (instance == null) {
			instance = new PessoaRepository();
		}
		return instance;
	}

	@Override
	public Class<Pessoa> getEntityClass() {
		return Pessoa.class;
	}
}
