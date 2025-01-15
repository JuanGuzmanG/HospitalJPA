package GUI;

import LOGIC.Controller;
import LOGIC.Doctor;
import LOGIC.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditUser extends JFrame {
    private JPanel EditUserp;
    private JPanel EditUserPanel;
    private JTextField documentlbl;
    private JSpinner yearspinner;
    private JTextArea mhtxa;
    private JButton RETURNButton;
    private JButton SAVEButton;
    private JButton CLEARButton;
    private JTable tabledoctors;
    private JTextField namelbl;
    private JTextField lastnamelbl;
    private JTextField emaillbl;
    private JTextField phonelbl;
    private JSpinner dayspinner;
    private JSpinner monthspinner;

    User user;
    Controller controller = new Controller();

    //----window
    private ViewUsers view;
    public void openViewUser(ViewUsers view,Long Document) {
        this.view = view;
        upload(Document);
        user = controller.findUserByDocument(Document);
    }

    public EditUser(){
        setContentPane(EditUserp);


        RETURNButton.addActionListener(e -> {
            view.openEdituser(this);
            view.setVisible(true);
            view.setLocationRelativeTo(null);
            setVisible(false);
        });


        CLEARButton.addActionListener(e -> {
            documentlbl.setText("");
            namelbl.setText("");
            lastnamelbl.setText("");
            emaillbl.setText("");
            phonelbl.setText("");
            dayspinner.setValue(0);
            monthspinner.setValue(0);
            yearspinner.setValue(0);
            mhtxa.setText("");
        });
        SAVEButton.addActionListener(e -> {
            Long document = Long.parseLong(documentlbl.getText());
            String name = namelbl.getText();
            String lastname = lastnamelbl.getText();
            String email = emaillbl.getText();
            Long phone = Long.parseLong(phonelbl.getText());
            Date date = new Date((int)monthspinner.getValue(),(int)dayspinner.getValue(),(int)yearspinner.getValue());
            String history = mhtxa.getText();

            int[] selectedRows = tabledoctors.getSelectedRows();
            List<Doctor> doctors = new ArrayList<>();
            for (int row : selectedRows){
                Long DocumentDoctor = (long) tabledoctors.getValueAt(row,0);
                Doctor doctor = controller.findDoctorByDocument(DocumentDoctor);
                if (doctor != null){
                    doctors.add(doctor);
                }
            }

            controller.updateUser(user, document, name, lastname, email, phone, history,date,doctors);

            view.setVisible(true);
            view.openEdituser(this);
            view.setLocationRelativeTo(this);
            setVisible(false);
        });
    }

    private void upload(Long document){
        User user = controller.findUserByDocument(document);

        documentlbl.setText(document.toString());
        namelbl.setText(user.getName());
        lastnamelbl.setText(user.getLastname());
        emaillbl.setText(user.getEmail());
        phonelbl.setText(user.getPhone().toString());
        mhtxa.setText(user.getMedicalHistory());

        LocalDate localDate = user.getBrithdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        dayspinner.setValue(localDate.getDayOfMonth());
        monthspinner.setValue(localDate.getMonthValue());
        yearspinner.setValue(localDate.getYear());

        List<Doctor> doctors = controller.getdoctors();
        DefaultTableModel modeluser = new DefaultTableModel(){
            //no sean editables
            @Override
            public boolean isCellEditable (int row, int column){
                return false;
            }
        };
        String[] titles ={"document","specialty","name", "last name"};
        modeluser.setColumnIdentifiers(titles);

        if(doctors!=null){
            for(Doctor doctor : doctors){
                modeluser.addRow(new Object[]{doctor.getDocument(),doctor.getSpecialty(),doctor.getName(),doctor.getLastname()});
            }
        }else {
            System.out.println("no encontro nada");
        }
        tabledoctors.setModel(modeluser);

        List<Doctor> userDoctors = user.getDoctors(); // Lista de doctores del usuario
        if (userDoctors != null) {
            for (int i = 0; i < tabledoctors.getRowCount(); i++) {
                Long doctorDocument = (Long) tabledoctors.getValueAt(i, 0); // Documento de doctor en la tabla
                for (Doctor userDoctor : userDoctors) {
                    if (userDoctor.getDocument().equals(doctorDocument)) {
                        tabledoctors.addRowSelectionInterval(i, i); // Seleccionar la fila
                    }
                }
            }
        }
    }
}
