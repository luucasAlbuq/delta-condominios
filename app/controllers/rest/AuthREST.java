package controllers.rest;

import java.util.UUID;

import javax.persistence.NoResultException;

import model.usuario.Token;
import model.usuario.Usuario;

import org.apache.commons.codec.binary.Base64;

import play.cache.Cache;
import play.db.jpa.Transactional;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import dao.UsuarioRepository;

/**
 * Serviços REST de Login
 */
public class AuthREST extends Controller {
	private static final String SEPARATOR = ":";
	private static final String BASIC = "Basic";

	private static UsuarioRepository usuarioRepository = UsuarioRepository
			.getInstance();

	@Transactional
	public static Result login() {
		String auth = request().getHeader(AUTHORIZATION);
		String[] token = auth.split(" ");
		String type = token[0];
		String tokenValue = token[1];
		if (BASIC.equals(type)) {
			String[] usrNPass = new String(Base64.decodeBase64(tokenValue))
					.split(SEPARATOR);
			String user = usrNPass[0];
			String pass = usrNPass[1];
			try {
				Usuario usuario = usuarioRepository.findUser(user, pass);
				String newToken = UUID.randomUUID().toString();
				Token tokenRest = new Token();
				tokenRest.setBearer(newToken);
				tokenRest.setUsuario(usuario);
				Cache.set(newToken, tokenRest);
				return ok(Json.toJson(tokenRest));
			} catch (NoResultException ex) {
				return unauthorized();
			}
		}
		return unauthorized();
	}
}
