package br.leg.rr.tce.cgesi.sisaudit.entity;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
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
 * The persistent class for the unidade_gestora_auditoria database table.
 * 
 */
@Dependent
@Entity
@Table(name="unidade_gestora_auditoria", schema="scsisaudit")
@NamedQuery(name="UnidadeGestoraAuditoria.findAll", query="SELECT u FROM UnidadeGestoraAuditoria u")
public class UnidadeGestoraAuditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//bi-directional many-to-one association to Auditoria
	@ManyToOne
	@JoinColumn(name="id_auditoria")
	private Auditoria auditoria;

	@ManyToOne
	@JoinColumn(name="id_unidade_gestora")
	private UnidadeGestora unidadeGestora;

	public UnidadeGestoraAuditoria() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Auditoria getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}

	public UnidadeGestora getUnidadeGestora() {
		return unidadeGestora;
	}

	public void setUnidadeGestora(UnidadeGestora unidadeGestora) {
		this.unidadeGestora = unidadeGestora;
	}

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
		UnidadeGestoraAuditoria other = (UnidadeGestoraAuditoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UnidadeGestoraAuditoria [id=" + id + "]";
	}

	
	

}