package br.leg.rr.tce.cgesi.sisaudit.ejb;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.leg.rr.tce.cgesi.sisaudit.entity.StatusPortaria;


@Stateless
public class StatusPortariaEjb extends AbstractEjb implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	@PersistenceContext(unitName = "sisauditUP")
	private EntityManager entityManager;
	
	
	public StatusPortaria pegarStatusPortariaId(Integer id) throws Exception{
		try {
			StatusPortaria statusPortaria = new StatusPortaria();
			statusPortaria=entityManager.find(StatusPortaria.class, id);
			return statusPortaria;
		} catch (Exception e) {
			throw e;
		}
	}
	
	
	
	/*
	//
	public List<Portaria> findAll() throws Exception {
		try {
			String sql = "select * from scsisaudit.portaria order by id";
			List<Portaria> listaPortaria = executaSqlNativo(sql, Portaria.class, entityManager);
			return listaPortaria;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}

	}
	*/
	
}
