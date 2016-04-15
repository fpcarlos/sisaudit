package br.leg.rr.tce.cgesi.sisaudit.ejb;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class AbstractEjb {

	private <T> String nomeTabela(Class<T> clazz) {

		try {

			for (Annotation fA : clazz.getAnnotations()) {
				if (fA.annotationType().getName().toString()
						.equals("javax.persistence.Table")) {
					javax.persistence.Table myAnnotation = (javax.persistence.Table) fA;
					return myAnnotation.schema() + "." + myAnnotation.name();
				}
			}
			return null;
		} catch (RuntimeException e) {
			throw e;

		} catch (Exception e) {
			throw e;

		}

	}
	
	 public <T> List<T> buscarComSqlNativo(Class<T> clazz, EntityManager entityManager) throws Exception {

			try {

				String sql = "select * from " + nomeTabela(clazz) + ";";
				return executaSqlNativo(sql, clazz, entityManager);
			} catch (RuntimeException e) {
				throw e;
			} catch (Exception e) {
				throw e;
			}
		}

	 
	  
	 
	 public <T> List<T> executaSqlNativo(String sql, Class<T> clazz, EntityManager entityManager)
				throws Exception {
			try {
				//printErro(sql);
				Query sqlQuery = entityManager.createNativeQuery(sql, clazz);
				System.out.println(sql);
				return sqlQuery.getResultList();

			} catch (RuntimeException re) {
				re.printStackTrace();
				throw new Exception("SQl:" + sql + ": Erro" + re.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception("SQl:" + sql + ": Erro" + e.getMessage());
			}

		}
	 
	 public <T> List<T> buscarComSqlNativoOrdenado(String ordem, Class<T> clazz,  EntityManager entityManager)
				throws Exception {
			try {

				String sql = "select * from " + nomeTabela(clazz) + " order by "
						+ ordem + ";";
				
				return executaSqlNativo(sql, clazz, entityManager);
			} catch (RuntimeException e) {
				throw e;
			} catch (Exception e) {
				throw e;
			}
		}
		
	
}
