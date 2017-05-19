package controllers.rest;

import java.io.IOException;
import java.util.List;

import model.condominio.Apartamento;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.ApartamentoService;
import beans.ApartamentoBean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import exceptions.ValidacaoException;

/**
 * Camada REST de recursos para manipulacao de apartamentos.
 * 
 * @author Lucas
 *
 */
public class ApartamentoREST extends Controller {

	private static ApartamentoService apartamentoService = ApartamentoBean
			.getInstance();
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * Cadastra um apartamento vinculado a um prédio no sistema.
	 * 
	 * @param idPredio
	 * @return se o cadastro foi realizado com sucesso.
	 */
	@Transactional
	public static Result cadastraApartamento(Long idCondominio, Long idPredio) {
		JsonNode jsonNode = request().body().asJson();
		try {
			Apartamento apartamento = mapper.readValue(jsonNode.toString(),
					Apartamento.class);
			apartamentoService.cadastrarApartamento(apartamento, idPredio);
			return created(Json.toJson(apartamento));
		} catch (ValidacaoException | IOException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Atualiza um apartamento.
	 * 
	 * @param id
	 * @return se a atualizacao foi feita com sucesso
	 */
	@Transactional
	public static Result putApartamento(Long idCondominio, Long idPredio,
			Long id) {
		JsonNode jsonNode = request().body().asJson();
		try {
			Apartamento apartamento = mapper.readValue(jsonNode.toString(),
					Apartamento.class);
			apartamentoService.atualizarApartamento(apartamento, id);
			return ok(Json.toJson(apartamento));
		} catch (ValidacaoException | IOException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Retorna um apartamento específico
	 * 
	 * @param Long
	 *            id do apartamento
	 * @return {@link Apartamento}
	 */
	@Transactional
	public static Result getApartamento(Long idCondominio, Long idPredio,
			Long idPartamento) {
		try {
			Apartamento apartamento = apartamentoService.consultarApartamento(
					idPredio, idPartamento);
			return ok(Json.toJson(apartamento));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Lista todos os apartamentos cadastrados no sistema
	 * 
	 * @return apartamentos
	 */
	@Transactional
	public static Result getApartamentos(int page, int pageSize) {
		try {
			List<Apartamento> apartamentos = apartamentoService
					.consultarTodosOsApartamentos(page, pageSize);
			return ok(Json.toJson(apartamentos));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Lista todos os apartamentos de um predio
	 * 
	 * @param idPredio
	 * @return apartamentos do predio
	 */
	@Transactional
	public static Result getApartamentoPredio(Long idCondominio, Long idPredio) {
		try {
			List<Apartamento> apartamentos = apartamentoService
					.consultarTodosOsApartamentosDoPredio(idPredio);
			return ok(Json.toJson(apartamentos));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}

}
