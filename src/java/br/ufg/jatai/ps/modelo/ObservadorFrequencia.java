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
public class ObservadorFrequencia implements Observador {

    @Override
    public void atualizar(Observado o) {
        Disciplina d = (Disciplina) o;
        int faltasPermitidas = d.getFaltasPermitidas() - d.getFaltasOcorridas();
        if (faltasPermitidas == 1) {
            d.adicionarMensagemAlerta("Atenção, você pode faltar apenas mais uma vez nesta disciplina!");
        } 
        if (faltasPermitidas == 0) {
            d.adicionarMensagemAlerta("Atenção, você não pode faltar mais nesta disciplina!");
        } 
    }
    
}
