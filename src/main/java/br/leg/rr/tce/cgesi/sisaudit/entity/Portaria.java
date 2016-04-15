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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import br.leg.rr.tce.cgesi.sisaudit.comum.entity.UnidadeGestora;
import javax.persistence.OneToMany;

/**
 * The persistent class for the portaria database table.
 *
 */
@Dependent
@Entity
@Table(name = "portaria", schema = "scsisaudit")
@NamedQuery(name = "Portaria.findAll", query = "SELECT p FROM Portaria p")
public class Portaria implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ano_portaria_revogada")
    private String anoPortariaRevogada;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_assinatura")
    private Date dataAssinatura;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_publica_doe")
    private Date dataPublicaDoe;

    private String deliberacao;

    @Column(name = "exec_dias_uteis")
    private Integer execDiasUteis;

    @Temporal(TemporalType.DATE)
    @Column(name = "exec_fim")
    private Date execFim;

    @Temporal(TemporalType.DATE)
    @Column(name = "exec_inicio")
    private Date execInicio;

    @Column(name = "id_auditoria")
    private Integer idAuditoria;

    @Transient
    private Auditoria auditoria;

    /*
	 * @Column(name="id_servidor") private Integer idServidor;
	 * 
	 * @Column(name="id_tipo_fiscalizacao") private Integer idTipoFiscalizacao;
     */
    
    @Column(name = "numero_portaria_revogada")
    private String numeroPortariaRevogada;

    @Column(name = "numero_publica_doe")
    private String numeroPublicaDoe;

    private String objetivo;

    @Column(name = "plan_dias_uteis")
    private Integer planDiasUteis;

    @Temporal(TemporalType.DATE)
    @Column(name = "plan_fim")
    private Date planFim;

    @Temporal(TemporalType.DATE)
    @Column(name = "plan_inicio")
    private Date planInicio;

    @Column(name = "processo_ano")
    private String processoAno;

    @Column(name = "processo_numero")
    private String processoNumero;

    @Column(name = "rela_dias_uteis")
    private Integer relaDiasUteis;

    @Temporal(TemporalType.DATE)
    @Column(name = "rela_fim")
    private Date relaFim;

    @Temporal(TemporalType.DATE)
    @Column(name = "rela_inicio")
    private Date relaInicio;

    @Column(name = "numero_portaria")
    private String numeroPortaria;

    @Column(name = "ano_portaria")
    private String anoPortaria;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_assinatua_portaria")
    private Date dataAssinatuaPortaria;

    // bi-directional many-to-one association to TipoFiscalizacao
    @ManyToOne
    @JoinColumn(name = "id_tipo_fiscalizacao")
    private TipoFiscalizacao tipoFiscalizacao;

    // bi-directional many-to-one association to Servidor
    @ManyToOne
    @JoinColumn(name = "id_servidor")
    private Servidor servidor;

    // bi-directional many-to-one association to UnidadeFiscalizadora
    @ManyToOne
    @JoinColumn(name = "id_unidade_fiscalizadora")
    private UnidadeFiscalizadora unidadeFiscalizadora;

    // bi-directional many-to-one association to PortariasAndamento
    //@OneToMany(mappedBy = "portaria")
    @Transient
    private List<PortariasAndamento> portariasAndamentos;

    //bi-directional many-to-one association to UnidadeGestoraPortaria
    //@Transient
    @OneToMany(mappedBy = "portaria")
    private List<UnidadeGestoraPortaria> unidadeGestoraPortarias = new ArrayList<>();

    @Transient
    private List<UnidadeGestoraPortaria> listaUnidadeGestoraDaPortaria = new ArrayList<>();

    // @Transient
    // private List<UnidadeGestoraPortaria> unidadeGestoraPortariaList = new
    // ArrayList<UnidadeGestoraPortaria>();
    @Transient
    private List<Auditoria> auditoriaList = new ArrayList<>();

    @Transient
    private List<UnidadeGestoraPortaria> unidadeGestoraPortariaExcluidas = new ArrayList<>();
    @Transient
    private List<UnidadeGestora> listaUnidadeGestoraTmp = new ArrayList<>();

    @Transient
    private List<EquipeFiscalizacao> equipeFiscalizacaoList = new ArrayList<>();

    @Transient
    private boolean mostraCampo;

    public Portaria() {
    }

    public Integer getId() {
        return this.id;
    }

    public void criarListaUGAuditoria() {
        try {
            unidadeGestoraPortarias = new ArrayList<UnidadeGestoraPortaria>();
            Map<Integer, UnidadeGestora> mapUGA = new HashMap<Integer, UnidadeGestora>();
            List<UnidadeGestoraPortaria> listUGAExcluir = new ArrayList<UnidadeGestoraPortaria>();
            // Map<Integer, UnidadeGestora> mapUGAExcluir = new HashMap<Integer,
            // UnidadeGestora>();
            Map<Integer, UnidadeGestora> mapUGSalvar = new HashMap<Integer, UnidadeGestora>();

            for (UnidadeGestoraPortaria tmp1 : unidadeGestoraPortarias) {
                mapUGA.put(tmp1.getUnidadeGestora().getId(), tmp1.getUnidadeGestora());
            }
            for (UnidadeGestora tmp1 : listaUnidadeGestoraTmp) {
                if (mapUGA.containsKey(tmp1.getId())) {
                    break;
                }
                mapUGSalvar.put(tmp1.getId(), tmp1);
                UnidadeGestoraPortaria ugp = new UnidadeGestoraPortaria();
                ugp.setPortaria(this);
                ugp.setUnidadeGestora(tmp1);
                unidadeGestoraPortarias.add(ugp);
            }
            for (UnidadeGestoraPortaria tmp1 : unidadeGestoraPortariaExcluidas) {
                if (mapUGSalvar.containsKey(tmp1.getId())) {
                    break;
                }
                listUGAExcluir.add(tmp1);

            }

            unidadeGestoraPortariaExcluidas = new ArrayList<UnidadeGestoraPortaria>();
            unidadeGestoraPortariaExcluidas = listUGAExcluir;

        } catch (Exception e) {
            throw e;
        }
    }

    public void selecionarUGA(UnidadeGestora entity) {
        boolean sc = true;
        for (UnidadeGestoraPortaria tmp : unidadeGestoraPortarias) {
            if (tmp.getUnidadeGestora().getId().equals(entity.getId())) {
                sc = false;
                break;
            }
        }
        for (UnidadeGestoraPortaria tmp : unidadeGestoraPortariaExcluidas) {
            if (tmp.getUnidadeGestora().getId().equals(entity.getId())) {
                unidadeGestoraPortariaExcluidas.remove(tmp);
            }
        }
        if (sc) {
            UnidadeGestoraPortaria ugp = new UnidadeGestoraPortaria();
            ugp.setPortaria(this);
            ugp.setUnidadeGestora(entity);
            unidadeGestoraPortarias.add(ugp);
        }

    }

    public void adincionarNaListaExcluidos(UnidadeGestora entity) {
        for (UnidadeGestoraPortaria tmp : unidadeGestoraPortarias) {
            if (tmp.getUnidadeGestora().getId().equals(entity.getId())) {
                unidadeGestoraPortariaExcluidas.add(tmp);
                break;
            }
        }
    }

    // adicionando na lista excluidas e removendo da lista temporari que sera
    // gravada
    public void removerUGA(UnidadeGestora aux) {
        for (UnidadeGestoraPortaria tmp1 : unidadeGestoraPortarias) {
            if (tmp1.getUnidadeGestora().getId() == aux.getId()) {
                unidadeGestoraPortariaExcluidas.add(tmp1);
                break;
            }
        }
    }

    public String getListaSiglaUnidadeGestoraDaPortaria() {
        String temp = "";
        Integer nId = this.getId();
        Integer nItem, nX = 0;

        //unidadeGestoraPortarias = new ArrayList<UnidadeGestoraPortaria>();
        
  
        //
        // if(!listaUnidadeGestoraDaPortaria.isEmpty()){
        nItem = this.unidadeGestoraPortarias.size();

        //for (UnidadeGestoraPortaria ptemp : this.unidadeGestoraPortarias) {
        for (UnidadeGestoraPortaria ptemp : this.unidadeGestoraPortarias) {            
            nX++;            
            UnidadeGestora vUnidGestoda = new UnidadeGestora();
            
            if (temp.length() > 0) {                
                if (nItem == nX) {
                    temp = temp + " e " + ptemp.getUnidadeGestora().getSigla();
                } else {
                    temp = temp + ", " + ptemp.getUnidadeGestora().getSigla();
                }
            } else {
                temp = ptemp.getUnidadeGestora().getSigla();
            }
            setMostraCampo(true);
        }
        // }else{
        // setMostraCampo(false);
        // temp="";
        // }

        return temp;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnoPortariaRevogada() {
        return this.anoPortariaRevogada;
    }

    public void setAnoPortariaRevogada(String anoPortariaRevogada) {
        this.anoPortariaRevogada = anoPortariaRevogada;
    }

    public Date getDataAssinatura() {
        return this.dataAssinatura;
    }

    public void setDataAssinatura(Date dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

    public Date getDataPublicaDoe() {
        return this.dataPublicaDoe;
    }

    public void setDataPublicaDoe(Date dataPublicaDoe) {
        this.dataPublicaDoe = dataPublicaDoe;
    }

    public String getDeliberacao() {
        return this.deliberacao;
    }

    public void setDeliberacao(String deliberacao) {
        this.deliberacao = deliberacao;
    }

    public Integer getExecDiasUteis() {
        return this.execDiasUteis;
    }

    public void setExecDiasUteis(Integer execDiasUteis) {
        this.execDiasUteis = execDiasUteis;
    }

    public Date getExecFim() {
        return this.execFim;
    }

    public void setExecFim(Date execFim) {
        this.execFim = execFim;
    }

    public Date getExecInicio() {
        return this.execInicio;
    }

    public void setExecInicio(Date execInicio) {
        this.execInicio = execInicio;
    }

    public Integer getIdAuditoria() {
        return this.idAuditoria;
    }

    public void setIdAuditoria(Integer idAuditoria) {
        this.idAuditoria = idAuditoria;
    }

    /*
	 * public Integer getIdServidor() { return this.idServidor; }
	 * 
	 * public void setIdServidor(Integer idServidor) { this.idServidor =
	 * idServidor; }
	 * 
	 * public Integer getIdTipoFiscalizacao() { return this.idTipoFiscalizacao;
	 * }
	 * 
	 * public void setIdTipoFiscalizacao(Integer idTipoFiscalizacao) {
	 * this.idTipoFiscalizacao = idTipoFiscalizacao; }
     */
    public String getNumeroPortariaRevogada() {
        return this.numeroPortariaRevogada;
    }

    public void setNumeroPortariaRevogada(String numeroPortariaRevogada) {
        this.numeroPortariaRevogada = numeroPortariaRevogada;
    }

    public String getNumeroPublicaDoe() {
        return this.numeroPublicaDoe;
    }

    public void setNumeroPublicaDoe(String numeroPublicaDoe) {
        this.numeroPublicaDoe = numeroPublicaDoe;
    }

    public String getObjetivo() {
        return this.objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Integer getPlanDiasUteis() {
        return this.planDiasUteis;
    }

    public void setPlanDiasUteis(Integer planDiasUteis) {
        this.planDiasUteis = planDiasUteis;
    }

    public Date getPlanFim() {
        return this.planFim;
    }

    public void setPlanFim(Date planFim) {
        this.planFim = planFim;
    }

    public Date getPlanInicio() {
        return this.planInicio;
    }

    public void setPlanInicio(Date planInicio) {
        this.planInicio = planInicio;
    }

    public String getProcessoAno() {
        return this.processoAno;
    }

    public void setProcessoAno(String processoAno) {
        this.processoAno = processoAno;
    }

    public String getProcessoNumero() {
        return this.processoNumero;
    }

    public void setProcessoNumero(String processoNumero) {
        this.processoNumero = processoNumero;
    }

    public Integer getRelaDiasUteis() {
        return this.relaDiasUteis;
    }

    public void setRelaDiasUteis(Integer relaDiasUteis) {
        this.relaDiasUteis = relaDiasUteis;
    }

    public Date getRelaFim() {
        return this.relaFim;
    }

    public void setRelaFim(Date relaFim) {
        this.relaFim = relaFim;
    }

    public Date getRelaInicio() {
        return this.relaInicio;
    }

    public void setRelaInicio(Date relaInicio) {
        this.relaInicio = relaInicio;
    }

    public List<PortariasAndamento> getPortariasAndamentos() {
        return this.portariasAndamentos;
    }

    public void setPortariasAndamentos(List<PortariasAndamento> portariasAndamentos) {
        this.portariasAndamentos = portariasAndamentos;
    }

    public TipoFiscalizacao getTipoFiscalizacao() {
        return this.tipoFiscalizacao;
    }

    public void setTipoFiscalizacao(TipoFiscalizacao tipoFiscalizacao) {
        this.tipoFiscalizacao = tipoFiscalizacao;
    }

    public Servidor getServidor() {
        return this.servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public String getNumeroPortaria() {
        return numeroPortaria;
    }

    public void setNumeroPortaria(String numeroPortaria) {
        this.numeroPortaria = numeroPortaria;
    }

    public String getAnoPortaria() {
        return anoPortaria;
    }

    public void setAnoPortaria(String anoPortaria) {
        this.anoPortaria = anoPortaria;
    }

    public Date getDataAssinatuaPortaria() {
        return dataAssinatuaPortaria;
    }

    public void setDataAssinatuaPortaria(Date dataAssinatuaPortaria) {
        this.dataAssinatuaPortaria = dataAssinatuaPortaria;
    }

    public Auditoria getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    public UnidadeFiscalizadora getUnidadeFiscalizadora() {
        return unidadeFiscalizadora;
    }

    public void setUnidadeFiscalizadora(UnidadeFiscalizadora unidadeFiscalizadora) {
        this.unidadeFiscalizadora = unidadeFiscalizadora;
    }

    public List<UnidadeGestoraPortaria> getListaUnidadeGestoraDaPortaria() {
        return listaUnidadeGestoraDaPortaria;
    }

    public void setListaUnidadeGestoraDaPortaria(List<UnidadeGestoraPortaria> listaUnidadeGestoraDaPortaria) {
        this.listaUnidadeGestoraDaPortaria = listaUnidadeGestoraDaPortaria;
    }

    public List<UnidadeGestoraPortaria> getUnidadeGestoraPortariaExcluidas() {
        return unidadeGestoraPortariaExcluidas;
    }

    public void setUnidadeGestoraPortariaExcluidas(List<UnidadeGestoraPortaria> unidadeGestoraPortariaExcluidas) {
        this.unidadeGestoraPortariaExcluidas = unidadeGestoraPortariaExcluidas;
    }

    public List<UnidadeGestora> getListaUnidadeGestoraTmp() {
        return listaUnidadeGestoraTmp;
    }

    public void setListaUnidadeGestoraTmp(List<UnidadeGestora> listaUnidadeGestoraTmp) {
        this.listaUnidadeGestoraTmp = listaUnidadeGestoraTmp;
    }

    /*
	 * public List<UnidadeGestoraPortaria> getUnidadeGestoraPortariaList() {
	 * return unidadeGestoraPortariaList; }
	 * 
	 * public void setUnidadeGestoraPortariaList(List<UnidadeGestoraPortaria>
	 * unidadeGestoraPortariaList) { this.unidadeGestoraPortariaList =
	 * unidadeGestoraPortariaList; }
     */
    public List<Auditoria> getAuditoriaList() {
        return auditoriaList;
    }

    public void setAuditoriaList(List<Auditoria> auditoriaList) {
        this.auditoriaList = auditoriaList;
    }

    public PortariasAndamento addPortariasAndamento(PortariasAndamento portariasAndamento) {
        getPortariasAndamentos().add(portariasAndamento);
        portariasAndamento.setPortaria(this);

        return portariasAndamento;
    }

    public PortariasAndamento removePortariasAndamento(PortariasAndamento portariasAndamento) {
        getPortariasAndamentos().remove(portariasAndamento);
        portariasAndamento.setPortaria(null);

        return portariasAndamento;
    }

    public List<UnidadeGestoraPortaria> getUnidadeGestoraPortarias() {
        return this.unidadeGestoraPortarias;
    }

    public void setUnidadeGestoraPortarias(List<UnidadeGestoraPortaria> unidadeGestoraPortarias) {
        this.unidadeGestoraPortarias = unidadeGestoraPortarias;
    }

    public List<EquipeFiscalizacao> getEquipeFiscalizacaoList() {
        return equipeFiscalizacaoList;
    }

    public void setEquipeFiscalizacaoList(List<EquipeFiscalizacao> equipeFiscalizacaoList) {
        this.equipeFiscalizacaoList = equipeFiscalizacaoList;
    }

    public UnidadeGestoraPortaria addUnidadeGestoraPortaria(UnidadeGestoraPortaria unidadeGestoraPortaria) {
        getUnidadeGestoraPortarias().add(unidadeGestoraPortaria);
        unidadeGestoraPortaria.setPortaria(this);

        return unidadeGestoraPortaria;
    }

    public UnidadeGestoraPortaria removeUnidadeGestoraPortaria(UnidadeGestoraPortaria unidadeGestoraPortaria) {
        getUnidadeGestoraPortarias().remove(unidadeGestoraPortaria);
        unidadeGestoraPortaria.setPortaria(null);

        return unidadeGestoraPortaria;
    }

    public boolean isMostraCampo() {
        return mostraCampo;
    }

    public void setMostraCampo(boolean mostraCampo) {
        this.mostraCampo = mostraCampo;
    }

}
