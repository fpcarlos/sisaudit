package br.leg.rr.tce.cgesi.sisaudit.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

import br.leg.rr.tce.cgesi.sisaudit.comum.entity.UnidadeGestora;
import br.leg.rr.tce.cgesi.sisaudit.comum.util.Util;
import br.leg.rr.tce.cgesi.sisaudit.ejb.AuditoriaEjb;
import br.leg.rr.tce.cgesi.sisaudit.ejb.CriteriosSelecaoEjb;
import br.leg.rr.tce.cgesi.sisaudit.entity.Auditoria;
import br.leg.rr.tce.cgesi.sisaudit.entity.UnidadeGestoraAuditoria;

@Named("auditoriaBean")
@SessionScoped
public class AuditoriaBean extends AbstractBean implements Serializable {

    private static final long serialVersionUID = 1L;
    //@ManagedProperty(value="#{AuditoriaBean}")

    @Inject
    private Auditoria auditoria;

    @Inject
    private transient SistemaBean sistemaBean;

    @EJB
    private AuditoriaEjb auditoriaEjb;

    @EJB
    private CriteriosSelecaoEjb criteriosSelecaoEjb;

    private List<Auditoria> items = new ArrayList<Auditoria>();

    private List<UnidadeGestora> unidadeGestoraLista = new ArrayList<UnidadeGestora>();

    public AuditoriaBean() {
        super();
    }

    public List<Auditoria> getItems() {
        return items;
    }

    public String abrirListaAuditoria() throws Exception {
        items = new ArrayList<Auditoria>();
        items = auditoriaEjb.findAll();
        return redirect("/sistema/auditoria/listaAuditorias.xhtml");
    }

    public List<UnidadeGestora> completeUG(String query) {
        List<UnidadeGestora> ugFiltrada = new ArrayList<UnidadeGestora>();
        for (int i = 0; i < unidadeGestoraLista.size(); i++) {
            UnidadeGestora skin = unidadeGestoraLista.get(i);
            if (skin.getNomeSilga().toLowerCase().startsWith(query)) {
                ugFiltrada.add(skin);
            }
        }
        return ugFiltrada;
    }

    public String abrirCadastroAuditoria() throws Exception {
        try {
            auditoria = new Auditoria();
            Date d = new Date();
            SimpleDateFormat fd = new SimpleDateFormat("yyyy");
            String year = fd.format(d);
            //int year = cal.get(Calendar.YEAR);

            auditoria.setAnoAuditoria(year);
            auditoria.setCriteriosSelecao(criteriosSelecaoEjb.carregarCriteriosSelecao(7));
            unidadeGestoraLista = new ArrayList<>();
            unidadeGestoraLista.addAll(sistemaBean.getUnidadeGestoraList());

            //String teste = sistemaBean.pegaContextoAplicacao();
            
            return redirect("/sistema/auditoria/cadastroAuditorias.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
            showFacesMessage(e.getMessage(), 4);
            return null;
        }
    }

    public Auditoria getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(Auditoria auditoria) {
        this.auditoria = auditoria;
    }

    public void setItems(List<Auditoria> items) {
        this.items = items;
    }

    //pega evento autocomplet remoção de seleção e atualiza listas
    public void unselectUGA(final UnselectEvent event) {
        try {
            final UnidadeGestora tmp = (UnidadeGestora) event.getObject();
            auditoria.adincionarNaListaExcluidos(tmp);
            unidadeGestoraLista.add(tmp);
        } catch (Exception e) {
            e.printStackTrace();
            showFacesMessage(e.getMessage(), 4);
        }
    }

    //pega evento autocomplet seleção de objetos e atualiza listas
    public void selectUGA(final SelectEvent event) {
        try {
            final UnidadeGestora tmp = (UnidadeGestora) event.getObject();
            unidadeGestoraLista.remove(tmp);
            auditoria.selecionarUGA(tmp);
        } catch (Exception e) {
            e.printStackTrace();
            showFacesMessage(e.getMessage(), 4);
        }

    }

    public String prepareEdit(Auditoria aux) {
        try {
            auditoria = new Auditoria();
            auditoria = auditoriaEjb.carregarAuditoria(aux.getId());

            unidadeGestoraLista = new ArrayList<UnidadeGestora>();
            unidadeGestoraLista.addAll(sistemaBean.getUnidadeGestoraList());
            auditoria.setListaUnidadeGestoraTmp(new ArrayList<UnidadeGestora>());
            //preencher lista temp de unidade gestora e eleiminar os duplicados da lista unidadeGestoraLista
            for (UnidadeGestoraAuditoria tmp : auditoria.getUnidadeGestoraAuditorias()) {
                auditoria.getListaUnidadeGestoraTmp().add(sistemaBean.getUnidadeGestoraMap().get(tmp.getUnidadeGestora().getId()));
                for (UnidadeGestora tmp2 : unidadeGestoraLista) {
                    //System.out.println(tmp2.getId());
                    if (tmp2.getId() == tmp.getUnidadeGestora().getId()) {
                        unidadeGestoraLista.remove(tmp2);
                        break;
                    }
                }
                //System.out.println("f:" + tmp.getId());
            }
            return redirect("/sistema/auditoria/cadastroAuditorias.xhtml");

        } catch (Exception e) {
            e.printStackTrace();
            showFacesMessage(e.getMessage(), 4);
            return null;
        }

    }

    public void salvar() throws Exception {
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();

            auditoriaEjb.salvarBloco(auditoria);
            showFacesMessage("salvo com sucesso!!!", 2);
            items = new ArrayList<Auditoria>();
            items = auditoriaEjb.findAll();
            ec.redirect("listaAuditorias.xhtml");
            //return redirect("sisaudit/sistema/auditoria/listaAuditorias.xhtml?faces-redirect=true");
            //return redirect("/sistema/auditoria/listaAuditorias.xhtml"); 

        } catch (Exception e) {

            e.printStackTrace();
            showFacesMessage(e.getMessage(), 4);
            //return null;

        }
    }

    public void dateChange() {
        // System.out.println("cheguei");

        if (auditoria.getPlanInicioPrev() != null && auditoria.getPlanFimPrev() != null) {
            auditoria.setPlanDiasUteisPrev(Util.diasEntreDatas(auditoria.getPlanInicioPrev(), auditoria.getPlanFimPrev()));
        }
        if (auditoria.getPlanInicioReal() != null && auditoria.getPlanFimReal() != null) {
            auditoria.setPlanDiasUteisReal(Util.diasEntreDatas(auditoria.getPlanInicioReal(), auditoria.getPlanFimReal()));
        }
        if (auditoria.getExecInicioPrev() != null && auditoria.getExecFimPrev() != null) {
            auditoria.setExecDiasUteisPrev(Util.diasEntreDatas(auditoria.getExecInicioPrev(), auditoria.getExecFimPrev()));
        }
        if (auditoria.getExecInicioReal() != null && auditoria.getExecFimReal() != null) {
            auditoria.setExecDiasUteisReal(Util.diasEntreDatas(auditoria.getExecInicioReal(), auditoria.getExecFimReal()));
        }
        if (auditoria.getRelaInicioPrev() != null && auditoria.getRelaFimPrev() != null) {
            auditoria.setRelaDiasUteisPrev(Util.diasEntreDatas(auditoria.getRelaInicioPrev(), auditoria.getRelaFimPrev()));
        }
        if (auditoria.getRelaInicioReal() != null && auditoria.getRelaFimReal() != null) {
            auditoria.setRelaDiasUteisReal(Util.diasEntreDatas(auditoria.getRelaInicioReal(), auditoria.getRelaFimReal()));
        }

    }

}
