package shopping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class manager_manage_cust_order implements ActionListener {


    private JFrame Edit_cust_order;

    private JTextArea user_id;
    private JTextArea pro_ID_No;
    private JTextArea pro_Name;
    private JTextArea pro_Quantity;
    private JTextArea pro_type;



    //To check customer data at text file
    public void Check_pro(String Pro_Id){

        //To check customer data
        try{

            Scanner sc = new Scanner(new File("src/product_data.txt"));
            List<String> pro_lines = new ArrayList<String>();

            while (sc.hasNextLine()){
                pro_lines.add(sc.nextLine());
                sc.hasNextLine();
            }

            //Array to store string from text file
            String[] new_pro_arr = pro_lines.toArray(new String[0]);

            for(String pro: new_pro_arr){

                //pro to store my string from array
                if (pro.contains(Pro_Id)){
                    edit_cust_order(pro);
                }

            }



        }catch(IOException e){
            e.printStackTrace();
        }

    }

    //Button
    JButton back = new JButton("Back");
    JButton edit = new JButton("Update");


    public void edit_cust_order(String pro) {

        String [] new_pro_data = pro.split(",");     //To split the string into userid,username,ph_no and address
        String new_user_id = new_pro_data[0];
        String new_pro_id = new_pro_data[1];
        String new_pro_name = new_pro_data[2];
        String new_pro_quantity = new_pro_data[3];
        String new_pro_type = new_pro_data[4];

        //Build the user interface
        Edit_cust_order = new JFrame("Cust order");
        Edit_cust_order.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Create a panel to hold everything
        JPanel M_edit_cust_order = new JPanel();


        //ADD to the frame
        Edit_cust_order.getContentPane().add(BorderLayout.CENTER, M_edit_cust_order);
        Edit_cust_order.setSize(600, 400);
        Edit_cust_order.setVisible(true);


        //Text area
        user_id = new JTextArea(new_user_id);
        user_id.setLineWrap(true);
        user_id.setWrapStyleWord(true);

        pro_ID_No = new JTextArea(new_pro_id);
        pro_ID_No.setLineWrap(true);
        pro_ID_No.setWrapStyleWord(true);

        pro_ID_No = new JTextArea(new_pro_id);
        pro_ID_No.setLineWrap(true);
        pro_ID_No.setWrapStyleWord(true);

        pro_Name = new JTextArea(new_pro_name);
        pro_Name.setLineWrap(true);
        pro_Name.setWrapStyleWord(true);

        pro_Quantity = new JTextArea(new_pro_quantity);
        pro_Quantity.setLineWrap(true);
        pro_Quantity.setWrapStyleWord(true);

        pro_type = new JTextArea(new_pro_type);
        pro_type.setLineWrap(true);
        pro_type.setWrapStyleWord(true);


        //Create a few label
        //JLabel search_product = new JLabel("Search");
        JLabel User_id = new JLabel("User ID");
        JLabel pro_id = new JLabel("Product ID");
        JLabel pro_name = new JLabel("Product Name");
        JLabel pro_quantity = new JLabel("Product Quantity");
        JLabel pro_Type = new JLabel("Product Type");


        //Add component panel
        M_edit_cust_order.add(User_id);
        M_edit_cust_order.add(user_id);

        M_edit_cust_order.add(pro_id);
        M_edit_cust_order.add(pro_ID_No);

        M_edit_cust_order.add(pro_name);
        M_edit_cust_order.add(pro_Name);

        M_edit_cust_order.add(pro_quantity);
        M_edit_cust_order.add(pro_Quantity);

        M_edit_cust_order.add(pro_Type);
        M_edit_cust_order.add(pro_type);

        M_edit_cust_order.add(back);
        M_edit_cust_order.add(edit);

        back.addActionListener(this);
        edit.addActionListener(this);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            Edit_cust_order.setVisible(false);
        }else if (e.getSource() == edit){
            product_data update_pro = new product_data();
            customer_data update_cart = new customer_data();

            update_cart.setCustomer_ID(user_id.getText());
            update_pro.setProduct_id(pro_ID_No.getText());
            update_pro.setProduct_name(pro_Name.getText());
            update_pro.setProduct_quantity(pro_Quantity.getText());
            update_pro.setProduct_rate(pro_type.getText());
            Update_cust_order(update_cart.getCustomer_ID(),update_pro.getProduct_id(),update_pro.getProduct_name(),update_pro.getProduct_quantity(),update_pro.getProduct_rate());

            //Update_cust_order(user_id,pro_ID_No,pro_Name,pro_Quantity,pro_type);
        }
    }


    public void Update_cust_order(String User_id, String product_id, String product_name,String product_quantity,String product_rate){
        //To open login text file
        File update = new File("src/product_data.txt");


        try {
            Scanner read3 = new Scanner(update);
            read3.useDelimiter(",");

            String new_product_data[][] = {{User_id,product_id,product_name,product_quantity,product_rate}};


            //Array to store string for text file
            ArrayList<String[]> lines_03 = new ArrayList<>();


            while (read3.hasNextLine()){
                String row[] = read3.nextLine().split(",");
                lines_03.add(row);
                read3.hasNextLine();
            }

            read3.close();

            String[][] pro_data = new String[lines_03.size()][4];

            int p =0;
            do{
                pro_data[p]= lines_03.get(p);

                if (pro_data[p][0].equals(new_product_data[0][0])){

                    pro_data[p] = new_product_data[0];

                    System.out.println("Found it" + pro_data);

                }
                p++;
            }while (p <lines_03.size());

            Overwrite_product(pro_data);

        }catch(IOException e){
            e.printStackTrace();
        }


    }

    public void Overwrite_product(String[][] new_pro_data){

        //To open login text file
        File fold = new File("src/product_data.txt");

        //To delete the file
        fold.delete();

        //To overwrite a new file
        File fnew = new File("src/product_data.txt");


        //String [][] cust_data = new_cust_data;    //To split the string into userid,username,ph_no and address

        try{
            BufferedWriter save1 = new BufferedWriter(new FileWriter(fnew, true));

            StringJoiner sj = new StringJoiner(System.lineSeparator());

            for(String[] row1 : new_pro_data){
                String s = Arrays.toString(row1);
                sj.add(s.substring(1, s.length()-1));
                System.out.print(new_pro_data);

            }
            String result = sj.toString();
            save1.write(result);
            save1.close();



        }catch(IOException e){
            e.printStackTrace();
        }


    }



}

