package shopping;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;

public class search_customer implements ActionListener{


    //To check customer data at text file
    public void Check_cust(String customer_id,String customer_name){

        //To check customer data
        try{

            Scanner sc = new Scanner(new File("src/customer_data.txt"));
            List<String> lines = new ArrayList<>();

            while (sc.hasNextLine()){
                lines.add(sc.nextLine());
                sc.hasNextLine();
            }

            //Array to store string from text file
            String[] arr = lines.toArray(new String[0]);

            for(String a:arr){

                //a to store my string from array
                if (a.contains(customer_id) && a.contains(customer_name)){
                    Search_Cust(a);
                }

            }



        }catch(IOException e){
            e.printStackTrace();
        }

    }

    private JFrame Search_Customer;

    private JTextArea customer_ID_No;
    private JTextArea customer_Name;
    private JTextArea customer_address;
    private JTextArea customer_ph_no;


    //Button
    JButton back = new JButton("Back");
    JButton edit = new JButton("Update");
    JButton delete = new JButton("Confirm delete");    //<------ delete

    public void Search_Cust(String a){

        String [] cust_data = a.split(",");     //To split the string into userid,username,ph_no and address
        String user_id = cust_data[0];
        String user_name = cust_data[1];
        String user_ph_no = cust_data[2];
        String user_addr = cust_data[3];

        //Build the user interface
        Search_Customer = new JFrame("Customer data");
        Search_Customer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create a panel to hold everything
        JPanel Search_customer_panel = new JPanel();

        //ADD to the frame
        Search_Customer.getContentPane().add(BorderLayout.CENTER,Search_customer_panel);
        Search_Customer.setSize(400,400);
        Search_Customer.setVisible(true);


        //Text area
        customer_ID_No = new JTextArea(user_id);
        customer_ID_No.setLineWrap(true);
        customer_ID_No.setWrapStyleWord(true);

        customer_Name = new JTextArea(user_name);
        customer_Name.setLineWrap(true);
        customer_Name.setWrapStyleWord(true);


        customer_address = new JTextArea(user_addr);
        customer_address.setLineWrap(true);
        customer_address.setWrapStyleWord(true);

        customer_ph_no = new JTextArea(user_ph_no);
        customer_ph_no.setLineWrap(true);
        customer_ph_no.setWrapStyleWord(true);


        //Create a few label
        JLabel id_label = new JLabel("Customer ID");
        JLabel name_label = new JLabel("Customer name");
        JLabel address_label = new JLabel("Address");
        JLabel phone_label = new JLabel("Phone no");


        //Add component panel
        Search_customer_panel.add(id_label);
        Search_customer_panel.add(customer_ID_No);

        Search_customer_panel.add(name_label);
        Search_customer_panel.add(customer_Name);

        Search_customer_panel.add(address_label);
        Search_customer_panel.add(customer_address);

        Search_customer_panel.add(phone_label);
        Search_customer_panel.add(customer_ph_no);

        Search_customer_panel.add(edit);
        Search_customer_panel.add(back);
        Search_customer_panel.add(delete);

        //Add action listener
        back.addActionListener(this);
        edit.addActionListener(this);
        delete.addActionListener(this);


    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == back){
            //customer back = new customer();
            Search_Customer.setVisible(false);

        }

        else if (e.getSource() == edit){

            customer_data edit = new customer_data();//From customer data class

            edit.setCustomer_ID(customer_ID_No.getText());
            edit.setCustomer_Name(customer_Name.getText());
            edit.setCustomer_Ph_No(customer_ph_no.getText());
            edit.setCustomer_Address(customer_address.getText());

            edit_cust(edit.getCustomer_ID(),edit.getCustomer_Name(),edit.getCustomer_Ph_No(),edit.getCustomer_Address());

        }

        else if (e.getSource() == delete){
            customer_data remove = new customer_data();//From customer data class

            remove.setCustomer_ID(customer_ID_No.getText());
            remove.setCustomer_Name(customer_Name.getText());
            remove.setCustomer_Ph_No(customer_ph_no.getText());
            remove.setCustomer_Address(customer_address.getText());

            Remove_cust(remove.getCustomer_ID(),remove.getCustomer_Name());
        }
    }

    //To delete customer
    public void Remove_cust(String customer_id,String customer_name){

        //To open login text file
        File delete = new File("src/customer_data.txt");

        try{
            Scanner read2 = new Scanner(delete);
            read2.useDelimiter(",");


            //Array to store string for text file
            ArrayList<String[]> remove_cust = new ArrayList<>();


            while (read2.hasNextLine()){
                String row[] = read2.nextLine().split(",");
                remove_cust.add(row);
                read2.hasNextLine();
            }

            read2.close();

            String[][] delete_cust_data = new String[remove_cust.size()][3];

            for (int r =0; r < remove_cust.size(); r++){

                delete_cust_data[r]= remove_cust.get(r);

                if(delete_cust_data[r][0].equals(customer_id)){

                    delete_cust_data[r] = null;

                    System.out.print("Successfully remove");

                }

            }
            Overwrite(delete_cust_data);

        }catch (IOException e){
            e.printStackTrace();
        }

    }

    //To update customer data
   public void edit_cust(String customer_id,String customer_name,String customer_ph_no,String customer_address) {

        //To open login text file
        File update = new File("src/customer_data.txt");
        

        try {
            Scanner read1 = new Scanner(update);
            read1.useDelimiter(",");

            String add_cust_data[][] = {{customer_id,customer_name,customer_ph_no,customer_address}};


            //Array to store string for text file
            ArrayList<String[]> lines_2 = new ArrayList<>();


            while (read1.hasNextLine()){
                String row[] = read1.nextLine().split(",");
                lines_2.add(row);
                read1.hasNextLine();
            }

            read1.close();

            String[][] cust_data = new String[lines_2.size()][3];

            int n =0;
            do{
                cust_data[n]= lines_2.get(n);

                if (cust_data[n][0].equals(add_cust_data[0][0])){

                    cust_data[n] = add_cust_data[0];

                    System.out.println("Found it" + cust_data);

                }
                n++;
            }while (n <lines_2.size());

            Overwrite(cust_data);

            }catch(IOException e){
               e.printStackTrace();
            }
        }

   public void Overwrite(String[][] new_cust_data){

       //To open login text file
       File fold = new File("src/customer_data.txt");

       //To delete the file
       fold.delete();

       //To overwrite a new file
       File fnew = new File("src/customer_data.txt");


       //String [][] cust_data = new_cust_data;    //To split the string into userid,username,ph_no and address

       try{
           BufferedWriter save1 = new BufferedWriter(new FileWriter(fnew, true));

           StringJoiner sj = new StringJoiner(System.lineSeparator());

           for(String[] row : new_cust_data){
               String s = Arrays.toString(row);
               sj.add(s.substring(1, s.length()-1));
               System.out.print(new_cust_data);

           }
            String result = sj.toString();
            save1.write(result);
            save1.close();



       }catch(IOException e){
           e.printStackTrace();
       }


   }



}



