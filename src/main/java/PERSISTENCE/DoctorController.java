package PERSISTENCE;

import LOGIC.Doctor;
import LOGIC.User;

import javax.persistence.*;
import java.util.List;

public class DoctorController {

    private EntityManagerFactory emf;
    private EntityManager em;

    public DoctorController() {
        emf = Persistence.createEntityManagerFactory("hospitalUP");
        em = emf.createEntityManager();
    }

    public void createDoctor(Doctor doctor) {
        try {
            em.getTransaction().begin();
            em.persist(doctor);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public Doctor getDoctor(Long id) {
        return em.find(Doctor.class, id);
    }

    public List<Doctor> getAllDoctors() {
        return em.createQuery("SELECT d FROM Doctor d", Doctor.class).getResultList();
    }

    public void updateDoctor(Doctor doctor) {
        try {
            em.getTransaction().begin();
            em.merge(doctor);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteDoctor(Long id) {
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
        }
    }

    public List<User> getUsersForDoctor(Long doctorId) {
        Doctor doctor = getDoctor(doctorId);
        if (doctor != null) {
            return doctor.getUser();
        }
        return null;
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

