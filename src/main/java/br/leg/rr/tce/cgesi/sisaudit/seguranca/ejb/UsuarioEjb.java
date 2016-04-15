package br.leg.rr.tce.cgesi.sisaudit.seguranca.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.leg.rr.tce.cgesi.sisaudit.ejb.AbstractEjb;
import br.leg.rr.tce.cgesi.sisaudit.entity.Servidor;

@Stateless
public class UsuarioEjb extends AbstractEjb implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "sisauditUP")
	private EntityManager entityManager;
	
	public void salvar(Servidor entity) throws Exception{
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
        if (entity != null) {
            try {
                Servidor UsuarioRemover = entityManager.find(Servidor.class, entity.getId());                
                entityManager.remove(UsuarioRemover);
            } catch (Exception exception) {
                throw new Exception("Erro removendo: " + exception);
            }
        }
    }
	
	public List<Servidor> retrieveUsuarios() throws Exception {
        try {
        	String sql = "select * from scsisaudit.servidor";
			List<Servidor> listaPortaria = executaSqlNativo(sql, Servidor.class, entityManager);
			return listaPortaria;
        } catch (Exception e) {
            throw new Exception("Erro na query: " + e);
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
	
	
	
	public Servidor pegaLogado(String vnome) throws Exception{
		try {
			//FacesContext context = FacesContext.getCurrentInstance();
			//String vnome = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
			String sql = "select * from scsisaudit.servidor where login = '" + vnome +"' " ;
			List<Servidor> listaU = executaSqlNativo(sql, Servidor.class, entityManager);
			
			Servidor aux = entityManager.find(Servidor.class, listaU.get(0).getId());
			//Servidor aux = executaSqlNativo(sql, Servidor.class, entityManager);
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
