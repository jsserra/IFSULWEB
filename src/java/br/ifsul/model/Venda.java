/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JSF
 */

@Entity
@Table
public class Venda implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer Id;
    
    @NotNull(message = "A data não pode ser nula")
    @Temporal(TemporalType.DATE)
    @Column(name = "data", nullable = false)
    private Calendar data;
    
    @NotNull(message = "O valor deve ser informado")
    @Min(value = 0, message = "O valor total não pode ser negativo")
    @Column(name = "valor_total", nullable = false, columnDefinition = "decimal(12,2)")
    private Double valorTotal;
    
    @NotNull(message = "A quantidade de parcelas deve ser informada")
    @Min(value = 0, message = "Deve ser informado pelo menos uma parcela")    
    @Column(nullable = false)
    private Integer parcelas;
    
    @NotNull(message = "informar a pessoa")
    @ManyToOne
    @JoinColumn(name="idpessoafisica", referencedColumnName = "id", nullable = false)
    private PessoaFisica pessoaFissica;
    
    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<VendaItens> itens = new ArrayList<>();
    
    @OneToMany(mappedBy = "parcelaID.venda", cascade = CascadeType.ALL, orphanRemoval = false, fetch = FetchType.LAZY)
    private List<Parcela> listaParcelas = new ArrayList<>();

    public Venda() {
        this.valorTotal = 0.0;
    } 
    
    //Métodos utilitários - Adicionar e Remover ItemVenda
    public void adicionarItem(VendaItens obj){
        obj.setVenda(this);
        this.valorTotal += obj.getValorTotal();
        this.itens.add(obj);
    }
    
    public void removerItem(int index){
        VendaItens obj = this.itens.get(index);
        this.valorTotal -= obj.getValorTotal();
        this.itens.remove(index);
    }
    
    //Método utilitário para gerar parcelas
    public void gerarParcelas(){
        Double valorParcela = this.valorTotal / this.parcelas;
        for(int i = 1; i <= this.parcelas; i++){
            Parcela p = new Parcela();
            ParcelaID id = new ParcelaID();
            id.setNumero(i);
            id.setVenda(this);
            p.setParcelaID(id);
            p.setValor(valorParcela);
            Calendar vencimento = (Calendar) this.data.clone();
            vencimento.add(Calendar.MONTH, i);
            p.setVencimento(vencimento);
            this.listaParcelas.add(p);
        }
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public Calendar getData() {
        return data;
    }

    public void setData(Calendar data) {
        this.data = data;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getParcelas() {
        return parcelas;
    }

    public void setParcelas(Integer parcelas) {
        this.parcelas = parcelas;
    }

    public PessoaFisica getPessoaFissica() {
        return pessoaFissica;
    }

    public void setPessoaFissica(PessoaFisica pessoaFissica) {
        this.pessoaFissica = pessoaFissica;
    }

    public List<Parcela> getListaParcelas() {
        return listaParcelas;
    }

    public void setListaParcelas(List<Parcela> listaParcelas) {
        this.listaParcelas = listaParcelas;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.Id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Venda other = (Venda) obj;
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
        return true;
    }

    public List<VendaItens> getItens() {
        return itens;
    }

    public void setItens(List<VendaItens> itens) {
        this.itens = itens;
    }
    
    
    
}
