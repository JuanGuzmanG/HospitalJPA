package GUI;

import LOGIC.Controller;
import LOGIC.Doctor;

import javax.swing.*;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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
    private JButton returnbtn;
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
    private JList list1;
    private JButton btnClear;
    private JTable table1;

    //----Controller
    Controller controller = new Controller();

    //----window
    private MainForm MF;

    public void openMainForm(MainForm mf) {
        this.MF = mf;
    }

    public CreateUser() {

        setContentPane(JframeCreatePanel);

        //----tab funtion
        MainForm.setTabTraversal(taDocument);
        MainForm.setTabTraversal(taNameUser);
        MainForm.setTabTraversal(talastnameUser);
        MainForm.setTabTraversal(taEmailUser);
        MainForm.setTabTraversal(taphoneUser);
        MainForm.setTabTraversal(taMHUser);

        //----clear button
        btnClear.addActionListener(e -> {
            clear();
        });

        //----create button
        btncreateButton.addActionListener(e -> {
            Long document = Long.valueOf(taDocument.getText());
            String name = taNameUser.getText();
            String lastname = talastnameUser.getText();
            String email = taEmailUser.getText();
            Long phone = Long.parseLong(taphoneUser.getText());
            int year = (int) YearSpinner.getValue();
            int month = (int) MonthSpinner.getValue();
            int day = (int) DaySpinner.getValue();
            Calendar calendar = Calendar.getInstance();
            calendar.set(year, month - 1, day);
            Date date = calendar.getTime();
            String history = taMHUser.getText();
            List<Doctor> doctors = list1.getSelectedValuesList();

            controller.saveUser(document, name, lastname, email, phone, date, history, doctors);
            clear();

            JOptionPane.showMessageDialog(CreateUser.this, "User created successfully");
            MF.openCreateUser(CreateUser.this);
            MF.setVisible(true);
            setVisible(false);
        });

        //----return btn
        returnbtn.addActionListener(e -> {
            MF.openCreateUser(CreateUser.this);
            MF.setVisible(true);
            setVisible(false);
            MF.setLocationRelativeTo(this);
        });
    }

    //----upload data
    public void upload() {
        DefaultListModel<Doctor> doctormodel = new DefaultListModel<>();
        List<Doctor> doctors = controller.getdoctors();
        if (doctors != null) {
            for (Doctor doc : doctors) {
                doctormodel.addElement(doc);
            }
            list1.setModel(doctormodel);
        }
    }

    //----clear texts
    public void clear() {
        taDocument.setText("");
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
}