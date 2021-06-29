/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.dao;

import br.ifsul.util.Util;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;


/**
 *
 * @author JSF
 */
public class DAOGenerico<T> implements Serializable {

    private final EntityManager em;
    private final Class<T> classe;

    protected List<T> listaObjetos;
    protected List<T> listaTodos;
    protected Class classePersistente;
    protected String mensagem = "";
   /* protected String ordem = "id"; //para ordenar pelo atributo desejado;
    //páginação, exibir qtd de objetos na tela, posição atutal
    protected String filtro = "";
    protected Integer maximoObjetos = 8;
    protected Integer posicaoAtual = 0;
    protected Integer totalObjetos = 0;*/

    public DAOGenerico(EntityManager em, Class<T> classe) {
        this.em = em;
        this.classe = classe;
    }
    
    public void roolback() {
        if (em.getTransaction().isActive() == false) {
            em.getTransaction().begin();
        }
        em.getTransaction().rollback();
    }

    public boolean persist(T obj) {
        try {
            em.getTransaction().begin();
            em.persist(obj);
            em.getTransaction().commit();
            mensagem = "Salvo com sucesso!";
            return true;
        } catch (Exception e) {
            roolback();
            mensagem = "Erro ao persistir: " + Util.getMensagemErro(e);
            return false;
        }
    }

    public boolean merge(T obj) {
        try {
            em.getTransaction().begin();
            em.merge(obj);
            em.getTransaction().commit();
            mensagem = "Atualizado com sucesso!";
            return true;
        } catch (Exception e) {
            roolback();
            mensagem = "Erro ao atualizar: " + Util.getMensagemErro(e);
            return false;
        }
    }

    public boolean remove(T obj) {
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Excluido com sucesso!";
            return true;
        } catch (Exception e) {
            roolback();
            mensagem = "Erro ao remover: " + Util.getMensagemErro(e);
            return false;
        }
    }

    public T localizar(Integer id) {
        roolback();
        T obj = (T) em.find(classe, id);
        return obj;
    }

    public void setListaTodos(List<T> listaTodos) {
        this.listaTodos = listaTodos;
    }


    //Método que retorna a consulta páginada
 /*   public List<T> getListaObjetos() {
        String jpql = "from " + classe.getSimpleName();
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
    }    */

    public void setListaObjetos(List<T> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public List<T> getListaTodos() {
        String jpql = "from " + classe.getSimpleName() + " order by nome";
       // Query q = em.createQuery(jpql);
        return em.createQuery(jpql).getResultList();
        //return q.getResultList();
    }

  /*  public void primeiro() {
        posicaoAtual = 0;
    }

    public void anterior() {
        posicaoAtual -= maximoObjetos;
        if (posicaoAtual < 0) {
            posicaoAtual = 0;
        }
    }

    public void proximo() {
        if (posicaoAtual + maximoObjetos < totalObjetos) {
            posicaoAtual += maximoObjetos;
        }
    }

    public void ultimo() {
        int resto = totalObjetos % maximoObjetos;
        if (resto > 0) {
            posicaoAtual = totalObjetos - resto;
        } else {
            posicaoAtual = totalObjetos - maximoObjetos;
        }
    }

    public String getMensagemNavegacao() {
        int ate = posicaoAtual + maximoObjetos;
        if (ate > totalObjetos) {
            ate = totalObjetos;
        }
        return "Listando de " + (posicaoAtual + 1) + " até " + ate + " de " + totalObjetos + " registros";
    }*/
    
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;   }

  

}
