package GUI;

import LOGIC.Controller;
import LOGIC.Doctor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewDoctors extends JFrame {
    private JPanel ViewDoctorpanel;
    private JPanel Mainpanel;
    private JButton DELETEButton;
    private JButton SAVECHANGESButton;
    private JButton RETURNButton;
    private JTable table;
    private JList list1;
    private JScrollPane scroll;
    Controller controller = null;

    private MainForm mf;
    public void openMainForm(MainForm mf) {
        this.mf = mf;
    }

    public ViewDoctors() {
        controller = new Controller();
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

    public void loadinfoDoctors(){
        DefaultTableModel model = new DefaultTableModel(){};

        String[] titles = {"name","lastname","specialty","phone","address"};
        model.setColumnIdentifiers(titles);

        List<Doctor> ListDoctors = controller.getdoctors();

        if(ListDoctors!=null){
            for(Doctor d : ListDoctors){
                model.addRow(new Object[]{d.getName(),d.getLastname(),d.getSpecialty(),d.getPhone(),d.getAddress()});
            }
        }else{
            JOptionPane.showMessageDialog(null, "No Doctors Found");
        }

        table.setModel(model);
    }
}
