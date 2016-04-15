package br.leg.rr.tce.cgesi.sisaudit.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.leg.rr.tce.cgesi.sisaudit.entity.PortariasAndamento;

/**
 * Session Bean implementation class PortariaEjb
 */
@Stateless
public class PortariasAndamentoEjb extends AbstractEjb implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "sisauditUP")
	private EntityManager entityManager;

	public void salvar(PortariasAndamento entity) throws Exception {
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


	public void remove(PortariasAndamento entity) throws Exception {
		try {
			PortariasAndamento aux = entityManager.find(PortariasAndamento.class, entity.getId());
			entityManager.remove(aux);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
	}

	public List<PortariasAndamento> findAll() throws Exception {
		try {
			String sql = "select * from scsisaudit.postrarias_andamento";
			List<PortariasAndamento> listaPortariasAndamento = executaSqlNativo(sql, PortariasAndamento.class, entityManager);
			return listaPortariasAndamento;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}

	}
	
	public PortariasAndamento pegaPortariasAndamentoPeloId(Integer id) throws Exception{
		try {
			PortariasAndamento aux = entityManager.find(PortariasAndamento.class, id);
			
			return aux;
			
		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}
		
	}

}
