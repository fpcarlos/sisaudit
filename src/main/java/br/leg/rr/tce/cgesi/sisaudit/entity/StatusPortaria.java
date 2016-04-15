package br.leg.rr.tce.cgesi.sisaudit.entity;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the status_portaria database table.
 * 
 */
@Dependent
@Entity
@Table(name="status_portaria", schema="scsisaudit")
@NamedQuery(name="StatusPortaria.findAll", query="SELECT s FROM StatusPortaria s")
public class StatusPortaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	//bi-directional many-to-one association to PortariasAndamento
	@OneToMany(mappedBy="statusPortaria")
	private List<PortariasAndamento> portariasAndamentos;

	public StatusPortaria() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<PortariasAndamento> getPortariasAndamentos() {
		return this.portariasAndamentos;
	}

	public void setPortariasAndamentos(List<PortariasAndamento> portariasAndamentos) {
		this.portariasAndamentos = portariasAndamentos;
	}

	public PortariasAndamento addPortariasAndamento(PortariasAndamento portariasAndamento) {
		getPortariasAndamentos().add(portariasAndamento);
		portariasAndamento.setStatusPortaria(this);

		return portariasAndamento;
	}

	public PortariasAndamento removePortariasAndamento(PortariasAndamento portariasAndamento) {
		getPortariasAndamentos().remove(portariasAndamento);
		portariasAndamento.setStatusPortaria(null);

		return portariasAndamento;
	}

}