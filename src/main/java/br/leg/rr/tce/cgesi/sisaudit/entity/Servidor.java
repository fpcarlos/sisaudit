package br.leg.rr.tce.cgesi.sisaudit.entity;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the servidor database table.
 * 
 */
@Dependent
@Entity
@Table(name="servidor", schema="scsisaudit")
@NamedQuery(name="Servidor.findAll", query="SELECT s FROM Servidor s")
public class Servidor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="auditor_fiscal")
	private String auditorFiscal;

	private String autoridade;

	private String formacao;

	private String login;

	private String nome;

	@Column(name="servidor_administrador")
	private String servidorAdministrador;

	@Column(name="servidor_atualiza_publicacao")
	private String servidorAtualizaPublicacao;

	@Column(name="servidor_autorizador")
	private String servidorAutorizador;

	@Column(name="servidor_gabinete_autoridade")
	private String servidorGabineteAutoridade;

	private String sexo;

	//bi-directional many-to-one association to EquipeFiscalizacao
	//@OneToMany(mappedBy="servidor")
	@Transient
	private List<EquipeFiscalizacao> equipeFiscalizacaos;

	//bi-directional many-to-one association to Portaria
	//@OneToMany(mappedBy="servidor")
	@Transient
	private List<Portaria> portarias;

	public Servidor() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuditorFiscal() {
		return this.auditorFiscal;
	}

	public void setAuditorFiscal(String auditorFiscal) {
		this.auditorFiscal = auditorFiscal;
	}

	public String getAutoridade() {
		return this.autoridade;
	}

	public void setAutoridade(String autoridade) {
		this.autoridade = autoridade;
	}

	public String getFormacao() {
		return this.formacao;
	}

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getServidorAdministrador() {
		return this.servidorAdministrador;
	}

	public void setServidorAdministrador(String servidorAdministrador) {
		this.servidorAdministrador = servidorAdministrador;
	}

	public String getServidorAtualizaPublicacao() {
		return this.servidorAtualizaPublicacao;
	}

	public void setServidorAtualizaPublicacao(String servidorAtualizaPublicacao) {
		this.servidorAtualizaPublicacao = servidorAtualizaPublicacao;
	}

	public String getServidorAutorizador() {
		return this.servidorAutorizador;
	}

	public void setServidorAutorizador(String servidorAutorizador) {
		this.servidorAutorizador = servidorAutorizador;
	}

	public String getServidorGabineteAutoridade() {
		return this.servidorGabineteAutoridade;
	}

	public void setServidorGabineteAutoridade(String servidorGabineteAutoridade) {
		this.servidorGabineteAutoridade = servidorGabineteAutoridade;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public List<EquipeFiscalizacao> getEquipeFiscalizacaos() {
		return this.equipeFiscalizacaos;
	}

	public void setEquipeFiscalizacaos(List<EquipeFiscalizacao> equipeFiscalizacaos) {
		this.equipeFiscalizacaos = equipeFiscalizacaos;
	}

	public EquipeFiscalizacao addEquipeFiscalizacao(EquipeFiscalizacao equipeFiscalizacao) {
		getEquipeFiscalizacaos().add(equipeFiscalizacao);
		equipeFiscalizacao.setServidor(this);

		return equipeFiscalizacao;
	}

	public EquipeFiscalizacao removeEquipeFiscalizacao(EquipeFiscalizacao equipeFiscalizacao) {
		getEquipeFiscalizacaos().remove(equipeFiscalizacao);
		equipeFiscalizacao.setServidor(null);

		return equipeFiscalizacao;
	}

	public List<Portaria> getPortarias() {
		return this.portarias;
	}

	public void setPortarias(List<Portaria> portarias) {
		this.portarias = portarias;
	}
/*
	public Portaria addPortaria(Portaria portaria) {
		getPortarias().add(portaria);
		portaria.setServidor(this);

		return portaria;
	}

	public Portaria removePortaria(Portaria portaria) {
		getPortarias().remove(portaria);
		portaria.setServidor(null);

		return portaria;
	}
*/

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Servidor other = (Servidor) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Servidor [id=" + id + "]";
	}
	
	
	
	
}