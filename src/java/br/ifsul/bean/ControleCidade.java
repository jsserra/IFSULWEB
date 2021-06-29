/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.bean;

import br.ifsul.dao.CidadeDao;
import br.ifsul.dao.EstadoDao;
import br.ifsul.model.Cidade;
import br.ifsul.model.Estado;
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
public class ControleCidade implements Serializable {

    @Inject
    private CidadeDao dao;

    @Inject
    private EstadoDao daoEstado;

    private Cidade objeto;

    private Estado estado;

    private Integer estadoId;

    public String listar() {
        return "/privado/cidade/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new Cidade();
    }

    public void salvar() {
        this.estado = daoEstado.localizar(estadoId);
        objeto.setEstado(estado);
        dao.persistir(objeto);
    }

    public String cancelar() {
        return "listar?faces-redirect=true";
    }

    public void editar(Integer id) {
        objeto = dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        dao.remover(objeto);
    }

    public Cidade getObjeto() {
        return objeto;
    }

    public void setObjeto(Cidade objeto) {
        this.objeto = objeto;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public CidadeDao getDao() {
        return dao;
    }

    public void setDao(CidadeDao dao) {
        this.dao = dao;
    }

    public EstadoDao getDaoEstado() {
        return daoEstado;
    }

    public void setDaoEstado(EstadoDao daoEstado) {
        this.daoEstado = daoEstado;
    }

}
