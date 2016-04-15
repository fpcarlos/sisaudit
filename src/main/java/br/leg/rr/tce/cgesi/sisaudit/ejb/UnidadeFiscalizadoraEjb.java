package br.leg.rr.tce.cgesi.sisaudit.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.leg.rr.tce.cgesi.sisaudit.entity.UnidadeFiscalizadora;

@Stateless
public class UnidadeFiscalizadoraEjb extends AbstractEjb implements Serializable {
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "sisauditUP")
	private EntityManager entityManager;
	
	
	public void salvar(UnidadeFiscalizadora entity) throws Exception{
		try {
			if(entity.getId()!=null && entity.getId()>0){
				entityManager.merge(entity);
			}else{
				entityManager.persist(entity);	
				
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void remove(UnidadeFiscalizadora entity) throws Exception{
    	try {
    		UnidadeFiscalizadora aux = entityManager.find(UnidadeFiscalizadora.class, entity.getId());
    		entityManager.remove(aux);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }

    public List<UnidadeFiscalizadora> findAll()
			throws Exception {    	
		try {
			String sql = "select * from scsisaudit.unidade_fiscalizadora";
			List<UnidadeFiscalizadora> listaUnidadeFiscalizadora = executaSqlNativo(sql,UnidadeFiscalizadora.class, entityManager);
			return listaUnidadeFiscalizadora;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}
    }

	

}
