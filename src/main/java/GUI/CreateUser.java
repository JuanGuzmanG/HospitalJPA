package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateUser extends JFrame {
    private JPanel JframeCreatePanel;
    private JPanel MainCreatePanel;
    private JPanel BtnsPanel;
    private JTextArea taNameUser;
    private JPanel panel1;
    private JTextArea taMHUser;
    private JLabel lb_nameuser;
    private JLabel lb_lastnameuser;
    private JLabel lb_emailuser;
    private JLabel lb_phoneuser;
    private JLabel lb_bduser;
    private JPanel lb_sa;
    private JTextArea talastnameUser;
    private JTextArea taEmailUser;
    private JTextArea taphoneUser;
    private JButton btnClearUser;
    private JButton btncreateButton;
    private JPanel Date;
    private JSpinner YearSpinner;
    private JSpinner DaySpinner;
    private JSpinner MonthSpinner;
    private JScrollPane scroll;
    private JList AllergiesList;
    private JList SpecialAttentionList;

    private MainForm MF;
    public void openMainForm(MainForm mf) {
        this.MF = mf;
    }

    public CreateUser() {
        setContentPane(JframeCreatePanel);
        btncreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MF.openCreateUser(CreateUser.this);
                MF.setVisible(true);
                setVisible(false);
            }
        });
        btnClearUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taNameUser.setText("");
                talastnameUser.setText("");
                taEmailUser.setText("");
                taphoneUser.setText("");
                DaySpinner.setValue(0);
                MonthSpinner.setValue(0);
                YearSpinner.setValue(0);
                AllergiesList.setSelectedIndex(0);
                SpecialAttentionList.setSelectedIndex(0);
                taMHUser.setText("");
            }
        });
    }

}
