/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.service;

import app.genieLogiciel.djangui.model.Adherent;
import app.genieLogiciel.djangui.persistence.AdherentJpaController;
import app.genieLogiciel.djangui.persistence.exceptions.IllegalOrphanException;
import app.genieLogiciel.djangui.persistence.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevinash
 */
public class AdherentDAO extends AbstractDAO {

    private static final AdherentJpaController dao;

    static {
        dao = new AdherentJpaController(emf);
    }

    public static void addAdherent(Adherent a) {
        dao.create(a);
    }
    
    public static Adherent getAdherent(Integer matricule) {
        return dao.findAdherent(matricule);
    }
    
    public static void updateAdherent(Adherent a) {
        try {
            dao.edit(a);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AdherentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(AdherentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void deleteAdherent(Integer matricule) {
        try {
            dao.destroy(matricule);
        } catch (IllegalOrphanException ex) {
            Logger.getLogger(AdherentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(AdherentDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static List<Adherent> getAllAdherents() {
        return dao.findAdherentEntities();
    }

    public static int getNumberOfAdherents() {
        return dao.getAdherentCount();
    }
}
