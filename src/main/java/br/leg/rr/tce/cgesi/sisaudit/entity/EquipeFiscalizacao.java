package br.leg.rr.tce.cgesi.sisaudit.entity;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.*;


/**
 * The persistent class for the equipe_fiscalizacao database table.
 * 
 */
@Dependent
@Entity
@Table(name="equipe_fiscalizacao", schema="scsisaudit")
@NamedQuery(name="EquipeFiscalizacao.findAll", query="SELECT e FROM EquipeFiscalizacao e")
public class EquipeFiscalizacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//bi-directional many-to-one association to Portaria
	@ManyToOne
	@JoinColumn(name="id_portaria")
	private Portaria portaria;

	//bi-directional many-to-one association to Servidor
	@ManyToOne
	@JoinColumn(name="id_servidor")
	private Servidor servidor;

	//bi-directional many-to-one association to TipoAuditor
	@ManyToOne
	@JoinColumn(name="id_tipo_auditor")
	private TipoAuditor tipoAuditor;

	public EquipeFiscalizacao() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Portaria getPortaria() {
		return this.portaria;
	}

	public void setPortaria(Portaria portaria) {
		this.portaria = portaria;
	}

	public Servidor getServidor() {
		return this.servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	public TipoAuditor getTipoAuditor() {
		return this.tipoAuditor;
	}

	public void setTipoAuditor(TipoAuditor tipoAuditor) {
		this.tipoAuditor = tipoAuditor;
	}

}