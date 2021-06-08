/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author JSF
 */
@Named
@ViewScoped
public class ControleFormulario implements Serializable {
    
    private String nome;
    private String email;
    private LocalDate nascimento;
    
    public String processarFormulario(){
        String mensagem = "Você informou: " +
                "Nome: " + this.nome + ", e-mail: " + this.email + 
                ", Nascimento: " + this.nascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                FacesMessage msg = new FacesMessage(mensagem);
                FacesContext.getCurrentInstance().addMessage(null, msg);
                return "sobre";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public void setNascimento(LocalDate nascimento) {
        this.nascimento = nascimento;
    }
    
    
    
    
}
