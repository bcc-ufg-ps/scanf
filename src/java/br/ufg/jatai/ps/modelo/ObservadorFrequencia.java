/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.jatai.ps.modelo;

import br.ufg.jatai.ps.beans.DisciplinaBean;
import br.ufg.jatai.ps.util.Mensagens;
import javax.faces.application.FacesMessage;

/**
 *
 * @author Paulo
 */
public class ObservadorFrequencia implements Observador {

    @Override
    public void atualizar(Observado o) {
        DisciplinaBean bdisciplina = (DisciplinaBean) o;
        Disciplina d = bdisciplina.getDisciplina();
        int faltasPermitidas = d.getFaltasPermitidas() - d.getFaltasOcorridas();
        if (faltasPermitidas == 1) {
            Mensagens.adicionarMensagem(FacesMessage.SEVERITY_ERROR, 
                    "Atenção, você pode faltar apenas mais uma vez nesta disciplina!", 
                    "" + d.getId());
        }
        if (faltasPermitidas == 0) {
            Mensagens.adicionarMensagem(FacesMessage.SEVERITY_ERROR, 
                    "Atenção, você não pode faltar mais nesta disciplina!", 
                    "" + d.getId());
        }
    }

}
