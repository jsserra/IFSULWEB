/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.converters;

import br.ifsul.model.Pais;
import java.io.Serializable;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author JSF
 */
@FacesConverter(value = "converterPais")
public class ConverterPais implements Converter, Serializable{
    
    @Inject
    EntityManager em;
    //converte da tela para o objeto
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
       if(string == null || string.equals("Selecione um registro")){
       return null;
       }
       return em.find(Pais.class, Integer.parseInt(string));
    }

    //converte do objeto para a tela
    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object t) {
        if( t == null){
            return null;
        }
        Pais obj = (Pais) t;
        return obj.getId().toString();
    }
    
}
