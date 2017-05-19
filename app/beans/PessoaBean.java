package beans;

import java.util.List;

import model.condominio.Pessoa;
import services.PessoaService;
import dao.PessoaRepository;

/**
 * 
 * @author Lucas
 *
 */
public class PessoaBean implements PessoaService {

	private PessoaRepository pessoaRepository = PessoaRepository.getInstance();

	// TODO falta implementar a camada de validação

	private static PessoaBean instance;

	/**
	 * Implementacao do padrao singleton
	 * 
	 * @return Instancia de PessoaBean
	 */
	public static PessoaBean getInstance() {
		if (instance == null) {
			instance = new PessoaBean();
		}
		return instance;
	}

	/**
	 * Construtor default
	 */
	public PessoaBean() {
	}

	@Override
	public void cadastrarUsuario(Pessoa pessoa) {
		pessoaRepository.persist(pessoa);
	}

	@Override
	public void atualizaPessoa(Pessoa pessoa) {
		pessoaRepository.merge(pessoa);
	}

	@Override
	public void removerPessoa(Long id) {
		pessoaRepository.removeById(id);
		;
	}

	@Override
	public Pessoa consultarPessoa(Long id) {
		return pessoaRepository.findByEntityId(id);
	}

	@Override
	public List<Pessoa> consultarTodasAsPessoas(int pageNumber, int pageSize) {
		return pessoaRepository.findAll(pageNumber, pageSize);
	}
}
