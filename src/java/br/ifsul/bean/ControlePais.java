/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.bean;

import br.ifsul.dao.PaisDao;
import br.ifsul.model.Pais;
import br.ifsul.util.Util;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author JSF
 */
@Named
@SessionScoped
public class ControlePais implements Serializable{
    
    @Inject
    private PaisDao dao;
    
    private Pais objeto;
   
    
    
    public String listar(){
        return "/privado/pais/listar?faces-redirect=true";
    }
    
    public String novo(){
        objeto = new Pais();
        return "formulario?faces-redirect=true";
    }
    
    public String salvar(){
        if(dao.salvarPais(objeto)){
            Util.mensagemInformacao(dao.getMsg());
            return "listar?faces-redirect=true";
        }else{
            Util.mensagemErro(dao.getMsg());
            return "formulario?faces-redirect=true";
        }
    }
    
    public String cancelar(){
        return "listar?faces-redirect=true";
    }
    
    public String editar(Integer id){
        objeto = dao.localizar(id);
        return "formulario?faces-redirect=true";
    }
    
    public void remover(Integer id){
        objeto = dao.localizarPais(id);
        if(dao.removerPais(objeto)){
            Util.mensagemInformacao(dao.getMsg());
        }else{
            Util.mensagemErro(dao.getMsg());
        }
    }

    public PaisDao getDao() {
        return dao;
    }

    public void setDao(PaisDao dao) {
        this.dao = dao;
    }

    public Pais getObjeto() {
        return objeto;
    }

    public void setObjeto(Pais objeto) {
        this.objeto = objeto;
    }
    
}
