package br.leg.rr.tce.cgesi.sisaudit.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.leg.rr.tce.cgesi.sisaudit.comum.entity.UnidadeGestora;


/**
 * The persistent class for the unidade_gestora_portaria database table.
 * 
 */
@Entity
@Table(name="unidade_gestora_portaria", schema="scsisaudit")
@NamedQuery(name="UnidadeGestoraPortaria.findAll", query="SELECT u FROM UnidadeGestoraPortaria u")
public class UnidadeGestoraPortaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	//@Column(name="id_unidade_gestora")
	//private Integer idUnidadeGestora;
	@ManyToOne
	@JoinColumn(name="id_unidade_gestora")
	private UnidadeGestora unidadeGestora;

	//bi-directional many-to-one association to Portaria
	@ManyToOne
	@JoinColumn(name="id_portaria")
	private Portaria portaria;

	public UnidadeGestoraPortaria() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
		
	public UnidadeGestora getUnidadeGestora() {
		return unidadeGestora;
	}

	public void setUnidadeGestora(UnidadeGestora unidadeGestora) {
		this.unidadeGestora = unidadeGestora;
	}

	/*
	public Integer getIdUnidadeGestora() {
		return this.idUnidadeGestora;
	}

	public void setIdUnidadeGestora(Integer idUnidadeGestora) {
		this.idUnidadeGestora = idUnidadeGestora;
	}
*/
	public Portaria getPortaria() {
		return this.portaria;
	}

	public void setPortaria(Portaria portaria) {
		this.portaria = portaria;
	}

}