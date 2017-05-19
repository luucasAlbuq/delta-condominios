package dao;

import model.condominio.Predio;

public class PredioRepository extends Repository<Predio> {
	private static PredioRepository instance;

	private PredioRepository() {
	}

	public static PredioRepository getInstance() {
		if (instance == null) {
			instance = new PredioRepository();
		}
		return instance;
	}

	@Override
	public Class<Predio> getEntityClass() {
		return Predio.class;
	}
}
