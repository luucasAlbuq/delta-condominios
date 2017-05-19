package beans;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import model.financeiro.Conta;
import services.ContaService;
import validadores.ContaValidador;
import dao.ContaRepository;

/**
 * Implementa os serviços de {@link Conta}
 * 
 * @author Lucas
 *
 */
public class ContaBean implements ContaService {

	private ContaRepository contaRepository = ContaRepository.getInstance();

	private static ContaBean instance;
	private ContaValidador contaValidador = ContaValidador.getInstance();

	/**
	 * Implementacao do padrao singleton
	 * 
	 * @return Instancia de ContaBean
	 */
	public static ContaBean getInstance() {
		if (instance == null) {
			instance = new ContaBean();
		}
		return instance;
	}

	/**
	 * Construtor default
	 */
	public ContaBean() {
	}

	@Override
	public void cadastrarConta(Conta conta, Long idCondominio) {
		contaValidador.validaCadastro(conta);
		contaRepository.persist(conta);
	}

	@Override
	public void atualizarConta(Conta conta, Long id) {
		contaValidador.validaAtualizacao(conta, id);
		contaRepository.merge(conta);
	}

	@Override
	public Conta consultarConta(Long idCondominio, Long idConta) {
		return contaRepository.findByEntityId(idConta);
	}

	@Override
	public void removerConta(Long idConta) {
		contaRepository.removeById(idConta);
	}

	@Override
	public List<Conta> consultarTodasAsContasDoCondominio(Date mesInicio,
			Date mesFim, Long idCondominio) {
		List<Conta> contas = null;
		try {
			contas = contaRepository.findContasDoMes(mesInicio, mesFim,
					idCondominio);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return contas;
	}

}
