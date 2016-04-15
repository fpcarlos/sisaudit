package br.leg.rr.tce.cgesi.sisaudit.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.leg.rr.tce.cgesi.sisaudit.entity.Servidor;

/**
 * Session Bean implementation class PortariaEjb
 */
@Stateless
public class ServidorEjb extends AbstractEjb implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "sisauditUP")
	private EntityManager entityManager;

	public void salvar(Servidor entity) throws Exception {
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


	public void remove(Servidor entity) throws Exception {
		try {
			Servidor aux = entityManager.find(Servidor.class, entity.getId());
			entityManager.remove(aux);	
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	public List<Servidor> findAll() throws Exception {
		try {
			String sql = "select * from scsisaudit.servidor";
			List<Servidor> listaPortaria = executaSqlNativo(sql, Servidor.class, entityManager);
			return listaPortaria;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}

	}

}
