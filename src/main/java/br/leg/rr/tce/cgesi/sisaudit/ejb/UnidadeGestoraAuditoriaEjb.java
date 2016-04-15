package br.leg.rr.tce.cgesi.sisaudit.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.leg.rr.tce.cgesi.sisaudit.entity.UnidadeGestoraAuditoria;
@Stateless
public class UnidadeGestoraAuditoriaEjb extends AbstractEjb implements Serializable {
	private static final long serialVersionUID = 1L;
	@PersistenceContext(unitName = "sisauditUP")
	private EntityManager entityManager;

	public void salvar(UnidadeGestoraAuditoria entity) throws Exception{
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

    public void remove(UnidadeGestoraAuditoria entity) throws Exception{
    	try {
    		UnidadeGestoraAuditoria aux = entityManager.find(UnidadeGestoraAuditoria.class,entity.getId());
    		entityManager.remove(aux);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
    }
    
    
    public List<UnidadeGestoraAuditoria> findAll() throws Exception {    	
		try {
			String sql = "select up.*, p.titulo, u.nome from scsisaudit.unidade_gestora_auditoria up"
					+ "join scsisaudit.unidade_gestora u on up.id_unidade_gestora=u.cod_ug"
					+ "join scsisaudit.auditoria p on up.id_auditoria=p.id";
			List<UnidadeGestoraAuditoria> listaUnidadeGestoraAuditoria = executaSqlNativo(sql,UnidadeGestoraAuditoria.class, entityManager);
			return listaUnidadeGestoraAuditoria;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}
    }

    
    public List<UnidadeGestoraAuditoria> findUgId(Integer auditoria) throws Exception {    	
		try {
			String sql = "select * from scsisaudit.unidade_gestora_auditoria where id_auditoria = " + auditoria + " ";
			List<UnidadeGestoraAuditoria> listaUnidadeGestoraAuditoria = executaSqlNativo(sql,UnidadeGestoraAuditoria.class, entityManager);
			return listaUnidadeGestoraAuditoria;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}
    }
       

}
