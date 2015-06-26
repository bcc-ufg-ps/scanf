/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.jatai.ps.modelo;

/**
 *
 * @author Paulo
 */
public class ObservadorNotas implements Observador {

    @Override
    public void atualizar(Observado o) {
        Disciplina d = (Disciplina) o;
        double pontosNecessarios = d.getNotaMinimaParaAprovacao() - d.getPontosObtidos();
        if (pontosNecessarios == 0.0) {
            d.adicionarMensagemAlerta("Parabéns! Você está aprovado nesta disciplina.");
        }
        if (pontosNecessarios == 1.0) {
            d.adicionarMensagemAlerta("Vamos lá, só falta um ponto para você ser aprovado nesta disciplina!");
        }
    }

}
