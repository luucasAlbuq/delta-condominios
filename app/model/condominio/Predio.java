package model.condominio;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.base.Objects;

/**
 * Classe Modelo de um prédio.
 * 
 * @author Lucas
 *
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Predio {

	@Id
	@Column(name = "id_predio")
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "condominio_id")
	@JsonBackReference
	private Condominio condominio;

	private String nome;
	private String nomeBloco;
	private int qtdResidencias;
	private int qtdAndares;
	
	@OneToMany(mappedBy = "predio", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Apartamento> apartamentos;
	
	public Predio() {}

	/**
	 * Construtor basico de predio.
	 * 
	 * @param nome
	 * @param idCondominio
	 */
	public Predio(String nome) {
		this.nome = nome;
	}

	/**
	 * Construtor Predio
	 * 
	 * @param nome
	 * @param qtdResidencias
	 */
	public Predio(String nome, int qtdResidencias) {
		this.qtdResidencias = qtdResidencias;
	}

	/**
	 * Construtor de prédio
	 * 
	 * @param nome
	 * @param nomeBloco
	 * @param qtdResidencias
	 * @param qtdAndares
	 */
	public Predio(String nome, String nomeBloco, int qtdResidencias,
			int qtdAndares) {

		this.nome = nome;
		this.nomeBloco = nomeBloco;
		this.qtdAndares = qtdAndares;
		this.qtdResidencias = qtdResidencias;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeBloco() {
		return nomeBloco;
	}

	public void setNomeBloco(String nomeBloco) {
		this.nomeBloco = nomeBloco;
	}

	public int getQtdResidencias() {
		return qtdResidencias;
	}

	public void setQtdResidencias(int qtdResidencias) {
		this.qtdResidencias = qtdResidencias;
	}

	public int getQtdAndares() {
		return qtdAndares;
	}

	public void setQtdAndares(int qtdAndares) {
		this.qtdAndares = qtdAndares;
	}

	@JsonIgnore
	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}

	public List<Apartamento> getApartamentos() {
		return apartamentos;
	}
	
	public void setApartamentos(List<Apartamento> apartamentos) {
		this.apartamentos = apartamentos;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.nome, this.nomeBloco, this.qtdResidencias,
				this.qtdAndares, this.condominio);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Predio)) {
			return false;
		}
		Predio other = (Predio) obj;
		return Objects.equal(this.nome, other.nome)
				&& Objects.equal(this.nomeBloco, other.nomeBloco)
				&& Objects.equal(this.qtdAndares, other.qtdAndares)
				&& Objects.equal(this.qtdResidencias, other.qtdResidencias)
				&& Objects.equal(this.condominio, other.condominio);
	}
}
