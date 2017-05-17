/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.service;

import app.genieLogiciel.djangui.model.Action;
import app.genieLogiciel.djangui.persistence.ActionJpaController;
import app.genieLogiciel.djangui.persistence.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author kevinash
 */
public class ActionDAO extends AbstractDAO {
    private static ActionJpaController dao;
    
    static {
        dao = new ActionJpaController(emf);
    }

    public static void addAction(Action a) {
        dao.create(a);
    }
    
    public static void editAction(Action a) throws Exception{
        dao.edit(a);
    }
    
    public static void deleteAction(Integer idAction) throws NonexistentEntityException {
        dao.destroy(idAction);
    }
    
    public static List<Action> getAllActions() {
        return dao.findActionEntities();
    }
    
    public static int getNumberOfActions() {
        return dao.getActionCount();
    }
    
}
