/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.dao;

import br.ifsul.model.Estado;
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
public class EstadoDao extends Paginacao implements Serializable {

    @Inject
    EntityManager em;

    private DAOGenerico<Estado> dao;

    @PostConstruct
    void start() {
        this.dao = new DAOGenerico<>(this.em, Estado.class);
    }

    public List<Estado> listarEstado() {
        return dao.getListaTodos();
    }

    public boolean persiste(Estado estado) {
        boolean persistiu = false;
        if (estado.getId() == null) {
            if (dao.persist(estado)) {
                persistiu = dao.persist(estado);
                Util.mensagemInformacao(dao.getMensagem());
            } else {
                Util.mensagemErro(dao.getMensagem());
            }
        } else {
            if (dao.merge(estado)) {
                persistiu = dao.merge(estado);
                Util.mensagemInformacao(dao.getMensagem());
            } else {
                Util.mensagemErro(dao.getMensagem());
            }
        }
        return persistiu;
    }

    public boolean remove(Estado estado) {
        boolean remover = false;
        if(dao.remove(estado)){
            remover = dao.remove(estado);
            Util.mensagemInformacao(dao.getMensagem());
        }else{
            Util.mensagemErro(dao.getMensagem());
        }
        return remover;
    }

    //Método que retorna a consulta páginada
    public List<Estado> getListaObjetos() {
        String jpql = "from Estado";
        String where = "";
        filtro = filtro.replaceAll("[';-]", "");//se tiver algum dos caracateres entre conchetes vai alterar para string vazio
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
    
    public Estado localizar(Integer id){
        return dao.localizar(id);
    }

}
