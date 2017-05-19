package controllers.rest;

import java.io.IOException;
import java.util.List;

import model.usuario.RoleLevel;
import model.usuario.Usuario;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import services.UsuarioService;
import beans.UsuarioBean;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import controllers.rest.interceptors.RolesAllowed;
import exceptions.ValidacaoException;

/**
 * Camada REST de recursos para manipulacao de usuario.
 * 
 * @author Julio
 *
 */
public class UsuarioREST extends Controller {

	public static UsuarioService usuarioService = UsuarioBean.getInstance();
	private static ObjectMapper mapper = new ObjectMapper();

	/**
	 * Cadastra um usuario no sistema.
	 * 
	 * @param usuario
	 *            usuario a ser cadastrada.
	 */
	@Transactional
	public static Result cadastraUsuario() {
		JsonNode jsonNode = request().body().asJson();
		try {
			Usuario usuario = mapper.readValue(jsonNode.toString(),
					Usuario.class);
			usuarioService.cadastrarUsuario(usuario);
			return ok(Json.toJson(usuario));
		} catch (ValidacaoException | IOException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Lista todos os usuarios cadastrados no sistema
	 * 
	 * @return List<Usuario> usuarios
	 */
	@Transactional
	public static Result consultaTodosUsuarios(int page, int pageSize) {
		try {
			List<Usuario> usuarios = usuarioService.consultaTodosUsuarios(page,
					pageSize);
			return ok(Json.toJson(usuarios));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Retorna um usuario específico
	 * 
	 * @param Long
	 *            id do usuario
	 * @return {@link Usuario}
	 */
	@Transactional
	public static Result consultaUsuario(Long id) {
		try {
			Usuario usuario = usuarioService.consultaUsuario(id);
			return ok(Json.toJson(usuario));
		} catch (ValidacaoException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Atualiza uma entidade usuario
	 * 
	 * @param Long
	 *            id
	 */
	@RolesAllowed({ RoleLevel.ADMIN })
	@Transactional
	public static Result atualizaUsuario(Long id) {
		JsonNode jsonNode = request().body().asJson();
		try {
			Usuario usuario = mapper.readValue(jsonNode.toString(),
					Usuario.class);
			usuarioService.atualizaUsuario(usuario);
			return ok(Json.toJson(usuario));
		} catch (ValidacaoException | IOException ex) {
			return badRequest(ex.getMessage());
		}
	}

	/**
	 * Remove um usuario do sistema
	 * 
	 * @param Long
	 *            id
	 * @return Result
	 */
	@Transactional
	public static Result deleteUsuario(Long id) {
		try {
			usuarioService.removerUsuario(id);
			return ok();
		} catch (Exception ex) {
			return badRequest(ex.getMessage());
		}
	}
}
