/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.dao;

import br.ifsul.model.Cidade;
import br.ifsul.model.TipoEndereco;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author JSF
 */
public class TipoEnderecoDao extends Paginacao implements Serializable{
    
    @Inject
    EntityManager em;
    
    private DAOGenerico<TipoEndereco> dao; 

    @PostConstruct
    void start(){
        this.dao = new DAOGenerico<>(this.em, TipoEndereco.class);
    }
    
    public List<TipoEndereco> getListaTodos(){
        return dao.getListaTodos();
    }
    
    public TipoEndereco localizar(Integer id){
        return dao.localizar(id);
    }


    
    
}
