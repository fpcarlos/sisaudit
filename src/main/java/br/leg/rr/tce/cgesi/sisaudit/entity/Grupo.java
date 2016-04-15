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


/**
 * The persistent class for the grupo database table.
 * 
 */
@Entity
@Table(name="grupo", schema="scsisaudit")
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String descricao;

	//bi-directional many-to-one association to Servidor
	@ManyToOne
	@JoinColumn(name="login", referencedColumnName="login")
	private Servidor servidor;

	public Grupo() {
	}

	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Servidor getServidor() {
		return this.servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

}