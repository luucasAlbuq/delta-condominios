package model.condominio;

/**
 * Enumeracao dos tipos de condominios.
 * 
 * @author Lucas
 *
 */
public enum TipoCondominio {
	HORIZONTAL	("Horizontal"),
	VERTICAL	("Vertical");
	
	private String nome;
	
	private TipoCondominio(String nome){
		this.nome = nome;
	}
	
	@Override
	public String toString(){
		return nome;
	}
}
