/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.jatai.ps.beans;

import br.ufg.jatai.ps.dao.AlunoDAO;
import br.ufg.jatai.ps.dao.jpa.FabricaDAOJPA;
import br.ufg.jatai.ps.modelo.Aluno;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "baluno")
public class AlunoBean {

    private final AlunoDAO aDAO = (new FabricaDAOJPA()).obterAlunoDAO();
    private Aluno aluno = new Aluno();
    private String confirmarSenha;
    
    public String cadastrarAluno() {
        aDAO.salvar(aluno);
        aluno = new Aluno();
        return "login.xhtml?faces-redirect=true";
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getConfirmarSenha() {
        return confirmarSenha;
    }

    public void setConfirmarSenha(String confirmarSenha) {
        this.confirmarSenha = confirmarSenha;
    }
}
