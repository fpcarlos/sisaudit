package br.leg.rr.tce.cgesi.sisaudit.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.leg.rr.tce.cgesi.sisaudit.entity.TipoFiscalizacao;


@Stateless
public class TipoFiscalizacaoEjb extends AbstractEjb implements Serializable{
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "sisauditUP")
	private EntityManager entityManager;

	public void salvar(TipoFiscalizacao entity) throws Exception{
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

    public void remove(TipoFiscalizacao entity) throws Exception{
    	try {
    		TipoFiscalizacao aux = entityManager.find(TipoFiscalizacao.class, entity.getId());
    		entityManager.remove(aux);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    	
    }

    public List<TipoFiscalizacao> findAll()
			throws Exception {    	
		try {
			String sql = "select * from scsisaudit.tipo_fiscalizacao";
			List<TipoFiscalizacao> listaTipoFiscalizacao = executaSqlNativo(sql,TipoFiscalizacao.class, entityManager);
			return listaTipoFiscalizacao;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}

	}

    
    

}
