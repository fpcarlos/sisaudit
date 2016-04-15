package br.leg.rr.tce.cgesi.sisaudit.entity;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the portarias_andamento database table.
 * 
 */
@Dependent
@Entity
@Table(name="portarias_andamento", schema="scsisaudit")
@NamedQuery(name="PortariasAndamento.findAll", query="SELECT p FROM PortariasAndamento p")
public class PortariasAndamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Temporal(TemporalType.DATE)
	@Column(name="status_date")
	private Date statusDate;

	@Column(name="status_justificativa")
	private String statusJustificativa;

	@Column(name="status_usr")
	private String statusUsr;

	//bi-directional many-to-one association to Portaria
	@ManyToOne
	@JoinColumn(name="id_portaria")
	private Portaria portaria;

	//bi-directional many-to-one association to StatusPortaria
	@ManyToOne
	@JoinColumn(name="id_status_portaria")
	private StatusPortaria statusPortaria;

	public PortariasAndamento() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStatusDate() {
		return this.statusDate;
	}

	public void setStatusDate(Date statusDate) {
		this.statusDate = statusDate;
	}

	public String getStatusJustificativa() {
		return this.statusJustificativa;
	}

	public void setStatusJustificativa(String statusJustificativa) {
		this.statusJustificativa = statusJustificativa;
	}

	public String getStatusUsr() {
		return this.statusUsr;
	}

	public void setStatusUsr(String statusUsr) {
		this.statusUsr = statusUsr;
	}

	public Portaria getPortaria() {
		return this.portaria;
	}

	public void setPortaria(Portaria portaria) {
		this.portaria = portaria;
	}

	public StatusPortaria getStatusPortaria() {
		return this.statusPortaria;
	}

	public void setStatusPortaria(StatusPortaria statusPortaria) {
		this.statusPortaria = statusPortaria;
	}

}