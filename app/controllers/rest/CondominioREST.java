package controllers.rest;

import java.io.IOException;
import java.util.List;

import model.condominio.Condominio;
import model.condominio.Predio;
import model.usuario.RoleLevel;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.CondominioService;
import beans.CondominioBean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.rest.interceptors.RolesAllowed;
import exceptions.ValidacaoException;

/**
 * Camada REST de recursos para manipulacao de condominios.
 * 
 * @author Lucas
 *
 */
public class CondominioREST extends Controller {

	private static CondominioService condominioService = CondominioBean
			.getInstance();
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * Cadastra um condominio no sistema.
	 * 
	 * @param Condominio
	 *            condominio a ser cadastrado.
	 */
	@Transactional
	@RolesAllowed({ RoleLevel.ADMIN })
	public static Result cadastraCondominio() {
		JsonNode jsonNode = request().body().asJson();
		try {
			Condominio condominio = mapper.readValue(jsonNode.toString(),
					Condominio.class);
			condominioService.cadastrarCondominio(condominio);
			return ok(Json.toJson(condominio));
		} catch (ValidacaoException | IOException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Lista todos os condomínios cadastrados no sistema
	 * 
	 * @return List<Condominio> condominios
	 */
	@Transactional
	public static Result getCondominios(int page, int pageSize, int sindicoId) {
		try {
			List<Condominio> condominios;
			if (sindicoId == -1) {
				condominios = condominioService
						.getAllCondominio(page, pageSize);
			} else {
				condominios = condominioService
						.consultaCondominiosSindico(sindicoId);
			}
			return ok(Json.toJson(condominios));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Retorna um condomínio específico
	 * 
	 * @param Long
	 *            id do condomínio
	 * @return {@link Condominio}
	 */
	@Transactional
	public static Result getCondominio(Long id) {
		try {
			Condominio condominio = condominioService.consultarCondominio(id);
			return ok(Json.toJson(condominio));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Atualiza uma entidade condomínio
	 * 
	 * @param Long
	 *            id
	 */
	@Transactional
	public static Result putCondiminio(Long id) {
		JsonNode jsonNode = request().body().asJson();
		try {
			Condominio condominio = mapper.readValue(jsonNode.toString(),
					Condominio.class);
			condominioService.atualizarCondominio(condominio, id);
			return ok(Json.toJson(condominio));
		} catch (ValidacaoException | IOException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Remove um condominio do sistema
	 * 
	 * @param Long
	 *            id
	 * @return Result
	 */
	@Transactional
	public static Result deleteCondominio(Long id) {
		try {
			condominioService.removerCondominio(id);
			return ok();
		} catch (Exception ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Retorna os predios vinculados ao condiminio.
	 * 
	 * @param Long
	 *            id do condomínio
	 * @return List<Predio> predios
	 */
	@Transactional
	public static Result getPredios(Long id) {
		try {
			List<Predio> predios = condominioService.consultarPredios(id);
			return ok(Json.toJson(predios));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}
}
