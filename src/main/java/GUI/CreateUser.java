package GUI;

import LOGIC.Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

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
    private JLabel Documentlb;
    private JTextArea taDocument;

    Controller controller = new Controller();

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
        btncreateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long document = Long.valueOf(taDocument.getText());
                String name = taNameUser.getText();
                String lastname = talastnameUser.getText();
                String email = taEmailUser.getText();
                Long phone = Long.parseLong(taphoneUser.getText());
                int year = (int)YearSpinner.getValue();
                int month = (int)MonthSpinner.getValue();
                int day = (int)DaySpinner.getValue();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month-1, day);
                Date date = calendar.getTime();
                String history = taMHUser.getText();

                controller.saveUser(document,name,lastname,email,phone,date,history);

                JOptionPane.showMessageDialog(CreateUser.this, "User created successfully");
            }
        });
    }

}
