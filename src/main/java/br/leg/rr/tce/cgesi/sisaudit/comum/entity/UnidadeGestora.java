/* Autor: Adiel dos Santos de Araujo
 * email: adielrr@gmail.com
 */
package br.leg.rr.tce.cgesi.sisaudit.comum.entity;

import java.io.Serializable;
import java.util.Date;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Dependent
@Entity
@Table(name = "v_unidade_gestora", schema = "comum")
public class UnidadeGestora implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_ug")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "cod_unid_administrativa")
	private UnidadeAdministrativa unidadeAdministrativa;
	
	@ManyToOne
	@JoinColumn(name="cod_tipo_ug")
	private	TipoUg	tipoUg;	
	
	@ManyToOne
	@JoinColumn(name="cod_poder")
	private	Poder	poder;		
	
	@Column(name="data_criacao")
	@Temporal(javax.persistence.TemporalType.DATE)
	private	Date	data_criacao;
	
	@Column(name="data_extincao")
	@Temporal(javax.persistence.TemporalType.DATE)
	private	Date	data_extincao;	
	
	@Column(name="cnpj", columnDefinition = "VARCHAR(14)")
	private	String	cnpj;	
	
//	@ManyToOne
//	@JoinColumn(name="cod_ug_principal")
//	@NotFound(action = NotFoundAction.IGNORE)
	@Column(name="cod_ug_principal")
	private	Integer	unidadeGestoraPrincipal;	
	
	@Column(name="inscricao_estadual", columnDefinition = "VARCHAR(9)")
	private	String	inscricao_estadual;	
	
	@Column(name="nome", columnDefinition = "VARCHAR(150)")
	private	String	nome;
	
	@Column(name="sigla", columnDefinition = "VARCHAR(12)")
	private	String	sigla;
	
	@Column(name="logradouro", columnDefinition = "VARCHAR(60)")
	private	String	logradouro;
	
	@Column(name="numero", columnDefinition = "VARCHAR(6)")
	private	String	numero;		
	
	@Column(name="complemento", columnDefinition = "VARCHAR(30)")
	private	String	complemento;
	
	@Column(name="bairro", columnDefinition = "VARCHAR(40)")
	private	String	bairro;
	
	@Column(name="caixa_postal", columnDefinition = "VARCHAR(10)")
	private	String	caixa_postal;		
	
	@Column(name="cep", columnDefinition = "VARCHAR(8)")
	private	String	cep;
	
	@Column(name="fone_1", columnDefinition = "VARCHAR(20)")
	private	String	fone_1;
	
	@Column(name="fone_2", columnDefinition = "VARCHAR(20)")
	private	String	fone_2;
	
	@Column(name="fax", columnDefinition = "VARCHAR(20)")
	private	String	fax;
	
	@Column(name="e_mail", columnDefinition = "VARCHAR(50)")
	private	String	e_mail;
	
	@Column(name="contato", columnDefinition = "VARCHAR(200)")
	private	String	contato;
	
	@Column(name="situacao")
	private	Integer	situacao;
	
	@Column(name="data_cadastro")
	@Temporal(javax.persistence.TemporalType.DATE)
	private	Date	data_cadastro;
	
	@Column(name="opcao_lrf_rgf_semestral")
	private	Integer	opcao_lrf_rgf_semestral;		
	
	@Column(name="data_validacao_ug")
	@Temporal(javax.persistence.TemporalType.DATE)
	private	Date	data_validacao_ug;

	@Override
	public int hashCode() {
		final int prime = 41;
		int result = 157;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnidadeGestora other = (UnidadeGestora) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public String getNomeSilga(){
		return this.nome+" ("+this.sigla+")";
	}
	
	// -------INICIO DOS GETS E SETS---------------------------------------------------

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public TipoUg getTipoUg() {
		return tipoUg;
	}

	public void setTipoUg(TipoUg tipoUg) {
		this.tipoUg = tipoUg;
	}

	public Poder getPoder() {
		return poder;
	}

	public void setPoder(Poder poder) {
		this.poder = poder;
	}

	public UnidadeAdministrativa getUnidadeAdministrativa() {
		return unidadeAdministrativa;
	}

	public void setUnidadeAdministrativa(UnidadeAdministrativa unidadeAdministrativa) {
		this.unidadeAdministrativa = unidadeAdministrativa;
	}

	public Date getData_criacao() {
		return data_criacao;
	}

	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}

	public Date getData_extincao() {
		return data_extincao;
	}

	public void setData_extincao(Date data_extincao) {
		this.data_extincao = data_extincao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Integer getUnidadeGestoraPrincipal() {
		return unidadeGestoraPrincipal;
	}

	public void setUnidadeGestoraPrincipal(Integer unidadeGestoraPrincipal) {
		this.unidadeGestoraPrincipal = unidadeGestoraPrincipal;
	}

	public String getInscricao_estadual() {
		return inscricao_estadual;
	}

	public void setInscricao_estadual(String inscricao_estadual) {
		this.inscricao_estadual = inscricao_estadual;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCaixa_postal() {
		return caixa_postal;
	}

	public void setCaixa_postal(String caixa_postal) {
		this.caixa_postal = caixa_postal;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getFone_1() {
		return fone_1;
	}

	public void setFone_1(String fone_1) {
		this.fone_1 = fone_1;
	}

	public String getFone_2() {
		return fone_2;
	}

	public void setFone_2(String fone_2) {
		this.fone_2 = fone_2;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getE_mail() {
		return e_mail;
	}

	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}

	public Integer getSituacao() {
		return situacao;
	}

	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}

	public Date getData_cadastro() {
		return data_cadastro;
	}

	public void setData_cadastro(Date data_cadastro) {
		this.data_cadastro = data_cadastro;
	}

	public Integer getOpcao_lrf_rgf_semestral() {
		return opcao_lrf_rgf_semestral;
	}

	public void setOpcao_lrf_rgf_semestral(Integer opcao_lrf_rgf_semestral) {
		this.opcao_lrf_rgf_semestral = opcao_lrf_rgf_semestral;
	}

	public Date getData_validacao_ug() {
		return data_validacao_ug;
	}

	public void setData_validacao_ug(Date data_validacao_ug) {
		this.data_validacao_ug = data_validacao_ug;
	}

	@Override
	public String toString() {
		return "UnidadeGestora [id=" + id + "]";
	}


}
