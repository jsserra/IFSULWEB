/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.model;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 *
 * @author JSF
 */
@Entity
@Table(name = "parcela")
public class Parcela implements Serializable{
    
    @EmbeddedId
    private ParcelaID parcelaID;
    
    @NotNull(message = "O valor deve ser informado")
    @Min(value = 0, message = "O valor não pode ser negativo")
    @Column(nullable = false, columnDefinition = "decimal(12,2)")
    private Double valor;
    
    @NotNull(message = "A data de vencimento não pode ser nulo")
    //@Past(message = "A data do vencimento não pode ser anterior a de hoje")
    @Future(message = "A data do vencimento não pode ser anterior a de hoje")
    @Temporal(TemporalType.DATE)    
    @Column(nullable = false)
    private Calendar vencimento;  
  
    @Min(value = 0, message = "O valor do pagamento não pode ser negativo")
    @Column(name="valor_pagamento", columnDefinition = "decimal(12,2)")
    private Double valorPagamento;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "data_pagamento")
    private Calendar dataPagamento;

    public ParcelaID getParcelaID() {
        return parcelaID;
    }

    public void setParcelaID(ParcelaID parcelaID) {
        this.parcelaID = parcelaID;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Calendar getVencimento() {
        return vencimento;
    }

    public void setVencimento(Calendar vencimento) {
        this.vencimento = vencimento;
    }

    public Double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(Double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public Calendar getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Calendar dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
    
    
}
