package model.condominio;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.common.base.Objects;

/**
 * Classe Modelo de Pessoa
 * 
 * @author Lucas
 *
 */
@Entity(name = "Pessoa")
public class Pessoa {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany
	private List<Apartamento> apartamentos;
	
	private String cpf;
	
	private String nome;
	
	private String email;
	
	private String fone;
	
	
	/**
	 * Construtor default de Pessoa
	 */
	public Pessoa(){}
	
	/**
	 * Construtor de pessoa.
	 * 
	 * @param cpf
	 * @param nome
	 * @param email
	 * @param fone
	 */
	public Pessoa(String cpf, String nome, String email, String fone){
		this.cpf = cpf;
		this.nome = nome;
		this.email = email;
		this.fone = fone;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}
	
	public List<Apartamento> getApartamentos() {
		return apartamentos;
	}

	public void setApartamentos(List<Apartamento> apartamentos) {
		this.apartamentos = apartamentos;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Pessoa)) {
			return false;
		}

		Pessoa other = (Pessoa) obj;
		return Objects.equal(this.cpf, other.cpf)
				&& Objects.equal(this.nome, other.nome)
				&& Objects.equal(this.email, other.email);
	}
}
