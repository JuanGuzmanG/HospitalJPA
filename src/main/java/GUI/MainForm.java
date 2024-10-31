package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends  JFrame {
    private JPanel JFramePanel;
    private JButton viewDoctorsButton;
    private JButton viewUsersButton;
    private JButton createDoctorButton;
    private JButton createUserButton;
    private JPanel MainPanel;
    private JTextArea textArea;

    public static void setTabTraversal(JTextArea textArea) {
        textArea.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, null);
        textArea.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, null);
    }

    public String[] OptionsDoctor = {"-","Pediatrics", "General-Medicine", "Optometry", "Gastroenterology"};
    public String[] Allergies = {"-","Peanut","Acetaminophen","Aspirin","Ibuprofen"};
    public String[] SAttention = {"-","diabetic","hypertensive","heart-problems","disorder"};

    //WINDOWS
    private CreateUser createUser = new CreateUser();
    public void openCreateUser(CreateUser cs){
        this.createUser = cs;
    }
    private CreateDoctor createdoctor = new CreateDoctor();
    public void openCreateDoctor(CreateDoctor cd){
        this.createdoctor = cd;
    }
    private ViewDoctors viewDoctors = new ViewDoctors();
    public void openViewDoctors(ViewDoctors vd){
        this.viewDoctors = vd;
    }
    private ViewUsers viewUsers = new ViewUsers();
    public void openViewUsers(ViewUsers u){
        this.viewUsers = u;
    }

    public MainForm() {
        setContentPane(JFramePanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JTextArea textArea1 = textArea;

        // CREATE USER
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUser.openMainForm(MainForm.this);
                createUser.setSize(800,500);
                createUser.setLocationRelativeTo(null);
                createUser.setVisible(true);
            }
        });
        // CREATE DOCTOR
        createDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createdoctor.openMainForm(MainForm.this);
                createdoctor.setSize(800,500);
                createdoctor.setLocationRelativeTo(null);
                createdoctor.setVisible(true);
            }
        });
        // VIEW DOCTORS
        viewDoctorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewDoctors.openMainForm(MainForm.this);
                viewDoctors.setSize(800,400);
                viewDoctors.setLocationRelativeTo(null);
                viewDoctors.loadinfo();
                viewDoctors.setVisible(true);
            }
        });
        // VIEW USERS
        viewUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewUsers.openMainform(MainForm.this);
                viewUsers.setSize(800,400);
                viewUsers.setLocationRelativeTo(null);
                viewUsers.loadInfo();
                viewUsers.setVisible(true);
            }
        });
    }
}
