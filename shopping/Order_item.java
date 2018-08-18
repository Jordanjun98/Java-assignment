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

//This class only for manager to add stock
public class Order_item implements ActionListener {

    //To go next line in text file
    String newline = System.getProperty("line.separator");


    public void Add_stock(String Product_id , String Product_name, String Product_quantity, String Product_rate) {

        File Add_New_stock = new File("src/stock_data.txt");


        try{

            BufferedWriter add_new_stock = new BufferedWriter(new FileWriter(Add_New_stock, true));

            add_new_stock.append(Product_id);
            add_new_stock.append(",");
            add_new_stock.append(Product_name);
            add_new_stock.append(",");
            add_new_stock.append(Product_quantity);
            add_new_stock.append(",");
            add_new_stock.append(Product_rate);
            add_new_stock.append(",");
            add_new_stock.append(newline);
            add_new_stock.close();



        }catch (IOException e){
            e.printStackTrace();
        }

    }


    //To check stock data at text file
    public void Check_stock(String Stock_id,String Stock_name ){

        //To check customer data
        try{

            Scanner sc4 = new Scanner(new File("src/stock_data.txt"));
            List<String> stock = new ArrayList<String>();

            while (sc4.hasNextLine()){
                stock.add(sc4.nextLine());
                sc4.hasNextLine();
            }

            //Array to store string from text file
            String[] arr_stock = stock.toArray(new String[0]);

            for(String s:arr_stock){

                //a to store my string from array
                if (s.contains(Stock_id) && s.contains(Stock_name)){
                    display_stock(s);
                }

            }



        }catch(IOException e){
            e.printStackTrace();
        }

    }


    private JFrame Display_stock;

    private JTextArea stock_id;
    private JTextArea stock_name;
    private JTextArea stock_quantity;
    private JTextArea stock_type;

    //Button
    JButton back = new JButton("Back");
    JButton update = new JButton("Update");
    JButton delete = new JButton("Delete");


    public void display_stock(String S){
                                            //<---------- stop here <---------

        String [] stock_data = S.split(",");     //To split the stock data to string
        String Stock_id = stock_data[0];
        String Stock_name = stock_data[1];
        String Stock_quantity = stock_data[2];
        String Stock_type = stock_data[3];


        //Build the user interface
        Display_stock = new JFrame("Stock data");
        Display_stock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create a panel to hold everything
        JPanel Search_stock_panel = new JPanel();

        //ADD to the frame
        Display_stock.getContentPane().add(BorderLayout.CENTER,Search_stock_panel);
        Display_stock.setSize(400,400);
        Display_stock.setVisible(true);


        //Text area
        stock_id = new JTextArea(Stock_id);
        stock_id.setLineWrap(true);
        stock_id.setWrapStyleWord(true);

        stock_name = new JTextArea(Stock_name);
        stock_name.setLineWrap(true);
        stock_name.setWrapStyleWord(true);


        stock_quantity = new JTextArea(Stock_quantity);
        stock_quantity.setLineWrap(true);
        stock_quantity.setWrapStyleWord(true);

        stock_type = new JTextArea(Stock_type);
        stock_type.setLineWrap(true);
        stock_type.setWrapStyleWord(true);


        //Create a few label
        JLabel stock_id_label = new JLabel("Stock ID");
        JLabel stock_name_label = new JLabel("Stock name");
        JLabel stock_quantity_label = new JLabel("Stock quantity");
        JLabel stock_type_label = new JLabel("Stock type");


        //Add component panel
        Search_stock_panel.add(stock_id_label);
        Search_stock_panel.add(stock_id);

        Search_stock_panel.add(stock_name_label);
        Search_stock_panel.add(stock_name);

        Search_stock_panel.add(stock_quantity_label);
        Search_stock_panel.add(stock_quantity);

        Search_stock_panel.add(stock_type_label);
        Search_stock_panel.add(stock_type);

        Search_stock_panel.add(delete);
        Search_stock_panel.add(update);
        Search_stock_panel.add(back);


        //Add action listener
        back.addActionListener(this);
        update.addActionListener(this);
        delete.addActionListener(this);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            Display_stock.setVisible(false);

        }else if (e.getSource() == update){

            product_data update_stock = new product_data();

            update_stock.setProduct_id(stock_id.getText());
            update_stock.setProduct_name(stock_name.getText());
            update_stock.setProduct_quantity(stock_quantity.getText());
            update_stock.setProduct_rate(stock_type.getText());

            Edit_stock(update_stock.getProduct_id(),update_stock.getProduct_name(),update_stock.getProduct_quantity(),update_stock.getProduct_rate());

        }else if (e.getSource() == delete){

            product_data delete_stock = new product_data();

            delete_stock.setProduct_id(stock_id.getText());

            Remove_stock(delete_stock.getProduct_id());

        }
    }


    //To delete customer order       //<-------- in progress
    public void Remove_stock(String stock_id){

        //To open login text file
        File delete = new File("src/stock_data.txt");

        try{
            Scanner read2 = new Scanner(delete);
            read2.useDelimiter(",");


            //Array to store string for text file
            ArrayList<String[]> remove_stock = new ArrayList<>();


            while (read2.hasNextLine()){
                String row[] = read2.nextLine().split(",");
                remove_stock.add(row);
                read2.hasNextLine();
            }

            read2.close();

            String[][] delete_stock_data = new String[remove_stock.size()][3];

            for (int r =0; r < remove_stock.size(); r++){

                delete_stock_data[r]= remove_stock.get(r);

                if(delete_stock_data[r][0].equals(stock_id)){

                    delete_stock_data[r] = null;

                    System.out.print("Successfully remove stock");

                }

            }
            Overwrite_stock(delete_stock_data);

        }catch (IOException e){
            e.printStackTrace();
        }

    }


    //To update customer data
    public void Edit_stock(String stock_id,String stock_name,String stock_quantity,String stock_rate) {

        //To open login text file
        File update = new File("src/stock_data.txt");


        try {
            Scanner read1 = new Scanner(update);
            read1.useDelimiter(",");

            String add_stock_data[][] = {{stock_id,stock_name,stock_quantity,stock_rate}};


            //Array to store string for text file
            ArrayList<String[]> stock_lines = new ArrayList<>();


            while (read1.hasNextLine()){
                String row[] = read1.nextLine().split(",");
                stock_lines.add(row);
                read1.hasNextLine();
            }

            read1.close();

            String[][] stock_data = new String[stock_lines.size()][3];

            int s =0;
            do{
                stock_data[s]= stock_lines.get(s);

                if (stock_data[s][0].equals(add_stock_data[0][0])){

                    stock_data[s] = add_stock_data[0];

                    System.out.println("Found it" + stock_data);

                }
                s++;
            }while (s <stock_lines.size());

            System.out.print("found it");
            Overwrite_stock(stock_data);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void Overwrite_stock(String[][] new_stock_data){

        //To open login text file
        File fold = new File("src/stock_data.txt");

        //To delete the file
        fold.delete();

        //To overwrite a new file
        File fnew = new File("src/stock_data.txt");


        //String [][] cust_data = new_cust_data;    //To split the string into userid,username,ph_no and address

        try{
            BufferedWriter save1 = new BufferedWriter(new FileWriter(fnew, true));

            StringJoiner sj = new StringJoiner(System.lineSeparator());

            for(String[] row : new_stock_data){
                String s = Arrays.toString(row);
                sj.add(s.substring(1, s.length()-1));
                System.out.print(new_stock_data);

            }
            String result = sj.toString();
            save1.write(result);
            save1.close();



        }catch(IOException e){
            e.printStackTrace();
        }


    }

}
