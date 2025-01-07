package LOGIC;

import javax.persistence.*;
import javax.print.Doc;
import javax.swing.*;
import java.util.*;

@Entity
public class User {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_user;
    private Long Document;
    private String name;
    private String lastname;
    private String email;
    private Long phone;
    private String MedicalHistory;
@Temporal(TemporalType.DATE)
    private Date brithdate;

    @ManyToMany
    @JoinTable(
            name = "user_doctor",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "doctor_id")
    )
    private List<Doctor> doctors;

    public User() {
    }

    public User(Long Document,String name, String lastname, String email, Long phone, Date brithdate, String medicalHistory) {
        this.Document = Document;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.brithdate = brithdate;
        MedicalHistory = medicalHistory;
    }

    public User(Long Document, String name, String lastname, String email, Long phone, Date brithdate, String medicalHistory, List<Doctor> doctors) {
        this.Document = Document;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.brithdate = brithdate;
        this.MedicalHistory = medicalHistory;
        this.doctors = doctors;
    }

    public Long getId() {
        return id_user;
    }

    public void setId(Long id) {
        this.id_user = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Date getBrithdate() {
        return brithdate;
    }

    public void setBrithdate(Date brithdate) {
        this.brithdate = brithdate;
    }

    public String getMedicalHistory() {
        return MedicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        MedicalHistory = medicalHistory;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    public String toString() {
        return name+" "+lastname;
    }
}
