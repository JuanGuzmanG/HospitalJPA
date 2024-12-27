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

    public static String[] OptionsDoctor = {"-","Pediatrics", "General-Medicine", "Optometry", "Gastroenterology"};
    public static String[] Allergies = {"-","Peanut","Acetaminophen","Aspirin","Ibuprofen"};
    public static String[] SAttention = {"-","diabetic","hypertensive","heart-problems","disorder"};

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
                createUser.setLocationRelativeTo(MainForm.this);
                createdoctor.loadinfo();
                createUser.setVisible(true);
                createUser.upload();
            }
        });
        // CREATE DOCTOR
        createDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createdoctor.openMainForm(MainForm.this);
                createdoctor.setSize(800,500);
                createdoctor.setLocationRelativeTo(MainForm.this);
                createdoctor.setVisible(true);
                createdoctor.loadinfo();
            }
        });
        // VIEW DOCTORS
        viewDoctorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewDoctors.openMainForm(MainForm.this);
                viewDoctors.setSize(800,400);
                viewDoctors.setLocationRelativeTo(MainForm.this);
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
                viewUsers.setLocationRelativeTo(MainForm.this);
                viewUsers.loadInfo();
                viewUsers.setVisible(true);
            }
        });
    }
}
