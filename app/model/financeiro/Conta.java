package model.financeiro;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import model.condominio.Condominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

/**
 * Classe modelo de Conta
 * 
 * @author Lucas
 */
@Entity(name = "Conta")
public class Conta {

	@Id
	@GeneratedValue
	private Long id;
	
	private TipoConta tipoConta;

	/*
	 * Esse codigo e um codigo de identificacao da conta (eg: 1111 - Conta de
	 * energia)
	 */
	private String codigo;
	private BigDecimal valor;

	// @JsonFormat(shape=JsonFormat.Shape.STRING,
	// pattern="2015-07-16T03:00:00.000Z")
	private Date dataPagamento;

	// @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
	private Date dataVencimento;
	private String descricao;
	private Long codigoDeBarras;

	@Lob
	private byte[] documentoPDF;
	@ManyToOne
	// @JsonIgnore
	private Condominio condominio;

	/*
	 * TODO Lucas - acrescentar dois atributos FILE sendo um referente ao
	 * comprovante de pagamento e outro a conta propriamente dita.
	 */

	private Boolean pago;

	/**
	 * Construtor default de Conta
	 */
	public Conta() {
	}

	/**
	 * Construtor de Conta
	 * 
	 * @param codigo
	 * @param valor
	 */
	public Conta(String codigo, BigDecimal valor, Date dtVencimento, TipoConta tipoConta) {
		this.codigo = codigo;
		this.valor = valor;
		this.dataVencimento = dtVencimento;
		this.tipoConta=tipoConta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Boolean isPago() {
		return pago;
	}

	public void setPago(Boolean pago) {
		this.pago = pago;
	}

	public Long getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(Long codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.codigoDeBarras, this.codigo,
				this.dataVencimento, this.tipoConta);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Conta)) {
			return false;
		}

		Conta other = (Conta) obj;
		return Objects.equal(this.codigoDeBarras, other.codigoDeBarras)
				&& Objects.equal(this.codigo, other.codigo)
				&& Objects.equal(this.tipoConta, other.tipoConta)
				&& Objects.equal(this.dataVencimento, other.dataVencimento);
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dtPagamento) {
		this.dataPagamento = dtPagamento;
	}

	public Condominio getCondominio() {
		return condominio;
	}

	public void setCondominio(Condominio condominio) {
		this.condominio = condominio;
	}
	
	public TipoConta getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(TipoConta tipoConta) {
		this.tipoConta = tipoConta;
	}

	@JsonIgnore
	public byte[] getDocumentoPDF() {
		return documentoPDF;
	}

	public void setDocumentoPDF(byte[] documentoPDF) {
		this.documentoPDF = documentoPDF;
	}
}
