/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.jatai.ps.beans;

import br.ufg.jatai.ps.dao.AlunoDAO;
import br.ufg.jatai.ps.dao.DisciplinaDAO;
import br.ufg.jatai.ps.dao.jpa.FabricaDAOJPA;
import br.ufg.jatai.ps.modelo.Aluno;
import br.ufg.jatai.ps.modelo.Disciplina;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.persistence.PersistenceException;

@ManagedBean(name = "bdisciplina")
public class DisciplinaBean {

    private final DisciplinaDAO dDAO = (new FabricaDAOJPA()).obterDisciplinaDAO();
    private final AlunoDAO aDAO = (new FabricaDAOJPA()).obterAlunoDAO();
    private Disciplina disciplina = new Disciplina();
    
    public String atualizarDisciplina(Disciplina disciplina) {
        dDAO.atualizar(disciplina);
        return "minhasDisciplinas?faces-redirect=true";
    }    

    public String removerDisciplina(Disciplina disciplina) {
        dDAO.remover(disciplina);
        return "minhasDisciplinas?faces-redirect=true";
    }    
    
    public String cadastrarDisciplina() {
        Aluno u = aDAO.obterAlunoPorEmail("teste");
        disciplina.setAluno(u);
        dDAO.salvar(disciplina);
        disciplina = new Disciplina();
        return "minhasDisciplinas?faces-redirect=true";
    }    

    public List<Disciplina> getDisciplinas() {
        Aluno u; 
        try {
            u = aDAO.obterAlunoPorEmail("teste");
        } catch(PersistenceException ex) {
            u = new Aluno();            
        }        
        return dDAO.obterDisciplinasPorAluno(u);
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }

    
}
