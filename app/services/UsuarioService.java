package services;

import java.util.List;

import model.usuario.Usuario;

/**
 * Interface de serviços de um usuario
 * 
 * @author Julio
 *
 */
public interface UsuarioService {
	
	/**
	 * Persiste um usuario.
	 * 
	 * @param usuario
	 */
	public void cadastrarUsuario(Usuario usuario);
	
	/**
	 * Atuliza um usuario cadastrada no sistema
	 * 
	 * @param usuario
	 */
	public void atualizaUsuario(Usuario usuario);
	
	/**
	 * Remove um usuario do sistema
	 * 
	 * @param id
	 */
	public void removerUsuario(Long id);
	
	/**
	 * Retorna um usuario cadastrada no sistema
	 * 
	 * @param id
	 * @return usuario
	 */
	public Usuario consultaUsuario(Long id);
	
	/**
	 * Retorna todas os usuarios cadastradas no sistema.
	 * 
	 * @param pageNumber
	 * @param pageSize
	 * @return usuarios cadastrados no sistema
	 */
	public List<Usuario> consultaTodosUsuarios(int pageNumber, int pageSize);
	
}
