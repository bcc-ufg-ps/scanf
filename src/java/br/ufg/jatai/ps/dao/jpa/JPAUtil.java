package br.ufg.jatai.ps.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
    private static EntityManagerFactory emf = null;
    private static final String PU = "scanfPU";
    public static EntityManager obterConexao() {
        if (emf == null)
            emf = Persistence.createEntityManagerFactory(PU);
        return emf.createEntityManager();
    }            
}
