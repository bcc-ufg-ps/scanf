/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.jatai.ps.principal;

import br.ufg.jatai.ps.dao.AlunoDAO;
import br.ufg.jatai.ps.dao.DisciplinaDAO;
import br.ufg.jatai.ps.dao.FabricaDAO;
import br.ufg.jatai.ps.dao.jpa.FabricaDAOJPA;
import br.ufg.jatai.ps.modelo.Aluno;
import br.ufg.jatai.ps.modelo.Disciplina;
import java.util.List;

/**
 *
 * @author Paulo Júnior
 */
public class ScanfDesktop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FabricaDAO fDAO = new FabricaDAOJPA();
        AlunoDAO aDAO = fDAO.obterAlunoDAO();
        DisciplinaDAO dDAO = fDAO.obterDisciplinaDAO();

        try {

            Aluno a = new Aluno();
            a.setNome("Paulo");
            a.setEmail("paulo@paulo.com.br");
            a.setSenha("1234");
            aDAO.salvar(a);

            Disciplina d = new Disciplina();
            d.setNome("Projeto de Software");
            d.setProfessor("Paulo");
            d.setAluno(a);
            dDAO.salvar(d);

            d = new Disciplina();
            d.setNome("ED1");
            d.setProfessor("Paulo");
            d.setAluno(a);
            dDAO.salvar(d);

            dDAO.editarFrequenciaENota(d.getId(), 2, 2, 4);

            List<Disciplina> dList = dDAO.obterDisciplinasPorNomeOuProfessor(a, "ED");
            for (Disciplina di : dList) {
                System.out.println(di);
            }

            // Aluno a1 = aDAO.obterAlunoPorEmailESenha("teste", "123");
            Aluno a1 = aDAO.obterAlunoPorEmail("paulo@paulo.com.br");
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

}
