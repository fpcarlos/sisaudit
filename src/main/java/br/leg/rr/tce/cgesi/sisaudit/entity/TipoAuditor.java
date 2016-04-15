package br.leg.rr.tce.cgesi.sisaudit.entity;

import java.io.Serializable;

import javax.enterprise.context.Dependent;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipo_auditor database table.
 * 
 */
@Dependent
@Entity
@Table(name="tipo_auditor", schema="scsisaudit")
@NamedQuery(name="TipoAuditor.findAll", query="SELECT t FROM TipoAuditor t")
public class TipoAuditor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	private String descricao;

	private String funcao;

	//bi-directional many-to-one association to EquipeFiscalizacao
	@OneToMany(mappedBy="tipoAuditor")
	private List<EquipeFiscalizacao> equipeFiscalizacaos;

	public TipoAuditor() {
	}

	public Integer getId() {
		return this.id;
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

	public String getFuncao() {
		return this.funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public List<EquipeFiscalizacao> getEquipeFiscalizacaos() {
		return this.equipeFiscalizacaos;
	}

	public void setEquipeFiscalizacaos(List<EquipeFiscalizacao> equipeFiscalizacaos) {
		this.equipeFiscalizacaos = equipeFiscalizacaos;
	}

	public EquipeFiscalizacao addEquipeFiscalizacao(EquipeFiscalizacao equipeFiscalizacao) {
		getEquipeFiscalizacaos().add(equipeFiscalizacao);
		equipeFiscalizacao.setTipoAuditor(this);

		return equipeFiscalizacao;
	}

	public EquipeFiscalizacao removeEquipeFiscalizacao(EquipeFiscalizacao equipeFiscalizacao) {
		getEquipeFiscalizacaos().remove(equipeFiscalizacao);
		equipeFiscalizacao.setTipoAuditor(null);

		return equipeFiscalizacao;
	}

}