/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.bean;

import br.ifsul.dao.CidadeDao;
import br.ifsul.dao.PessoaFisicaDao;
import br.ifsul.model.PessoaFisica;
import br.ifsul.model.TipoEndereco;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author JSF
 */
@Named
@ViewScoped
public class ControlePessoaFisica implements Serializable {

    @Inject
    private PessoaFisicaDao<PessoaFisica> dao;
    
    @Inject
    private CidadeDao daoCidade;
    
    @Inject
    private TipoEndereco daoTipoEndereco;
    
    private PessoaFisica objeto;

    /*public ControlePessoaFisica(PessoaFisicaDao dao, CidadeDao daoCidade, TipoEndereco daoTipoEndereco) {
        this.dao = dao;
        this.daoCidade = daoCidade;
        this.daoTipoEndereco = daoTipoEndereco;
    }*/
    

    public String listar() {
        return "/privado/pessoaFisica/listar?faces-redirect=true";
    }

    public void novo() {
        objeto = new PessoaFisica();
    }

    public void salvar() {
        dao.persiste(objeto);
    }

    public String cancelar() {
        return "listar?faces-redirect=true";
    }

    public void editar(Integer id) {
        objeto = dao.localizar(id);
    }

    public void remover(Integer id) {
        objeto = dao.localizar(id);
        dao.remove(objeto);
    }

    public CidadeDao getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDao daoCidade) {
        this.daoCidade = daoCidade;
    }

    public TipoEndereco getDaoTipoEndereco() {
        return daoTipoEndereco;
    }

    public void setDaoTipoEndereco(TipoEndereco daoTipoEndereco) {
        this.daoTipoEndereco = daoTipoEndereco;
    }

    public PessoaFisica getObjeto() {
        return objeto;
    }

    public void setObjeto(PessoaFisica objeto) {
        this.objeto = objeto;
    }

    public PessoaFisicaDao<PessoaFisica> getDao() {
        return dao;
    }

    public void setDao(PessoaFisicaDao<PessoaFisica> dao) {
        this.dao = dao;
    }
    

    
}
