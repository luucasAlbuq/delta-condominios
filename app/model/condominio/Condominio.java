package model.condominio;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import model.usuario.Usuario;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.common.base.Objects;

/**
 * Classe Modelo de Condomínio
 * 
 * @author Lucas
 *
 */
@Entity(name = "Condominio")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Condominio {

	@Id
	@Column(name = "id_condiminio")
	@GeneratedValue
	private Long id;

	private String nome;

	private TipoCondominio tipo;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	/** Area em metros quadrados */
	private Double area;

	@OneToMany(mappedBy = "condominio", cascade = CascadeType.ALL)
	@JsonManagedReference
	private Set<Predio> predios;

	// Sindicos
	@ManyToMany
	@JsonIgnore
	private List<Usuario> sindicos;

	@OneToOne
	@JsonManagedReference
	private Usuario admin;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Aviso> quadroDeAvisos;

	// private Set<Pessoa> sindicos;
	// private List<Funcionario> funcionarios; ??????
	// private <BalancoFinanceiro> balancoFincanceiro;

	/**
	 * Construtor default
	 */
	public Condominio() {
		quadroDeAvisos = new ArrayList<>();
	}

	/**
	 * Construtor de condominio basico.
	 * 
	 * @param Double
	 *            area
	 * @param String
	 *            nome
	 * @param int numeroBlocos
	 */
	public Condominio(Double area, String nome, TipoCondominio tipo) {
		this();
		this.area = area;
		this.nome = nome;
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}

	public TipoCondominio getTipo() {
		return tipo;
	}

	public void setTipo(TipoCondominio tipo) {
		this.tipo = tipo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<Predio> getPredios() {
		return predios;
	}

	public void setPredios(Set<Predio> predios) {
		this.predios = predios;
	}

	public void addPredio(Predio predio) {
		this.predios.add(predio);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.area, this.nome, this.tipo, this.endereco);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Condominio)) {
			return false;
		}

		Condominio other = (Condominio) obj;
		return Objects.equal(this.area, other.area)
				&& Objects.equal(this.nome, other.nome)
				&& Objects.equal(this.tipo, other.tipo)
				&& Objects.equal(this.endereco, other.endereco);
	}

	public List<Usuario> getSindicos() {
		return sindicos;
	}

	public void setSindicos(List<Usuario> sindicos) {
		this.sindicos = sindicos;
	}

	public Usuario getAdmin() {
		return admin;
	}

	public void setAdmin(Usuario admin) {
		this.admin = admin;
	}

	public List<Aviso> getQuadroDeAvisos() {
		return quadroDeAvisos;
	}

	public void setQuadroDeAvisos(List<Aviso> quadroDeAvisos) {
		this.quadroDeAvisos = quadroDeAvisos;
	}
}
