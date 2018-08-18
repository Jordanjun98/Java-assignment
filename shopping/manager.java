package shopping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class manager extends manager_data implements ActionListener {


    private JFrame Add_manager;
    private JTextArea manager_ID_No;
    private JTextArea manager_Name;
    private JTextArea manager_Address;
    private JTextArea manager_Ph_no;


    //Sign up button and back
    JButton back = new JButton("Back");
    JButton Add_new = new JButton("Add");
    JButton search01 = new JButton("Search");

    public void manager(){

        //Build the user interface
        Add_manager = new JFrame("Add new manager");
        Add_manager.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create a panel to hold everything
        JPanel Add_new_manager_panel = new JPanel();


        //Text area
        manager_ID_No = new JTextArea(0,6);
        manager_ID_No.setLineWrap(true);
        manager_ID_No.setWrapStyleWord(true);

        manager_Name = new JTextArea(0,6);
        manager_Name.setLineWrap(true);
        manager_Name.setWrapStyleWord(true);

        manager_Address = new JTextArea(0,6);
        manager_Address.setLineWrap(true);
        manager_Address.setWrapStyleWord(true);

        manager_Ph_no = new JTextArea(0,6);
        manager_Ph_no.setLineWrap(true);
        manager_Ph_no.setWrapStyleWord(true);



        //Create a few label
        JLabel manager_id_label = new JLabel("Manager ID");
        JLabel manager_name_label = new JLabel("Name");
        JLabel manager_address_label = new JLabel("Address");
        JLabel manager_ph_label = new JLabel("Phone no");


        //ADD to the frame
        Add_manager.getContentPane().add(BorderLayout.CENTER,Add_new_manager_panel);
        Add_manager.setSize(700,400);
        Add_manager.setVisible(true);


        //Add action listener
        back.addActionListener(this);
        Add_new.addActionListener(this);
        search01.addActionListener(this);


        //Add component to customer panel
        Add_new_manager_panel.add(manager_id_label);
        Add_new_manager_panel.add(manager_ID_No);

        Add_new_manager_panel.add(manager_name_label);
        Add_new_manager_panel.add(manager_Name);

        Add_new_manager_panel.add(manager_address_label);
        Add_new_manager_panel.add(manager_Address);

        Add_new_manager_panel.add(manager_ph_label);
        Add_new_manager_panel.add(manager_Ph_no);

        Add_new_manager_panel.add(search01);
        Add_new_manager_panel.add(Add_new);
        Add_new_manager_panel.add(back);

    }
    //Get and set form manager_data class
    manager_data signup = new manager_data();

    @Override
    public void actionPerformed(ActionEvent e) {

        manager_data  Add = new manager_data();

        if(e.getSource() == Add_new){

            Add.setManagerID(manager_ID_No.getText());
            Add.setManagerName(manager_Name.getText());
            Add.setManagerPhNo(manager_Ph_no.getText());
            Add.setManagerAddress(manager_Address.getText());

            if(manager_ID_No.getText().isEmpty() || manager_Name.getText().isEmpty() || manager_Ph_no.getText().isEmpty() || manager_Address.getText().isEmpty()){

                JOptionPane.showMessageDialog(null, "Please key in the new manager data");

            }else {

                Save_Manager(Add.getManagerID(), Add.getManagerName(), Add.getManagerPhNo(), Add.getManagerAddress());
            }
        }if (e.getSource() == search01) {

            manager_data find1 = new manager_data();

            search_manager search1 = new search_manager();

            if(manager_ID_No.getText().isEmpty() || manager_Name.getText().isEmpty()){

                JOptionPane.showMessageDialog(null, "Please key in manager id and name");

            }else{
                find1.setManagerID(manager_ID_No.getText());
                find1.setManagerName(manager_Name.getText());

                search1.Check_manager(find1.getManagerID(),find1.getManagerName());
            }

        }if(e.getSource() == back){
            Add_manager.setVisible(false);
            //mainmenu back = new mainmenu();
            loginpage back = new loginpage();
            back.Admin_login();
        }
    }

    //To go next line in text file
    String newline1 = System.getProperty("line.separator");

    //To create new manager profile
    public void Save_Manager(String Manager_ID,String Manager_Name,String Manager_Ph,String Manager_add){

        File add = new File("src/managerdata.txt");

        try{

            BufferedWriter save1 = new BufferedWriter(new FileWriter(add, true));

            save1.append(Manager_ID);
            save1.append(",");
            save1.append(Manager_Name);
            save1.append(",");
            save1.append(Manager_Ph);
            save1.append(",");
            save1.append(Manager_add);
            save1.append(",");
            save1.append(newline1);
            save1.close();

            ManagerLogin(Manager_ID);

        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void ManagerLogin(String Manager_id){

        File mg_login = new File("src/login.txt");

        try{
            BufferedWriter save1 = new BufferedWriter(new FileWriter(mg_login, true));

            String Mg_pass = Manager_id;

            save1.append(Manager_id);
            save1.append(",");
            save1.append(Mg_pass);
            save1.append(",");
            save1.append("Manager");
            save1.append(",");
            save1.append(newline1);
            save1.close();


        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
