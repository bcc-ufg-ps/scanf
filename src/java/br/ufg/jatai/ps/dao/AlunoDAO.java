package br.ufg.jatai.ps.dao;

import br.ufg.jatai.ps.modelo.Aluno;

public interface AlunoDAO {
    public void salvar(Aluno aluno);
    public Aluno obterAlunoPorEmail(String email);
}
