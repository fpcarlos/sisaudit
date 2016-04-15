package br.leg.rr.tce.cgesi.sisaudit.comum.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "v_esfera", schema = "comum")
public class Esfera implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator( name = "sqente_id", sequenceName = "sqente_id",schema = "seguranca",allocationSize = 1)
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_esfera")
	private	Integer	id;	
	
	@Column(name="tipo", columnDefinition = "VARCHAR(20)")
	private String tipo;
	

	@Override
	public int hashCode() {
		final int prime = 41;
		int result = 131;
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
		Esfera other = (Esfera) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "Esfera [id=" + id + "]";
	}

	

}
