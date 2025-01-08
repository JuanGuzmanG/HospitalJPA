package LOGIC;

import PERSISTENCE.PersistenceController;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {
    PersistenceController pc = new PersistenceController();

    //=================USER==================================

    public void saveUser(Long document, String name, String lastname, String email, Long phone, Date brithdate, String medicalHistory, List<Doctor> doctors) {
        User existingUser = pc.findUserByDocument(document); // Verificar si el usuario ya existe
        if (existingUser == null) {
            List<Doctor> managedDoctors = new ArrayList<>();
            for (Doctor doctor : doctors) {
                Doctor managedDoctor = pc.findDoctorByDocument(doctor.getDocument()); // Buscar al doctor en la BD
                if (managedDoctor != null) {
                    managedDoctors.add(managedDoctor); // Agregar al doctor gestionado
                } else {
                    System.out.println("Doctor con documento " + doctor.getDocument() + " no existe.");
                }
            }
            // Crear el nuevo usuario con los doctores gestionados
            User user = new User(document, name, lastname, email, phone, brithdate, medicalHistory, managedDoctors);
            pc.createUser(user);
        } else {
            System.out.println("El usuario ya existe.");
        }
    }

    public List<User> getusers(){
        return pc.getAllUsers();
    }

    public User findUserByDocument(Long id){
        return pc.findUserByDocument(id);
    }
    //=================DOCTOR==================================
    public void saveDoctor(Long document, String name, String lastname, String specialty, Long phone, String address, List<User> users){
        List<User> existingUsers = new ArrayList<>();

        // Iterar sobre los usuarios seleccionados y asegurarte de que sean gestionados por JPA
        for (User user : users) {
            // Intentar obtener el usuario por su documento desde la base de datos
            User existingUser = pc.findUserByDocument(user.getDocument());

            if (existingUser != null) {
                // Si el usuario existe, agregarlo al doctor
                existingUsers.add(existingUser);
            } else {
                System.out.println("no hay usuario con ese documento");
            }
        }

        // Crear el doctor con la lista de usuarios gestionados
        Doctor doctor = new Doctor(document, name, lastname, specialty, phone, address, existingUsers);
        pc.createDoctor(doctor);
    }

    public List<Doctor> getdoctors(){
        return pc.getAllDoctors();
    }

    public void Deletedoctor(Long document){pc.deleteDoctor(document);}

    public Doctor findDoctorByDocument(Long document){
        return pc.findDoctorByDocument(document);
    };

    public void updateDoctor(Doctor doctor,Long document, String name,String lastname,String address,Long phone,String specialty ){
        doctor.setDocument(document);
        doctor.setName(name);
        doctor.setLastname(lastname);
        doctor.setAddress(address);
        doctor.setPhone(phone);
        doctor.setSpecialty(specialty);
        pc.updateDoctor(doctor);
    }
}
