/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.converters;

import br.ifsul.model.Produto;
import br.ifsul.util.JPAUtil;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author JSF
 */
@FacesConverter(value = "converterProduto")
public class ConverterProduto implements Converter, Serializable{
    

    EntityManager em = Persistence.createEntityManagerFactory("IFSULPUWEB").createEntityManager();
    //converte da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
       if(string == null || string.equals("Selecione um registro")){
       return null;
       }
       return em.find(Produto.class, Integer.parseInt(string));
    }

    //converte do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        if( o == null){
            return null;
        }
        Produto obj = (Produto) o;
        return obj.getId().toString();
    }
    
}
