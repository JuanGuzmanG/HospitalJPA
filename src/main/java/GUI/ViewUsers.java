package GUI;

import LOGIC.Controller;
import LOGIC.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ViewUsers extends javax.swing.JFrame {

    Controller controller = null;

    private JPanel Mainpanel;
    private JPanel ViewUsersMain;
    private JButton DELETEButton;
    private JButton SAVECHANGESButton;
    private JButton RETURNButton;
    private JTable table;

    private MainForm mf;
    public void openMainform(MainForm mf) {
        this.mf = mf;
    }

    public ViewUsers() {
        controller = new Controller();
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

    public void loadInfo(){
        DefaultTableModel model = new DefaultTableModel(){
            //no sean editables
            @Override
            public boolean isCellEditable (int row, int column){
                return false;
            }
        };

        String[] titles ={"name", "last name", "email","phone","Medical History","birthday"};
        model.setColumnIdentifiers(titles);

        List<User> ListUsers = controller.getusers();

        if(ListUsers!=null){
            for(User u : ListUsers){
                model.addRow(new Object[]{u.getName(),u.getLastname(),u.getEmail(),u.getPhone(),u.getMedicalHistory(),u.getBrithdate()});
            }
        }else {
            System.out.println("no encontro nada");
        }

        table.setModel(model);
    }
}
