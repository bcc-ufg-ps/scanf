package br.ufg.jatai.ps.dao.jpa;

import br.ufg.jatai.ps.dao.AlunoDAO;
import br.ufg.jatai.ps.modelo.Aluno;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class AlunoDAOJPA implements AlunoDAO {

    @Override
    public void salvar(Aluno aluno) {
        EntityManager em = JPAUtil.obterConexao();
        try {
            em.getTransaction().begin();
            em.persist(aluno);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public Aluno obterAlunoPorEmail(String email) {
        EntityManager em = JPAUtil.obterConexao();
        try {
            TypedQuery<Aluno> tq = em.createNamedQuery(Aluno.OBTER_ALUNO_POR_EMAIL, Aluno.class);
            tq.setParameter("email", email);
            return tq.getSingleResult();
        } finally {
            em.close();
        }
    }
}
