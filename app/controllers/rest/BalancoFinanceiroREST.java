package controllers.rest;

import java.util.Date;
import java.util.List;

import model.financeiro.BalancoFinanceiro;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.BalancoFinanceiroService;
import beans.BalancoFinanceiroBean;

import com.fasterxml.jackson.databind.ObjectMapper;

import exceptions.ValidacaoException;

/**
 * Camada REST de recursos para manipulacao de {@link BalancoFinanceiro}
 * 
 * @author Lucas
 *
 */
public class BalancoFinanceiroREST extends Controller {

	private static BalancoFinanceiroService balancoService = BalancoFinanceiroBean
			.getInstance();
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * Lista todos os balanços de um condominio
	 * 
	 * @param idCondominio
	 * @param mes
	 * @return balancos financeiros
	 */
	@Transactional
	public static Result getBalancoCondominio(Long idCondominio, Date mes) {
		try {
			List<BalancoFinanceiro> balancos = balancoService.consultarBalanco(
					idCondominio, mes);
			return ok(Json.toJson(balancos));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Lista todos os balanços de um condominio
	 * 
	 * @param idCondominio
	 * @param mes
	 * @return balancos financeiros
	 */
	@Transactional
	public static Result getBalancoCondominioBetween(Long idCondominio,
			Date mesInicio, Date mesFim) {
		try {
			List<BalancoFinanceiro> balancos = balancoService.consultarBalanco(
					mesInicio, mesFim, idCondominio);
			return ok(Json.toJson(balancos));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Lista todos o histórico balanços de um condominio
	 * 
	 * @param idCondominio
	 * @param mes
	 * @return balancos financeiros
	 */
	@Transactional
	public static Result getHistoricoBalancoCondominio(Long idCondominio) {
		try {
			List<BalancoFinanceiro> balancos = balancoService
					.consultarHistoricoBalanco(idCondominio);
			return ok(Json.toJson(balancos));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}
}
