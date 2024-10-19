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
    }
}
