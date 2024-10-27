package PERSISTENCE;

import LOGIC.User;

import java.util.List;

public class PersistenceController {
    //===USER===
    UserController uc = new UserController();
    //===DOCTOR===
    DoctorController dc = new DoctorController();

    public void createUser(User user) {
        uc.createUser(user);
    }
    public void editUser(User user) {
        uc.updateUser(user);
    }
    public void deleteUser(Long id) {
        uc.deleteUser(id);
    }
    public List<User> getAllUsers() {return uc.getAllUsers();
    }
}
