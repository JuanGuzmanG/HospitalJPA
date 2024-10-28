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
    private JButton editBtn;
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
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getRowCount() > 0) {
                    if (table.getSelectedRow() != -1) {

                    }
                }
            }
        });
    }

    public void loadinfoDoctors(){
        DefaultTableModel model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row,int column){
                return false;
            }
        };

        String[] titles = {"Document","name","lastname","specialty","phone","address"};
        model.setColumnIdentifiers(titles);

        List<Doctor> ListDoctors = controller.getdoctors();

        if(ListDoctors!=null){
            for(Doctor d : ListDoctors){
                model.addRow(new Object[]{d.getDocument(),d.getName(),d.getLastname(),d.getSpecialty(),d.getPhone(),d.getAddress()});
            }
        }else{
            JOptionPane.showMessageDialog(null, "No Doctors Found");
        }

        table.setModel(model);
    }
}
