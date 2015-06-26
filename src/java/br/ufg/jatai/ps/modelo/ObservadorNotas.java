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
public class ObservadorNotas implements Observador {

    @Override
    public void atualizar(Observado o) {
        DisciplinaBean bdisciplina = (DisciplinaBean) o;
        Disciplina d = bdisciplina.getDisciplina();
        double pontosNecessarios = d.getNotaMinimaParaAprovacao() - d.getPontosObtidos();
        if (pontosNecessarios == 0.0) {
            Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                    "Parabéns! Você tem a nota mínima necessária para ser aprovado nesta disciplina. Basta não ser reprovado por frequência!",
                    "" + d.getId());
        }
        if (pontosNecessarios == 1.0) {
            Mensagens.adicionarMensagem(FacesMessage.SEVERITY_INFO,
                    "Vamos lá, só falta um ponto para você ser aprovado nesta disciplina!",
                    "" + d.getId());
        }
    }

}
