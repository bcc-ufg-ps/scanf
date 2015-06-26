package br.ufg.jatai.ps.dao.jpa;

import br.ufg.jatai.ps.dao.AlunoDAO;
import br.ufg.jatai.ps.modelo.Aluno;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class AlunoDAOJPA implements AlunoDAO {

    @Override
    public void salvar(Aluno aluno) {
        EntityManager em = JPAUtil.obterConexao();
        try {
            em.getTransaction().begin();
            if (aluno.getId() == null) em.persist(aluno);
            else em.merge(aluno);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean existeAlunoComEmail(String email) {
        EntityManager em = JPAUtil.obterConexao();
        try {
            TypedQuery<Number> tq = em.createNamedQuery(Aluno.EXISTE_ALUNO_COM_EMAIL, Number.class);
            tq.setParameter("email", email);
            int count = tq.getSingleResult().intValue();
            return (count != 0);
        } finally {
            em.close();
        }
    }

    @Override
    public Aluno obterAlunoPorEmailESenha(String email, String senha) {
        EntityManager em = JPAUtil.obterConexao();
        try {
            TypedQuery<Aluno> tq = em.createNamedQuery(Aluno.OBTER_ALUNO_POR_EMAIL_E_SENHA, Aluno.class);
            tq.setParameter("email", email);
            tq.setParameter("senha", senha);
            List<Aluno> la = tq.getResultList();
            if (la == null || la.isEmpty())
                return null;
            return la.get(0);
        } finally {
            em.close();
        }
    }
}
