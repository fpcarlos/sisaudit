package br.leg.rr.tce.cgesi.sisaudit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.leg.rr.tce.cgesi.sisaudit.comum.entity.UnidadeGestora;
import br.leg.rr.tce.cgesi.sisaudit.ejb.AuditoriaEjb;
import br.leg.rr.tce.cgesi.sisaudit.ejb.PortariaEjb;
import br.leg.rr.tce.cgesi.sisaudit.entity.Auditoria;
import br.leg.rr.tce.cgesi.sisaudit.entity.Portaria;



@Named("cadasroPortariaBean")
@SessionScoped
public class CadasroPortariaBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;
	@Inject
	private transient SistemaBean sistemaBean;
	
	@Inject
	private Portaria portaria;
	
	@Inject
	private Auditoria auditoria;
	
	@EJB
	private AuditoriaEjb auditoriaEjb;
	
	@EJB
	private PortariaEjb portariaEjb;
	
	private List<UnidadeGestora> unidadeGestoraLista  = new ArrayList<UnidadeGestora>();
	
	public CadasroPortariaBean() {
		super();
	}
	
	
	public String preprarCadastroPortaria(Auditoria aux){
		try {
			auditoria = new Auditoria();
			auditoria = auditoriaEjb.carregarAuditoria(aux.getId());
			//auditoria.setsetPortariaList(portariaEjb.findIdAuditoria(aux.getId()));

			
			return redirect("/sistema/portaria/cadastroPortariaAuditoria.xhtml"); 
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


	public Auditoria getAuditoria() {
		return auditoria;
	}


	public void setAuditoria(Auditoria auditoria) {
		this.auditoria = auditoria;
	}
	
	

}
