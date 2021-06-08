/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.dao;

import br.ifsul.model.Pais;
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
public class PaisDao implements Serializable {

    @Inject
    EntityManager em;

    private DAOGenerico<Pais> dao;

    String ordem = "id"; //para ordenar pelo atributo desejado;
    //páginação, exibir qtd de objetos na tela, posição atutal
    String filtro = "";
    Integer maximoObjetos = 8;
    Integer posicaoAtual = 0;
    Integer totalObjetos = 0;
    
    private String msg;

    @PostConstruct
    void start() {
        this.dao = new DAOGenerico<>(this.em, Pais.class);
    }

    public List<Pais> getLista() {
        return dao.listaTodos;
    }

    public List<Pais> getListaPais() {
        return em.createQuery("from Pais order by nome").getResultList();
    }

    public boolean salvarPais(Pais pais) {
        boolean persistiu = false;
        if (pais.getId() == null) {
            persistiu = dao.persist(pais);
            msg = "Salvo com sucesso!";
        } else {
            persistiu = dao.merge(pais);
            msg = "Atualizado com Sucesso";
        }
        return persistiu;
    }

    public boolean salvar(Pais pais) {
        try {
            em.getTransaction().begin();
            if (pais.getId() == null) {
                em.persist(pais);
            } else {
                em.merge(pais);
            }
            em.getTransaction().commit();
            msg = "Objeto persisido com sucesso";
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive() == true) {
                em.getTransaction().rollback();
            }
            msg = "Erro ao persistir o objeto" + Util.getMensagemErro(e);
            return false;
        }
    }

    public boolean removerPais(Pais pais) {
        dao.remove(pais);
        msg = "Removido com sucesso";
        return true;
    }

    public boolean remover(Pais pais) {
        try {
            em.getTransaction().begin();
            em.remove(pais);
            em.getTransaction().commit();
            msg = "Objeto removido com sucesso";
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive() == true) {
                em.getTransaction().rollback();
            }
            msg = "Erro ao remover o objeto" + Util.getMensagemErro(e);
            return false;
        }
    }

    public Pais localizarPais(Integer id) {
        return dao.localizar(id);
    }

    public Pais localizar(Integer id) {
        return em.find(Pais.class, id);
    }
    
     //Método que retorna a consulta páginada
    public List<Pais> getListaObjetos() {
        String jpql = "from Pais";
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
    
        public List<Pais> getListaTodos() {
        String jpql = "from Pais" + " order by " + ordem;
        return em.createQuery(jpql).getResultList();
    }

    public void primeiro() {
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
            posicaoAtual = totalObjetos - 0;
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
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrdem() {
        return ordem;
    }

    public void setOrdem(String ordem) {
        this.ordem = ordem;
    }

    public String getFiltro() {
        return filtro;
    }

    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }

    public Integer getMaximoObjetos() {
        return maximoObjetos;
    }

    public void setMaximoObjetos(Integer maximoObjetos) {
        this.maximoObjetos = maximoObjetos;
    }

    public Integer getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Integer posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public Integer getTotalObjetos() {
        return totalObjetos;
    }

    public void setTotalObjetos(Integer totalObjetos) {
        this.totalObjetos = totalObjetos;
    }
    
    

}
