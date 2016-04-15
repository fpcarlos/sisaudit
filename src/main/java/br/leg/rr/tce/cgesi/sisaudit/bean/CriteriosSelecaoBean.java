package br.leg.rr.tce.cgesi.sisaudit.bean;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.leg.rr.tce.cgesi.sisaudit.ejb.CriteriosSelecaoEjb;
import br.leg.rr.tce.cgesi.sisaudit.entity.CriteriosSelecao;

@Named("criteriosSelecaoBean")
@RequestScoped
public class CriteriosSelecaoBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	CriteriosSelecao criteriosSelecao;
	
	@EJB
	CriteriosSelecaoEjb criteriosSelecaoEjb; 
	
	public CriteriosSelecaoBean() {
        super();
    }
	
	
	public void save(){
		criteriosSelecao.setNome("Teste ejb");
		//criteriosSelecaoEjb.salvar(criteriosSelecao);		
	}
	
	public String abrirListaAuditoria(){
		
		return "/sistema/auditoria/listaAuditorias.xhtml";
	}
	

}
