package controllers.rest;

import java.io.IOException;
import java.util.List;

import model.condominio.Pessoa;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.PessoaService;
import beans.PessoaBean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import exceptions.ValidacaoException;

/**
 * Camada REST de recursos para manipulacao de pessoa.
 * 
 * @author Lucas
 *
 */
public class PessoaREST extends Controller {
	
	public static PessoaService pessoaService = PessoaBean.getInstance();
	private static ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * Cadastra uma pessoa no sistema.
	 * 
	 * @param Pessoa
	 *            pessoa a ser cadastrada.
	 */
	@Transactional
	public static Result cadastraPessoa() {
		JsonNode jsonNode = request().body().asJson();
		try {
			Pessoa pessoa = mapper.readValue(jsonNode.toString(),
					Pessoa.class);
			pessoaService.cadastrarUsuario(pessoa);
			return ok(Json.toJson(pessoa));
		} catch (ValidacaoException | IOException ex) {
			return badRequest(ex.getMessage());
		}
	}
	
	/**
	 * Lista todos os pessoas cadastrados no sistema
	 * 
	 * @return List<Pessoa> pessoas
	 */
	@Transactional
	public static Result getAllPessoa(int page, int pageSize) {
		try {
			List<Pessoa> pessoas = pessoaService.consultarTodasAsPessoas(page, pageSize);
			return ok(Json.toJson(pessoas));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}
	
	/**
	 * Retorna uma pessoa específico
	 * 
	 * @param Long
	 *            id da pessoa
	 * @return {@link Pessoa}
	 */
	@Transactional
	public static Result getPessoa(Long id) {
		try {
			Pessoa pessoa = pessoaService.consultarPessoa(id);
			return ok(Json.toJson(pessoa));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}
	
	/**
	 * Atualiza uma entidade pessoa
	 * 
	 * @param Long
	 *            id
	 */
	@Transactional
	public static Result putPessoa(Long id) {
		JsonNode jsonNode = request().body().asJson();
		try {
			Pessoa pessoa = mapper.readValue(jsonNode.toString(),
					Pessoa.class);
			pessoaService.atualizaPessoa(pessoa);
			return ok(Json.toJson(pessoa));
		} catch (ValidacaoException | IOException ex) {
			return badRequest(ex.getMessage());
		}
	}
	
	/**
	 * Remove uma pessoa do sistema
	 * 
	 * @param Long id
	 * @return Result
	 */
	@Transactional
	public static Result deletePessoa(Long id){
		try {
			pessoaService.removerPessoa(id);
			return ok();
		} catch (Exception ex) {
			return badRequest(ex.getMessage());
		}
	}
}
