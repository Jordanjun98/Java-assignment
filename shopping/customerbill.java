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

public class customerbill implements ActionListener {

    //To check customer data at text file
    public void Cust_bill(String cust_id){

        //For customer to check product data
        try{

            Scanner sc = new Scanner(new File("src/product_data.txt"));
            List<String> lines = new ArrayList<>();

            while (sc.hasNextLine()){
                lines.add(sc.nextLine());
                sc.hasNextLine();
            }

            //Array to store string from text file
            String[] arr_bill = lines.toArray(new String[0]);

            for(String receipt:arr_bill){

                //a to store my string from array
                if (receipt.contains(cust_id)){
                    //Search_Cust(a);
                    Display_Bill(receipt);
                }

            }



        }catch(IOException e){
            e.printStackTrace();
        }

    }


    private JFrame Cust_Bill;

    //Button
    JButton back3 = new JButton("Back");

    public void Display_Bill(String bill){

        String [] check_bill = bill.split(",");     //To split the string into product
        String user_id = check_bill[0];
        String product_id = check_bill[1];
        String product_name = check_bill[2];
        String product_quantity = check_bill[3];
        String product_rate = check_bill[4];

        //Build the user interface
        Cust_Bill = new JFrame("Customer print bill");
        Cust_Bill.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create a panel to hold everything
        JPanel Cust_bill_panel = new JPanel();


        //ADD to the frame
        Cust_Bill.getContentPane().add(BorderLayout.CENTER,Cust_bill_panel);
        Cust_Bill.setSize(400,400);
        Cust_Bill.setVisible(true);

        //Create a few label
        JLabel user_id_label = new JLabel(user_id);
        JLabel product_id_label = new JLabel(product_id);
        JLabel product_name_label = new JLabel(product_name);
        JLabel product_quantity_label = new JLabel(product_quantity);
        JLabel product_rate_label = new JLabel(product_rate);




        Cust_bill_panel.add(user_id_label);
        Cust_bill_panel.add(product_id_label);
        Cust_bill_panel.add(product_name_label);
        Cust_bill_panel.add(product_quantity_label);
        Cust_bill_panel.add(product_rate_label);

        Cust_bill_panel.add(back3);

        back3.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == back3){
            Cust_Bill.setVisible(false);
        }
    }
}
