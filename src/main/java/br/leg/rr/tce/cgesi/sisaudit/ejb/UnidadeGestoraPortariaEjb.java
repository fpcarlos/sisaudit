package br.leg.rr.tce.cgesi.sisaudit.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.leg.rr.tce.cgesi.sisaudit.entity.UnidadeGestoraPortaria;

@Stateless
public class UnidadeGestoraPortariaEjb extends AbstractEjb implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext(unitName = "sisauditUP")
	private EntityManager entityManager;

	public void salvar(UnidadeGestoraPortaria entity)  throws Exception{
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
	
	public void excluir(UnidadeGestoraPortaria entity) throws Exception{
		try {
			UnidadeGestoraPortaria aux = entityManager.find(UnidadeGestoraPortaria.class, entity.getId());
			entityManager.remove(aux);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public List<UnidadeGestoraPortaria> findIdPortaria(Integer idP) throws Exception {
		try {
			String sql = "select * from scsisaudit.unidade_gestora_portaria where id_portaria = " + idP+ " ";
			List<UnidadeGestoraPortaria> listaUGP = executaSqlNativo(sql, UnidadeGestoraPortaria.class, entityManager);
			return listaUGP;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}
	}

	
	public List<UnidadeGestoraPortaria> listaUGDasPortaria(String listaPortaria) throws Exception {
		try {
			String sql = "select * from scsisaudit.unidade_gestora_portaria where id_portaria in (" + listaPortaria + ") order by id_portaria, id";
			List<UnidadeGestoraPortaria> listaUGP = executaSqlNativo(sql, UnidadeGestoraPortaria.class, entityManager);
			return listaUGP;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}
	}

	
	public List<UnidadeGestoraPortaria> findIAll() throws Exception {
		try {
			String sql = "select * from scsisaudit.unidade_gestora_portaria ";
			List<UnidadeGestoraPortaria> listaUGP = executaSqlNativo(sql, UnidadeGestoraPortaria.class, entityManager);
			return listaUGP;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}
	}
		

}
