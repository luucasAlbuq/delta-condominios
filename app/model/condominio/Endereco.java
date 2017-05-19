package model.condominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.google.common.base.Objects;

/**
 * Classe Modelo de um Endereco
 * 
 * @author Lucas
 *
 */
@Entity(name = "Endereco")
public class Endereco {

	@Id
	@GeneratedValue
	private Long id;
	private String nomeRua;
	private String bairro;
	private String numero;
	private String cep;
	private String cidade;

	public Endereco() {
	}

	public Endereco(String nomeRua, String numero, String cep, String bairro, String cidade) {
		this.nomeRua = nomeRua;
		this.numero = numero;
		this.cep = cep;
		this.bairro = bairro;
		this.cidade = cidade;
	}

	public String getNomeRua() {
		return nomeRua;
	}

	public void setNomeRua(String nomeRua) {
		this.nomeRua = nomeRua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.nomeRua, this.numero, this.cep);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Endereco)) {
			return false;
		}

		Endereco other = (Endereco) obj;
		return Objects.equal(this.nomeRua, other.nomeRua)
				&& Objects.equal(this.numero, other.numero)
				&& Objects.equal(this.cep, other.cep) 
				&& Objects.equal(this.bairro, bairro)
				&& Objects.equal(this.cidade, other.cidade);
	}
}
