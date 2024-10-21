package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewDoctors extends JFrame {
    private JPanel ViewDoctorpanel;
    private JPanel Mainpanel;
    private JList list3;
    private JButton DELETEButton;
    private JButton SAVECHANGESButton;
    private JButton RETURNButton;
    private JComboBox comboBox1;

    private MainForm mf;
    public void openMainForm(MainForm mf) {
        this.mf = mf;
    }

    public ViewDoctors() {
        setContentPane(ViewDoctorpanel);


        RETURNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mf.openViewDoctors(ViewDoctors.this);
                mf.setVisible(true);
                setVisible(false);
            }
        });
    }
}
