package br.leg.rr.tce.cgesi.sisaudit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.leg.rr.tce.cgesi.sisaudit.comum.entity.UnidadeGestora;
import br.leg.rr.tce.cgesi.sisaudit.ejb.UnidadeGestoraEjb;

@Named("unidadeGestoraBean")
@SessionScoped
public class UnidadeGestoraBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UnidadeGestora unidadeGestora;
	
	@Inject
	private  transient SistemaBean sistemaBean;
	
	@EJB
	private UnidadeGestoraEjb unidadeGestoraEjb;
	
	
	private List<UnidadeGestora> todasUnidadeGestoras = new ArrayList<UnidadeGestora>();
	
	public UnidadeGestoraBean() {
		super();		
	}

	public UnidadeGestora getUnidadeGestora() {
		return unidadeGestora;
	}

	public void setUnidadeGestora(UnidadeGestora unidadeGestora) {
		this.unidadeGestora = unidadeGestora;
	}

	public List<UnidadeGestora> getTodasUnidadeGestoras() {
		return todasUnidadeGestoras;
	}

	public void setTodasUnidadeGestoras(List<UnidadeGestora> todasUnidadeGestoras) {
		this.todasUnidadeGestoras = todasUnidadeGestoras;
	}

	

}
