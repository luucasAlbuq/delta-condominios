package model.financeiro;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.common.base.Objects;

/**
 * Classe modelo de Banlanco Financeiro
 * 
 * @author Lucas
 *
 */
@Entity(name = "BalancoFinanceiro")
public class BalancoFinanceiro {
	
	@Id
	@GeneratedValue
	private Long id;
	private Long idCondominio;
	private Date referenciaMes;
	private BigDecimal saldo;
	private String descricao;
	
	@OneToMany
	private List<Conta> contas;
	
	/**
	 * Construtor BalancoFinanceiro
	 * 
	 * @param referenciaMes
	 * @param contas
	 */
	public BalancoFinanceiro(Date referenciaMes, List<Conta> contas, Long idCondominio){
		this.contas = contas;
		this.referenciaMes = referenciaMes;
		this.idCondominio = idCondominio;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getReferenciaMes() {
		return referenciaMes;
	}

	public void setReferenciaMes(Date referenciaMes) {
		this.referenciaMes = referenciaMes;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Conta> getContas() {
		return contas;
	}

	public void setContas(List<Conta> contas) {
		this.contas = contas;
	}
	
	public Long getIdCondominio() {
		return idCondominio;
	}

	public void setIdCondominio(Long idCondominio) {
		this.idCondominio = idCondominio;
	}

	/**
	 * Adiciona uma conta ao BalancoFinanceiro
	 * 
	 * @param conta
	 */
	public void addConta(Conta conta){
		contas.add(conta);
	}
	
	/**
	 * Adiciona uma colecao de contas ao BalancoFinanceiro
	 * 
	 * @param contas
	 */
	public void addAllConta(List<Conta> contas){
		contas.addAll(contas);
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(this.contas, this.saldo,
				this.referenciaMes, this.idCondominio);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof BalancoFinanceiro)) {
			return false;
		}

		BalancoFinanceiro other = (BalancoFinanceiro) obj;
		return Objects.equal(this.referenciaMes, other.referenciaMes)
				&& Objects.equal(this.contas, other.contas)
				&& Objects.equal(this.saldo, other.saldo)
				&& Objects.equal(this.idCondominio, other.idCondominio);
	}
	
}
