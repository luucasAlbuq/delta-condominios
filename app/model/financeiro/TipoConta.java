package model.financeiro;

public enum TipoConta {
	ENERGIA	("Energia"),
	FUNCIONARIOS ("Funcionarios"),
	REPAROS ("Reparos"),
	OUTROS  ("Outros"),
	GAS		("GAS"),
	AGUA	("Água");
	
	private String nome;
	
	private TipoConta(String nome){
		this.nome = nome;
	}
	
	@Override
	public String toString(){
		return nome;
	}
}
