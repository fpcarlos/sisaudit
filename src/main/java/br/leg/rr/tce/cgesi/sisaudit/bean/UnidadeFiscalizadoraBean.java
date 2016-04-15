package br.leg.rr.tce.cgesi.sisaudit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.leg.rr.tce.cgesi.sisaudit.ejb.UnidadeFiscalizadoraEjb;
import br.leg.rr.tce.cgesi.sisaudit.entity.UnidadeFiscalizadora;

@Named("unidadeFiscalizadoraBean")
@SessionScoped
public class UnidadeFiscalizadoraBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private UnidadeFiscalizadora unidadeFiscalizadora;
	
	@Inject
	private transient SistemaBean sistemaBean;
	
	@EJB
	private UnidadeFiscalizadoraEjb unidadeFiscalizadoraEjb;
	
	private List<UnidadeFiscalizadora> items = new ArrayList<UnidadeFiscalizadora>();
    	
	public UnidadeFiscalizadoraBean() {
		super();		
	}

	public List<UnidadeFiscalizadora> getItems() {
		return items;
	}
	
	public UnidadeFiscalizadora getUnidadeFiscalizadora() {
		return unidadeFiscalizadora;
	}

	public void setUnidadeFiscalizadora(UnidadeFiscalizadora unidadeFiscalizadora) {
		this.unidadeFiscalizadora = unidadeFiscalizadora;
	}


}
