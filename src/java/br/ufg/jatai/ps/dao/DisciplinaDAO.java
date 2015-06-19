package br.ufg.jatai.ps.dao;

import br.ufg.jatai.ps.modelo.Aluno;
import br.ufg.jatai.ps.modelo.Disciplina;
import java.util.List;

public interface DisciplinaDAO {
    public void salvar(Disciplina disiplina);
    public List<Disciplina> obterDisciplinasPorAluno(Aluno aluno);
    public void editarFrequenciaENota(Disciplina disciplina);    
}
