package beans;

import java.util.List;

import model.usuario.Usuario;
import services.UsuarioService;
import dao.UsuarioRepository;

/**
 * 
 * @author Julio
 *
 */
public class UsuarioBean implements UsuarioService {

	private UsuarioRepository usuarioRepository = UsuarioRepository.getInstance();

	// TODO falta implementar a camada de validação

	private static UsuarioBean instace;

	/**
	 * Implementacao do padrao singleton
	 * 
	 * @return Instancia de UsuarioBean
	 */
	public static UsuarioBean getInstance() {
		if (instace == null) {
			instace = new UsuarioBean();
		}
		return instace;
	}

	/**
	 * Construtor default
	 */
	public UsuarioBean() {
	}

	@Override
	public void cadastrarUsuario(Usuario usuario) {
		usuarioRepository.persist(usuario);
	}

	@Override
	public void atualizaUsuario(Usuario usuario) {
		usuarioRepository.merge(usuario);
	}

	@Override
	public void removerUsuario(Long id) {
		usuarioRepository.removeById(id);
	}

	@Override
	public Usuario consultaUsuario(Long id) {
		return usuarioRepository.findByEntityId(id);
	}

	@Override
	public List<Usuario> consultaTodosUsuarios(int pageNumber, int pageSize) {
		return usuarioRepository.findAll(pageNumber, pageSize);
	}
}
