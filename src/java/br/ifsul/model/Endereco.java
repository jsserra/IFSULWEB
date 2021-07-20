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
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author JSF
 */
@Entity
public class Endereco implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @NotNull(message = "nickname não pode ser nulo!")
    @NotBlank(message = "nickname não pode ser em branco!")
    @Length(max = 20, message = "o apelido não pode ter mais de {max} caracteres")
    @Column(name = "nickname", length = 20, nullable = false)
    private String nickname;

    @NotNull(message = "endereco não pode ser nulo!")
    @NotBlank(message = "endereco não pode ser em branco!")
    @Length(max = 50, message = "o endereco não pode ter mais de {max} caracteres")
    @Column(name = "endereco", length = 50, nullable = false)
    private String endereco;

    @NotNull(message = "numero não pode ser nulo!")
    @NotBlank(message = "numero não pode ser em branco!")
    @Length(max = 10, message = "o numero não pode ter mais de {max} caracteres")
    @Column(name = "numero", length = 10, nullable = false)
    private String numero;

    @Length(max = 20, message = "o complemento não pode ter mais de {max} caracteres")
    @Column(name = "complemento", length = 20)
    private String complemento;

    @NotNull(message = "cep não pode ser nulo!")
    @NotBlank(message = "cep não pode ser em branco!")
    @Length(max = 9, message = "o cep não pode ter mais de {max} caracteres")
    @Column(name = "cep", length = 9)
    private String cep;

    @NotNull(message = "bairro não pode ser nulo!")
    @NotBlank(message = "bairro não pode ser em branco!")
    @Length(max = 40, message = "o bairro não pode ter mais de {max} caracteres")
    @Column(name = "bairro", length = 40, nullable = false)
    private String bairro;

    @Length(max = 30, message = "o referencia não pode ter mais de {max} caracteres")
    @Column(name = "referencia", length = 30)
    private String referencia;

    @NotNull(message = "A pessoa deve ser informada")
    @ManyToOne
    @JoinColumn(name = "idpessoa", referencedColumnName = "id", nullable = false)
    private Pessoa pessoa;
    
    @NotNull(message = "O tipo de endereco deve ser informado")
    @ManyToOne
    @JoinColumn(name = "idtipoendereco", referencedColumnName = "id", nullable = false)
    private TipoEndereco tipoEndereco;

    @ManyToOne
    @JoinColumn(name = "cidade", referencedColumnName = "id", nullable = false)
    private Cidade cidade;
    
    
    public Endereco() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

}
