package br.leg.rr.tce.cgesi.sisaudit.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.leg.rr.tce.cgesi.sisaudit.entity.TipoAuditor;

/**
 * Session Bean implementation class PortariaEjb
 */
@Stateless
public class TipoAuditorEjb extends AbstractEjb implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "sisauditUP")
	private EntityManager entityManager;

	public void salvar(TipoAuditor entity) throws Exception {
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


	public void remove(TipoAuditor entity) throws Exception {
		try {
			TipoAuditor aux = entityManager.find(TipoAuditor.class, entity.getId());
			entityManager.remove(aux);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	public List<TipoAuditor> findAll() throws Exception {
		try {
			String sql = "select * from scsisaudit.auditoria";
			List<TipoAuditor> listaTipoAuditor = executaSqlNativo(sql, TipoAuditor.class, entityManager);
			return listaTipoAuditor;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}

	}

}
