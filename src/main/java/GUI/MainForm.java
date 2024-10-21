package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm extends  JFrame {
    private JPanel JFramePanel;
    private JButton viewDoctorsButton;
    private JButton viewUsersButton;
    private JButton createDoctorButton;
    private JButton createUserButton;
    private JPanel MainPanel;

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
        createUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createUser.openMainForm(MainForm.this);
                createUser.setSize(800,500);
                createUser.setLocationRelativeTo(null);
                createUser.setVisible(true);
            }
        });
        createDoctorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createdoctor.openMainForm(MainForm.this);
                createdoctor.setSize(800,500);
                createdoctor.setLocationRelativeTo(null);
                createdoctor.setVisible(true);
            }
        });
        viewDoctorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewDoctors.openMainForm(MainForm.this);
                viewDoctors.setSize(800,400);
                viewDoctors.setLocationRelativeTo(null);
                viewDoctors.setVisible(true);
            }
        });
        viewUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewUsers.openMainform(MainForm.this);
                viewUsers.setSize(800,400);
                viewUsers.setLocationRelativeTo(null);
                viewUsers.setVisible(true);
            }
        });
    }
}
