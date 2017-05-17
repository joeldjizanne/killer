/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.persistence;

import app.genieLogiciel.djangui.persistence.exceptions.NonexistentEntityException;
import app.genieLogiciel.djangui.model.Fonds;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author kevinash
 */
public class FondsJpaController implements Serializable {

    public FondsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fonds fonds) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(fonds);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fonds fonds) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            fonds = em.merge(fonds);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fonds.getIdFonds();
                if (findFonds(id) == null) {
                    throw new NonexistentEntityException("The fonds with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fonds fonds;
            try {
                fonds = em.getReference(Fonds.class, id);
                fonds.getIdFonds();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fonds with id " + id + " no longer exists.", enfe);
            }
            em.remove(fonds);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fonds> findFondsEntities() {
        return findFondsEntities(true, -1, -1);
    }

    public List<Fonds> findFondsEntities(int maxResults, int firstResult) {
        return findFondsEntities(false, maxResults, firstResult);
    }

    private List<Fonds> findFondsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fonds.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Fonds findFonds(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fonds.class, id);
        } finally {
            em.close();
        }
    }

    public int getFondsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fonds> rt = cq.from(Fonds.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
