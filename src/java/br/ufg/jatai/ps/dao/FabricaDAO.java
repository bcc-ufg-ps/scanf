package br.ufg.jatai.ps.dao;

public abstract class FabricaDAO {

    public abstract AlunoDAO obterAlunoDAO();

    public abstract DisciplinaDAO obterDisciplinaDAO();
}
