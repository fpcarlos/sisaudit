package br.leg.rr.tce.cgesi.sisaudit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.leg.rr.tce.cgesi.sisaudit.ejb.AuditoriaEjb;
import br.leg.rr.tce.cgesi.sisaudit.ejb.EquipeFiscalizacaoEjb;
import br.leg.rr.tce.cgesi.sisaudit.entity.Auditoria;
import br.leg.rr.tce.cgesi.sisaudit.entity.EquipeFiscalizacao;


@Named("equipeFiscalizacaoBean")
@SessionScoped
public class EquipeFiscalizacaoBean extends AbstractBean implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EquipeFiscalizacao equipeFiscalizacao;
	
	@Inject
	private Auditoria auditoria;
	
	@Inject
	private transient SistemaBean sistemaBean;
	
	@EJB
	private EquipeFiscalizacaoEjb equipeFiscalizacaoEjb;
	
	@EJB
	private AuditoriaEjb auditoriaEjb;
	
	private List<Auditoria> listaAuditoria = new ArrayList<Auditoria>();

	private List<EquipeFiscalizacao> listaEquipeFiscalizacao = new ArrayList<EquipeFiscalizacao>();

	
	public EquipeFiscalizacaoBean() {
		super();
	}


	public String abreCadastroEquipeFiscalizacao(Auditoria aux){
		try {
			auditoria = new Auditoria();
			auditoria = auditoriaEjb.carregarAuditoria(aux.getId());
			equipeFiscalizacao = new EquipeFiscalizacao();
			
			return redirect("/sistema/auditoria/cadastroEquipes.xhtml"); 
			
		} catch (Exception e) {
			e.printStackTrace();
			showFacesMessage(e.getMessage(), 4);
			return null;
		}
	}
	
	
	public void salvar(){
		try {
			equipeFiscalizacaoEjb.salvar(equipeFiscalizacao);
			showFacesMessage("salvo com sucesso!!!", 2);
			
		} catch (Exception e) {
			e.printStackTrace();
			showFacesMessage(e.getMessage(), 4);
		}
	}
	
	public EquipeFiscalizacao getEquipeFiscalizacao() {
		return equipeFiscalizacao;
	}

	public void setEquipeFiscalizacao(EquipeFiscalizacao equipeFiscalizacao) {
		this.equipeFiscalizacao = equipeFiscalizacao;
	}

	public Auditoria getAuditoria() {
		return auditoria;
	}

	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}

	public List<Auditoria> getListaAuditoria() {
		return listaAuditoria;
	}

	public void setListaAuditoria(List<Auditoria> listaAuditoria) {
		this.listaAuditoria = listaAuditoria;
	}

	public List<EquipeFiscalizacao> getListaEquipeFiscalizacao() {
		return listaEquipeFiscalizacao;
	}

	public void setListaEquipeFiscalizacao(List<EquipeFiscalizacao> listaEquipeFiscalizacao) {
		this.listaEquipeFiscalizacao = listaEquipeFiscalizacao;
	}
	
	
	
	
}
