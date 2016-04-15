package br.leg.rr.tce.cgesi.sisaudit.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.leg.rr.tce.cgesi.sisaudit.comum.entity.UnidadeGestora;
import br.leg.rr.tce.cgesi.sisaudit.entity.Auditoria;
import br.leg.rr.tce.cgesi.sisaudit.entity.CriteriosSelecao;
import br.leg.rr.tce.cgesi.sisaudit.entity.OrigemAuditoria;
import br.leg.rr.tce.cgesi.sisaudit.entity.Servidor;
import br.leg.rr.tce.cgesi.sisaudit.entity.TipoAuditor;
import br.leg.rr.tce.cgesi.sisaudit.entity.TipoFiscalizacao;
import br.leg.rr.tce.cgesi.sisaudit.entity.UnidadeFiscalizadora;
import br.leg.rr.tce.cgesi.sisaudit.entity.UnidadeGestoraAuditoria;

@Stateless
public class SistemaEjb extends AbstractEjb implements Serializable {
	private static final long serialVersionUID = 1L;
        
	@PersistenceContext(unitName = "sisauditUP")
	private EntityManager entityManager;
	
	public List<UnidadeGestora> getUnidadeGestora() throws Exception {
		try {
			return buscarComSqlNativoOrdenado("nome",
					UnidadeGestora.class, entityManager);
		} catch (Exception e) {
			throw e;
		}
	}

	public List<CriteriosSelecao> getCriteriosSelecao() throws Exception {
		try {
			return buscarComSqlNativoOrdenado("nome",
					CriteriosSelecao.class, entityManager);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<OrigemAuditoria> getOrigemAuditoria() throws Exception {
		try {
			return buscarComSqlNativoOrdenado("nome",
					OrigemAuditoria.class, entityManager);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<UnidadeFiscalizadora> getUnidadeFiscalizadora() throws Exception {
		try {
			return buscarComSqlNativoOrdenado("nome",
					UnidadeFiscalizadora.class, entityManager);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<TipoFiscalizacao> getTipoFiscalizacao() throws Exception {
		try {
			return buscarComSqlNativoOrdenado("nome",
					TipoFiscalizacao.class, entityManager);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<UnidadeGestoraAuditoria> getUnidadeGestoraAuditoria() throws Exception {
		try {
			return buscarComSqlNativo(UnidadeGestoraAuditoria.class, entityManager);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<Servidor> getServidorList() throws Exception{
		try {
			return buscarComSqlNativo(Servidor.class, entityManager);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<TipoAuditor> getTipoAuditorList() throws Exception{
		try {
			return buscarComSqlNativo(TipoAuditor.class, entityManager);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public List<UnidadeGestora> findUGA(Auditoria aux) throws Exception {
		try {
			String sql = "select distinct ug.* from scsisaudit.unidade_gestora_auditoria uga "
					+ "join comum.v_unidade_gestora ug on uga.id_unidade_gestora=ug.cod_ug "
					+ "join scsisaudit.auditoria a on uga.id_auditoria=a.id "
					+ "where uga.id_auditoria = " + aux.getId() + " ";
			List<UnidadeGestora> listaUGA = executaSqlNativo(sql, UnidadeGestora.class, entityManager);
			return listaUGA;

		} catch (RuntimeException re) {
			re.printStackTrace();
			throw new Exception(" Erro" + re.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(" Erro" + e.getMessage());
		}

	}	
	
	
}
