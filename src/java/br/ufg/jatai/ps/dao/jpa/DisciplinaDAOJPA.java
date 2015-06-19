package br.ufg.jatai.ps.dao.jpa;

import br.ufg.jatai.ps.dao.DisciplinaDAO;
import br.ufg.jatai.ps.modelo.Aluno;
import br.ufg.jatai.ps.modelo.Disciplina;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class DisciplinaDAOJPA implements DisciplinaDAO {

    @Override
    public void salvar(Disciplina disiplina) {
        EntityManager em = JPAUtil.obterConexao();
        try {
            em.getTransaction().begin();
            em.persist(disiplina);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Disciplina> obterDisciplinasPorAluno(Aluno aluno) {
        EntityManager em = JPAUtil.obterConexao();
        try {
            TypedQuery<Disciplina> tq = em.createNamedQuery(Disciplina.OBTER_DISCIPLINAS_POR_ALUNO, Disciplina.class);
            tq.setParameter("idAluno", aluno.getId());
            return tq.getResultList();
        } finally {
            em.close();
        }
    }
    
    @Override
    public void atualizar(Disciplina disciplina) {
        EntityManager em = JPAUtil.obterConexao();
        try {
            em.getTransaction().begin();
            em.merge(disciplina);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Override
    public void remover(Disciplina disciplina) {
        EntityManager em = JPAUtil.obterConexao();
        try {
            em.getTransaction().begin();
            disciplina = em.merge(disciplina);
            em.remove(disciplina);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
