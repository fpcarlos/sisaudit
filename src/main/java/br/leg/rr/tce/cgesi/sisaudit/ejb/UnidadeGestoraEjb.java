package br.leg.rr.tce.cgesi.sisaudit.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.leg.rr.tce.cgesi.sisaudit.comum.entity.UnidadeGestora;

@Stateless(mappedName="UnidadeGestoraEjb")
public class UnidadeGestoraEjb extends AbstractEjb implements Serializable {
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "sisauditUP")
	private EntityManager entityManager;

	public void salvar(UnidadeGestora entity) throws Exception{
		try {
			//comentario
			//Unidade Gestora não pode sofrer alterações pelo sistema
			//
			if(entity.getId()!=null && entity.getId()>0){
				//entityManager.merge(entity);
			}else{				
				//entityManager.persist(entity);					
			}
			
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

    public void remove(UnidadeGestora entity) throws Exception{
    	try {
    		//UnidadeGestora aux = entityManager.find(UnidadeGestora.class, entity.getId());
        	//entityManager.remove(aux);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    	
    }

    
    
    public List<UnidadeGestora> findAll()
			throws Exception {    	
		try {
			String sql = "select * from comum.v_unidade_gestora";
			List<UnidadeGestora> listaUnidadeGestora = executaSqlNativo(sql,UnidadeGestora.class, entityManager);
			return listaUnidadeGestora;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}
	}
    
	

    
    
    /*
    public List<UnidadeGestoraAuditoria> findUgsAuditoria(Projeto aux) throws Exception {
		try {
			
			String sql = "select u.* from sispgraudi.unidade_gestora_projeto up "
					+ "join sispgraudi.unidade_gestora u on up.unidadegestora_id=u.cod_ug "
					+ "join sispgraudi.projeto p on up.projeto_id= " + aux.getId() + " ";
			List<UnidadeGestoraProjeto> listaUnidadeGestoraProjeto = executaSqlNativo(sql,UnidadeGestoraProjeto.class, entityManager);
			
			return listaUnidadeGestoraProjeto;
		} catch (Exception e) {
			throw e;
		}

	}
*/
    
    
}
