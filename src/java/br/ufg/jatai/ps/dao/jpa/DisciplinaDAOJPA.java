package br.ufg.jatai.ps.dao.jpa;

import br.ufg.jatai.ps.dao.DisciplinaDAO;
import br.ufg.jatai.ps.modelo.Aluno;
import br.ufg.jatai.ps.modelo.Disciplina;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import org.eclipse.persistence.internal.jpa.querydef.CriteriaQueryImpl;

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
    
    public List<Disciplina> obterDisciplinasPorNomeOuProfessor(Aluno aluno, String texto) {
        EntityManager em = JPAUtil.obterConexao();
        try {
            TypedQuery<Disciplina> tq = em.createNamedQuery(Disciplina.OBTER_DISCIPLINAS_POR_NOME_OU_PROF, Disciplina.class);
            tq.setParameter("idAluno", aluno.getId());
            tq.setParameter("texto", "%" + texto + "%");
            return tq.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void editarFrequenciaENota(Long idDisciplina, int nFaltasOcorridas, double pontosObtidos, double pontosDistribuidos) {
        EntityManager em = JPAUtil.obterConexao();
        try {
            em.getTransaction().begin();
            Disciplina d = em.find(Disciplina.class, idDisciplina);
            d.setnFaltasOcorridas(nFaltasOcorridas);
            d.setPontosObtidos(pontosObtidos);
            d.setPontosDistribuidos(pontosDistribuidos);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
