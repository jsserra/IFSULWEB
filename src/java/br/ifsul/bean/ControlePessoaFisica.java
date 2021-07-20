/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.bean;

import br.ifsul.dao.CidadeDao;
import br.ifsul.dao.PessoaFisicaDao;
import br.ifsul.dao.ProdutoDao;
import br.ifsul.dao.TipoEnderecoDao;
import br.ifsul.model.Cidade;
import br.ifsul.model.Endereco;
import br.ifsul.model.PessoaFisica;
import br.ifsul.model.Produto;
import br.ifsul.model.TipoEndereco;
import br.ifsul.util.Util;
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
    private TipoEnderecoDao daoTipoEndereco;
    
    @Inject
    private ProdutoDao daoProduto;
    
    private Produto produto;

    private PessoaFisica objeto;
    private Endereco endereco = null;
    private Boolean novoEndereco;
    private Cidade cidade = null;
    private Integer cidadeId;
    private TipoEndereco tipoEndereco = null;
    private Integer tipoEnderecoId;


    /*public ControlePessoaFisica(PessoaFisicaDao dao, CidadeDao daoCidade, TipoEndereco daoTipoEndereco) {
        this.dao = dao;
        this.daoCidade = daoCidade;
        this.daoTipoEndereco = daoTipoEndereco;
    }*/
    //gets e setters    
    public CidadeDao getDaoCidade() {
        return daoCidade;
    }

    public void setDaoCidade(CidadeDao daoCidade) {
        this.daoCidade = daoCidade;
    }

    public TipoEnderecoDao getDaoTipoEndereco() {
        return daoTipoEndereco;
    }

    public void setDaoTipoEndereco(TipoEnderecoDao daoTipoEndereco) {
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getNovoEndereco() {
        return novoEndereco;
    }

    public void setNovoEndereco(Boolean novoEndereco) {
        this.novoEndereco = novoEndereco;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Integer getCidadeId() {
        return cidadeId;
    }

    public void setCidadeId(Integer cidadeId) {
        this.cidadeId = cidadeId;
    }

    public TipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(TipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public Integer getTipoEnderecoId() {
        return tipoEnderecoId;
    }

    public void setTipoEnderecoId(Integer tipoEnderecoId) {
        this.tipoEnderecoId = tipoEnderecoId;
    }
    
        public ProdutoDao getDaoProduto() {
        return daoProduto;
    }

    public void setDaoProduto(ProdutoDao daoProduto) {
        this.daoProduto = daoProduto;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    //Métodos
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

    //ENDEREÇO
    public void novoEndereco() {
        endereco = new Endereco();
        novoEndereco = true;
    }

    public void alterarEndereco(int index) {
        endereco = objeto.getEnderecos().get(index);
        cidade = endereco.getCidade();
        tipoEndereco = endereco.getTipoEndereco();
        novoEndereco = false;
    }

    public void salvarEndereco() {
        if (cidadeId != null) {
            cidade = daoCidade.localizar(cidadeId);
        }
        endereco.setCidade(cidade);
        if (tipoEnderecoId != null) {
            tipoEndereco = daoTipoEndereco.localizar(tipoEnderecoId);
        }
        endereco.setTipoEndereco(tipoEndereco);
        if (this.novoEndereco) {
            objeto.adicionarEndereco(endereco);
        }       
    }

    public void removerEndereco(int index) {
        objeto.removerEndereco(index);
        Util.mensagemInformacao("Enderecoremovido com sucesso");
    }

    //DESEJO
    public void adicionarDesejo(){
        if(produto != null){
            if(!objeto.getDesejos().contains(produto)){
                objeto.getDesejos().add(produto);
                Util.mensagemInformacao("Desejo adicionado com sucesso!");
            }else {
                Util.mensagemErro("Este desejo já existe na sua lista!");
            }
        }
    }
    
    public void removerDesejo(int index){
        produto = objeto.getDesejos().get(index);
        objeto.getDesejos().remove(produto);
        Util.mensagemInformacao("Desejo removido com sucesso!");
    }

}
