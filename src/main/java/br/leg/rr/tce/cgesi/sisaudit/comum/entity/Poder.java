/* Autor: Adiel dos Santos de Araujo
 * email: adielrr@gmail.com
 *  
 * 
 */
package br.leg.rr.tce.cgesi.sisaudit.comum.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "v_poder", schema = "comum")
public class Poder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator( name = "sqente_id", sequenceName = "sqente_id",schema = "seguranca",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_poder")
	private	Integer	id;	
	@Column(name="descricao", columnDefinition = "VARCHAR(20)")
	private String descricao;

	@Override
	public int hashCode() {
		final int prime = 41;
		int result = 163;
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
		Poder other = (Poder) obj;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Poder [id=" + id + "]";
	}


	


}
