package shopping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;

public class customer extends customer_data implements ActionListener {


    private JFrame Add_Customer;
    private JTextArea customer_ID_No;
    private JTextArea customer_Name;
    private JTextArea customer_address;
    private JTextArea customer_ph_no;

    //Sign up button and back
    JButton signup = new JButton("Register");
    JButton back = new JButton("Back");
    JButton search_cust = new JButton("Search");
    JButton delete_cust = new JButton("Delete");


    public void customer(){

        //Build the user interface
        Add_Customer = new JFrame("Customer");
        Add_Customer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Text area
        customer_ID_No = new JTextArea(0,6);
        customer_ID_No.setLineWrap(true);
        customer_ID_No.setWrapStyleWord(true);

        customer_Name = new JTextArea(0,6);
        customer_Name.setLineWrap(true);
        customer_Name.setWrapStyleWord(true);

        customer_address = new JTextArea(0,6);
        customer_address.setLineWrap(true);
        customer_address.setWrapStyleWord(true);

        customer_ph_no = new JTextArea(0,6);
        customer_ph_no.setLineWrap(true);
        customer_ph_no.setWrapStyleWord(true);


        //Create a few label
        JLabel id_label = new JLabel("Customer ID");
        JLabel name_label = new JLabel("Customer name");
        JLabel address_label = new JLabel("Address");
        JLabel phone_label = new JLabel("Phone no");


        //Create a panel to hold everything
        JPanel Add_new_customer_panel = new JPanel();


        //ADD to the frame
        Add_Customer.getContentPane().add(BorderLayout.CENTER,Add_new_customer_panel);
        Add_Customer.setSize(400,400);
        Add_Customer.setVisible(true);



        //Add action listener
        back.addActionListener(this);
        signup.addActionListener(this);
        search_cust.addActionListener(this);
        delete_cust.addActionListener(this);


        //Add component to customer panel
        Add_new_customer_panel.add(id_label);
        Add_new_customer_panel.add(customer_ID_No);

        Add_new_customer_panel.add(name_label);
        Add_new_customer_panel.add(customer_Name);

        Add_new_customer_panel.add(address_label);
        Add_new_customer_panel.add(customer_address);

        Add_new_customer_panel.add(phone_label);
        Add_new_customer_panel.add(customer_ph_no);

        Add_new_customer_panel.add(signup);
        Add_new_customer_panel.add(search_cust);
        Add_new_customer_panel.add(delete_cust);  //<--------- havent do
        Add_new_customer_panel.add(back);

    }



    @Override
    public void actionPerformed(ActionEvent e) {

        customer_data Signup = new customer_data();

        search_customer search = new search_customer();//To click the search button

        customer_data find = new customer_data();//From customer data class

        //mainmenu Back = new mainmenu();
        if (e.getSource() == signup) {
            if(customer_ID_No.getText().isEmpty() || customer_Name.getText().isEmpty() || customer_address.getText().isEmpty() || customer_ph_no.getText().isEmpty()){

                JOptionPane.showMessageDialog(null, "Please key in your detail");

            }else {

                Signup.setCustomer_ID(customer_ID_No.getText());
                Signup.setCustomer_Name(customer_Name.getText());
                Signup.setCustomer_Address(customer_address.getText());
                Signup.setCustomer_Ph_No(customer_ph_no.getText());

                SaveCustomer(Signup.getCustomer_ID(), Signup.getCustomer_Name(), Signup.getCustomer_Ph_No(), Signup.getCustomer_Address());

            }
        } else if (e.getSource() == back) {

            Add_Customer.setVisible(false);

            loginpage back = new loginpage();
            back.Admin_login();

        } else if (e.getSource() == search_cust) {


            if(customer_ID_No.getText().isEmpty() || customer_Name.getText().isEmpty()){

                JOptionPane.showMessageDialog(null, "Please key in username and Name");

            }else {

                find.setCustomer_ID(customer_ID_No.getText());
                find.setCustomer_Name(customer_Name.getText());

                search.Check_cust(find.getCustomer_ID(), find.getCustomer_Name());

            }
        } else if (e.getSource() == search_cust || e.getSource() == delete_cust) {

            if (customer_ID_No.getText().isEmpty() || customer_Name.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Please key in username and Name");

            } else {

                find.setCustomer_ID(customer_ID_No.getText());
                find.setCustomer_Name(customer_Name.getText());

                search.Check_cust(find.getCustomer_ID(), find.getCustomer_Name());

            }
        }
    }


    //To go next line in text file
    String newline = System.getProperty("line.separator");

    //To create new customer profile
    public void SaveCustomer(String Customer_ID_NO, String Cust_Name, String Cust_Ph_No, String Cust_Address){
        //To open login text file
        File signup = new File("src/customer_data.txt");

        try  {

            BufferedWriter save = new BufferedWriter(new FileWriter(signup, true));

            save.append(Customer_ID_NO);
            save.append(",");
            save.append(Cust_Name);
            save.append(",");
            save.append(Cust_Ph_No);
            save.append(",");
            save.append(Cust_Address);
            save.append(",");
            save.append(newline);
            save.close();

            CustomerLogin(Customer_ID_NO);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CustomerLogin(String Cust_id){

        File cust_login = new File("src/login.txt");

        try{

            BufferedWriter save = new BufferedWriter(new FileWriter(cust_login, true));

            String Cust_pass = Cust_id;

            save.append(Cust_id);
            save.append(",");
            save.append(Cust_pass);
            save.append(",");
            save.append("cust");    //To add customer type in text file
            save.append(",");
            save.append(newline);
            save.close();

        }catch(IOException e){
            e.printStackTrace();
        }

    }



}
