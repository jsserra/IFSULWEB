/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.dao;

import br.ifsul.model.Produto;
import br.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

/**
 *
 * @author JSF
 */
public class ProdutoDao extends Paginacao implements Serializable {

    @Inject
    EntityManager em;

    private DAOGenerico<Produto> dao;
    
    private String ordem = "nome";

    @PostConstruct
    void start() {
        this.dao = new DAOGenerico(this.em, Produto.class);
    }

    public List<Produto> getListaTodos() {
        return dao.getListaTodos();
    }

    public Produto localizar(Integer id) {
        return dao.localizar(id);
    }

    public boolean persiste(Produto pessoa) {
        boolean persistiu = false;
        if (pessoa.getId() == null) {
            persistiu = dao.persist(pessoa);
        } else {
            persistiu = dao.merge(pessoa);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
        return persistiu;
    }

    public boolean remove(Produto pessoa) {
        boolean remover = false;
        if (dao.remove(pessoa)) {
            remover = dao.remove(pessoa);
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
        return remover;
    }
    
    public List<Produto> getListaPaginada(){
        String jpql = "from Produto";
        String where = "";
        filtro = filtro.replaceAll("[';-]", "");
        if (filtro.length() > 0) {
            if (ordem.equals("id")) {
                try {
                    Integer.parseInt(filtro);
                    where += " where" + ordem + " = '" + filtro + "' ";
                } catch (Exception e) {
                    e.getMessage();
                }
            } else {
                where += " where upper(" + ordem + ") like '" + filtro.toUpperCase() + "%' ";
            }
        }
        jpql += where;
        jpql += " order by " + ordem;
        totalObjetos = em.createQuery(jpql).getResultList().size();
        return em.createQuery(jpql).setFirstResult(posicaoAtual).setMaxResults(maximoObjetos).getResultList();
        
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }
    
    

}
