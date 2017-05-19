package model.condominio;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Objects;

/**
 * Classe Modelo de um apartamento.
 * 
 * @author Julio
 *
 */
@Entity
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Apartamento {

	@Id
	@Column(name = "id_apartamento")
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "predio_id")
	@JsonBackReference
	private Predio predio;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pessoa_id")
	private Pessoa responsavel;

	private String identificacaoApartamento;
	private int andarApartamento;
	private double area;
	private int qtQuartos;
	private int qtBanheiros;
	private int qtVarandas;

	public Apartamento() {
	}

	/**
	 * Construtor basico de apartamento.
	 * 
	 * @param identificacaoApartamento
	 * @param Predio
	 */
	public Apartamento(String identificacaoApartamento, Pessoa reponsavel) {
		this.identificacaoApartamento = identificacaoApartamento;
		this.responsavel = reponsavel;
	}

	/**
	 * Construtor Apartamento
	 * 
	 * @param identificacaoApartamento
	 * @param area
	 */
	public Apartamento(String identificacaoApartamento, double area,
			Pessoa reponsavel) {
		this.identificacaoApartamento = identificacaoApartamento;
		this.area = area;
		this.responsavel = reponsavel;
	}

	/**
	 * Construtor de apartamento
	 * 
	 * @param identificacaoApartamento
	 * @param andarApartamento
	 * @param area
	 * @param qtQuartos
	 * @param qtBanheiros
	 * @param qtVarandas
	 */
	public Apartamento(String identificacaoApartamento, int andarApartamento,
			double area, int qtQuartos, int qtBanheiros, int qtVarandas,
			Pessoa responsavel) {

		this.identificacaoApartamento = identificacaoApartamento;
		this.andarApartamento = andarApartamento;
		this.area = area;
		this.qtQuartos = qtQuartos;
		this.qtBanheiros = qtBanheiros;
		this.qtVarandas = qtVarandas;
		this.responsavel = responsavel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Predio getPredio() {
		return predio;
	}

	public void setPredio(Predio predio) {
		this.predio = predio;
	}

	public String getIdentificacaoApartamento() {
		return identificacaoApartamento;
	}

	public void setIdentificacaoApartamento(String identificacaoApartamento) {
		this.identificacaoApartamento = identificacaoApartamento;
	}

	public int getAndarApartamento() {
		return andarApartamento;
	}

	public void setAndarApartamento(int andarApartamento) {
		this.andarApartamento = andarApartamento;
	}

	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}

	public int getQtQuartos() {
		return qtQuartos;
	}

	public void setQtQuartos(int qtQuartos) {
		this.qtQuartos = qtQuartos;
	}

	public int getQtBanheiros() {
		return qtBanheiros;
	}

	public void setQtBanheiros(int qtBanheiros) {
		this.qtBanheiros = qtBanheiros;
	}

	public int getQtVarandas() {
		return qtVarandas;
	}

	public void setQtVarandas(int qtVarandas) {
		this.qtVarandas = qtVarandas;
	}

	public Pessoa getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Pessoa responsavel) {
		this.responsavel = responsavel;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.identificacaoApartamento,
				this.andarApartamento, this.area, this.qtQuartos,
				this.qtBanheiros, this.qtVarandas, this.predio,
				this.responsavel);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Apartamento)) {
			return false;
		}
		Apartamento other = (Apartamento) obj;
		return Objects.equal(this.identificacaoApartamento,
				other.identificacaoApartamento)
				&& Objects.equal(this.andarApartamento, other.andarApartamento)
				&& Objects.equal(this.area, other.area)
				&& Objects.equal(this.qtQuartos, other.qtQuartos)
				&& Objects.equal(this.qtBanheiros, other.qtBanheiros)
				&& Objects.equal(this.qtVarandas, other.qtVarandas)
				&& Objects.equal(this.responsavel, other.responsavel)
				&& Objects.equal(this.predio, other.predio);
	}
}
