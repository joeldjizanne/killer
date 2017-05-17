/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.persistence;

import app.genieLogiciel.djangui.persistence.exceptions.NonexistentEntityException;
import app.genieLogiciel.djangui.model.Action;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import app.genieLogiciel.djangui.model.Adherent;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author kevinash
 */
public class ActionJpaController implements Serializable {

    public ActionJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Action action) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Adherent matriculeAdherent = action.getMatriculeAdherent();
            if (matriculeAdherent != null) {
                matriculeAdherent = em.getReference(matriculeAdherent.getClass(), matriculeAdherent.getMatricule());
                action.setMatriculeAdherent(matriculeAdherent);
            }
            em.persist(action);
            if (matriculeAdherent != null) {
                matriculeAdherent.getActionList().add(action);
                matriculeAdherent = em.merge(matriculeAdherent);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Action action) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Action persistentAction = em.find(Action.class, action.getId());
            Adherent matriculeAdherentOld = persistentAction.getMatriculeAdherent();
            Adherent matriculeAdherentNew = action.getMatriculeAdherent();
            if (matriculeAdherentNew != null) {
                matriculeAdherentNew = em.getReference(matriculeAdherentNew.getClass(), matriculeAdherentNew.getMatricule());
                action.setMatriculeAdherent(matriculeAdherentNew);
            }
            action = em.merge(action);
            if (matriculeAdherentOld != null && !matriculeAdherentOld.equals(matriculeAdherentNew)) {
                matriculeAdherentOld.getActionList().remove(action);
                matriculeAdherentOld = em.merge(matriculeAdherentOld);
            }
            if (matriculeAdherentNew != null && !matriculeAdherentNew.equals(matriculeAdherentOld)) {
                matriculeAdherentNew.getActionList().add(action);
                matriculeAdherentNew = em.merge(matriculeAdherentNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = action.getId();
                if (findAction(id) == null) {
                    throw new NonexistentEntityException("The action with id " + id + " no longer exists.");
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
            Action action;
            try {
                action = em.getReference(Action.class, id);
                action.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The action with id " + id + " no longer exists.", enfe);
            }
            Adherent matriculeAdherent = action.getMatriculeAdherent();
            if (matriculeAdherent != null) {
                matriculeAdherent.getActionList().remove(action);
                matriculeAdherent = em.merge(matriculeAdherent);
            }
            em.remove(action);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Action> findActionEntities() {
        return findActionEntities(true, -1, -1);
    }

    public List<Action> findActionEntities(int maxResults, int firstResult) {
        return findActionEntities(false, maxResults, firstResult);
    }

    private List<Action> findActionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Action.class));
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

    public Action findAction(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Action.class, id);
        } finally {
            em.close();
        }
    }

    public int getActionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Action> rt = cq.from(Action.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
