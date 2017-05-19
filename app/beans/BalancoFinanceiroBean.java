package beans;

import java.util.Date;
import java.util.List;

import model.financeiro.BalancoFinanceiro;
import services.BalancoFinanceiroService;
import services.ContaService;
import dao.BalancoFinanceiroRepository;

/**
 * Implementa os servicos de BalancoFinanceiro
 * 
 * @author Lucas
 *
 */
public class BalancoFinanceiroBean implements BalancoFinanceiroService {

	private static BalancoFinanceiroBean instance;
	private ContaService contaService = ContaBean.getInstance();
	private BalancoFinanceiroRepository balancoRepository = BalancoFinanceiroRepository
			.getInstance();

	/**
	 * Implementacao do padrao singleton
	 * 
	 * @return Instancia de BalancoFinanceiroBean
	 */
	public static BalancoFinanceiroBean getInstance() {
		if (instance == null) {
			instance = new BalancoFinanceiroBean();
		}
		return instance;
	}

	/**
	 * Construtor default
	 */
	public BalancoFinanceiroBean() {
	};

	@Override
	public BalancoFinanceiro gerarBalanco(Long idCondominio) {
		// List<Conta> contas = contaService.consultarContasPorMes(idCondominio,
		// new Date());
		// BalancoFinanceiro balanco = new BalancoFinanceiro(new Date(), contas,
		// idCondominio);
		// balancoRepository.persist(balanco);
		// return balanco;
		return null;
	}

	@Override
	public List<BalancoFinanceiro> consultarBalanco(Date mesInicio,
			Date mesFim, Long idCondominio) {
		// List<BalancoFinanceiro> balancos = null;
		// try {
		// balancos = balancoRepository.findBalancoFinanceiro(mesInicio, mesFim,
		// idCondominio);
		// } catch (ParseException e) {
		// e.printStackTrace();
		// }
		// return balancos;
		return null;
	}

	@Override
	public List<BalancoFinanceiro> consultarBalanco(Long idCondominio, Date mes) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BalancoFinanceiro> consultarHistoricoBalanco(Long idCondominio) {
		// return
		// balancoRepository.findByAttributeName("balancoFinanceiro.idCondominio",
		// idCondominio.toString());
		return null;
	}

}
