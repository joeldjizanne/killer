/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.genieLogiciel.djangui.persistence;

import app.genieLogiciel.djangui.persistence.exceptions.IllegalOrphanException;
import app.genieLogiciel.djangui.persistence.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import app.genieLogiciel.djangui.model.Action;
import app.genieLogiciel.djangui.model.Adherent;
import java.util.ArrayList;
import java.util.List;
import app.genieLogiciel.djangui.model.Emprunt;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author kevinash
 */
public class AdherentJpaController implements Serializable {

    public AdherentJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Adherent adherent) {
        if (adherent.getActionList() == null) {
            adherent.setActionList(new ArrayList<Action>());
        }
        if (adherent.getEmpruntList() == null) {
            adherent.setEmpruntList(new ArrayList<Emprunt>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Action> attachedActionList = new ArrayList<Action>();
            for (Action actionListActionToAttach : adherent.getActionList()) {
                actionListActionToAttach = em.getReference(actionListActionToAttach.getClass(), actionListActionToAttach.getId());
                attachedActionList.add(actionListActionToAttach);
            }
            adherent.setActionList(attachedActionList);
            List<Emprunt> attachedEmpruntList = new ArrayList<Emprunt>();
            for (Emprunt empruntListEmpruntToAttach : adherent.getEmpruntList()) {
                empruntListEmpruntToAttach = em.getReference(empruntListEmpruntToAttach.getClass(), empruntListEmpruntToAttach.getIdEmprunt());
                attachedEmpruntList.add(empruntListEmpruntToAttach);
            }
            adherent.setEmpruntList(attachedEmpruntList);
            em.persist(adherent);
            for (Action actionListAction : adherent.getActionList()) {
                Adherent oldMatriculeAdherentOfActionListAction = actionListAction.getMatriculeAdherent();
                actionListAction.setMatriculeAdherent(adherent);
                actionListAction = em.merge(actionListAction);
                if (oldMatriculeAdherentOfActionListAction != null) {
                    oldMatriculeAdherentOfActionListAction.getActionList().remove(actionListAction);
                    oldMatriculeAdherentOfActionListAction = em.merge(oldMatriculeAdherentOfActionListAction);
                }
            }
            for (Emprunt empruntListEmprunt : adherent.getEmpruntList()) {
                Adherent oldMatriculeAdherentOfEmpruntListEmprunt = empruntListEmprunt.getMatriculeAdherent();
                empruntListEmprunt.setMatriculeAdherent(adherent);
                empruntListEmprunt = em.merge(empruntListEmprunt);
                if (oldMatriculeAdherentOfEmpruntListEmprunt != null) {
                    oldMatriculeAdherentOfEmpruntListEmprunt.getEmpruntList().remove(empruntListEmprunt);
                    oldMatriculeAdherentOfEmpruntListEmprunt = em.merge(oldMatriculeAdherentOfEmpruntListEmprunt);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Adherent adherent) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Adherent persistentAdherent = em.find(Adherent.class, adherent.getMatricule());
            List<Action> actionListOld = persistentAdherent.getActionList();
            List<Action> actionListNew = adherent.getActionList();
            List<Emprunt> empruntListOld = persistentAdherent.getEmpruntList();
            List<Emprunt> empruntListNew = adherent.getEmpruntList();
            List<String> illegalOrphanMessages = null;
            for (Action actionListOldAction : actionListOld) {
                if (!actionListNew.contains(actionListOldAction)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Action " + actionListOldAction + " since its matriculeAdherent field is not nullable.");
                }
            }
            for (Emprunt empruntListOldEmprunt : empruntListOld) {
                if (!empruntListNew.contains(empruntListOldEmprunt)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Emprunt " + empruntListOldEmprunt + " since its matriculeAdherent field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Action> attachedActionListNew = new ArrayList<Action>();
            for (Action actionListNewActionToAttach : actionListNew) {
                actionListNewActionToAttach = em.getReference(actionListNewActionToAttach.getClass(), actionListNewActionToAttach.getId());
                attachedActionListNew.add(actionListNewActionToAttach);
            }
            actionListNew = attachedActionListNew;
            adherent.setActionList(actionListNew);
            List<Emprunt> attachedEmpruntListNew = new ArrayList<Emprunt>();
            for (Emprunt empruntListNewEmpruntToAttach : empruntListNew) {
                empruntListNewEmpruntToAttach = em.getReference(empruntListNewEmpruntToAttach.getClass(), empruntListNewEmpruntToAttach.getIdEmprunt());
                attachedEmpruntListNew.add(empruntListNewEmpruntToAttach);
            }
            empruntListNew = attachedEmpruntListNew;
            adherent.setEmpruntList(empruntListNew);
            adherent = em.merge(adherent);
            for (Action actionListNewAction : actionListNew) {
                if (!actionListOld.contains(actionListNewAction)) {
                    Adherent oldMatriculeAdherentOfActionListNewAction = actionListNewAction.getMatriculeAdherent();
                    actionListNewAction.setMatriculeAdherent(adherent);
                    actionListNewAction = em.merge(actionListNewAction);
                    if (oldMatriculeAdherentOfActionListNewAction != null && !oldMatriculeAdherentOfActionListNewAction.equals(adherent)) {
                        oldMatriculeAdherentOfActionListNewAction.getActionList().remove(actionListNewAction);
                        oldMatriculeAdherentOfActionListNewAction = em.merge(oldMatriculeAdherentOfActionListNewAction);
                    }
                }
            }
            for (Emprunt empruntListNewEmprunt : empruntListNew) {
                if (!empruntListOld.contains(empruntListNewEmprunt)) {
                    Adherent oldMatriculeAdherentOfEmpruntListNewEmprunt = empruntListNewEmprunt.getMatriculeAdherent();
                    empruntListNewEmprunt.setMatriculeAdherent(adherent);
                    empruntListNewEmprunt = em.merge(empruntListNewEmprunt);
                    if (oldMatriculeAdherentOfEmpruntListNewEmprunt != null && !oldMatriculeAdherentOfEmpruntListNewEmprunt.equals(adherent)) {
                        oldMatriculeAdherentOfEmpruntListNewEmprunt.getEmpruntList().remove(empruntListNewEmprunt);
                        oldMatriculeAdherentOfEmpruntListNewEmprunt = em.merge(oldMatriculeAdherentOfEmpruntListNewEmprunt);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = adherent.getMatricule();
                if (findAdherent(id) == null) {
                    throw new NonexistentEntityException("The adherent with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Adherent adherent;
            try {
                adherent = em.getReference(Adherent.class, id);
                adherent.getMatricule();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The adherent with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Action> actionListOrphanCheck = adherent.getActionList();
            for (Action actionListOrphanCheckAction : actionListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Adherent (" + adherent + ") cannot be destroyed since the Action " + actionListOrphanCheckAction + " in its actionList field has a non-nullable matriculeAdherent field.");
            }
            List<Emprunt> empruntListOrphanCheck = adherent.getEmpruntList();
            for (Emprunt empruntListOrphanCheckEmprunt : empruntListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Adherent (" + adherent + ") cannot be destroyed since the Emprunt " + empruntListOrphanCheckEmprunt + " in its empruntList field has a non-nullable matriculeAdherent field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(adherent);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Adherent> findAdherentEntities() {
        return findAdherentEntities(true, -1, -1);
    }

    public List<Adherent> findAdherentEntities(int maxResults, int firstResult) {
        return findAdherentEntities(false, maxResults, firstResult);
    }

    private List<Adherent> findAdherentEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Adherent.class));
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

    public Adherent findAdherent(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Adherent.class, id);
        } finally {
            em.close();
        }
    }

    public int getAdherentCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Adherent> rt = cq.from(Adherent.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
