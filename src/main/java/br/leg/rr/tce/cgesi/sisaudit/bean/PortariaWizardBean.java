package br.leg.rr.tce.cgesi.sisaudit.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FlowEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

//import com.sun.enterprise.universal.StringUtils;
import br.leg.rr.tce.cgesi.sisaudit.comum.entity.UnidadeGestora;
import br.leg.rr.tce.cgesi.sisaudit.comum.util.Util;
import br.leg.rr.tce.cgesi.sisaudit.ejb.AuditoriaEjb;
import br.leg.rr.tce.cgesi.sisaudit.ejb.EquipeFiscalizacaoEjb;
import br.leg.rr.tce.cgesi.sisaudit.ejb.PortariaEjb;
import br.leg.rr.tce.cgesi.sisaudit.ejb.PortariasAndamentoEjb;
import br.leg.rr.tce.cgesi.sisaudit.ejb.ServidorEjb;
import br.leg.rr.tce.cgesi.sisaudit.ejb.StatusPortariaEjb;
import br.leg.rr.tce.cgesi.sisaudit.ejb.UnidadeGestoraPortariaEjb;
import br.leg.rr.tce.cgesi.sisaudit.entity.Auditoria;
import br.leg.rr.tce.cgesi.sisaudit.entity.EquipeFiscalizacao;
import br.leg.rr.tce.cgesi.sisaudit.entity.Portaria;
import br.leg.rr.tce.cgesi.sisaudit.entity.PortariasAndamento;
import br.leg.rr.tce.cgesi.sisaudit.entity.Servidor;
import br.leg.rr.tce.cgesi.sisaudit.entity.StatusPortaria;
import br.leg.rr.tce.cgesi.sisaudit.entity.TipoAuditor;
import br.leg.rr.tce.cgesi.sisaudit.entity.TipoFiscalizacao;
import br.leg.rr.tce.cgesi.sisaudit.entity.UnidadeFiscalizadora;
import br.leg.rr.tce.cgesi.sisaudit.entity.UnidadeGestoraAuditoria;
import br.leg.rr.tce.cgesi.sisaudit.entity.UnidadeGestoraPortaria;
import br.leg.rr.tce.cgesi.sisaudit.seguranca.bean.UsuarioBean;

@Named("portariaWizardBean")
@SessionScoped
@DependsOn("SistemaBean")
public class PortariaWizardBean extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private transient SistemaBean sistemaBean;

    @Inject
    private transient UsuarioBean usuarioBean;

    @Inject
    private Portaria portaria;

    @Inject
    private Auditoria auditoria;

    @Inject
    private EquipeFiscalizacao equipeFiscalizacao;

    @EJB
    private PortariaEjb portariaEjb;

    @EJB
    private AuditoriaEjb auditoriaEjb;

    @EJB
    private UnidadeGestoraPortariaEjb unidadeGestoraPortariaEjb;

    @EJB
    private EquipeFiscalizacaoEjb equipeFiscalizacaoEjb;

    @EJB
    private ServidorEjb servidorEjb;

    @EJB
    private PortariasAndamentoEjb portariasAndamentoEjb;

    @EJB
    private StatusPortariaEjb statusPortariaEjb;

    // Listas
    private List<UnidadeGestora> unidadeGestoraLista = new ArrayList<>();
    private List<UnidadeGestoraPortaria> unidadeGestoraPortariaList = new ArrayList<>();
    private List<UnidadeGestoraPortaria> unidadeGestoraPortariaList2 = new ArrayList<>();

    private List<UnidadeGestora> unidadeGestoraDaAuditoria = new ArrayList<>();
    private List<UnidadeGestora> unidadeGestoraSelecionadas = new ArrayList<>();
    private List<UnidadeGestora> unidadeGestoraDaPortaria = new ArrayList<>();

    private List<Portaria> portariaList = new ArrayList<>();
    
    private List<Servidor> servidorList = new ArrayList<>();
    
    private List<EquipeFiscalizacao> equipeFiscalizacaoList = new ArrayList<>();
    private List<Servidor> servidorAutoridadeList = new ArrayList<>();
    private List<TipoFiscalizacao> tipoFiscalizacaoList = new ArrayList<>();
    private List<UnidadeFiscalizadora> unidadeFiscalizadoraList = new ArrayList<>();
    private List<TipoAuditor> tipoAuditorList = new ArrayList<>();

    private String msgTexto;

    private boolean skip;

    private boolean exibir;

    private transient String possuiAuditoria;

    private transient String nomeEvento;

    public PortariaWizardBean() {
        super();
    }

    public String abrirListaPortaria() throws Exception {
        portaria = new Portaria();
        portariaList = new ArrayList<>();
        portariaList = portariaEjb.findAll();
        
        //System.out.println("teste");
        for (Portaria vPortaria : portariaList) {
            
            List<UnidadeGestoraPortaria> vUGP = new ArrayList<>();
            vUGP=unidadeGestoraPortariaEjb.findIdPortaria(vPortaria.getId());
            vPortaria.setUnidadeGestoraPortarias(vUGP);
            
        }
        //System.out.println("teste");
        
        /*

        Map<Integer, Portaria> mapPortaria = new HashMap<Integer, Portaria>();
        String listaId = "";
        List<UnidadeGestoraPortaria> listaUGP = new ArrayList<UnidadeGestoraPortaria>();
        listaUGP = unidadeGestoraPortariaEjb.findIAll();

        for (Portaria ptemp : portariaList) {
            mapPortaria.put(ptemp.getId(),
                    ptemp);

            if (listaId.length() > 0) {
                listaId = listaId + "," + ptemp.getId();
            } else {
                listaId = ptemp.getId().toString();
            }

        }
        listaUGP = unidadeGestoraPortariaEjb.listaUGDasPortaria(listaId);

        for (UnidadeGestoraPortaria ltemp : listaUGP) {
            mapPortaria.get(ltemp.getPortaria().getId()).
                    getListaUnidadeGestoraDaPortaria().add(ltemp);

            ltemp.setUnidadeGestora(sistemaBean.getUnidadeGestoraMap().get(ltemp.
                    getUnidadeGestora()));
            ltemp.setPortaria(mapPortaria.get(ltemp.getPortaria()));
        }
*/
        return redirect("/sistema/portaria/listaPortarias.xhtml");
    }

    public String editarWizardPortaria(Portaria aux) {
        try {
            portaria = new Portaria();
            portaria = portariaEjb.pegarPortaria(aux.getId());
            servidorAutoridadeList = new ArrayList<>();

            unidadeGestoraDaAuditoria = new ArrayList<>();
            unidadeGestoraSelecionadas = new ArrayList<>();
            unidadeGestoraDaPortaria = new ArrayList<>();

            tipoFiscalizacaoList = new ArrayList<>();
            tipoFiscalizacaoList = sistemaBean.getTipoFiscalizacaoList();

            unidadeFiscalizadoraList = new ArrayList<>();
            unidadeFiscalizadoraList = sistemaBean.getUnidadeFiscalizadoraList();

            if (auditoria.getId() != null) {
                Integer vaudi = auditoria.getId();
                //Auditoria 
                auditoria = new Auditoria();
                auditoria = auditoriaEjb.carregarAuditoria(vaudi);
                portaria.setIdAuditoria(auditoria.getId());
            }

            if (portaria.getIdAuditoria() != null) {
                auditoria = new Auditoria();
                auditoria = auditoriaEjb.carregarAuditoria(aux.getIdAuditoria());

                portaria.setAuditoria(auditoria);
                for (UnidadeGestoraAuditoria x : portaria.getAuditoria().getUnidadeGestoraAuditorias()) {
                    UnidadeGestora unG = new UnidadeGestora();
                    UnidadeGestoraPortaria unGP = new UnidadeGestoraPortaria();
                    unG = x.getUnidadeGestora();
                    unidadeGestoraDaAuditoria.add(unG);
                    unGP.setPortaria(portaria);
                    unGP.setUnidadeGestora(unG);
                    portaria.getListaUnidadeGestoraDaPortaria().add(unGP);
                }
            } else {
                for (UnidadeGestora x : sistemaBean.getUnidadeGestoraList()) {
                    UnidadeGestoraPortaria unGP = new UnidadeGestoraPortaria();
                    unidadeGestoraDaAuditoria.add(x);
                    unGP.setPortaria(portaria);
                    unGP.setUnidadeGestora(x);
                    portaria.getListaUnidadeGestoraDaPortaria().add(unGP);
                }
            }
            for (UnidadeGestoraPortaria x : unidadeGestoraPortariaEjb.findIdPortaria(portaria.getId())) {
                UnidadeGestora unG = new UnidadeGestora();
                unG = sistemaBean.selecionarUnidadeGestora(x.getUnidadeGestora().getId());
                unidadeGestoraSelecionadas.add(unG);
            }

            equipeFiscalizacaoList = new ArrayList<EquipeFiscalizacao>();
            equipeFiscalizacaoList = equipeFiscalizacaoEjb.findIdPortaria(aux.getId());
            servidorList = new ArrayList<Servidor>();
            tipoAuditorList = sistemaBean.getTipoAuditorList();

            for (Servidor stemp : servidorEjb.findAll()) {
                String vtipo = stemp.getAutoridade();
                servidorList.add(stemp);
                if (vtipo.contains("S")) {
                    servidorAutoridadeList.add(stemp);
                }
            }
            //String vNumPort = String.format("%03d",portaria.getNumeroPortaria());
            portaria.setNumeroPortaria(portaria.getNumeroPortaria());

            return redirect("/sistema/portaria/cadastro/frmCadPortariaEtapa1.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
            showFacesMessage(e.getMessage(), 4);
            return null;
        }
    }

    // prepara editar portaria de auditoria
    public void selecionandoUGP() {
        try {
            unidadeGestoraPortariaList = new ArrayList<UnidadeGestoraPortaria>();
            unidadeGestoraPortariaList2 = new ArrayList<UnidadeGestoraPortaria>();

            Map<Integer, UnidadeGestora> mapUGS = new HashMap<Integer, UnidadeGestora>();
            Map<Integer, UnidadeGestora> mapUGE = new HashMap<Integer, UnidadeGestora>();
            Map<Integer, UnidadeGestora> mapUGP = new HashMap<Integer, UnidadeGestora>();
            // unidades selecionadas
            for (UnidadeGestora x : unidadeGestoraSelecionadas) {
                mapUGS.put(x.getId(), x);
                UnidadeGestoraPortaria ugp = new UnidadeGestoraPortaria();
                ugp.setPortaria(portaria);
                ugp.setUnidadeGestora(x);
                unidadeGestoraPortariaList.add(ugp);
            }
            // da auditoria
            if (portaria.getIdAuditoria() != null) {
                for (UnidadeGestoraAuditoria x : portaria.getAuditoria().getUnidadeGestoraAuditorias()) {
                    mapUGE.put(x.getUnidadeGestora().getId(), x.getUnidadeGestora());
                    UnidadeGestoraPortaria ugp = new UnidadeGestoraPortaria();
                    ugp.setPortaria(portaria);
                    ugp.setUnidadeGestora(x.getUnidadeGestora());
                    // portaria.getUnidadeGestoraPortariaExcluidas().add(ugp);
                }
            }
            // daportaria
            for (UnidadeGestoraPortaria x : portaria.getUnidadeGestoraPortarias()) {
                mapUGP.put(x.getUnidadeGestora().getId(), x.getUnidadeGestora());
                UnidadeGestoraPortaria ugp = new UnidadeGestoraPortaria();
                ugp.setId(x.getId());
                ugp.setPortaria(portaria);
                ugp.setUnidadeGestora(x.getUnidadeGestora());
                unidadeGestoraPortariaList2.add(ugp);
                if (mapUGS.containsKey(x.getUnidadeGestora().getId())) {
                    portaria.removeUnidadeGestoraPortaria(ugp);
                }
            }
            portaria.setUnidadeGestoraPortarias(getUnidadeGestoraPortariaList());
            portaria.setUnidadeGestoraPortariaExcluidas(unidadeGestoraPortariaList2);
            portaria.setEquipeFiscalizacaoList(getEquipeFiscalizacaoList());
            this.salvarMinutaPortaria();
            // this.salvar();

        } catch (Exception e) {
            e.printStackTrace();
            showFacesMessage(e.getMessage(), 4);
        }

    }

    public String abrirCadastroPortaria() {
        try {
            Date d = new Date();
            SimpleDateFormat fd = new SimpleDateFormat("yyyy");
            String year = fd.format(d);
            portaria = new Portaria();
            servidorAutoridadeList = new ArrayList<Servidor>();

            unidadeGestoraDaAuditoria = new ArrayList<UnidadeGestora>();
            unidadeGestoraSelecionadas = new ArrayList<UnidadeGestora>();
            unidadeGestoraDaPortaria = new ArrayList<UnidadeGestora>();

            tipoFiscalizacaoList = new ArrayList<TipoFiscalizacao>();
            tipoFiscalizacaoList = sistemaBean.getTipoFiscalizacaoList();

            unidadeFiscalizadoraList = new ArrayList<UnidadeFiscalizadora>();
            unidadeFiscalizadoraList = sistemaBean.getUnidadeFiscalizadoraList();

            if (auditoria.getId() != null) {
                Integer vaudi = auditoria.getId();
                Auditoria auditoria = new Auditoria();
                auditoria = auditoriaEjb.carregarAuditoria(vaudi);
                portaria.setIdAuditoria(auditoria.getId());
            }

            if (portaria.getIdAuditoria() != null) {
                //auditoria = new Auditoria();
                //auditoria = auditoriaEjb.carregarAuditoria(aux.getIdAuditoria());

                portaria.setAuditoria(auditoria);
                for (UnidadeGestoraAuditoria x : portaria.getAuditoria().getUnidadeGestoraAuditorias()) {
                    UnidadeGestora unG = new UnidadeGestora();
                    UnidadeGestoraPortaria unGP = new UnidadeGestoraPortaria();
                    unG = x.getUnidadeGestora();
                    unidadeGestoraDaAuditoria.add(unG);
                    unGP.setPortaria(portaria);
                    unGP.setUnidadeGestora(unG);
                    portaria.getListaUnidadeGestoraDaPortaria().add(unGP);
                }
            } else {
                for (UnidadeGestora x : sistemaBean.getUnidadeGestoraList()) {
                    UnidadeGestoraPortaria unGP = new UnidadeGestoraPortaria();
                    unidadeGestoraDaAuditoria.add(x);
                    unGP.setPortaria(portaria);
                    unGP.setUnidadeGestora(x);
                    portaria.getListaUnidadeGestoraDaPortaria().add(unGP);
                }
            }
            for (UnidadeGestoraPortaria x : unidadeGestoraPortariaEjb.findIdPortaria(portaria.getId())) {
                UnidadeGestora unG = new UnidadeGestora();
                unG = sistemaBean.selecionarUnidadeGestora(x.getUnidadeGestora().getId());
                unidadeGestoraSelecionadas.add(unG);
            }
            /*
			for (UnidadeGestora x : sistemaBean.getUnidadeGestoraList()) {
				UnidadeGestoraPortaria unGP = new UnidadeGestoraPortaria();
				unidadeGestoraDaAuditoria.add(x);
				unGP.setPortaria(portaria);
				unGP.setUnidadeGestora(x);
				portaria.getListaUnidadeGestoraDaPortaria().add(unGP);
			}
			
			for (UnidadeGestoraPortaria x : unidadeGestoraPortariaEjb.findIdPortaria(portaria.getId())) {
				UnidadeGestora unG = new UnidadeGestora();
				unG = sistemaBean.selecionarUnidadeGestora(x.getUnidadeGestora().getId());
				unidadeGestoraSelecionadas.add(unG);
			}
             */
            for (Servidor stemp : servidorEjb.findAll()) {
                String vtipo = stemp.getAutoridade();
                servidorList.add(stemp);
                if (vtipo.contains("S")) {
                    servidorAutoridadeList.add(stemp);
                }
            }

//			portaria.setNumeroPortaria(StringUtils.padLeft(portariaEjb.ultimoNumeroPortaria(year), 3, '0'));
            portaria.setAnoPortaria(year);

            return redirect("/sistema/portaria/cadastro/frmCadPortariaEtapa1.xhtml");
        } catch (Exception e) {
            e.printStackTrace();
            showFacesMessage(e.getMessage(), 4);
            return null;
        }

    }

    public String preparaCriarPortariaDaAuditoria(Auditoria aux) {
        try {
            auditoria = new Auditoria();
            auditoria = auditoriaEjb.carregarAuditoria(aux.getId());

            portariaList = new ArrayList<>();
            portariaList = portariaEjb.findIdAuditoria(aux.getId());

            return redirect("/sistema/portaria/cadastroPortariaAuditoria.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
            showFacesMessage(e.getMessage(), 4);
            return null;
        }
    }

    public void salvarMinutaPortaria() {
        try {
            //portaria.addPortariasAndamento(portariasAndamentoEjb.pegaPortariasAndamentoPeloId(1));
            StatusPortaria stPortaria = new StatusPortaria();
            stPortaria = statusPortariaEjb.pegarStatusPortariaId(1);

            System.out.println(stPortaria);

            PortariasAndamento pAndamento = new PortariasAndamento();

            pAndamento.setStatusDate(Util.hoje());
            pAndamento.setStatusJustificativa(stPortaria.getNome());
            pAndamento.setStatusPortaria(stPortaria);
            pAndamento.setStatusUsr(usuarioBean.getMostraUser());
            portaria.setPortariasAndamentos(new ArrayList<PortariasAndamento>());
            portaria.addPortariasAndamento(pAndamento);

            portariaEjb.salvarMinuta(portaria);
        } catch (Exception e) {
            e.printStackTrace();
            showFacesMessage(e.getMessage(), 4);
        }
    }

    public void addEquipe() {
        try {
            EquipeFiscalizacao eqLista = new EquipeFiscalizacao();
            eqLista.setPortaria(portaria);
            eqLista.setServidor(equipeFiscalizacao.getServidor());
            eqLista.setTipoAuditor(equipeFiscalizacao.getTipoAuditor());
            equipeFiscalizacaoList.add(eqLista);
            equipeFiscalizacaoEjb.salvar(eqLista);

        } catch (Exception e) {
            e.printStackTrace();
            showFacesMessage(e.getMessage(), 4);
        }
    }

    public void remEquipe(EquipeFiscalizacao eq) {
        try {
            equipeFiscalizacaoList.remove(eq);
            equipeFiscalizacaoEjb.remove(eq);
        } catch (Exception e) {
            e.printStackTrace();
            showFacesMessage(e.getMessage(), 4);
        }
    }

    public boolean mostraNaTela() {
        boolean mostra = false;
        if (possuiAuditoria.equals('2')) {
            mostra = true;
        }

        return mostra;
    }

    public String onFlowProcess(FlowEvent event) throws Exception {
        if (skip) {
            skip = false; // reset in case user goes back
            return "confirm";
        } else {
            if (event.getNewStep().equals("periodo")) {
                this.salvarMinutaPortaria();
            }
            if (event.getNewStep().equals("jurisdicionadoGrupo")) {
                this.salvarMinutaPortaria();
            }

            if (event.getNewStep().equals("equipe") && !event.getOldStep().equals("resumo")) {
                this.selecionandoUGP();
            }
            this.setNomeEvento(event.toString());

            return event.getNewStep();
        }
    }

    public void dateChange() {
        if (portaria.getPlanInicio() != null && portaria.getPlanFim() != null) {
            portaria.setPlanDiasUteis(Util.diasEntreDatas(portaria.getPlanInicio(), portaria.getPlanFim()));
        }
        if (portaria.getExecInicio() != null && portaria.getExecFim() != null) {
            portaria.setExecDiasUteis(Util.diasEntreDatas(portaria.getExecInicio(), portaria.getExecFim()));
        }
        if (portaria.getRelaInicio() != null && portaria.getRelaFim() != null) {
            portaria.setRelaDiasUteis(Util.diasEntreDatas(portaria.getRelaInicio(), portaria.getRelaFim()));
        }
    }

    public void dateChangeDias() {

        portaria.setPlanFim(Util.addDiasUteis(portaria.getPlanInicio(), portaria.getPlanDiasUteis()));
        portaria.setExecFim(Util.addDiasUteis(portaria.getExecInicio(), portaria.getExecDiasUteis()));
        portaria.setRelaFim(Util.addDiasUteis(portaria.getRelaInicio(), portaria.getRelaDiasUteis()));

    }

    // pega evento autocomplet remoï¿½ï¿½o de seleï¿½ï¿½o e atualiza listas
    public void unselectUGA(final UnselectEvent event) {
        try {
            final UnidadeGestora tmp = (UnidadeGestora) event.getObject();
            portaria.adincionarNaListaExcluidos(tmp);
            unidadeGestoraLista.add(tmp);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // pega evento autocomplet seleï¿½ï¿½o de objetos e atualiza listas
    public void selectUGA(final SelectEvent event) {
        try {
            final UnidadeGestora tmp = (UnidadeGestora) event.getObject();
            unidadeGestoraLista.remove(tmp);
            portaria.selecionarUGA(tmp);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

    //remove portaria
    public String remover(Portaria aux) {
        try {
            //Integer id = aux.getIdAuditoria();
            portariaEjb.remove(aux);
            showFacesMessage("portaria deletada com sucesso!!!", 2);

            portaria = new Portaria();
            portariaList = new ArrayList<>();
            portariaList = portariaEjb.listaPortaria();
            //auditoria.setPortariaList(portariaEjb.findIdAuditoria(id));
            return redirect("/sistema/portaria/listaPortarias.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
            showFacesMessage(e.getMessage(), 4);
            return null;
        }
    }

    public Portaria getPortaria() {
        return portaria;
    }

    public void setPortaria(Portaria portaria) {
        this.portaria = portaria;
    }

    public List<Portaria> getPortariaList() {
        return portariaList;
    }

    public void setPortariaList(List<Portaria> portariaList) {
        this.portariaList = portariaList;
    }

    public List<Servidor> getServidorList() {
        return servidorList;
    }

    public void setServidorList(List<Servidor> servidorList) {
        this.servidorList = servidorList;
    }

    public List<Servidor> getServidorAutoridadeList() {
        return servidorAutoridadeList;
    }

    public void setServidorAutoridadeList(List<Servidor> servidorAutoridadeList) {
        this.servidorAutoridadeList = servidorAutoridadeList;
    }

    public String getMsgTexto() {
        return msgTexto;
    }

    public void setMsgTexto(String msgTexto) {
        this.msgTexto = msgTexto;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public boolean isExibir() {
        return exibir;
    }

    public void setExibir(boolean exibir) {
        this.exibir = exibir;
    }

    public List<UnidadeGestora> getUnidadeGestoraLista() {
        return unidadeGestoraLista;
    }

    public void setUnidadeGestoraLista(List<UnidadeGestora> unidadeGestoraLista) {
        this.unidadeGestoraLista = unidadeGestoraLista;
    }

    public List<UnidadeGestoraPortaria> getUnidadeGestoraPortariaList() {
        return unidadeGestoraPortariaList;
    }

    public void setUnidadeGestoraPortariaList(List<UnidadeGestoraPortaria> unidadeGestoraPortariaList) {
        this.unidadeGestoraPortariaList = unidadeGestoraPortariaList;
    }

    public List<UnidadeGestoraPortaria> getUnidadeGestoraPortariaList2() {
        return unidadeGestoraPortariaList2;
    }

    public void setUnidadeGestoraPortariaList2(List<UnidadeGestoraPortaria> unidadeGestoraPortariaList2) {
        this.unidadeGestoraPortariaList2 = unidadeGestoraPortariaList2;
    }

    public List<UnidadeGestora> getUnidadeGestoraDaAuditoria() {
        return unidadeGestoraDaAuditoria;
    }

    public void setUnidadeGestoraDaAuditoria(List<UnidadeGestora> unidadeGestoraDaAuditoria) {
        this.unidadeGestoraDaAuditoria = unidadeGestoraDaAuditoria;
    }

    public List<UnidadeGestora> getUnidadeGestoraSelecionadas() {
        return unidadeGestoraSelecionadas;
    }

    public void setUnidadeGestoraSelecionadas(List<UnidadeGestora> unidadeGestoraSelecionadas) {
        this.unidadeGestoraSelecionadas = unidadeGestoraSelecionadas;
    }

    public List<UnidadeGestora> getUnidadeGestoraDaPortaria() {
        return unidadeGestoraDaPortaria;
    }

    public void setUnidadeGestoraDaPortaria(List<UnidadeGestora> unidadeGestoraDaPortaria) {
        this.unidadeGestoraDaPortaria = unidadeGestoraDaPortaria;
    }

    public Auditoria getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    public List<EquipeFiscalizacao> getEquipeFiscalizacaoList() {
        return equipeFiscalizacaoList;
    }

    public void setEquipeFiscalizacaoList(List<EquipeFiscalizacao> equipeFiscalizacaoList) {
        this.equipeFiscalizacaoList = equipeFiscalizacaoList;
    }

    public List<TipoFiscalizacao> getTipoFiscalizacaoList() {
        return tipoFiscalizacaoList;
    }

    public void setTipoFiscalizacaoList(List<TipoFiscalizacao> tipoFiscalizacaoList) {
        this.tipoFiscalizacaoList = tipoFiscalizacaoList;
    }

    public List<UnidadeFiscalizadora> getUnidadeFiscalizadoraList() {
        return unidadeFiscalizadoraList;
    }

    public void setUnidadeFiscalizadoraList(List<UnidadeFiscalizadora> unidadeFiscalizadoraList) {
        this.unidadeFiscalizadoraList = unidadeFiscalizadoraList;
    }

    public EquipeFiscalizacao getEquipeFiscalizacao() {
        return equipeFiscalizacao;
    }

    public void setEquipeFiscalizacao(EquipeFiscalizacao equipeFiscalizacao) {
        this.equipeFiscalizacao = equipeFiscalizacao;
    }

    public List<TipoAuditor> getTipoAuditorList() {
        return tipoAuditorList;
    }

    public void setTipoAuditorList(List<TipoAuditor> tipoAuditorList) {
        this.tipoAuditorList = tipoAuditorList;
    }

    public String getPossuiAuditoria() {
        return possuiAuditoria;
    }

    public void setPossuiAuditoria(String possuiAuditoria) {
        this.possuiAuditoria = possuiAuditoria;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

}
