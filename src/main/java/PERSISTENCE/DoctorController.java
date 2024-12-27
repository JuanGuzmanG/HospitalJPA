package PERSISTENCE;

import LOGIC.Doctor;
import LOGIC.User;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class DoctorController {

    private EntityManagerFactory emf;
    private EntityManager em;

    public DoctorController() {
        emf = Persistence.createEntityManagerFactory("hospitalUP");
        em = emf.createEntityManager();
    }

    public void createDoctor(Doctor doctor) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(doctor);
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

    public Doctor getDoctor(Long id) {
        return em.find(Doctor.class, id);
    }


    public void updateDoctor(Doctor doctor) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(doctor);
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

    public void deleteDoctor(Long id) {
        em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Doctor doctor = em.find(Doctor.class, id);
            if (doctor != null) {
                em.remove(doctor);
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

    public Doctor findDoctorByDocument(Long document) {
        em = emf.createEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Doctor> cq = cb.createQuery(Doctor.class);
            Root<Doctor> doctor = cq.from(Doctor.class);
            cq.select(doctor).where(cb.equal(doctor.get("Document"), document));

            return em.createQuery(cq).getSingleResult();
        } catch (NoResultException e) {
            return null; // Si no se encuentra un doctor con el documento especificado
        }finally {
            em.close();
        }
    }

    public List<Doctor> getAlldoctors(){return getAlldoctors(true,-1,-1);}
    public List<Doctor> getAlldoctors(boolean all,int first,int max){
        EntityManager em = emf.createEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Doctor.class));
            Query q = em.createQuery(cq);
            if(!all){
                q.setMaxResults(max);
                q.setFirstResult(first);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
}

