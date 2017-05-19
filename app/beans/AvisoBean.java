package beans;

import java.util.List;

import model.condominio.Apartamento;
import model.condominio.Aviso;
import model.condominio.Condominio;
import services.AvisoService;
import dao.AvisoRepository;
import dao.CondominioRepository;
import dao.Repository;

/**
 * Implementa os serviços de {@link Apartamento}
 * 
 * @author Julio
 *
 */
public class AvisoBean implements AvisoService {

	private Repository<Condominio> condominioRepository = CondominioRepository
			.getInstance();
	private AvisoRepository avisoRepository = AvisoRepository.getInstance();

	private static AvisoBean instance;

	/**
	 * Implementacao do padrao singleton
	 * 
	 * @return Instancia de AvisoBean
	 */
	public static AvisoBean getInstance() {
		if (instance == null) {
			instance = new AvisoBean();
		}
		return instance;
	}

	/**
	 * Construtor default
	 */
	public AvisoBean() {
	}

	@Override
	public void cadastrarAviso(Aviso aviso, Long idCondominio) {
		Condominio condominio = condominioRepository
				.findByEntityId(idCondominio);
		condominio.getQuadroDeAvisos().add(aviso);
		condominioRepository.merge(condominio);
	}

	@Override
	public List<Aviso> buscarAvisosDoCondominio(Long idCondominio) {
		return avisoRepository.getAvisosDoCondominio(idCondominio);
	}
}
