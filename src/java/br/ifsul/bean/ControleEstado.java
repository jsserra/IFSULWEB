/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.bean;

import br.ifsul.dao.EstadoDao;
import br.ifsul.dao.PaisDao;
import br.ifsul.model.Estado;
import br.ifsul.model.Pais;
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
public class ControleEstado implements Serializable {

    @Inject
    private EstadoDao dao;
    
    @Inject
    private PaisDao daoPais;

    private Estado objeto;
    
    private Pais pais;
    
    private Integer paisId;
    
    

    public String listar() {
        return "/privado/estado/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Estado();
    }

    public void salvar() {
        this.pais = daoPais.localizarPais(this.paisId);
        objeto.setPais(pais);
        dao.persiste(objeto);
    }

  /*  public String cancelar() {
        return "listar?faces-redirect=true";
    }*/

    public void editar(Integer id) {
        objeto = dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        dao.remove(objeto);
    }

    public Estado getObjeto() {
        return objeto;
    }

    public void setObjeto(Estado objeto) {
        this.objeto = objeto;
    }

    public EstadoDao getDao() {
        return dao;
    }

    public void setDao(EstadoDao dao) {
        this.dao = dao;
    }

    public PaisDao getDaoPais() {
        return daoPais;
    }

    public void setDaoPais(PaisDao daoPais) {
        this.daoPais = daoPais;
    }

    public Integer getPaisId() {
        return paisId;
    }

    public void setPaisId(Integer paisId) {
        this.paisId = paisId;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }
    
    

}
