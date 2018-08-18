package shopping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class customer_search_product implements ActionListener {

    //To check customer data at text file
    public void Cust_check_pro(String product_id){

        //For customer to check product data
        try{

            Scanner sc = new Scanner(new File("src/stock_data.txt"));
            List<String> lines = new ArrayList<>();

            while (sc.hasNextLine()){
                lines.add(sc.nextLine());
                sc.hasNextLine();
            }

            //Array to store string from text file
            String[] arr_check_pro = lines.toArray(new String[0]);

            for(String p:arr_check_pro){

                //a to store my string from array
                if (p.contains(product_id)){
                    //Search_Cust(a);
                    Display_search_prodcut(p);
                }

            }



        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private JFrame Search_pro_cust;

    //Button
    JButton back = new JButton("Back");

    public void Display_search_prodcut(String P){   ///<------ fix here


        String [] check_pro_data = P.split(",");     //To split the string into product
        String product_id = check_pro_data[0];
        String product_name = check_pro_data[1];
        String product_quantity = check_pro_data[2];
        String product_rate = check_pro_data[3];


        //Build the user interface
        Search_pro_cust = new JFrame("Customer Search Product");
        Search_pro_cust.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create a panel to hold everything
        JPanel Search_pro_panel = new JPanel();


        //ADD to the frame
        Search_pro_cust.getContentPane().add(BorderLayout.CENTER,Search_pro_panel);
        Search_pro_cust.setSize(400,400);
        Search_pro_cust.setVisible(true);


        //Create a few label
        JLabel pro_id_label = new JLabel(product_id);

        JLabel pro_name_label = new JLabel(product_name);

        JLabel pro_quantity_label = new JLabel(product_quantity);

        JLabel pro_type = new JLabel(product_rate);

        //Add action listener
        back.addActionListener(this);

        Search_pro_panel.add(pro_id_label);
        Search_pro_panel.add(pro_name_label);
        Search_pro_panel.add(pro_quantity_label);
        Search_pro_panel.add(pro_type);
        Search_pro_panel.add(back);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            Search_pro_cust.setVisible(false);
        }
    }
}
