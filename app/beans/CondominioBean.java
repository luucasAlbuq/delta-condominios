package beans;

import java.util.List;

import model.condominio.Condominio;
import model.condominio.Predio;
import services.CondominioService;
import validadores.CondominioValidador;
import validadores.PredioValidador;
import dao.CondominioRepository;
import dao.PredioRepository;
import dao.Repository;

/**
 * Implementa os serviços de condominio
 * 
 * @author Lucas
 *
 */
public class CondominioBean implements CondominioService {

	private CondominioRepository condominioRepository = CondominioRepository
			.getInstance();
	private PredioRepository predioRepository = PredioRepository.getInstance();
	private CondominioValidador condominioValidador = CondominioValidador
			.getInstance();
	private PredioValidador predioValidador = PredioValidador.getInstance();
	private static CondominioBean instance;

	/**
	 * Implementacao do padrao singleton
	 * 
	 * @return Instancia de CondominioBean
	 */
	public static CondominioBean getInstance() {
		if (instance == null) {
			instance = new CondominioBean();
		}
		return instance;
	}

	/**
	 * Construtor default
	 */
	public CondominioBean() {
	}

	@Override
	public void cadastrarCondominio(Condominio condominio) {
		condominioValidador.validaCadastro(condominio);
		predioValidador.validaPredios(condominio);
		condominioRepository.persist(condominio);
	}

	@Override
	public void atualizarCondominio(Condominio condominio, Long idPath) {
		condominioValidador.validaAtualizacao(condominio, idPath);
		condominioRepository.merge(condominio);
	}

	@Override
	public Condominio consultarCondominio(Long id) {
		return condominioRepository.findByEntityId(id);
	}

	@Override
	public List<Condominio> getAllCondominio(int pageNumber, int pageSize) {
		return condominioRepository.findAll(pageNumber, pageSize);
	}

	@Override
	public List<Predio> consultarPredios(Long idCondominio) {
		return predioRepository.findByAttributeName("condominio.id",
				idCondominio.toString());
	}

	@Override
	public void removerCondominio(Long id) {
		condominioRepository.removeById(id);
	}

	@Override
	public List<Condominio> consultaCondominiosSindico(int sindicoId) {
		return condominioRepository.findAllByUserId(sindicoId);
	}

}
