package br.leg.rr.tce.cgesi.sisaudit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.leg.rr.tce.cgesi.sisaudit.ejb.UnidadeGestoraAuditoriaEjb;
import br.leg.rr.tce.cgesi.sisaudit.entity.UnidadeGestoraAuditoria;

@Named("unidadeGestoraAuditoriaBean")
@SessionScoped
public class UnidadeGestoraAuditoriaBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;
	//@ManagedProperty(value="#{projetoBean}")

	@Inject
	private UnidadeGestoraAuditoria unidadeGestoraAuditoria;
	
	@Inject
	private transient SistemaBean sistemaBean;
	
	@EJB
	private UnidadeGestoraAuditoriaEjb unidadeGestoraAuditoriaEjb;
	
	private List<UnidadeGestoraAuditoria> items = new ArrayList<UnidadeGestoraAuditoria>();
	
	
	
	
	public UnidadeGestoraAuditoriaBean() {
		super();
	}
    	
	public List<UnidadeGestoraAuditoria> getItems() {
		return items;
	}
	
	public String abrirListaAuditoria() throws Exception{
		items = new ArrayList<UnidadeGestoraAuditoria>();
        items = unidadeGestoraAuditoriaEjb.findAll();
		return redirect("/sistema/auditoria/listaAuditorias.xhtml");
	}

	public String abrirCadastroAuditoria(){
		unidadeGestoraAuditoria = new UnidadeGestoraAuditoria();
		return redirect("/sistema/auditoria/cadastroAuditorias.xhtml");
	}

	public UnidadeGestoraAuditoria getUnidadeGestoraAuditoria() {
		return unidadeGestoraAuditoria;
	}

	public void setUnidadeGestoraAuditoria(UnidadeGestoraAuditoria unidadeGestoraAuditoria) {
		this.unidadeGestoraAuditoria = unidadeGestoraAuditoria;
	}

	public UnidadeGestoraAuditoriaEjb getUnidadeGestoraAuditoriaEjb() {
		return unidadeGestoraAuditoriaEjb;
	}

	public void setUnidadeGestoraAuditoriaEjb(UnidadeGestoraAuditoriaEjb unidadeGestoraAuditoriaEjb) {
		this.unidadeGestoraAuditoriaEjb = unidadeGestoraAuditoriaEjb;
	}

	public void setItems(List<UnidadeGestoraAuditoria> items) {
		this.items = items;
	}
	
	public String prepareEdit(UnidadeGestoraAuditoria aux) {
        unidadeGestoraAuditoria = aux;
        return redirect("/sistema/auditoria/cadastroAuditorias.xhtml");
    }

	public void salvar(){
		try {
			unidadeGestoraAuditoriaEjb.salvar(unidadeGestoraAuditoria);
			showFacesMessage("salvo com sucesso!!!", 2);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			showFacesMessage(e.getMessage(), 4);
		}
	}
	
	
	/*
	public void salvar(){
		try {
			unidadeGestoraProjetoEjb.salvar(unidadeGestoraProjeto);
			showFacesMessage("salvo com sucesso!!!", 2);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			showFacesMessage(e.getMessage(), 4);
		}
	}
	*/

}
