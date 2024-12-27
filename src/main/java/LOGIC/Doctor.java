package LOGIC;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_doc;
    private Long Document;
    private String name;
    private String lastname;
    private String specialty;
    private Long phone;
    private String address;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> users;

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

    public Doctor(Long Document,String name, String lastname, String specialty, Long phone, String address,List<User> users) {
        this.Document = Document;
        this.name = name;
        this.lastname = lastname;
        this.specialty = specialty;
        this.phone = phone;
        this.address = address;
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Long getId() {
        return id_doc;
    }

    public void setId(Long id) {
        this.id_doc = id;
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

    public String toString(){
        return specialty +" "+name+" "+lastname;
    }
}
