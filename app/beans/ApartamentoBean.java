package beans;

import java.util.List;

import model.condominio.Apartamento;
import model.condominio.Predio;
import services.ApartamentoService;
import validadores.ApartamentoValidador;
import dao.ApartamentoRepository;
import dao.PredioRepository;
import dao.Repository;

/**
 * Implementa os serviços de {@link Apartamento}
 * 
 * @author Julio
 *
 */
public class ApartamentoBean implements ApartamentoService {

	private Repository<Apartamento> apartamentoRepository = ApartamentoRepository
			.getInstance();
	private Repository<Predio> predioRepository = PredioRepository
			.getInstance();
	private ApartamentoValidador apartamentoValidador = ApartamentoValidador
			.getInstance();

	private static ApartamentoBean instance;

	/**
	 * Implementacao do padrao singleton
	 * 
	 * @return Instancia de ApartamentoBean
	 */
	public static ApartamentoBean getInstance() {
		if (instance == null) {
			instance = new ApartamentoBean();
		}
		return instance;
	}

	/**
	 * Construtor default
	 */
	public ApartamentoBean() {
	}

	@Override
	public void cadastrarApartamento(Apartamento apartamento, Long idPredio) {
		Predio predio = predioRepository.findByEntityId(idPredio);
		apartamento.setPredio(predio);
		apartamentoValidador.validaCadastro(apartamento);
		apartamentoRepository.persist(apartamento);
	}

	@Override
	public void atualizarApartamento(Apartamento apartamento, Long id) {
		apartamentoValidador.validaAtualizacao(apartamento);
		apartamentoRepository.merge(apartamento);
	}

	@Override
	public Apartamento consultarApartamento(Long idPredio, Long idApartamento) {
		return apartamentoRepository.findByEntityId(idApartamento);
	}

	@Override
	public List<Apartamento> consultarTodosOsApartamentos(int pageNumber, int pageSize) {
		return apartamentoRepository.findAll(pageNumber, pageSize);
	}

	@Override
	public List<Apartamento> consultarTodosOsApartamentosDoPredio(Long idPredio) {
		return apartamentoRepository.findByAttributeName("predio.id",
				idPredio.toString());
	}

	@Override
	public void removerApartamento(Long idApartamento, Long id) {
		apartamentoRepository.removeById(idApartamento);
	}
}
