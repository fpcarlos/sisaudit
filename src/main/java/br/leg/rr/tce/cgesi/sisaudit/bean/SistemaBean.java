package br.leg.rr.tce.cgesi.sisaudit.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Named;

import br.leg.rr.tce.cgesi.sisaudit.comum.entity.UnidadeGestora;
import br.leg.rr.tce.cgesi.sisaudit.ejb.SistemaEjb;
import br.leg.rr.tce.cgesi.sisaudit.entity.Auditoria;
import br.leg.rr.tce.cgesi.sisaudit.entity.CriteriosSelecao;
import br.leg.rr.tce.cgesi.sisaudit.entity.OrigemAuditoria;
import br.leg.rr.tce.cgesi.sisaudit.entity.Servidor;
import br.leg.rr.tce.cgesi.sisaudit.entity.TipoAuditor;
import br.leg.rr.tce.cgesi.sisaudit.entity.TipoFiscalizacao;
import br.leg.rr.tce.cgesi.sisaudit.entity.UnidadeFiscalizadora;
import br.leg.rr.tce.cgesi.sisaudit.entity.UnidadeGestoraAuditoria;
import javax.faces.context.FacesContext;

@Startup
@Singleton
@Named("sistemaBean")
public class SistemaBean extends AbstractBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EJB
	SistemaEjb sistemaEjb;
	
	private List<UnidadeGestora> unidadeGestoraList;
	private Map<Integer, UnidadeGestora> unidadeGestoraMap = new HashMap<Integer, UnidadeGestora>();
	
	private List<UnidadeGestoraAuditoria> unidadeGestoraAuditoriaList;
	private Map<Integer, UnidadeGestoraAuditoria> unidadeGestoraAuditoriaMap = new HashMap<Integer, UnidadeGestoraAuditoria>();
	
	private List<UnidadeFiscalizadora> unidadeFiscalizadoraList;
	private Map<Integer, UnidadeFiscalizadora> unidadeFiscalidadoraMap = new HashMap<Integer, UnidadeFiscalizadora>();
	private List<CriteriosSelecao> criteriosSelecaoList;
	private Map<Integer, CriteriosSelecao> criteriosSelecaoMap = new HashMap<Integer, CriteriosSelecao>();
	private List<OrigemAuditoria> origemAuditoriaList;
	private Map<Integer, OrigemAuditoria> origemAuditoriaMap = new HashMap<Integer, OrigemAuditoria>();
	
	private List<TipoFiscalizacao> tipoFiscalizacaoList;
	private Map<Integer, TipoFiscalizacao> tipoFiscalizacaoMap = new HashMap<Integer, TipoFiscalizacao>();
	
	private List<Servidor> servidorList;
	private Map<Integer, Servidor> servidorMap = new HashMap<Integer, Servidor>();
	
	private List<TipoAuditor> tipoAuditorList;
	private Map<Integer, TipoAuditor> tipoAuditorMap = new HashMap<Integer, TipoAuditor>();
		
	@PostConstruct
	public void init() {
		try {
			getUnidadeGestoraList();
			getUnidadeFiscalizadoraList();
			getCriteriosSelecaoList();
			getTipoFiscalizacaoList();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
        
        
        public String pegaContextoAplicacao(){
            String contextPath = FacesContext.getCurrentInstance().getExternalContext().getApplicationContextPath();
            return contextPath+"/";
        }
	
	
	public UnidadeGestora selecionarUnidadeGestora(Integer id){
		for(UnidadeGestora aux : unidadeGestoraList){
			if(aux.getId().equals(id)){
				return aux;
			}
		}
		return null;
	}
	
	public List<TipoFiscalizacao> getTipoFiscalizacaoList() {
		try {
			if(tipoFiscalizacaoList == null || tipoFiscalizacaoList.size()<1){
				tipoFiscalizacaoList = sistemaEjb.getTipoFiscalizacao();
				tipoFiscalizacaoMap = new HashMap<Integer, TipoFiscalizacao>();
				for(TipoFiscalizacao x: tipoFiscalizacaoList){
					tipoFiscalizacaoMap.put(x.getId(), x);
				}
			}
			return tipoFiscalizacaoList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	
	}
	
	public void setTipoFiscalizacaoList(List<TipoFiscalizacao> tipoFiscalizacaoList) {
		this.tipoFiscalizacaoList = tipoFiscalizacaoList;
	}
	
	public Map<Integer, TipoFiscalizacao> getTipoFiscalizacaoMap() {
		return tipoFiscalizacaoMap;
	}
	
	public void setTipoFiscalizacaoMap(Map<Integer, TipoFiscalizacao> tipoFiscalizacaoMap) {
		this.tipoFiscalizacaoMap = tipoFiscalizacaoMap;
	}
	
	public List<OrigemAuditoria> getOrigemAuditoriaList() {
		try {
			if(origemAuditoriaList == null || origemAuditoriaList.size()<1){
				origemAuditoriaList = sistemaEjb.getOrigemAuditoria();
				origemAuditoriaMap = new HashMap<Integer, OrigemAuditoria>();
				for(OrigemAuditoria x : origemAuditoriaList ){
					origemAuditoriaMap.put(x.getId(), x);
				}
			}
			return origemAuditoriaList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setOrigemAuditoriaList(List<OrigemAuditoria> origemAuditoriaList) {
		this.origemAuditoriaList = origemAuditoriaList;
	}

	public Map<Integer, OrigemAuditoria> getOrigemAuditoriaMap() {
		return origemAuditoriaMap;
	}

	public void setOrigemAuditoriaMap(Map<Integer, OrigemAuditoria> origemAuditoriaMap) {
		this.origemAuditoriaMap = origemAuditoriaMap;
	}

	public List<CriteriosSelecao> getCriteriosSelecaoList() {
		try {
			if(criteriosSelecaoList == null || criteriosSelecaoList.size()<1){
				criteriosSelecaoList=sistemaEjb.getCriteriosSelecao();
				criteriosSelecaoMap = new HashMap<Integer,CriteriosSelecao>();
				for(CriteriosSelecao x : criteriosSelecaoList){
					criteriosSelecaoMap.put(x.getId(), x);
				}
			}
			return criteriosSelecaoList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void setCriteriosSelecaoList(List<CriteriosSelecao> criteriosSelecaoList) {
		this.criteriosSelecaoList = criteriosSelecaoList;
	}
	public Map<Integer, CriteriosSelecao> getCriteriosSelecaoMap() {
		return criteriosSelecaoMap;
	}
	public void setCriteriosSelecaoMap(Map<Integer, CriteriosSelecao> criteriosSelecaoMap) {
		this.criteriosSelecaoMap = criteriosSelecaoMap;
	}
	public List<UnidadeFiscalizadora> getUnidadeFiscalizadoraList() {
		try {
			if(unidadeFiscalizadoraList == null || unidadeFiscalizadoraList.size()<1){
				unidadeFiscalizadoraList = sistemaEjb.getUnidadeFiscalizadora();
				unidadeFiscalidadoraMap = new HashMap<Integer, UnidadeFiscalizadora>();
				for(UnidadeFiscalizadora x : unidadeFiscalizadoraList){
				  unidadeFiscalidadoraMap.put(x.getId(), x);	
				}
			}
			return unidadeFiscalizadoraList;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void setUnidadeFiscalizadoraList(List<UnidadeFiscalizadora> unidadeFiscalizadoraList) {
		this.unidadeFiscalizadoraList = unidadeFiscalizadoraList;
	}

	public Map<Integer, UnidadeFiscalizadora> getUnidadeFiscalidadoraMap() {
		return unidadeFiscalidadoraMap;
	}

	public void setUnidadeFiscalidadoraMap(Map<Integer, UnidadeFiscalizadora> unidadeFiscalidadoraMap) {
		this.unidadeFiscalidadoraMap = unidadeFiscalidadoraMap;
	}

	//modelo de lista padrão por sistema
	public List<UnidadeGestora> getUnidadeGestoraList() {
		try {
			if(unidadeGestoraList == null || unidadeGestoraList.size()<1){
				unidadeGestoraList = sistemaEjb.getUnidadeGestora();
				unidadeGestoraMap = new HashMap<Integer, UnidadeGestora>();
				for(UnidadeGestora x : unidadeGestoraList){
				  unidadeGestoraMap.put(x.getId(), x);	
				}
			}
			return unidadeGestoraList;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	public void setUnidadeGestoraList(List<UnidadeGestora> unidadeGestoraList) {
		this.unidadeGestoraList = unidadeGestoraList;
	}
	public Map<Integer, UnidadeGestora> getUnidadeGestoraMap() {
		return unidadeGestoraMap;
	}
	public void setUnidadeGestoraMap(Map<Integer, UnidadeGestora> unidadeGestoraMap) {
		this.unidadeGestoraMap = unidadeGestoraMap;
	}


	public List<UnidadeGestoraAuditoria> getUnidadeGestoraAuditoriaList() {
		try {
			if(unidadeGestoraAuditoriaList == null || unidadeGestoraAuditoriaList.size()<1){
				unidadeGestoraAuditoriaList = sistemaEjb.getUnidadeGestoraAuditoria();
				unidadeGestoraAuditoriaMap = new HashMap<Integer, UnidadeGestoraAuditoria>();
				for(UnidadeGestoraAuditoria x : unidadeGestoraAuditoriaList){
					  unidadeGestoraAuditoriaMap.put(x.getId(), x);	
				}
			}
			return unidadeGestoraAuditoriaList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}


	public void setUnidadeGestoraAuditoriaList(List<UnidadeGestoraAuditoria> unidadeGestoraAuditoriaList) {
		this.unidadeGestoraAuditoriaList = unidadeGestoraAuditoriaList;
	}

	public List<UnidadeGestora> completeUnidadeGestora(String query){
		List<UnidadeGestora> allUg = this.getUnidadeGestoraList();
        List<UnidadeGestora> filtradoUG = new ArrayList<UnidadeGestora>();

        for (int i = 0; i < allUg.size(); i++) {
            UnidadeGestora skin = allUg.get(i);
            if(skin.getNome().toLowerCase().startsWith(query)) {
            	filtradoUG.add(skin);
            }
        }
         
		return filtradoUG;
	}
	
	
	public List<UnidadeGestora> listaUGA(Auditoria aux){
		try {
			List<UnidadeGestora> ugaLista = new ArrayList<UnidadeGestora>();
			ugaLista = sistemaEjb.findUGA(aux);
			
			return ugaLista;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}


	public List<Servidor> getServidorList() {
		try {
			if(servidorList == null || servidorList.size()<1){
				servidorList = sistemaEjb.getServidorList();
				servidorMap = new HashMap<Integer, Servidor>();
				for(Servidor x : servidorList){
					  servidorMap.put(x.getId(), x);	
					}
			}
			return servidorList;	
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public void setServidorList(List<Servidor> servidorList) {
		this.servidorList = servidorList;
	}


	public Map<Integer, Servidor> getServidorMap() {
		return servidorMap;
	}


	public void setServidorMap(Map<Integer, Servidor> servidorMap) {
		this.servidorMap = servidorMap;
	}


	public List<TipoAuditor> getTipoAuditorList() {
		try {
			if(tipoAuditorList == null || tipoAuditorList.size()<1){
				tipoAuditorList = sistemaEjb.getTipoAuditorList();
				tipoAuditorMap = new HashMap<Integer, TipoAuditor>();
				for(TipoAuditor x : tipoAuditorList){
					  tipoAuditorMap.put(x.getId(), x);	
					}
			}			
			return tipoAuditorList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}


	public void setTipoAuditorList(List<TipoAuditor> tipoAuditorList) {
		this.tipoAuditorList = tipoAuditorList;
	}


	public Map<Integer, TipoAuditor> getTipoAuditorMap() {
		return tipoAuditorMap;
	}


	public void setTipoAuditorMap(Map<Integer, TipoAuditor> tipoAuditorMap) {
		this.tipoAuditorMap = tipoAuditorMap;
	}
	
	
	
	
}
