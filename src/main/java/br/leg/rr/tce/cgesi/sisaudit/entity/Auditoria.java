package br.leg.rr.tce.cgesi.sisaudit.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.leg.rr.tce.cgesi.sisaudit.comum.entity.UnidadeGestora;


/**
 * The persistent class for the auditoria database table.
 * 
 */
@Dependent
@Entity
@Table(name="auditoria", schema="scsisaudit")
@NamedQuery(name="Auditoria.findAll", query="SELECT a FROM Auditoria a")
public class Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="exec_dias_uteis_prev")
	private Integer execDiasUteisPrev;

	@Column(name="exec_dias_uteis_real")
	private Integer execDiasUteisReal;

	@Temporal(TemporalType.DATE)
	@Column(name="exec_fim_prev")
	private Date execFimPrev;

	@Temporal(TemporalType.DATE)
	@Column(name="exec_fim_real")
	private Date execFimReal;

	@Temporal(TemporalType.DATE)
	@Column(name="exec_inicio_prev")
	private Date execInicioPrev;

	@Temporal(TemporalType.DATE)
	@Column(name="exec_inicio_real")
	private Date execInicioReal;

	@Column(name="exercicios_anteriores")
	private Boolean exerciciosAnteriores;

	private String idauditoria;

	@Column(name="motivacao_fiscal")
	private String motivacaoFiscal;

	private String numprocesso;

	private String objetivo;

	@Column(name="plan_dias_uteis_prev")
	private Integer planDiasUteisPrev;

	@Column(name="plan_dias_uteis_real")
	private Integer planDiasUteisReal;

	@Temporal(TemporalType.DATE)
	@Column(name="plan_fim_prev")
	private Date planFimPrev;

	@Temporal(TemporalType.DATE)
	@Column(name="plan_fim_real")
	private Date planFimReal;

	@Temporal(TemporalType.DATE)
	@Column(name="plan_inicio_prev")
	private Date planInicioPrev;

	@Temporal(TemporalType.DATE)
	@Column(name="plan_inicio_real")
	private Date planInicioReal;

	@Column(name="rela_dias_uteis_prev")
	private Integer relaDiasUteisPrev;

	@Column(name="rela_dias_uteis_real")
	private Integer relaDiasUteisReal;

	@Temporal(TemporalType.DATE)
	@Column(name="rela_fim_prev")
	private Date relaFimPrev;

	@Temporal(TemporalType.DATE)
	@Column(name="rela_fim_real")
	private Date relaFimReal;

	@Temporal(TemporalType.DATE)
	@Column(name="rela_inicio_prev")
	private Date relaInicioPrev;

	@Temporal(TemporalType.DATE)
	@Column(name="rela_inicio_real")
	private Date relaInicioReal;
	
	@Column(name="ano_auditoria")
	private String anoAuditoria;

	private String relator;

	private String titulo;

	//bi-directional many-to-one association to CriteriosSelecao
	@ManyToOne
	@JoinColumn(name="id_criterios_selecao")
	private CriteriosSelecao criteriosSelecao;

	//bi-directional many-to-one association to OrigemAuditoria
	@ManyToOne
	@JoinColumn(name="id_origem_auditoria")
	private OrigemAuditoria origemAuditoria;

	//bi-directional many-to-one association to TipoFiscalizacao
	@ManyToOne
	@JoinColumn(name="id_tipo_fiscalizacao")
	private TipoFiscalizacao tipoFiscalizacao;

	//bi-directional many-to-one association to UnidadeFiscalizadora
	@ManyToOne
	@JoinColumn(name="id_unidade_fiscalizadora")
	private UnidadeFiscalizadora unidadeFiscalizadora;

	//bi-directional many-to-one association to UnidadeGestoraAuditoria
	@OneToMany(mappedBy="auditoria")
	private List<UnidadeGestoraAuditoria> unidadeGestoraAuditorias = new ArrayList<UnidadeGestoraAuditoria>();

	@Transient
    private List<UnidadeGestoraAuditoria> unidadeGestoraAuditoriaList = new ArrayList<UnidadeGestoraAuditoria>();
	
	@Transient
	private List<UnidadeGestora> listaUnidadeGestoraTmp = new ArrayList<UnidadeGestora>();
	
	@Transient
	private List<UnidadeGestoraAuditoria> unidadeGestoraAuditoriasExcluidas = new ArrayList<UnidadeGestoraAuditoria>();
	
	
	
	public Auditoria() {
	}
	
	
	
	public void selecionarUGA(UnidadeGestora entity) {
		boolean sc=true;
		for(UnidadeGestoraAuditoria tmp: unidadeGestoraAuditorias){
			if(tmp.getUnidadeGestora().getId().equals(entity.getId())){
				sc=false;
				break;
			}
		}
		for(UnidadeGestoraAuditoria tmp: unidadeGestoraAuditoriasExcluidas){
			if(tmp.getUnidadeGestora().getId().equals(entity.getId())){
				unidadeGestoraAuditoriasExcluidas.remove(tmp);
			}
		}
		if(sc){
			UnidadeGestoraAuditoria ugp = new UnidadeGestoraAuditoria();
			ugp.setAuditoria(this);
			ugp.setUnidadeGestora(entity);
			unidadeGestoraAuditoriaList.add(ugp);
		}
	
	}
	
	public void adincionarNaListaExcluidos(UnidadeGestora entity) {
		for(UnidadeGestoraAuditoria tmp: unidadeGestoraAuditorias){
			if(tmp.getUnidadeGestora().getId().equals(entity.getId())){
				unidadeGestoraAuditoriasExcluidas.add(tmp);
				break;
			}
		}
	}
	
	
	
	public void criarListaUGAuditoria(){
		try {
			unidadeGestoraAuditoriaList = new ArrayList<UnidadeGestoraAuditoria>();
			Map<Integer, UnidadeGestora> mapUGA = new HashMap<Integer, UnidadeGestora>();
			List<UnidadeGestoraAuditoria> listUGAExcluir = new ArrayList<UnidadeGestoraAuditoria>();
			//Map<Integer, UnidadeGestora> mapUGAExcluir = new HashMap<Integer, UnidadeGestora>();
			Map<Integer, UnidadeGestora> mapUGSalvar = new HashMap<Integer, UnidadeGestora>();
			
			for (UnidadeGestoraAuditoria tmp1 : unidadeGestoraAuditorias) {
				mapUGA.put(tmp1.getUnidadeGestora().getId(), tmp1.getUnidadeGestora());
			}
			for (UnidadeGestora tmp1 : listaUnidadeGestoraTmp) {
				if(mapUGA.containsKey(tmp1.getId())){
					break;
				}
				mapUGSalvar.put(tmp1.getId(), tmp1);
				UnidadeGestoraAuditoria ugp = new UnidadeGestoraAuditoria();
				ugp.setAuditoria(this);
				ugp.setUnidadeGestora(tmp1);
				unidadeGestoraAuditoriaList.add(ugp);
			}		
			for (UnidadeGestoraAuditoria tmp1 : unidadeGestoraAuditoriasExcluidas) {
				if(mapUGSalvar.containsKey(tmp1.getId())){
					break;
				}
				listUGAExcluir.add(tmp1);
				
			}
			
			unidadeGestoraAuditoriasExcluidas = new ArrayList<UnidadeGestoraAuditoria>();
			unidadeGestoraAuditoriasExcluidas = listUGAExcluir;
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	//adicionando na lista excluidas e removendo da lista temporari que sera gravada
	public void removerUGA(UnidadeGestora aux){
		for(UnidadeGestoraAuditoria tmp1: unidadeGestoraAuditorias){
			if(tmp1.getUnidadeGestora().getId()==aux.getId()){
				unidadeGestoraAuditoriasExcluidas.add(tmp1);
				//removerUG(aux);
				break;
			}
		}
	}
	
    //remove da lista que será gravada
	public void removerUG(UnidadeGestora aux){
		for(UnidadeGestora tmp1: listaUnidadeGestoraTmp){
			if(tmp1.getId()==aux.getId()){
				listaUnidadeGestoraTmp.remove(tmp1);
				break;
			}
		}
	}

	//retorna lista de string
	public String getPegarSiglaDasUgsInteressadas() {
		String aux = "";

		for (UnidadeGestoraAuditoria ugI : unidadeGestoraAuditorias) {
			if (aux.length() > 0) {
				aux = aux + ", " + ugI.getUnidadeGestora().getSigla();
			} else {
				aux = ugI.getUnidadeGestora().getSigla();
			}
		}

		return aux;

	}
	
	
	
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getExecDiasUteisPrev() {
		return this.execDiasUteisPrev;
	}

	public void setExecDiasUteisPrev(Integer execDiasUteisPrev) {
		this.execDiasUteisPrev = execDiasUteisPrev;
	}

	public Integer getExecDiasUteisReal() {
		return this.execDiasUteisReal;
	}

	public void setExecDiasUteisReal(Integer execDiasUteisReal) {
		this.execDiasUteisReal = execDiasUteisReal;
	}

	public Date getExecFimPrev() {
		return this.execFimPrev;
	}

	public void setExecFimPrev(Date execFimPrev) {
		this.execFimPrev = execFimPrev;
	}

	public Date getExecFimReal() {
		return this.execFimReal;
	}

	public void setExecFimReal(Date execFimReal) {
		this.execFimReal = execFimReal;
	}

	public Date getExecInicioPrev() {
		return this.execInicioPrev;
	}

	public void setExecInicioPrev(Date execInicioPrev) {
		this.execInicioPrev = execInicioPrev;
	}

	public Date getExecInicioReal() {
		return this.execInicioReal;
	}

	public void setExecInicioReal(Date execInicioReal) {
		this.execInicioReal = execInicioReal;
	}

	public Boolean getExerciciosAnteriores() {
		return this.exerciciosAnteriores;
	}

	public void setExerciciosAnteriores(Boolean exerciciosAnteriores) {
		this.exerciciosAnteriores = exerciciosAnteriores;
	}

	public String getIdauditoria() {
		return this.idauditoria;
	}

	public void setIdauditoria(String idauditoria) {
		this.idauditoria = idauditoria;
	}

	public String getMotivacaoFiscal() {
		return this.motivacaoFiscal;
	}

	public void setMotivacaoFiscal(String motivacaoFiscal) {
		this.motivacaoFiscal = motivacaoFiscal;
	}

	public String getNumprocesso() {
		return this.numprocesso;
	}

	public void setNumprocesso(String numprocesso) {
		this.numprocesso = numprocesso;
	}

	public String getObjetivo() {
		return this.objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public Integer getPlanDiasUteisPrev() {
		return this.planDiasUteisPrev;
	}

	public void setPlanDiasUteisPrev(Integer planDiasUteisPrev) {
		this.planDiasUteisPrev = planDiasUteisPrev;
	}

	public Integer getPlanDiasUteisReal() {
		return this.planDiasUteisReal;
	}

	public void setPlanDiasUteisReal(Integer planDiasUteisReal) {
		this.planDiasUteisReal = planDiasUteisReal;
	}

	public Date getPlanFimPrev() {
		return this.planFimPrev;
	}

	public void setPlanFimPrev(Date planFimPrev) {
		this.planFimPrev = planFimPrev;
	}

	public Date getPlanFimReal() {
		return this.planFimReal;
	}

	public void setPlanFimReal(Date planFimReal) {
		this.planFimReal = planFimReal;
	}

	public Date getPlanInicioPrev() {
		return this.planInicioPrev;
	}

	public void setPlanInicioPrev(Date planInicioPrev) {
		this.planInicioPrev = planInicioPrev;
	}

	public Date getPlanInicioReal() {
		return this.planInicioReal;
	}

	public void setPlanInicioReal(Date planInicioReal) {
		this.planInicioReal = planInicioReal;
	}

	public Integer getRelaDiasUteisPrev() {
		return this.relaDiasUteisPrev;
	}

	public void setRelaDiasUteisPrev(Integer relaDiasUteisPrev) {
		this.relaDiasUteisPrev = relaDiasUteisPrev;
	}

	public Integer getRelaDiasUteisReal() {
		return this.relaDiasUteisReal;
	}

	public void setRelaDiasUteisReal(Integer relaDiasUteisReal) {
		this.relaDiasUteisReal = relaDiasUteisReal;
	}

	public Date getRelaFimPrev() {
		return this.relaFimPrev;
	}

	public void setRelaFimPrev(Date relaFimPrev) {
		this.relaFimPrev = relaFimPrev;
	}

	public Date getRelaFimReal() {
		return this.relaFimReal;
	}

	public void setRelaFimReal(Date relaFimReal) {
		this.relaFimReal = relaFimReal;
	}

	public Date getRelaInicioPrev() {
		return this.relaInicioPrev;
	}

	public void setRelaInicioPrev(Date relaInicioPrev) {
		this.relaInicioPrev = relaInicioPrev;
	}

	public Date getRelaInicioReal() {
		return this.relaInicioReal;
	}

	public void setRelaInicioReal(Date relaInicioReal) {
		this.relaInicioReal = relaInicioReal;
	}

	public String getRelator() {
		return this.relator;
	}

	public void setRelator(String relator) {
		this.relator = relator;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public CriteriosSelecao getCriteriosSelecao() {
		return this.criteriosSelecao;
	}

	public void setCriteriosSelecao(CriteriosSelecao criteriosSelecao) {
		this.criteriosSelecao = criteriosSelecao;
	}

	public OrigemAuditoria getOrigemAuditoria() {
		return this.origemAuditoria;
	}

	public void setOrigemAuditoria(OrigemAuditoria origemAuditoria) {
		this.origemAuditoria = origemAuditoria;
	}

	public TipoFiscalizacao getTipoFiscalizacao() {
		return this.tipoFiscalizacao;
	}

	public void setTipoFiscalizacao(TipoFiscalizacao tipoFiscalizacao) {
		this.tipoFiscalizacao = tipoFiscalizacao;
	}

	public UnidadeFiscalizadora getUnidadeFiscalizadora() {
		return this.unidadeFiscalizadora;
	}

	public void setUnidadeFiscalizadora(UnidadeFiscalizadora unidadeFiscalizadora) {
		this.unidadeFiscalizadora = unidadeFiscalizadora;
	}

	public List<UnidadeGestoraAuditoria> getUnidadeGestoraAuditorias() {
		return this.unidadeGestoraAuditorias;
	}

	public void setUnidadeGestoraAuditorias(List<UnidadeGestoraAuditoria> unidadeGestoraAuditorias) {
		this.unidadeGestoraAuditorias = unidadeGestoraAuditorias;
	}

	public UnidadeGestoraAuditoria addUnidadeGestoraAuditoria(UnidadeGestoraAuditoria unidadeGestoraAuditoria) {
		getUnidadeGestoraAuditorias().add(unidadeGestoraAuditoria);
		unidadeGestoraAuditoria.setAuditoria(this);

		return unidadeGestoraAuditoria;
	}

	public UnidadeGestoraAuditoria removeUnidadeGestoraAuditoria(UnidadeGestoraAuditoria unidadeGestoraAuditoria) {
		getUnidadeGestoraAuditorias().remove(unidadeGestoraAuditoria);
		unidadeGestoraAuditoria.setAuditoria(null);

		return unidadeGestoraAuditoria;
	}

	public List<UnidadeGestoraAuditoria> getUnidadeGestoraAuditoriaList() {
		return unidadeGestoraAuditoriaList;
	}

	public void setUnidadeGestoraAuditoriaList(List<UnidadeGestoraAuditoria> unidadeGestoraAuditoriaList) {
		this.unidadeGestoraAuditoriaList = unidadeGestoraAuditoriaList;
	}

	public List<UnidadeGestora> getListaUnidadeGestoraTmp() {
		return listaUnidadeGestoraTmp;
	}

	public void setListaUnidadeGestoraTmp(List<UnidadeGestora> listaUnidadeGestoraTmp) {
		this.listaUnidadeGestoraTmp = listaUnidadeGestoraTmp;
	}
	
	public List<UnidadeGestoraAuditoria> getUnidadeGestoraAuditoriasExcluidas() {
		return unidadeGestoraAuditoriasExcluidas;
	}

	public void setUnidadeGestoraAuditoriasExcluidas(List<UnidadeGestoraAuditoria> unidadeGestoraAuditoriasExcluidas) {
		this.unidadeGestoraAuditoriasExcluidas = unidadeGestoraAuditoriasExcluidas;
	}

	public String getAnoAuditoria() {
		return anoAuditoria;
	}

	public void setAnoAuditoria(String anoAuditoria) {
		this.anoAuditoria = anoAuditoria;
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
		Auditoria other = (Auditoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Auditoria [id=" + id + "]";
	}

	
	
}