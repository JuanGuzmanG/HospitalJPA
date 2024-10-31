package LOGIC;

import PERSISTENCE.PersistenceController;

import javax.print.Doc;
import java.util.Date;
import java.util.List;

public class Controller {
    PersistenceController pc = new PersistenceController();

    //=================USER==================================

    public void saveUser(Long document,String name, String lastname, String email, Long phone, Date brithdate, String medicalHistory){

        User user = new User(document,name, lastname, email, phone, brithdate, medicalHistory);
        pc.createUser(user);
    }
    public List<User> getusers(){
        return pc.getAllUsers();
    }

    //=================DOCTOR==================================
    public void saveDoctor(Long document,String name,String lastname,String specialty, Long phone,String address){
        Doctor doctor = new Doctor(document
                ,name, lastname, specialty, phone, address);
        pc.createDoctor(doctor);
    }
    public List<Doctor> getdoctors(){
        return pc.getAllDoctors();
    }
    public void Deletedoctor(Long document){pc.deleteDoctor(document);}
    public Doctor findDoctorByDocument(Long document){
        return pc.findDoctorByDocument(document);
    };
    public void updateDoctor(Doctor doctor){pc.updateDoctor(doctor);}
}
