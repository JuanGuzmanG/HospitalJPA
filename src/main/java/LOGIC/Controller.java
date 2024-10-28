package LOGIC;

import PERSISTENCE.PersistenceController;

import java.util.Date;
import java.util.List;

public class Controller {
    PersistenceController pc = new PersistenceController();

    //=================USER==================================

    public void saveUser(String name, String lastname, String email, Long phone, Date brithdate, String medicalHistory){

        User user = new User(name, lastname, email, phone, brithdate, medicalHistory);
        pc.createUser(user);
    }

    public List<User> getusers(){
        return pc.getAllUsers();
    }

    //=================DOCTOR==================================
    public void saveDoctor(String name,String lastname,String specialty, Long phone,String address){
        Doctor doctor = new Doctor(name, lastname, specialty, phone, address);
        pc.createDoctor(doctor);
    }

    public List<Doctor> getdoctors(){
        return pc.getAllDoctors();
    }
}
