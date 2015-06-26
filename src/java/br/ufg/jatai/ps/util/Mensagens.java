/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.jatai.ps.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

/**
 *
 * @author Paulo
 */
public class Mensagens {
    public static void adicionarMensagem(Severity sev, String msg, String componente) {
        FacesMessage fm = new FacesMessage(sev, msg, msg);
        FacesContext.getCurrentInstance().addMessage(componente, fm);
    }
}
