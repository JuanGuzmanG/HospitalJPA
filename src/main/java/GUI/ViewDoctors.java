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

    //WINDOW MAINFORM
    private MainForm mf;
    public void openMainForm(MainForm mf) {
        this.mf = mf;
    }

    //WINDOW EDITORDOCTOR
    private EditDoctor editdoctor = new EditDoctor();
    public void openEditdoctor(EditDoctor ed){
        this.editdoctor = ed;
    }

    public ViewDoctors() {
        controller = new Controller();
        setContentPane(ViewDoctorpanel);

        //RETURN BUTTON
        RETURNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mf.openViewDoctors(ViewDoctors.this);
                mf.setVisible(true);
                setVisible(false);
            }
        });

        //EDIT BUTTON
        editBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getSelectedRow()!=-1){
                    Long docDoctor = Long.parseLong(table.getValueAt(table.getSelectedRow(), 0).toString());

                    editdoctor.openMainForm(ViewDoctors.this,docDoctor);
                    editdoctor.setSize(800,500);
                    editdoctor.setLocationRelativeTo(null);
                    editdoctor.setVisible(true);
                }else{
                    message("No doctor selected","error","Error Selected");
                }
            }
        });

        //DELETE BUTTON
        DELETEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(table.getRowCount() > 0) {
                    if(table.getSelectedRow()==-1) {
                        message("No doctor selected","error","Error Selected");
                    }
                    if (table.getSelectedRow() != -1) {
                        Long Docdoctor = Long.parseLong(table.getValueAt(table.getSelectedRow(), 0).toString());
                        Doctor doctor = controller.findDoctorByDocument(Docdoctor);
                        controller.Deletedoctor(doctor.getId());
                        message("Removed doctor successfully","info","Complete");
                        loadinfo();
                    }
                } else if (table.getRowCount()==0) {
                    message("Table is Empty","error","Error empty");
                }
            }
        });
    }

    //MESSAGE ACTIONS
    public void message(String message, String type, String title){
        JOptionPane optionPane = new JOptionPane(message);
        if(type.equals("info")){
            optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        }else if(type.equals("error")){
            optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
        }
        JDialog dialog = optionPane.createDialog(title);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    //REFRESH TABLE
    public void loadinfo(){
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
