package model.condominio;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Modelo de um aviso
 * 
 * @author marcos v. candeia
 *
 */
@Entity(name = "Aviso")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Aviso {

	@Id
	@Column(name = "id_condiminio")
	@GeneratedValue
	private Long id;

	private String descricao;

	private String nomeAutor;

	@Temporal(TemporalType.DATE)
	private Date dataPostagem;

	public Aviso() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNomeAutor() {
		return nomeAutor;
	}

	public void setNomeAutor(String nomeAutor) {
		this.nomeAutor = nomeAutor;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}
}
