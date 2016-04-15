package br.leg.rr.tce.cgesi.sisaudit.entity;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the origem_auditoria database table.
 * 
 */
@Dependent
@Entity
@Table(name="origem_auditoria", schema="scsisaudit")
@NamedQuery(name="OrigemAuditoria.findAll", query="SELECT o FROM OrigemAuditoria o")
public class OrigemAuditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	//bi-directional many-to-one association to Auditoria
	@OneToMany(mappedBy="origemAuditoria")
	private List<Auditoria> auditorias;

	public OrigemAuditoria() {
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

	public List<Auditoria> getAuditorias() {
		return this.auditorias;
	}

	public void setAuditorias(List<Auditoria> auditorias) {
		this.auditorias = auditorias;
	}

	public Auditoria addAuditoria(Auditoria auditoria) {
		getAuditorias().add(auditoria);
		auditoria.setOrigemAuditoria(this);

		return auditoria;
	}

	public Auditoria removeAuditoria(Auditoria auditoria) {
		getAuditorias().remove(auditoria);
		auditoria.setOrigemAuditoria(null);

		return auditoria;
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
		OrigemAuditoria other = (OrigemAuditoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OrigemAuditoria [id=" + id + "]";
	}
	
	
	

}