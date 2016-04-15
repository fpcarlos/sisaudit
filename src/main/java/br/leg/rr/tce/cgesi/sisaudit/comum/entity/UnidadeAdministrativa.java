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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "v_unidade_administrativa", schema = "comum")
public class UnidadeAdministrativa  implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
//	@SequenceGenerator( name = "sqente_id", sequenceName = "sqente_id",schema = "seguranca",allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cod_unid_administrativa")
	private	Integer	id;	
	
	@Column(name="sigla", columnDefinition = "VARCHAR(3)")
	private String sigla;
	
	@Column(name = "nome", columnDefinition = "VARCHAR(30)")
	private	String	nome;
	
	@ManyToOne
	@JoinColumn(name = "cod_esfera")
	private Esfera esfera;
	
	@Column(name = "qtd_habitante")
	private Integer qtd_habitante;
	
	@Column(name = "id_municipio")
	private Integer id_municipio;
	
	@Column(name = "id_municipio_sagf")
	private Integer id_municipio_sagf;
	

	@Override
	public int hashCode() {
		final int prime = 41;
		int result = 127;
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
		UnidadeAdministrativa other = (UnidadeAdministrativa) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	//-------INICIO DOS GETS E SETS---------------------------------------------------

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Esfera getEsfera() {
		return esfera;
	}

	public void setEsfera(Esfera esfera) {
		this.esfera = esfera;
	}

	public Integer getQtd_habitante() {
		return qtd_habitante;
	}

	public void setQtd_habitante(Integer qtd_habitante) {
		this.qtd_habitante = qtd_habitante;
	}

	public Integer getId_municipio() {
		return id_municipio;
	}

	public void setId_municipio(Integer id_municipio) {
		this.id_municipio = id_municipio;
	}

	public Integer getId_municipio_sagf() {
		return id_municipio_sagf;
	}

	public void setId_municipio_sagf(Integer id_municipio_sagf) {
		this.id_municipio_sagf = id_municipio_sagf;
	}

	@Override
	public String toString() {
		return "UnidadeAdministrativa [id=" + id + "]";
	}

	
	
	
}
