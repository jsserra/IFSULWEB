/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.dao;

import br.ifsul.model.Cidade;
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
public class CidadeDao extends Paginacao implements Serializable {

    @Inject
    EntityManager em;

    private DAOGenerico<Cidade> dao;   
    
    public String ordem = "nome";

    @PostConstruct
    void start() {        
        this.dao = new DAOGenerico<>(this.em, Cidade.class);
    }
    
    
    public boolean persistir(Cidade cidade) {
        boolean persistiu = false;
        if(cidade.getId() == null){
            persistiu = dao.persist(cidade);
        } else {
            persistiu = dao.merge(cidade);
        }
        if (persistiu) {
            Util.mensagemInformacao(dao.getMensagem());
        } else {
            Util.mensagemErro(dao.getMensagem());
        }
        return persistiu;
    }
    
    public boolean remover(Cidade cidade){
        boolean remover = false;
        if(dao.remove(cidade)){
            remover = true;
            Util.mensagemInformacao(dao.getMensagem());
        }else{
            Util.mensagemErro(dao.getMensagem());
        }
        return remover;
    }
    
    public Cidade localizar(Integer id){
        return dao.localizar(id);
    }
    
    public List<Cidade> getListaTodos(){        
        return dao.getListaTodos();
    }
    
    public List<Cidade> getListarCidade() throws Exception{
        List<Cidade> lista = null;
        em.getTransaction().begin();
        lista = em.createQuery("from Cidade").getResultList(); //dao.listarAll(); 
        em.getTransaction().commit();
        return  lista;
    }
    
        //Método que retorna a consulta páginada
    public List<Cidade> getListaPaginada() {
        String jpql = "from Cidade";
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

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }
    
    

}
