import java.util.Arrays;
import java.util.List;

import model.usuario.RoleLevel;
import model.usuario.Usuario;
import play.Application;
import play.GlobalSettings;
import play.cache.Cache;
import play.db.jpa.JPA;
import play.libs.F;
import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Http.Context;
import play.mvc.Result;

import com.google.common.base.Strings;

import controllers.rest.interceptors.RolesAllowed;
import dao.Repository;
import dao.UsuarioRepository;

public class Global extends GlobalSettings {
	private static final String ROLE_NOT_ALLOWED = "Role not allowed";
	private static final String ROLE = "Role";
	private Repository<Usuario> usuarioRepository = UsuarioRepository
			.getInstance();

	@Override
	public void onStart(Application arg0) {
		// CRIA OS USUARIOS
		JPA.withTransaction(new play.libs.F.Callback0() {
			@Override
			public void invoke() throws Throwable {
				List<Usuario> usuarios = usuarioRepository.findAll();
				for (Usuario user : usuarios) {
					usuarioRepository.remove(user);
				}
				Usuario usr = new Usuario("marcos", "123123");
				usr.setNome("Marcos Candeia");
				usr.setRole(RoleLevel.ADMIN);
				Usuario lucas = new Usuario("lucas", "123123");
				lucas.setNome("Luquita da Galera");
				lucas.setRole(RoleLevel.SINDICO);
				usuarioRepository.persist(usr);
				usuarioRepository.persist(lucas);
				usuarioRepository.flush();
			}
		});
	}

	// For CORS
	private class ActionWrapper extends Action.Simple {
		private static final String VIEWS = "app";
		private static final String UNAUTHORIZED = "unauthorized";
		private static final String LOGIN_URI = "/api/login";
		private static final String BEARER = "Bearer";
		private static final String AUTHORIZATION = "Authorization";

		public ActionWrapper(Action<?> action) {
			this.delegate = action;
		}

		@Override
		public Promise<Result> call(Http.Context ctx)
				throws java.lang.Throwable {
			String uri = ctx.request().uri();
			String auth = ctx.request().getHeader(AUTHORIZATION);
			if (uri.contains(VIEWS)) {
				Promise<Result> result = this.delegate.call(ctx);
				return result;
			} else if (Strings.isNullOrEmpty(auth)) {
				return F.Promise.pure((Result) unauthorized(UNAUTHORIZED));
			}
			String[] token = auth.split(" ");
			String type = token[0];
			String tokenValue = token[1];
			// Verifica se está tentando logar usando LOGIN_URI ou se o token é
			// válido
			if (LOGIN_URI.equals(uri)
					|| (BEARER.equals(type) && Cache.get(tokenValue) != null)) {
				Promise<Result> result = this.delegate.call(ctx);
				return result;
			}
			return F.Promise.pure((Result) unauthorized(UNAUTHORIZED));
		}
	}

	@Override
	public Action<?> onRequest(Http.Request request,
			java.lang.reflect.Method actionMethod) {
		RolesAllowed rolesAlloweds = actionMethod
				.getAnnotation(RolesAllowed.class);
		// Roles para os métodos
		if (rolesAlloweds != null
				&& !Arrays.asList(rolesAlloweds.value()).contains(
						request.getHeader(ROLE))) {
			return new Action.Simple() {
				@Override
				public Promise<Result> call(Context arg0) throws Throwable {
					return F.Promise
							.pure((Result) unauthorized(ROLE_NOT_ALLOWED));
				}
			};
		}
		return new ActionWrapper(super.onRequest(request, actionMethod));
	}

}