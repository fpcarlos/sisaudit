package br.leg.rr.tce.cgesi.sisaudit.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.leg.rr.tce.cgesi.sisaudit.entity.EquipeFiscalizacao;

/**
 * Session Bean implementation class AuditoriaEjb
 */
@Stateless
public class EquipeFiscalizacaoEjb extends AbstractEjb implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext(unitName = "sisauditUP")
	private EntityManager entityManager;

	public void salvar(EquipeFiscalizacao entity) throws Exception {
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

	public void remove(EquipeFiscalizacao entity) throws Exception {
		try {
			EquipeFiscalizacao aux = entityManager.find(EquipeFiscalizacao.class, entity.getId());
			entityManager.remove(aux);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<EquipeFiscalizacao> findAll() throws Exception {
		try {
			String sql = "select * from scsisaudit.equipe_fiscalizacao";
			List<EquipeFiscalizacao> listaEquipeFiscalizacao = executaSqlNativo(sql, EquipeFiscalizacao.class,
					entityManager);
			return listaEquipeFiscalizacao;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}
	}

	public List<EquipeFiscalizacao> findIdPortaria(Integer id) throws Exception {
		try {
			String sql = "select * from scsisaudit.equipe_fiscalizacao where id_portaria = " + id + " ";
			List<EquipeFiscalizacao> listaEquipeFiscalizacao = executaSqlNativo(sql, EquipeFiscalizacao.class,
					entityManager);
			return listaEquipeFiscalizacao;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}

	}

}
