/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.jatai.ps.beans;

import br.ufg.jatai.ps.dao.DisciplinaDAO;
import br.ufg.jatai.ps.dao.jpa.FabricaDAOJPA;
import br.ufg.jatai.ps.modelo.Aluno;
import br.ufg.jatai.ps.modelo.Disciplina;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "bdisciplina")
public class DisciplinaBean {

    private final DisciplinaDAO dDAO = (new FabricaDAOJPA()).obterDisciplinaDAO();

    public List<Disciplina> getDisciplinas() {
        Aluno u = new Aluno();
        u.setId(1L);
        return dDAO.obterDisciplinasPorAluno(u);
    }

}
