/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.testes.junit;

import br.ifsul.model.Estado;
import br.ifsul.model.Pais;
import br.ifsul.util.JPAUtil;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 *
 * @author JSF
 */
public class TesteEstado {

    EntityManager em;


    @BeforeEach
    public void setUp() {
        em = new JPAUtil().getEntityManager();
    }

    @AfterEach
    public void tearDown() {
        em.close();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void teste() {

        try {
            Estado e = new Estado();
            e.setNome("São Paulo");
            e.setUf("SP");
            e.setPais(em.find(Pais.class, 1));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
