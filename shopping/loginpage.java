package shopping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


public class loginpage extends Component implements ActionListener{

    private JTextArea username;
    private JPasswordField password;
    private JFrame loginpage;

    //Login
    JButton login = new JButton("Login");


    //User interface of loginpage
    public loginpage(){


        //Build the user interface
        loginpage = new JFrame("Loginpage");
        loginpage.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create a panel to hold everything
        JPanel loginpanel = new JPanel();

        //Text area username and password
        username = new JTextArea(0,6);
        username.setLineWrap(true);
        username.setWrapStyleWord(true);

        password = new JPasswordField(6);


        //Create a few label
        JLabel usr_label = new JLabel("Username");
        JLabel psw_label = new JLabel("Password");

        //Add component to loginpanel
        loginpanel.add(usr_label);
        loginpanel.add(username);
        loginpanel.add(psw_label);
        loginpanel.add(password);
        loginpanel.add(login);
        //loginpanel.add(signup);

        //Add action listener
        login.addActionListener(this);


        //ADD to the frame
        loginpage.getContentPane().add(BorderLayout.CENTER,loginpanel);
        loginpage.setSize(200,300);
        loginpage.setVisible(true);


        //mainmenu pass_username = new mainmenu();
       // pass_username.actionPerformed(username.getText());


}

    //Global object
    mainmenu menu = new mainmenu();

    @Override
    public void actionPerformed(ActionEvent e){

        //When login button is click
        if (e.getSource() == login) {
            menu.setUsername(username.getText());
            menu.setPassword(new String(password.getPassword()));

            try {
                //To call check login  method
                checkLogin(menu.getUsername(),menu.getPassword());
            } catch (IOException e1) {
                e1.printStackTrace();
            }


        }
    }

    //To open file
    public void checkLogin(String Username, String Password) throws IOException {


        //To open login text file
        File login = new File("src/login.txt");

        try{
            Scanner read = new Scanner(login);
            read.useDelimiter(",");

            while(read != null){

                String user = read.next();
                String pass = read.next();
                String type = read.next();
                read.useDelimiter(",");
                read.nextLine();

                if(username.getText().isEmpty() || password.getText().isEmpty()){

                    No_input();

                }else if(Username.equals(user) && Password.equals(pass) && type.equals("admin")){                //For admin login

                    Admin_login();
                    
                }else if (Username.equals(user) && Password.equals(pass) && type.equals("cust")){               //For customer login

                    Cust_login();


                }else if(Username.equals(user) && Password.equals(pass) && type.equals("Manager")){             //For manager login

                    Manager_login();


                }else if (!Username.equals(user) && Password.equals(pass)){
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }

            }
        }catch (Exception e){
            //System.out.print("Wrong username or password");
        }

    }

    public void No_input(){

        JOptionPane.showMessageDialog(null, "Please key in your username or password");
    }

    public void Admin_login(){
        //Main menu class
        menu.mainmenu();

        //Close the login page
        loginpage.setVisible(false);

        //Restrict customer to manage product
        menu.Order.setVisible(false);

        menu.Product.setVisible(false);

        //menu.Order_item.setVisible(false);


    }

    public void Cust_login(){
        //Main menu class
        menu.mainmenu();

        //Restrict customer to see add Customer page
        menu.Customer.setVisible(false);

        //Restrict customer to see add Manager page
        menu.Manager.setVisible(false);

        menu.Order.setVisible(false);

        //menu.Order_item.setVisible(false);

        //Close login page
        loginpage.setVisible(false);


    }


    public void Manager_login(){
        //To main menu page
        menu.mainmenu();

        //Restrict manager to see add Customer detail
        menu.Customer.setVisible(false);

        //Restrict manager to see add Manager page
        menu.Manager.setVisible(false);

        //Close login page
        loginpage.setVisible(false);

        menu.Product.setVisible(false);
    }




    public static void main(String[] args ){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                new loginpage();

            }
        });
    }
}
