package LOGIC;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long Document;
    private String name;
    private String lastname;
    private String specialty;
    private Long phone;
    private String address;

    @ManyToMany
    private List<User> user;

    public Doctor() {
    }

    public Doctor(Long Document,String name, String lastname, String specialty, Long phone, String address) {
        this.Document = Document;
        this.name = name;
        this.lastname = lastname;
        this.specialty = specialty;
        this.phone = phone;
        this.address = address;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(LinkedList<User> user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDocument() {
        return Document;
    }

    public void setDocument(Long document) {
        Document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
