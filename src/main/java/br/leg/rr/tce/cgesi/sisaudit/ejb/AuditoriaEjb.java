package br.leg.rr.tce.cgesi.sisaudit.ejb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.leg.rr.tce.cgesi.sisaudit.comum.entity.UnidadeGestora;
import br.leg.rr.tce.cgesi.sisaudit.entity.Auditoria;
import br.leg.rr.tce.cgesi.sisaudit.entity.UnidadeGestoraAuditoria;

/**
 * Session Bean implementation class AuditoriaEjb
 */
@Stateless
public class AuditoriaEjb extends AbstractEjb implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "sisauditUP")
	private EntityManager entityManager;
	
	@EJB
	private UnidadeGestoraAuditoriaEjb unidadeGestoraAuditoriaEjb;

	public void salvar(Auditoria entity) throws Exception {
		try {
			if (entity.getId() != null && entity.getId() > 0) {
				entityManager.merge(entity);
			} else {
				entityManager.persist(entity);
			}

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

	public void salvarBloco(Auditoria entity) throws Exception {
		try {
			this.salvar(entity);
			//entity.criarListaUGAuditoria();
			for (UnidadeGestoraAuditoria aux : entity.getUnidadeGestoraAuditoriaList()) {
				unidadeGestoraAuditoriaEjb.salvar(aux);
			}
			for (UnidadeGestoraAuditoria aux : entity.getUnidadeGestoraAuditoriasExcluidas()) {
				unidadeGestoraAuditoriaEjb.remove(aux);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public Auditoria carregarAuditoria(Integer id) throws Exception{
		try {
			Auditoria aud = new Auditoria();
			aud = entityManager.find(Auditoria.class, id);
                        aud.setUnidadeGestoraAuditorias(new ArrayList<>());
			aud.setUnidadeGestoraAuditorias(unidadeGestoraAuditoriaEjb.findUgId(id));
			aud.setUnidadeGestoraAuditoriaList(new ArrayList<>());
			aud.setListaUnidadeGestoraTmp(new ArrayList<>());
			aud.setUnidadeGestoraAuditoriasExcluidas(new ArrayList<>());
			return aud;			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void remove(Auditoria entity) throws Exception {
		try {
			Auditoria aux = entityManager.find(Auditoria.class, entity.getId());
			entityManager.remove(aux);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	public List<Auditoria> findAll() throws Exception {
		try {
			List<Auditoria> listaAuditoria = new ArrayList<>();
			String sql = "select * from scsisaudit.auditoria";
			listaAuditoria = executaSqlNativo(sql, Auditoria.class, entityManager);
			return listaAuditoria;

		} catch (RuntimeException re) {
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			throw new Exception(" Erro" + e.getMessage());
		}

	}
	


}
