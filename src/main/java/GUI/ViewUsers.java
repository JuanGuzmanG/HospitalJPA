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
    private JButton EditBtn;
    private JButton RETURNButton;
    private JTable table;

    private MainForm mf;
    public void openMainform(MainForm mf) {
        this.mf = mf;
    }

    private EditUser ed = new EditUser();
    public void openEdituser(EditUser ed) {this.ed = ed;}

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

        EditBtn.addActionListener(e -> {
            if(table.getRowCount()>0){
                if(table.getSelectedRow()!=-1){
                    User user = controller.findUserByDocument((long)table.getValueAt(table.getSelectedRow(),0));
                    ed.openViewUser(this,user.getDocument());
                    ed.setVisible(true);
                    ed.setSize(800,500);
                    ed.setLocationRelativeTo(this);
                    setVisible(false);
                }else{
                    mf.message("No user selected","error","error selected");
                }
            }else{
                mf.message("table is empty","error","error empty table");
            }
        });


        DELETEButton.addActionListener(e ->{
            if(table.getRowCount()>0){
                if(table.getSelectedRow()==-1){
                    mf.message("No user selected","error","error selected");
                }else if(table.getSelectedRow()!=-1){
                    Long iduser = (Long)table.getValueAt(table.getSelectedRow(),0);
                    if(iduser!=null){
                        User user = controller.findUserByDocument(iduser);
                        controller.DeleteUser(user.getDocument());
                        mf.message("Deleted user","info","deleted");
                        loadInfo();
                    }else {
                        mf.message("No user selected","error","error selected");
                    }

                }
            }else if(table.getRowCount()==0){
                mf.message("Table is empty","error","Error empty table");
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

        String[] titles ={"document","name", "last name", "email","phone","Medical History","birthday"};
        model.setColumnIdentifiers(titles);

        List<User> ListUsers = controller.getusers();

        if(ListUsers!=null){
            for(User u : ListUsers){
                model.addRow(new Object[]{u.getDocument(),u.getName(),u.getLastname(),u.getEmail(),u.getPhone(),u.getMedicalHistory(),u.getBrithdate()});
            }
        }else {
            System.out.println("no encontro nada");
        }

        table.setModel(model);
    }
}
