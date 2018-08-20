package shopping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//To purchase product only for customer

public class product implements ActionListener {


    private JFrame Product;

    private JTextArea Search;

    private JTextArea Product_id;
    private JTextArea Product_name;
    private JTextArea Product_quantity;
    private JTextArea Product_type;
    private JTextArea Cust_id;

    //Button
    JButton back = new JButton("Back");
    JButton search = new JButton("Search");
    JButton add = new JButton("Add new product");
    JButton bill = new JButton("Purchase history");



    public void product(){

        //Build the user interface
        Product = new JFrame("Buy new product");
        Product.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Text area

        Cust_id = new JTextArea(0,6);
        Cust_id.setSize(220,400);
        Cust_id.setLineWrap(true);
        Cust_id.setWrapStyleWord(true);



        Product_id = new JTextArea(0,6);
        Product_id.setSize(220,400);
        Product_id.setLineWrap(true);
        Product_id.setWrapStyleWord(true);



        Product_id = new JTextArea(0,6);
        Product_id.setSize(220,400);
        Product_id.setLineWrap(true);
        Product_id.setWrapStyleWord(true);

        Product_name = new JTextArea(0,6);
        Product_name.setSize(220,400);
        Product_name.setLineWrap(true);
        Product_name.setWrapStyleWord(true);

        Product_quantity = new JTextArea(0,6);
        Product_quantity.setSize(220,400);
        Product_quantity.setLineWrap(true);
        Product_quantity.setWrapStyleWord(true);

        Product_type = new JTextArea(0,6);
        Product_type.setSize(220,400);
        Product_type.setLineWrap(true);
        Product_type.setWrapStyleWord(true);


        //Create a panel to hold everything
        JPanel Add_new_product_panel = new JPanel();


        //ADD to the frame
        Product.getContentPane().add(BorderLayout.CENTER,Add_new_product_panel);
        Product.setSize(950,400);
        Product.setVisible(true);


        //Create a few label
        JLabel cust_id = new JLabel("Customer ID");
        JLabel pro_id = new JLabel("Product ID");
        JLabel pro_name = new JLabel("Product Name");
        JLabel pro_quantity = new JLabel("Product Quantity");
        JLabel pro_type = new JLabel("Product Type");

        //Add action listener
        add.addActionListener(this);
        back.addActionListener(this);
        search.addActionListener(this);
        bill.addActionListener(this);


        //Add component to customer panel
        //Add_new_product_panel.add(Bring_username);

        Add_new_product_panel.add(cust_id);
        Add_new_product_panel.add(Cust_id);

        Add_new_product_panel.add(pro_id);
        Add_new_product_panel.add(Product_id);

        Add_new_product_panel.add(pro_name);
        Add_new_product_panel.add(Product_name);

        Add_new_product_panel.add(pro_quantity);
        Add_new_product_panel.add(Product_quantity);

        Add_new_product_panel.add(pro_type);
        Add_new_product_panel.add(Product_type);

        Add_new_product_panel.add(add);
        Add_new_product_panel.add(back);
        Add_new_product_panel.add(search);
        Add_new_product_panel.add(bill);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

        product_data Add_new = new product_data();

        customer_data cust_buy_id = new customer_data();

        product_data search_pro = new product_data();



        if(e.getSource() == add){
            if(Product_id.getText().isEmpty() || Product_name.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please add in product detail");
            }else{

                cust_buy_id.setCustomer_ID(Cust_id.getText());
                Add_new.setProduct_id(Product_id.getText());
                Add_new.setProduct_name(Product_name.getText());
                Add_new.setProduct_quantity(Product_quantity.getText());
                Add_new.setProduct_rate(Product_type.getText());

                Save_product(cust_buy_id.getCustomer_ID(),Add_new.getProduct_id(),Add_new.getProduct_name(),Add_new.getProduct_quantity(),Add_new.getProduct_rate());

                JOptionPane.showMessageDialog(null, "Successfull added");

            }
        }

        else if (e.getSource() == back){
            Product.setVisible(false);

           loginpage back1 = new loginpage();
           back1.Cust_login();
        }

        else if (e.getSource() == search){
            if(Product_id.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter product id to search");
            }else {

                customer_search_product check_pro_cust = new customer_search_product();

                search_pro.setProduct_id(Product_id.getText());

                check_pro_cust.Cust_check_pro(search_pro.getProduct_id());


            }
        }

        else if (e.getSource() == bill){  //<------ display bill in progress
            if(Cust_id.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter customer id to check purchase history");
            }else{
                cust_buy_id.setCustomer_ID(Cust_id.getText());

                customerbill bill = new customerbill();

                bill.Cust_bill(cust_buy_id.getCustomer_ID());
            }
        }


    }

    //To go next line in text file
    String newline = System.getProperty("line.separator");


    public void Save_product(String Customer_id,String product_ID, String product_Name, String product_Quantity,String product_type){

        File Add_New = new File("src/product_data.txt");

        try{

            BufferedWriter add_new = new BufferedWriter(new FileWriter(Add_New, true));

            add_new.append(Customer_id);
            add_new.append(",");
            add_new.append(product_ID);
            add_new.append(",");
            add_new.append(product_Name);
            add_new.append(",");
            add_new.append(product_Quantity);
            add_new.append(",");
            add_new.append(product_type);
            add_new.append(",");
            add_new.append(newline);
            add_new.close();



        }catch (IOException e){
            e.printStackTrace();
        }

    }
}


