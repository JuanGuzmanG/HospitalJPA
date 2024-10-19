package GUI;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUser extends JFrame {
    private JPanel JframeCreatePanel;
    private JPanel MainCreatePanel;
    private JPanel BtnsPanel;
    private JTextArea taNameUser;
    private JPanel panel1;
    private JTextArea ta_MHUser;
    private JSpinner spinner1;
    private JLabel lb_nameuser;
    private JLabel lb_lastnameuser;
    private JLabel lb_emailuser;
    private JLabel lb_phoneuser;
    private JLabel lb_bduser;
    private JPanel lb_sa;
    private JTextArea ta_lastnameUser;
    private JTextArea ta_EmailUser;
    private JTextArea ta_phoneUser;
    private JTextArea ta_bdUser;
    private JButton btn_ClearUser;
    private JButton btn_createButton;

    private MainForm MF;
    public void openMainForm(MainForm mf) {
        this.MF = mf;
    }

    public CreateUser() {
        setContentPane(JframeCreatePanel);
        btn_createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MF.openCreateUser(CreateUser.this);
                MF.setVisible(true);
            }
        });
    }

}
