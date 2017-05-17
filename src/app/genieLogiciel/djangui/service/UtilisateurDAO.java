/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.service;

import app.genieLogiciel.djangui.model.Utilisateur;
import app.genieLogiciel.djangui.persistence.UtilisateurJpaController;
import app.genieLogiciel.djangui.persistence.exceptions.NonexistentEntityException;
import static app.genieLogiciel.djangui.service.AbstractDAO.emf;
import java.util.List;

/**
 *
 * @author kevinash
 */
public class UtilisateurDAO extends AbstractDAO {

    private static final UtilisateurJpaController dao;

    static {
        dao = new UtilisateurJpaController(emf);
    }

    public static void addUtilisateur(Utilisateur e) throws Exception {
        dao.create(e);
    }
    
    public static void updateUtilisateur(Utilisateur u) throws Exception{
        dao.edit(u);
    }
    
    public static void editUtilisateur(Utilisateur e) throws Exception {
        dao.edit(e);
    }

    public static void deleteUtilisateur(String username) throws NonexistentEntityException {
        dao.destroy(username);
    }
    
    public static Utilisateur getUtilisateur(String username) {
        return dao.findUtilisateur(username);
    }
    
    public static List<Utilisateur> getAllUtilisateur() {
        return dao.findUtilisateurEntities();
    }

    public static int getNumberOfUtilisateur() {
        return dao.getUtilisateurCount();
    }

}
