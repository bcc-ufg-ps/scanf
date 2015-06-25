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
import br.ufg.jatai.ps.util.Mensagens;
import br.ufg.jatai.ps.util.Sessao;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "bdisciplina")
public class DisciplinaBean {

    private final DisciplinaDAO dDAO = (new FabricaDAOJPA()).obterDisciplinaDAO();
    private final AlunoDAO aDAO = (new FabricaDAOJPA()).obterAlunoDAO();
    private Disciplina disciplina = new Disciplina();
    
    public String atualizarDisciplina(Disciplina disciplina) {
        disciplina.setDataUltimaAtualizacao(Calendar.getInstance().getTime());
        dDAO.atualizar(disciplina);
        return "minhasDisciplinas?faces-redirect=true";
    }    

    public String removerDisciplina(Disciplina disciplina) {
        dDAO.remover(disciplina);
        return "minhasDisciplinas?faces-redirect=true";
    }    
    
    public String cadastrarDisciplina() {
        Aluno alunoSessao = Sessao.obterAlunoSessao();        
        if (alunoSessao == null) {
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR, 
                    "Não foi possível cadastrar esse disciplina. Por favor, entre em contato com o administrador do sistema.");
            return null;
        }
        disciplina.setAluno(alunoSessao);
        dDAO.salvar(disciplina);
        disciplina = new Disciplina();
        return "minhasDisciplinas?faces-redirect=true";
    }    

    public List<Disciplina> getDisciplinas() {
        Aluno alunoSessao = Sessao.obterAlunoSessao();        
        if (alunoSessao == null) {
            Mensagens.adicionarMensagem(
                    FacesMessage.SEVERITY_ERROR, 
                    "Não foi possível recuperar a lista de disciplinas. Por favor, entre em contato com o administrador do sistema.");
            return new ArrayList<Disciplina>();
        }
        return dDAO.obterDisciplinasPorAluno(alunoSessao);
    }

    public Disciplina getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) {
        this.disciplina = disciplina;
    }    
}
