package br.ufg.jatai.ps.dao.jpa;

import br.ufg.jatai.ps.dao.AlunoDAO;
import br.ufg.jatai.ps.dao.DisciplinaDAO;
import br.ufg.jatai.ps.dao.FabricaDAO;

public class FabricaDAOJPA extends FabricaDAO {

    @Override
    public AlunoDAO obterAlunoDAO() {
        return (new AlunoDAOJPA());
    }

    @Override
    public DisciplinaDAO obterDisciplinaDAO() {
        return (new DisciplinaDAOJPA());
    }

}
