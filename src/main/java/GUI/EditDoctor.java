package GUI;

import LOGIC.Controller;
import LOGIC.Doctor;
import LOGIC.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EditDoctor extends javax.swing.JFrame {
    private JPanel EditDoctorpanel;
    private JPanel Mainpanel;
    private JTextField taDocument;
    private JComboBox cbSpecialty;
    private JButton RETURNButton;
    private JButton SAVEButton;
    private JButton CLEARButton;
    private JTextField taName;
    private JTextField taLastname;
    private JTextField taAddress;
    private JTextField taPhone;
    private JTable table;

    Doctor doctor;
    Controller controller = new Controller();

    //----window
    private ViewDoctors vd;
    public void openMainForm(ViewDoctors vd,Long Document) {
        this.vd = vd;
        upload(Document);
        doctor = controller.findDoctorByDocument(Document);
    }

    public EditDoctor() {
        setContentPane(EditDoctorpanel);

        //----specialty menu
        for(String o : MainForm.OptionsDoctor) {
            cbSpecialty.addItem(o);
        }

        //----return button
        RETURNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vd.openEditdoctor(EditDoctor.this);
                vd.setVisible(true);
                setVisible(false);
            }
        });

        //----clear button
        CLEARButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taDocument.setText("");
                taName.setText("");
                taLastname.setText("");
                taAddress.setText("");
                taPhone.setText("");
                cbSpecialty.setSelectedIndex(0);
            }
        });

        //----save button
        SAVEButton.addActionListener(e ->{
                Long Document = Long.parseLong(taDocument.getText());
                String name = taName.getText();
                String lastname = taLastname.getText();
                String address = taAddress.getText();
                Long phone = Long.parseLong(taPhone.getText());
                String specialty = cbSpecialty.getSelectedItem().toString();

                int[] selectedRows = table.getSelectedRows();
                List<User> selectedusers = new ArrayList<>();
                for(int row : selectedRows) {
                    Long userDocument = (long) table.getValueAt(row, 0);
                    User user = controller.findUserByDocument(userDocument);
                    if(user != null) {
                        selectedusers.add(user);
                    }
                }


                controller.updateDoctor(doctor,Document,name,lastname,address,phone,specialty,selectedusers);

                vd.openEditdoctor(EditDoctor.this);
                vd.setVisible(true);
                vd.loadinfo();
                setVisible(false);
        });
    }

    //----upload changes
    private void upload(Long document){
        Doctor doctor = new Doctor();
        doctor = controller.findDoctorByDocument(document);

        taDocument.setText(document.toString());
        taName.setText(doctor.getName());
        taLastname.setText(doctor.getLastname());
        taAddress.setText(doctor.getAddress());
        taPhone.setText(doctor.getPhone().toString());
        for (String o : MainForm.OptionsDoctor){
            if(o.equals(doctor.getSpecialty())){
                cbSpecialty.setSelectedItem(o);
            }
        }

        List<User> Users = controller.getusers();

        DefaultTableModel model = new DefaultTableModel(){
            //no sean editables
            @Override
            public boolean isCellEditable (int row, int column){
                return false;
            }
        };
        String[] titles ={"document","name", "last name"};
        model.setColumnIdentifiers(titles);

        if(Users!=null){
            for(User u : Users){
                model.addRow(new Object[]{u.getDocument(),u.getName(),u.getLastname()});
            }
        }else {
            System.out.println("no encontro nada");
        }

        table.setModel(model);

        List<User> users = doctor.getUsers();
        if(users!=null){
            for (int i = 0; i < table.getRowCount(); i++) {
                Long userdocument = (Long) table.getValueAt(i, 0); // Documento de doctor en la tabla
                for (User user : users) {
                    if (user.getDocument().equals(userdocument)) {
                        table.addRowSelectionInterval(i, i); // Seleccionar la fila
                    }
                }
            }
        }
    }
}
