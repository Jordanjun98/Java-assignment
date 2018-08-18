package shopping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class mainmenu extends username_password implements ActionListener {

    public JFrame main_menu;

    //Login and signup button
    JButton Customer = new JButton("Customer");
    JButton Manager = new JButton("Manager");
    JButton Order = new JButton("View order item"); //only for manager
    JButton Product = new JButton("Purchase");
    JButton Logout = new JButton("Logout");


    public void mainmenu(){

        //Create a panel to hold everything
        JPanel menu = new JPanel();

        //Build the user interface
        main_menu = new JFrame("Main menu page");
        main_menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        //ADD to the frame
        main_menu.getContentPane().add(BorderLayout.CENTER,menu);
        main_menu.setSize(400,400);
        main_menu.setVisible(true);


        //Add component to menu panel
        menu.add(Customer);
        menu.add(Manager);
        menu.add(Order);
        menu.add(Product);
        menu.add(Logout);

        //Add action listener
        Customer.addActionListener(this);
        Logout.addActionListener(this);
        Manager.addActionListener(this);
        Product.addActionListener(this);
        Order.addActionListener(this);


    }

    manager Register_manager = new manager();
    customer Register_customer = new customer();
    product Add_new_product = new product();

    @Override
    public void actionPerformed(ActionEvent e){

        //To add feature for each user
        if (e.getSource() == Customer) {
            Register_customer.customer();
            main_menu.setVisible(false);
            //System.out.println("Customer is click");
        }

        else if (e.getSource() == Manager){
            Register_manager.manager();
            main_menu.setVisible(false);
        }

        else if (e.getSource() == Logout){
            loginpage logout = new loginpage();
            main_menu.setVisible(false);
            logout.setVisible(true);
            //menu.setVisible();
        }

        else if (e.getSource() == Order ){

            Purchase buy = new Purchase();

            buy.Read_product_file(); //To view customer order

           // main_menu.setVisible(false);
        }

        else if(e.getSource() == Product){    //Customer pruchase or add product

            Add_new_product.product();
            main_menu.setVisible(false);
        }





    }

    /*public void ToExit(){

        exit.loginpage();

    }*/


}
