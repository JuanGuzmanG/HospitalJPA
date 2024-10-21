package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JTextArea taphone;
    private JList pacientslist;
    private JComboBox cbSpecialty;
    private JList list1;

    private MainForm mainForm;
    public void openMainForm(MainForm mf) {
        this.mainForm = mf;
    }

    public CreateDoctor() {
        setContentPane(JFrameCreateDoctor);

        btn_createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainForm.openCreateDoctor(CreateDoctor.this);
                mainForm.setVisible(true);
                setVisible(false);
            }
        });
        btn_ClearDoctor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taNameDoctor.setText("");
                taLastname.setText("");
                taphone.setText("");
                cbSpecialty.setSelectedIndex(0);
                pacientslist.setSelectedIndex(0);
            }
        });
    }
}
