package PERSISTENCE;

import LOGIC.Doctor;
import LOGIC.User;

import java.util.List;

public class PersistenceController {
    //===USER=====
    UserController uc = new UserController();
    //===DOCTOR===
    DoctorController dc = new DoctorController();

    public void createUser(User user) {
        uc.createUser(user);
    }
    public void updateUser(User user) {
        uc.updateUser(user);
    }
    public void deleteUser(Long id) {
        uc.deleteUser(id);
    }
    public List<User> getAllUsers() {return uc.getAllUsers();}
    public User findUserByDocument(Long Document){
        return uc.findUserByDocument(Document);
    }

    public void createDoctor(Doctor doctor) {dc.createDoctor(doctor);}
    public void updateDoctor(Doctor doctor) {dc.updateDoctor(doctor);}
    public void deleteDoctor(Long id) {dc.deleteDoctor(id);}
    public List<Doctor> getAllDoctors() {return dc.getAlldoctors();}
    public Doctor findDoctorByDocument(Long document){
        return dc.findDoctorByDocument(document);
    }
}
