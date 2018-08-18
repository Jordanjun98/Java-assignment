package shopping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;


//This class only for manager to view customer order

//This class is under manager

public class Purchase implements ActionListener {

    private JFrame purchase_item;

    //Table
    JTable purchase_table = new JTable(4,5);



    public void Read_product_file(){

        try {
            Scanner sc2 = new Scanner(new File("src/product_data.txt"));
            sc2.useDelimiter(",");

            ArrayList<String[]> product_lines = new ArrayList<>();

            while (sc2.hasNextLine()){
                String row[] = sc2.nextLine().split(",");
                product_lines.add(row);
                sc2.hasNextLine();
            }

            sc2.close();


            purchase(product_lines);


        }catch (IOException e){
            e.printStackTrace();
        }

    }

    private JTextArea product_id;

    //Button
    JButton back = new JButton("Back");
    JButton add = new JButton("Add");
    JButton search_product = new JButton("Search");



    public void purchase(ArrayList<String[]> arr_03) {


        //Create a panel to hold everything
        JPanel Buy_new_product = new JPanel();

        String[][] product_id_arr = new String[arr_03.size()][4];

        //String[][] labels = new String[arr_03.size()][4];

        String[] columnheader = {"Customer ID","Product ID", "Product Name", "Quantity","Type"};

        for(int i = 0; i < arr_03.size(); i++){

            product_id_arr[i]= arr_03.get(i);

        }

        purchase_table = new JTable(product_id_arr, columnheader);

        purchase_table.setBounds(90,90,450,450);


        JScrollPane js = new JScrollPane(purchase_table);
        js.setVisible(true);


        //Build the user interface
        purchase_item = new JFrame("View order product");
        purchase_item.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Text area
        product_id = new JTextArea();
        product_id.setLineWrap(true);
        product_id.setWrapStyleWord(true);

        //ADD to the frame
        purchase_item.getContentPane().add(BorderLayout.CENTER,Buy_new_product);
        purchase_item.setSize(550,550);
        purchase_item.setVisible(true);

        //Create a few label
        JLabel man_id_label = new JLabel("Product ID");


        //Add component panel
        Buy_new_product.add(add);
        Buy_new_product.add(back);

        Buy_new_product.add(man_id_label);
        Buy_new_product.add(product_id);

        Buy_new_product.add(search_product);      //  <<------- //Havent do yet
        Buy_new_product.add(js);


        //Add action listener
        back.addActionListener(this);
        add.addActionListener(this);
        search_product.addActionListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == back){
            purchase_item.setVisible(false);

        }

        else if (e.getSource() == add){
            manager_add_product add_new = new manager_add_product();
            purchase_item.setVisible(false);
            add_new.manager_add_interface();

        }

        else if (e.getSource() == search_product){

            manager_search_order m_search_order = new manager_search_order();

            product_data search1 = new product_data();

                            //<<------- stop here


            if(product_id.getText().isEmpty()){

                JOptionPane.showMessageDialog(null, "Please key in product id");

            }else{

                purchase_item.setVisible(false);

                search1.setProduct_id(product_id.getText());

                m_search_order.Check_pro(search1.getProduct_id());

            }



        }
    }


}
