package dao;

import model.condominio.Apartamento;

public class ApartamentoRepository extends Repository<Apartamento> {

	private static ApartamentoRepository instance;

	private ApartamentoRepository() {}

	public static ApartamentoRepository getInstance() {
		if (instance == null) {
			instance = new ApartamentoRepository();
		}
		return instance;
	}

	@Override
	public Class<Apartamento> getEntityClass() {
		return Apartamento.class;
	}
}
