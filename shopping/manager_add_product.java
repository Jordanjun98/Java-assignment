package shopping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This class is for manager to add stock

public class manager_add_product implements ActionListener {

    private JFrame Add_product;

    private JTextArea Search;
    private JTextArea Product_id;
    private JTextArea Product_name;
    private JTextArea Product_quantity;
    private JTextArea Product_type;


    //Button
    JButton back = new JButton("Back");
    JButton add = new JButton("Add");
    JButton search_stock = new JButton("Search stock");

    public void manager_add_interface(){
        //Build the user interface
        Add_product = new JFrame("Manager add product data");
        Add_product.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Create a panel to hold everything
        JPanel Add_Product = new JPanel();

        //ADD to the frame
        Add_product.getContentPane().add(BorderLayout.CENTER,Add_Product);
        Add_product.setSize(700,400);
        Add_product.setVisible(true);



        //Text area
        Search = new JTextArea(0,6);
        Search.setSize(220,400);
        Search.setLineWrap(true);
        Search.setWrapStyleWord(true);

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


        //Create a few label
        //JLabel search_product = new JLabel("Search");
        JLabel pro_id = new JLabel("Product ID");
        JLabel pro_name = new JLabel("Product Name");
        JLabel pro_quantity = new JLabel("Product Quantity");
        JLabel pro_type = new JLabel("Product Type");


        //Add component panel
        Add_Product.add(pro_id);
        Add_Product.add(Product_id);

        Add_Product.add(pro_name);
        Add_Product.add(Product_name);

        Add_Product.add(pro_quantity);
        Add_Product.add(Product_quantity);

        Add_Product.add(pro_type);
        Add_Product.add(Product_type);

        Add_Product.add(back);
        Add_Product.add(add);
        Add_Product.add(search_stock);


        //Add action listener
        back.addActionListener(this);
        add.addActionListener(this);
        search_stock.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == back){
            //customer back = new customer();
            Add_product.setVisible(false);

        }
        else if (e.getSource() == add){

            product_data Add_new = new product_data();

            //product Add_New = new product();

            if (Product_id.getText().isEmpty() || Product_name.getText().isEmpty() || Product_quantity.getText().isEmpty() || Product_type.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please add in product detail");
            }else{
                Add_new.setProduct_id(Product_id.getText());
                Add_new.setProduct_name(Product_name.getText());
                Add_new.setProduct_quantity(Product_quantity.getText());
                Add_new.setProduct_rate(Product_type.getText());


                Order_item man_add = new Order_item();

                man_add.Add_stock(Add_new.getProduct_id(),Add_new.getProduct_name(),Add_new.getProduct_quantity(),Add_new.getProduct_rate());

                JOptionPane.showMessageDialog(null, "Successfull added");

            }
        }
        else if (e.getSource() == search_stock){

            if (Product_id.getText().isEmpty() || Product_name.getText().isEmpty()){

                JOptionPane.showMessageDialog(null, "Please add in product detail");

            }else{

                product_data search_stock = new product_data();
                Order_item display_stock = new Order_item();

                search_stock.setProduct_id(Product_id.getText());
                search_stock.setProduct_name(Product_name.getText());

                display_stock.Check_stock(search_stock.getProduct_id(),search_stock.getProduct_name());

                Add_product.setVisible(false);

            }
        }


    }
}

