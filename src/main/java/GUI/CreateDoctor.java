package GUI;

import LOGIC.Controller;
import LOGIC.User;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

public class CreateDoctor extends JFrame {
    private JPanel JFrameCreateDoctor;
    private JPanel MainCreateDoctor;
    private JPanel panel1;
    private JLabel lb_name;
    private JTextArea taNameDoctor;
    private JButton btn_ClearDoctor;
    private JButton btn_createButton;
    private JLabel lb_lastname;
    private JLabel lb_Specialty;
    private JLabel lb_phone;
    private JTextArea taLastname;
    private JTextArea taaddres;
    private JComboBox cbSpecialty;
    private JList list1;
    private JTextArea taphone;
    private JTextArea taDocument;
    private JLabel lbDocument;
    private JButton retrunButton;
    private JButton clearButton;

    Controller controller = new Controller();
    //MAIN WINDOW
    private MainForm mainForm;
    public void openMainForm(MainForm mf) {
        this.mainForm = mf;
    }

    public CreateDoctor() {
        setContentPane(JFrameCreateDoctor);

        //---ENTER FUNCTION
        MainForm.setTabTraversal(taDocument);
        MainForm.setTabTraversal(taNameDoctor);
        MainForm.setTabTraversal(taLastname);
        MainForm.setTabTraversal(taaddres);
        MainForm.setTabTraversal(taphone);

        for(String o : MainForm.OptionsDoctor) {
            cbSpecialty.addItem(o);
        }

        //----create btn
        btn_createButton.addActionListener(e -> {

                Long document = Long.valueOf(taDocument.getText());
                String name = taNameDoctor.getText();
                String lastname = taLastname.getText();
                Long phone = Long.parseLong(taphone.getText());
                String Specialty = Objects.requireNonNull(cbSpecialty.getSelectedItem()).toString();
                String address = taaddres.getText();
                List<User> users = list1.getSelectedValuesList();

                controller.saveDoctor(document,name,lastname,Specialty,phone,address,users);

                JOptionPane.showMessageDialog(CreateDoctor.this, "Doctor created successfully");

                mainForm.openCreateDoctor(CreateDoctor.this);
                mainForm.setVisible(true);
                setVisible(false);
                mainForm.setLocationRelativeTo(this);
        });

        //----clear btn
        clearButton.addActionListener(e -> {
            taNameDoctor.setText("");
            taLastname.setText("");
            taaddres.setText("");
            cbSpecialty.setSelectedIndex(0);
            list1.setSelectedIndex(0);
        });
        retrunButton.addActionListener(e -> {
            mainForm.openCreateDoctor(CreateDoctor.this);
            mainForm.setVisible(true);
            setVisible(false);
            mainForm.setLocationRelativeTo(this);
        });
    }

    public void loadinfo(){
        DefaultListModel<User> usersmodel = new DefaultListModel<>();
        List<User> users = controller.getusers();
        if (users != null) {
            for (User user : users) {
                usersmodel.addElement(user);
            }
            list1.setModel(usersmodel);
        }
    }
}
