/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.service;

import app.genieLogiciel.djangui.model.Fonds;
import app.genieLogiciel.djangui.persistence.FondsJpaController;
import app.genieLogiciel.djangui.persistence.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author kevinash
 */
public class FondDAO extends AbstractDAO {
    private static final FondsJpaController dao;
    
    static {
        dao = new FondsJpaController(emf);
    }

    public void addFonds(Fonds e) throws Exception {
        dao.create(e);
    }
    
    public void editFonds(Fonds e) throws Exception{
        dao.edit(e);
    }
    
    public void deleteFonds(Integer idFonds) throws NonexistentEntityException {
        dao.destroy(idFonds);
    }
    
    public List<Fonds> getAllFonds() {
        return dao.findFondsEntities();
    }
    
    public int getNumberOfFonds() {
        return dao.getFondsCount();
    }
}
