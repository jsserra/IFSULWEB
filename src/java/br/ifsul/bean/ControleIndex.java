/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.bean;

import java.io.Serializable;
import java.util.Calendar;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author JSF
 */
@Named
@ViewScoped
//@RequesScoped "Ciclo de vida curto"
//@SessionScoped 
//@AplicationScoped
public class ControleIndex implements Serializable {

    private String mensagem;
    private Calendar dataHora;

    public ControleIndex() {
        this.mensagem = "Olá mundo! Seja bem-vindo ao Java Server Faces!";
        dataHora = Calendar.getInstance();
    }

    public Calendar getDataHora() {
        return dataHora;
    }

    public void setDataHora(Calendar dataHora) {
        this.dataHora = dataHora;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    //metodos
    public String sobre() {
        return "sobre?faces-redirect=true";
    }

    public String index() {
        return "index?faces-redirect=true";
    }

}
