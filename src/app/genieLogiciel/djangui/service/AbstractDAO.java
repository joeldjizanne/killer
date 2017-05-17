/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author kevinash
 */
public abstract class AbstractDAO {

    private final static String UNIT_NAME = "DjanguiAppPU";
    protected final static EntityManagerFactory emf;

    static {
        emf = Persistence.createEntityManagerFactory(UNIT_NAME);
    }
}
