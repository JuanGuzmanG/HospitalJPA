package PERSISTENCE;

import LOGIC.Doctor;
import LOGIC.User;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserController {

    private EntityManagerFactory emf;
    private EntityManager em;

    public UserController() {
        emf = Persistence.createEntityManagerFactory("hospitalUP");
        em = emf.createEntityManager();
    }

    public void createUser(User user) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally {
            em.close();
        }
    }

    public User getUser(Long id) {
        return em.find(User.class, id);
    }

    public void updateUser(User user) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally {
            em.close();
        }
    }

    public void deleteUser(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            User user = em.find(User.class, id);
            System.out.println("id"+id);
            System.out.println(user.getName());
            if (user != null) {
                em.remove(user);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }finally {
            em.close();
        }
    }

    public User findUserByDocument(Long document) {
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> user = cq.from(User.class);
            cq.select(user).where(cb.equal(user.get("Document"), document));
            return em.createQuery(cq).getSingleResult();  // Debería devolver el usuario gestionado si existe
        } catch (NoResultException e) {
            return null; // Si no se encuentra el usuario
        } finally {
            em.close();
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
}
