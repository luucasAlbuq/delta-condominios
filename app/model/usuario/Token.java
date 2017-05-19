package model.usuario;

import play.twirl.api.Content;

/**
 * Representa um token
 */
public class Token {

	private String bearer;
	private Usuario usuario;

	public Token() {
	}

	public String getBearer() {
		return bearer;
	}

	public void setBearer(String bearer) {
		this.bearer = bearer;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
