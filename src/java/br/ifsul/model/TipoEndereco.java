/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author JSF
 */
@Entity
@Table(name = "tipo_endereco")
public class TipoEndereco implements Serializable {
  
    @Id
   // @SequenceGenerator(name = "seq_tipo_endereco", sequenceName = "seq_tipo_endereco_id", allocationSize = 1)
   // @GeneratedValue(generator = "seq_tipo_endereco", strategy = GenerationType.SEQUENCE) 
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    
    @NotBlank(message = "A descricao deve ser informada")
    @Length(max = 30, message = "A decricao não pode ter mais de {max} caracteres")
    @NotNull(message = "A descrição não pode ser nula")
    @Column(name = "nome", length = 30, nullable = false)    
    private String descricao;  
    
    public TipoEndereco(){
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.id);
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
        final TipoEndereco other = (TipoEndereco) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
