/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.jatai.ps.util;

import br.ufg.jatai.ps.beans.AlunoBean;
import br.ufg.jatai.ps.modelo.Aluno;
import javax.faces.context.FacesContext;

/**
 *
 * @author Paulo
 */
public class Sessao {

    public static Aluno obterAlunoSessao() {
        FacesContext context = FacesContext.getCurrentInstance();
        AlunoBean baluno = (AlunoBean) context.getELContext().getELResolver().getValue(context.getELContext(), null, "baluno");
        if (baluno != null) {
            return baluno.getAlunoSessao();
        }
        return null;
    }

}
