package GUI;

import LOGIC.Controller;
import LOGIC.Doctor;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDoctor extends javax.swing.JFrame {
    private JPanel EditDoctorpanel;
    private JPanel Mainpanel;
    private JTextField taDocument;
    private JList listPacients;
    private JComboBox cbSpecialty;
    private JButton RETURNButton;
    private JButton SAVEButton;
    private JButton CLEARButton;
    private JTextField taName;
    private JTextField taLastname;
    private JTextField taAddress;
    private JTextField taPhone;

    Doctor doctor;
    Controller controller = new Controller();
    private ViewDoctors vd;
    public void openMainForm(ViewDoctors vd,Long Document) {
        this.vd = vd;
        upload(Document);
        doctor = controller.findDoctorByDocument(Document);
    }

    public EditDoctor() {
        setContentPane(EditDoctorpanel);

        for(String o : MainForm.OptionsDoctor) {
            cbSpecialty.addItem(o);
        }

        RETURNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vd.openEditdoctor(EditDoctor.this);
                vd.setVisible(true);
                setVisible(false);
            }
        });

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

        SAVEButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Long Document = Long.parseLong(taDocument.getText());
                String name = taName.getText();
                String lastname = taLastname.getText();
                String address = taAddress.getText();
                Long phone = Long.parseLong(taPhone.getText());
                String specialty = cbSpecialty.getSelectedItem().toString();

                controller.updateDoctor(doctor,Document,name,lastname,address,phone,specialty);

                vd.openEditdoctor(EditDoctor.this);
                vd.setVisible(true);
                vd.loadinfo();
                setVisible(false);

            }
        });
    }

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

    }
}
