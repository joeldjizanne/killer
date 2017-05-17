/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.service;

import app.genieLogiciel.djangui.model.Emprunt;
import app.genieLogiciel.djangui.persistence.EmpruntJpaController;
import app.genieLogiciel.djangui.persistence.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author kevinash
 */
public class EmpruntDAO extends AbstractDAO {

    private static final EmpruntJpaController dao;

    static {
        dao = new EmpruntJpaController(emf);
    }

    public void addEmprunt(Emprunt e) {
        dao.create(e);
    }

    public void editEmprunt(Emprunt e) throws Exception {
        dao.edit(e);
    }

    public void deleteEmprunt(Integer idEmprunt) throws NonexistentEntityException {
        dao.destroy(idEmprunt);
    }

    public List<Emprunt> getAllEmprunts() {
        return dao.findEmpruntEntities();
    }

    public int getNumberOfEmprunts() {
        return dao.getEmpruntCount();
    }
}
