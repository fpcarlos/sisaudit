package br.leg.rr.tce.cgesi.sisaudit.entity;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the unidade_fiscalizadora database table.
 * 
 */
@Dependent
@Entity
@Table(name="unidade_fiscalizadora", schema="scsisaudit")
@NamedQuery(name="UnidadeFiscalizadora.findAll", query="SELECT u FROM UnidadeFiscalizadora u")
public class UnidadeFiscalizadora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String nome;

	private String sigla;

	private String telefone;

	//bi-directional many-to-one association to Auditoria
	@OneToMany(mappedBy="unidadeFiscalizadora")
	private List<Auditoria> auditorias;

	public UnidadeFiscalizadora() {
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

	public String getSigla() {
		return this.sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getTelefone() {
		return this.telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public List<Auditoria> getAuditorias() {
		return this.auditorias;
	}

	public void setAuditorias(List<Auditoria> auditorias) {
		this.auditorias = auditorias;
	}

	public Auditoria addAuditoria(Auditoria auditoria) {
		getAuditorias().add(auditoria);
		auditoria.setUnidadeFiscalizadora(this);

		return auditoria;
	}

	public Auditoria removeAuditoria(Auditoria auditoria) {
		getAuditorias().remove(auditoria);
		auditoria.setUnidadeFiscalizadora(null);

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
		UnidadeFiscalizadora other = (UnidadeFiscalizadora) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UnidadeFiscalizadora [id=" + id + "]";
	}
	
	
	

}