package PERSISTENCE;

import LOGIC.User;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class UserController {

    private EntityManagerFactory emf;
    private EntityManager em;

    public UserController() {
        emf = Persistence.createEntityManagerFactory("hospitalUP");
        em = emf.createEntityManager();
    }

    public void createUser(User user) {
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public User getUser(Long id) {
        return em.find(User.class, id);
    }

    public void updateUser(User user) {
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteUser(Long id) {
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {return getAllUsers(true,-1,-1);}
    public List<User> getAllUsers(boolean all, int first, int max) {
        EntityManager em = emf.createEntityManager();
        try{
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(User.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(max);
                q.setFirstResult(first);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void close() {
        if (em.isOpen()) {
            em.close();
        }
        if (emf.isOpen()) {
            emf.close();
        }
    }
}
