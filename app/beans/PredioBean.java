package beans;

import java.util.List;

import model.condominio.Condominio;
import model.condominio.Predio;
import services.PredioService;
import validadores.PredioValidador;
import dao.CondominioRepository;
import dao.PredioRepository;
import dao.Repository;

/**
 * Implementa os serviços de {@link Predio}
 * 
 * @author Lucas
 *
 */
public class PredioBean implements PredioService {

	/*
	 * TODO @autor Lucas - 04/05/2015 - Implementar camada de validacao
	 */

	private PredioRepository predioRepository = PredioRepository.getInstance();
	private CondominioRepository condominioRepository = CondominioRepository
			.getInstance();

	private static PredioBean instance;

	/**
	 * Implementacao do padrao singleton
	 * 
	 * @return Instancia de CondominioBean
	 */
	public static PredioBean getInstance() {
		if (instance == null) {
			instance = new PredioBean();
		}
		return instance;
	}

	/**
	 * Construtor default
	 */
	public PredioBean() {
	}

	@Override
	public void cadastrarPredio(Predio predio, Long idCondominio) {
		Condominio c = condominioRepository.findByEntityId(idCondominio);
		predio.setCondominio(c);
		PredioValidador.getInstance().validaCadastro(predio);
		predioRepository.persist(predio);
	}

	@Override
	public void atualizarPredio(Predio predio, Long id) {
		PredioValidador.getInstance().validaAtualizacao(predio, id);
		predioRepository.merge(predio);
	}

	@Override
	public Predio getPredio(Long idCondominio, Long idPredio) {
		return predioRepository.findByEntityId(idPredio);
	}

	@Override
	public List<Predio> getAllPrediosDoCondominio(Long idCondominio) {
		return predioRepository.findByAttributeName("condominio.id",
				idCondominio.toString());
	}

	@Override
	public List<Predio> getAllPredios(int pageNumber, int pageSize) {
		return predioRepository.findAll(pageNumber, pageSize);
	}

	@Override
	public void removerPredio(Long idCondominio, Long idPredio) {
		predioRepository.removeById(idPredio);
	}
}
