/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.persistence;

import app.genieLogiciel.djangui.persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import app.genieLogiciel.djangui.model.Adherent;
import app.genieLogiciel.djangui.model.Emprunt;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author kevinash
 */
public class EmpruntJpaController implements Serializable {

    public EmpruntJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Emprunt emprunt) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Adherent matriculeAdherent = emprunt.getMatriculeAdherent();
            if (matriculeAdherent != null) {
                matriculeAdherent = em.getReference(matriculeAdherent.getClass(), matriculeAdherent.getMatricule());
                emprunt.setMatriculeAdherent(matriculeAdherent);
            }
            em.persist(emprunt);
            if (matriculeAdherent != null) {
                matriculeAdherent.getEmpruntList().add(emprunt);
                matriculeAdherent = em.merge(matriculeAdherent);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Emprunt emprunt) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Emprunt persistentEmprunt = em.find(Emprunt.class, emprunt.getIdEmprunt());
            Adherent matriculeAdherentOld = persistentEmprunt.getMatriculeAdherent();
            Adherent matriculeAdherentNew = emprunt.getMatriculeAdherent();
            if (matriculeAdherentNew != null) {
                matriculeAdherentNew = em.getReference(matriculeAdherentNew.getClass(), matriculeAdherentNew.getMatricule());
                emprunt.setMatriculeAdherent(matriculeAdherentNew);
            }
            emprunt = em.merge(emprunt);
            if (matriculeAdherentOld != null && !matriculeAdherentOld.equals(matriculeAdherentNew)) {
                matriculeAdherentOld.getEmpruntList().remove(emprunt);
                matriculeAdherentOld = em.merge(matriculeAdherentOld);
            }
            if (matriculeAdherentNew != null && !matriculeAdherentNew.equals(matriculeAdherentOld)) {
                matriculeAdherentNew.getEmpruntList().add(emprunt);
                matriculeAdherentNew = em.merge(matriculeAdherentNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = emprunt.getIdEmprunt();
                if (findEmprunt(id) == null) {
                    throw new NonexistentEntityException("The emprunt with id " + id + " no longer exists.");
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
            Emprunt emprunt;
            try {
                emprunt = em.getReference(Emprunt.class, id);
                emprunt.getIdEmprunt();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The emprunt with id " + id + " no longer exists.", enfe);
            }
            Adherent matriculeAdherent = emprunt.getMatriculeAdherent();
            if (matriculeAdherent != null) {
                matriculeAdherent.getEmpruntList().remove(emprunt);
                matriculeAdherent = em.merge(matriculeAdherent);
            }
            em.remove(emprunt);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Emprunt> findEmpruntEntities() {
        return findEmpruntEntities(true, -1, -1);
    }

    public List<Emprunt> findEmpruntEntities(int maxResults, int firstResult) {
        return findEmpruntEntities(false, maxResults, firstResult);
    }

    private List<Emprunt> findEmpruntEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Emprunt.class));
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

    public Emprunt findEmprunt(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Emprunt.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpruntCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Emprunt> rt = cq.from(Emprunt.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
