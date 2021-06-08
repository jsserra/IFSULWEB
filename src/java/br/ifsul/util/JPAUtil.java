/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ifsul.util;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author JSF
 */
public class JPAUtil {

    private static EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("IFSULPUWEB");
        } catch (Exception e) {
            System.out.println("Erro gerar seção JPA" + e.getMessage());
        }
    }

    @Produces
    @RequestScoped
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void close(@Disposes EntityManager em) {
        try {
            em.close();
        } catch (Exception e) {
            System.out.println("Erro ao fehar seção JPA" + e.getMessage());
        }
    }

}
