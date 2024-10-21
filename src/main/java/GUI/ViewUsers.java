package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewUsers extends javax.swing.JFrame {

    private JPanel Mainpanel;
    private JPanel ViewUsersMain;
    private JList list1;
    private JList list2;
    private JLabel lb_bduser;
    private JPanel Date;
    private JSpinner YearSpinner;
    private JSpinner DaySpinner;
    private JSpinner MonthSpinner;
    private JButton DELETEButton;
    private JButton SAVECHANGESButton;
    private JButton RETURNButton;

    private MainForm mf;
    public void openMainform(MainForm mf) {
        this.mf = mf;
    }

    public ViewUsers() {
        setContentPane(Mainpanel);

        RETURNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mf.openViewUsers(ViewUsers.this);
                mf.setVisible(true);
                setVisible(false);
            }
        });
    }
}
