package br.leg.rr.tce.cgesi.sisaudit.ejb;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.leg.rr.tce.cgesi.sisaudit.entity.CriteriosSelecao;

@Stateless
public class CriteriosSelecaoEjb extends AbstractEjb implements Serializable {
	private static final long serialVersionUID = 1L;
        
	@PersistenceContext(unitName = "sisauditUP")
	private EntityManager entityManager;
	
	public void salvar(CriteriosSelecao entity) throws Exception {
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

	public void excluir(CriteriosSelecao entity) throws Exception {
		try {
			CriteriosSelecao aux = entityManager.find(CriteriosSelecao.class, entity.getId());
			entityManager.remove(aux);
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public CriteriosSelecao carregarCriteriosSelecao(Integer id) throws Exception{
		try {
			CriteriosSelecao aux = new CriteriosSelecao();
			aux = entityManager.find(CriteriosSelecao.class, id);
			return aux;			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	/*
	public List<CriteriosSelecao> findAll() {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<CriteriosSelecao> criteriaQuery = criteriaBuilder
				.createQuery(CriteriosSelecao.class);
		TypedQuery<CriteriosSelecao> query = entityManager.createQuery(criteriaQuery);
		return query.getResultList();
	}
*/
		
	
}
