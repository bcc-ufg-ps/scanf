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
public interface Observado {
    public void adicionarObservador(Observador o);
    public void notificar();
}
