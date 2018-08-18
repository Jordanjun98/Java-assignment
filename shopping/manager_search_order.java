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


//This class is for manager to manage the customer order

public class manager_search_order implements ActionListener {

    private JFrame Manager_search_order;

    private JTextArea pro_ID_No;
    private JTextArea pro_Name;
    private JTextArea pro_Quantity;
    private JTextArea pro_type;

    JButton back = new JButton("Back");
    JButton update_pro = new JButton("Update");
    JButton delete_pro = new JButton("Delete");

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
                    Manager_Search_Product(pro);
                }

            }



        }catch(IOException e){
            e.printStackTrace();
        }

    }


    public void Manager_Search_Product(String pro_data){

        String [] new_pro_data = pro_data.split(",");     //To split the string into userid,username,ph_no and address
        String new_pro_id = new_pro_data[0];
        String new_pro_name = new_pro_data[1];
        String new_pro_quantity = new_pro_data[2];
        String new_pro_type = new_pro_data[3];

        //Build the user interface
        Manager_search_order = new JFrame("Product data");
        Manager_search_order.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Create a panel to hold everything
        JPanel M_search_pd = new JPanel();


        //ADD to the frame
        Manager_search_order.getContentPane().add(BorderLayout.CENTER,M_search_pd);
        Manager_search_order.setSize(400,400);
        Manager_search_order.setVisible(true);


        //Text area
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
        JLabel pro_id_label = new JLabel("Product ID");
        JLabel pro_name_label = new JLabel("Product name");
        JLabel pro_quantity_label = new JLabel("Quantity");
        JLabel pro_label = new JLabel("Product type");


        M_search_pd.add(pro_id_label);
        M_search_pd.add(pro_ID_No);

        M_search_pd.add(pro_name_label);
        M_search_pd.add(pro_Name);

        M_search_pd.add(pro_quantity_label);
        M_search_pd.add(pro_Quantity);

        M_search_pd.add(pro_label);
        M_search_pd.add(pro_type);

        M_search_pd.add(update_pro);
        M_search_pd.add(delete_pro);
        M_search_pd.add(back);

        //Add action listener
        back.addActionListener(this);
        update_pro.addActionListener(this);
        delete_pro.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == back){
            Manager_search_order.setVisible(false);
        }

        else if(e.getSource() == update_pro){

            product_data Update_new_product = new product_data();

            Update_new_product.setProduct_id(pro_ID_No.getText());
            Update_new_product.setProduct_name(pro_Name.getText());
            Update_new_product.setProduct_quantity(pro_Quantity.getText());
            Update_new_product.setProduct_rate(pro_type.getText());


            edit_product(Update_new_product.getProduct_id(), Update_new_product.getProduct_name(), Update_new_product.getProduct_quantity(), Update_new_product.getProduct_rate());


        }

        else if(e.getSource() == delete_pro){

            product_data delete_order = new product_data();

            delete_order.setProduct_id(pro_ID_No.getText());

            //delete_order.setCustomer_ID();

            Remove_product(delete_order.getProduct_id());

        }

    }


    //To delete customer order       //<-------- in progress
    public void Remove_product(String product_id){

        //To open login text file
        File delete = new File("src/product_data.txt");

        try{
            Scanner read2 = new Scanner(delete);
            read2.useDelimiter(",");


            //Array to store string for text file
            ArrayList<String[]> remove_product = new ArrayList<>();


            while (read2.hasNextLine()){
                String row[] = read2.nextLine().split(",");
                remove_product.add(row);
                read2.hasNextLine();
            }

            read2.close();

            String[][] delete_pro_data = new String[remove_product.size()][3];

            for (int r =0; r < remove_product.size(); r++){

                delete_pro_data[r]= remove_product.get(r);

                if(delete_pro_data[r][0].equals(product_id)){

                    delete_pro_data[r] = null;

                    System.out.print("Successfully remove product");

                }

            }
            Overwrite_product(delete_pro_data);

        }catch (IOException e){
            e.printStackTrace();
        }

    }


    public void edit_product(String New_pro_id, String New_pro_name, String New_pro_quantity,String New_pro_rate){

        //To open login text file
        File update = new File("src/product_data.txt");


        try {
            Scanner read3 = new Scanner(update);
            read3.useDelimiter(",");

            String new_product_data[][] = {{New_pro_id,New_pro_name,New_pro_quantity,New_pro_rate}};


            //Array to store string for text file
            ArrayList<String[]> lines_03 = new ArrayList<>();


            while (read3.hasNextLine()){
                String row[] = read3.nextLine().split(",");
                lines_03.add(row);
                read3.hasNextLine();
            }

            read3.close();

            String[][] pro_data = new String[lines_03.size()][3];

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
