package LOGIC;

import PERSISTENCE.PersistenceController;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Controller {
    PersistenceController pc = new PersistenceController();

    //=================USER==================================

    public void saveUser(Long document, String name, String lastname, String email, Long phone, Date brithdate, String medicalHistory, List<Doctor> doctors){
        User existingUser = pc.findUserByDocument(document); // Método para buscar si el usuario ya existe
        if (existingUser == null) {
            User user = new User(document, name, lastname, email, phone, brithdate, medicalHistory, doctors);
            pc.createUser(user);
        } else {
            // Si el usuario ya existe, solo asignamos los doctores
            existingUser.setDoctors(doctors);
            pc.updateUser(existingUser);
        }
    }

    public List<User> getusers(){
        return pc.getAllUsers();
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
                // Si el usuario no existe, agregarlo como nuevo (aunque esto no es ideal)
                existingUsers.add(user);
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
