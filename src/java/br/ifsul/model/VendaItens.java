/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 *
 * @author JSF
 */
@Entity
@Table(name = "venda_itens")
public class VendaItens implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "A quantidade deve ser informado")
    @Min(value = 0, message = "A quantidade não pode ser negativo")
    @Column(name = "quantidade", nullable = false, columnDefinition = "decimal(12,2)")
    private Double quantidade;
    
    @NotNull(message = "O valor do item deve ser informado")
    @Min(value = 0, message = "O valor do item não pode ser negativo")
    @Column(name = "valor_unitario", nullable = false, columnDefinition = "decimal(12,2)")
    private Double valorUnitario;
    
    @NotNull(message = "Valor total nulo")
    @Min(value = 0, message = "O valor total não pode ser negativo")
    @Column(name = "valor_total", nullable = false, columnDefinition = "decimal(12,2)")
    private Double valorTotal;

    @NotNull(message = "A venda não pode ser nula")
    @ManyToOne
    @JoinColumn(name = "idvenda", referencedColumnName = "id", nullable = false)
    private Venda venda;

    @NotNull(message = "O produto não pode ser nulo")
    @ManyToOne
    @JoinColumn(name = "idproduto", referencedColumnName = "id", nullable = false)
    private Produto produto;
   

    public VendaItens() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(Double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

}
