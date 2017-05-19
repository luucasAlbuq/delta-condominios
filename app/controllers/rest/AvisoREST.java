package controllers.rest;

import java.io.IOException;
import java.util.List;

import model.condominio.Apartamento;
import model.condominio.Aviso;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.AvisoService;
import beans.AvisoBean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import exceptions.ValidacaoException;

/**
 * Prove os serviços de avisos de um condomínio
 * 
 * @author marcos
 *
 */
public class AvisoREST extends Controller {
	private static AvisoService avisoService = AvisoBean.getInstance();
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * Cadastra um aviso vinculado a um condomínio no sistema.
	 * 
	 * @param idCondominio
	 * @return se o cadastro foi realizado com sucesso.
	 */
	@Transactional
	public static Result cadastrarAviso(Long idCondominio) {
		JsonNode jsonNode = request().body().asJson();
		try {
			Aviso aviso = mapper.readValue(jsonNode.toString(), Aviso.class);
			avisoService.cadastrarAviso(aviso, idCondominio);
			return created(Json.toJson(aviso));
		} catch (ValidacaoException | IOException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Lista todos os apartamentos cadastrados no sistema
	 * 
	 * @return apartamentos
	 */
	@Transactional
	public static Result getAvisos(Long idCondominio) {
		try {
			List<Aviso> avisos = avisoService
					.buscarAvisosDoCondominio(idCondominio);
			return ok(Json.toJson(avisos));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}
}
