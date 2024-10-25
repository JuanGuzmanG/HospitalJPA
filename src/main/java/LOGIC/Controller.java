package LOGIC;

import PERSISTENCE.PersistenceController;

import java.util.Date;

public class Controller {
    PersistenceController pc = new PersistenceController();

    public void saveUser(String name, String lastname, String email, int phone, Date brithdate, String medicalHistory){

        User user = new User(name, lastname, email, phone, brithdate, medicalHistory);
        pc.createUser(user);
    }
}
