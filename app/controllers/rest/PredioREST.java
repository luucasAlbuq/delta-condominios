package controllers.rest;

import java.io.IOException;
import java.util.List;

import model.condominio.Predio;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.CondominioService;
import services.PredioService;
import beans.CondominioBean;
import beans.PredioBean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import exceptions.ValidacaoException;

/**
 * Camada REST de recursos para manipulacao de {@link Predio}.
 * 
 * @author Lucas
 *
 */
public class PredioREST extends Controller {

	private static ObjectMapper mapper = new ObjectMapper();
	private static PredioService predioService = PredioBean.getInstance();
	private static CondominioService condominioService = CondominioBean
			.getInstance();

	/**
	 * Cadastra um predio no sistema.
	 * 
	 * @param Predio
	 *            predio a ser cadastrado.
	 * 
	 * @param idCondominio
	 */
	@Transactional
	public static Result cadastraPredio(Long idCondominio) {
		JsonNode jsonNode = request().body().asJson();
		try {
			Predio predio = mapper.readValue(jsonNode.toString(), Predio.class);
			predioService.cadastrarPredio(predio, idCondominio);
			return ok(Json.toJson(predio));
		} catch (ValidacaoException | IOException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Lista todos os {@link Predio} cadastrados no sistema
	 * 
	 * @return predios
	 */
	@Transactional
	public static Result getPredios(int page, int pageSize) {
		try {
			List<Predio> predios = predioService.getAllPredios(page, pageSize);
			return ok(Json.toJson(predios));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Lista todos os {@link Predio} de um condomìnio
	 * 
	 * @return predios
	 */
	@Transactional
	public static Result getPrediosDoCondominio(Long idCondominio) {
		try {
			List<Predio> predios = predioService
					.getAllPrediosDoCondominio(idCondominio);
			return ok(Json.toJson(predios));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Retorna um {@link Predio} específico
	 * 
	 * @param idCondominio
	 * @param id
	 *            do predio
	 * 
	 * @return {@link Predio}
	 */
	@Transactional
	public static Result getPredio(Long idCondominio, Long idPredio) {
		try {
			Predio predio = predioService.getPredio(idCondominio, idPredio);
			return ok(Json.toJson(predio));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Atualiza uma entidade {@link Predio}
	 * 
	 * @param idCondominio
	 * @param idPredio
	 */
	@Transactional
	public static Result putPredio(Long idCondominio, Long idPredio) {
		JsonNode jsonNode = request().body().asJson();
		try {
			Predio predio = mapper.readValue(jsonNode.toString(), Predio.class);
			predio.setCondominio(condominioService.consultarCondominio(idCondominio));
			predioService.atualizarPredio(predio, idPredio);
			return ok(Json.toJson(predio));
		} catch (ValidacaoException | IOException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Remove um {@link Predio} do sistema
	 * 
	 * @param idCondominio
	 * @param idPredio
	 * @return Result
	 */
	@Transactional
	public static Result deletePredio(Long idCondominio, Long id) {
		try {
			predioService.removerPredio(idCondominio, id);
			return ok();
		} catch (Exception ex) {
			return badRequest(ex.getMessage());
		}
	}

}
