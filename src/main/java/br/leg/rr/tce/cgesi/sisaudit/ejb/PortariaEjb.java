package br.leg.rr.tce.cgesi.sisaudit.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.leg.rr.tce.cgesi.sisaudit.entity.EquipeFiscalizacao;
import br.leg.rr.tce.cgesi.sisaudit.entity.Portaria;
import br.leg.rr.tce.cgesi.sisaudit.entity.PortariasAndamento;
import br.leg.rr.tce.cgesi.sisaudit.entity.UnidadeGestoraPortaria;
import java.util.ArrayList;

/**
 * Session Bean implementation class PortariaEjb
 */
@Stateless
public class PortariaEjb extends AbstractEjb implements Serializable {

    private static final long serialVersionUID = 1L;

    @PersistenceContext(unitName = "sisauditUP")
    private EntityManager entityManager;

    @EJB
    UnidadeGestoraPortariaEjb unidadeGestoraPortariaEjb;

    @EJB
    AuditoriaEjb auditoriaEjb;

    @EJB
    EquipeFiscalizacaoEjb equipeFiscalizacaoEjb;

    @EJB
    PortariasAndamentoEjb portariasAndamentoEjb;

    public void salvar(Portaria entity) throws Exception {
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

    public void salvarPacote(Portaria entity) throws Exception {
        try {
            boolean sc = false;
            if (entity.getId() == null) {
                sc = true;
            }
            this.salvar(entity);
            for (UnidadeGestoraPortaria x : entity.getUnidadeGestoraPortarias()) {
                unidadeGestoraPortariaEjb.salvar(x);
            }
            for (UnidadeGestoraPortaria aux : entity.getUnidadeGestoraPortariaExcluidas()) {
                unidadeGestoraPortariaEjb.excluir(aux);
            }
            if (sc == true) {
                for (EquipeFiscalizacao aux1 : entity.getEquipeFiscalizacaoList()) {
                    equipeFiscalizacaoEjb.salvar(aux1);
                }
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void salvarMinuta(Portaria entity) throws Exception {
        try {
            boolean sc = false;
            if (entity.getId() == null) {
                sc = true;
            }

            this.salvar(entity);

            if (!entity.getUnidadeGestoraPortarias().isEmpty()) {
                for (UnidadeGestoraPortaria x : entity.getUnidadeGestoraPortarias()) {
                    unidadeGestoraPortariaEjb.salvar(x);
                }
            }
            if (!entity.getUnidadeGestoraPortariaExcluidas().isEmpty()) {
                for (UnidadeGestoraPortaria aux : entity.getUnidadeGestoraPortariaExcluidas()) {
                    unidadeGestoraPortariaEjb.excluir(aux);
                }
            }

            if (sc == true) {
                for (PortariasAndamento x : entity.getPortariasAndamentos()) {
                    portariasAndamentoEjb.salvar(x);
                }
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void remove(Portaria entity) throws Exception {
        try {
            for (PortariasAndamento aux : entity.getPortariasAndamentos()) {
                portariasAndamentoEjb.remove(aux);
            }
            for (EquipeFiscalizacao aux1 : entity.getEquipeFiscalizacaoList()) {
                equipeFiscalizacaoEjb.remove(aux1);
            }

            Portaria aux = entityManager.find(Portaria.class, entity.getId());
            entityManager.remove(aux);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public Portaria pegarPortaria(Integer id) throws Exception {
        try {
            Portaria aux = new Portaria();
            aux = entityManager.find(Portaria.class, id);
            return aux;

        } catch (RuntimeException re) {
            throw new Exception(" Erro" + re.getMessage());
        } catch (Exception e) {
            throw new Exception(" Erro" + e.getMessage());
        }
    }

    public List<Portaria> findAll() throws Exception {
        try {
            List<Portaria> listaPortaria = new ArrayList<>();
            String sql = "select * from scsisaudit.portaria order by id";
            listaPortaria = executaSqlNativo(sql, Portaria.class, entityManager);
            return listaPortaria;

        } catch (RuntimeException re) {
            throw new Exception(" Erro" + re.getMessage());
        } catch (Exception e) {
            throw new Exception(" Erro" + e.getMessage());
        }

    }

    // lpad(cast(cod_curso as varchar),4,'0')
    public List<Portaria> listaPortaria() throws Exception {
        try {

            List<Portaria> vListaPortaria = new ArrayList<>();
            //String sql = "select id, id_auditoria, objetivo, deliberacao, id_servidor, id_tipo_fiscalizacao, ano_portaria_revogada, numero_portaria_revogada,lpad(cast(numero_portaria as varchar),3,'0') as numero_portaria, ano_portaria, id_unidade_fiscalizadora from scsisaudit.portaria order by id";
            String sql = "select lpad(cast(numero_portaria as varchar),3,'0') as numero_portaria, * from scsisaudit.portaria order by id";

            vListaPortaria = executaSqlNativo(sql, Portaria.class, entityManager);
            return vListaPortaria;

        } catch (RuntimeException re) {
            throw new Exception(" Erro RunTime: " + re.getMessage());
        } catch (Exception e) {
            throw new Exception(" Erro Exception: " + e.getMessage());
        }

    }

    public List<Portaria> findIdAuditoria(Integer id) throws Exception {
        try {
            String sql = "select * from scsisaudit.portaria where id_auditoria = " + id + " ";
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

    public String ultimoNumeroPortaria(String anop) throws Exception {
        try {
            String sql = "select COALESCE(max(cast(numero_portaria as integer))+1,0) as numero_portaria from scsisaudit.portaria where ano_portaria='"
                    + anop + "'";
            Integer resultSql = (Integer) entityManager.createNativeQuery(sql).getSingleResult();

            return resultSql.toString();

        } catch (RuntimeException re) {
            re.printStackTrace();
            throw new Exception(" Erro" + re.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(" Erro" + e.getMessage());
        }
    }
}
