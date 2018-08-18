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

public class search_manager implements ActionListener {

    //To check customer data at text file
    public void Check_manager(String manager_id,String manager_name){

        //To check customer data
        try{

            Scanner sc = new Scanner(new File("src/managerdata.txt"));
            List<String> lines = new ArrayList<String>();

            while (sc.hasNextLine()){
                lines.add(sc.nextLine());
                sc.hasNextLine();
            }

            //Array to store string from text file
            String[] arr = lines.toArray(new String[0]);

            for(String man:arr){

                //a to store my string from array
                if (man.contains(manager_id) && man.contains(manager_name)){

                    Search_manager(man);
                }

            }



        }catch(IOException e){
            //e.printStackTrace();
        }

    }


    private JFrame Search_man_gui;

    private JTextArea man_ID_No;
    private JTextArea man_Name;
    private JTextArea man_address;
    private JTextArea man_Ph_no;

    //Button
    JButton back = new JButton("Back");
    JButton edit_man = new JButton("Edit");
    JButton delete_man = new JButton("Delete");

    public void Search_manager(String Man){

        String [] man_data = Man.split(",");     //To split the string into userid,username,ph_no and address
        String man_user_id = man_data[0];
        String man_user_name = man_data[1];
        String man_ph_no = man_data[2];
        String man_addr = man_data[3];

        System.out.print("Found it");

        //Build the user interface
        Search_man_gui = new JFrame("Manager data");
        Search_man_gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create a panel to hold everything
        JPanel Search_man_panel = new JPanel();


        //ADD to the frame
        Search_man_gui.getContentPane().add(BorderLayout.CENTER,Search_man_panel);
        Search_man_gui.setSize(700,400);
        Search_man_gui.setVisible(true);


        //Text area
        man_ID_No = new JTextArea(man_user_id);
        man_ID_No.setLineWrap(true);
        man_ID_No.setWrapStyleWord(true);

        man_Name = new JTextArea(man_user_name);
        man_Name.setLineWrap(true);
        man_Name.setWrapStyleWord(true);


        man_address = new JTextArea(man_addr);
        man_address.setLineWrap(true);
        man_address.setWrapStyleWord(true);

        man_Ph_no = new JTextArea(man_ph_no);
        man_Ph_no.setLineWrap(true);
        man_Ph_no.setWrapStyleWord(true);


        //Create a few label
        JLabel man_id_label = new JLabel("Manager ID");
        JLabel man_name_label = new JLabel("Manager name");
        JLabel man_address_label = new JLabel("Manager Address");
        JLabel man_phone_label = new JLabel("Manager Phone no");



        Search_man_panel.add(man_id_label);
        Search_man_panel.add(man_ID_No);

        Search_man_panel.add(man_name_label);
        Search_man_panel.add(man_Name);

        Search_man_panel.add(man_address_label);
        Search_man_panel.add(man_address);

        Search_man_panel.add(man_phone_label);
        Search_man_panel.add(man_Ph_no);

        Search_man_panel.add(edit_man);
        Search_man_panel.add(delete_man);
        Search_man_panel.add(back);

        delete_man.addActionListener(this);
        back.addActionListener(this);
        edit_man.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            Search_man_gui.setVisible(false);
        }
        else if (e.getSource() == edit_man){
            manager_data update_man = new manager_data();

            update_man.setManagerID(man_ID_No.getText());
            update_man.setManagerName(man_Name.getText());
            update_man.setManagerAddress(man_address.getText());
            update_man.setManagerPhNo(man_Ph_no.getText());


            Edit_man(update_man.getManagerID(),update_man.getManagerName(),update_man.getManagerAddress(),update_man.getManagerPhNo());

        }
        else if (e.getSource() == delete_man){
            manager_data delete = new manager_data();

            delete.setManagerID(man_ID_No.getText());
            delete.setManagerName(man_Name.getText());
            delete.setManagerAddress(man_address.getText());
            delete.setManagerPhNo(man_Ph_no.getText());

            Remove_man(delete.getManagerID(),delete.getManagerName());

        }
    }

    //To delete manager
    public void Remove_man(String man_id,String man_name){

        //To open login text file
        File delete = new File("src/managerdata.txt");

        try{
            Scanner read2 = new Scanner(delete);
            read2.useDelimiter(",");


            //Array to store string for text file
            ArrayList<String[]> remove_man = new ArrayList<>();


            while (read2.hasNextLine()){
                String row[] = read2.nextLine().split(",");
                remove_man.add(row);
                read2.hasNextLine();
            }

            read2.close();

            String[][] delete_man_data = new String[remove_man.size()][3];

            for (int r =0; r < remove_man.size(); r++){

                delete_man_data[r]= remove_man.get(r);

                if(delete_man_data[r][0].equals(man_id)){

                    delete_man_data[r] = null;

                    System.out.print("Successfully remove");

                }

            }
            Overwrite_man_data(delete_man_data);

        }catch (IOException e){
            e.printStackTrace();
        }

    }


    //To update customer data
    public void Edit_man(String manager_id,String manager_name,String manager_ph_no,String manager_address) {

        //To open login text file
        File update = new File("src/managerdata.txt");


        try {
            Scanner read1 = new Scanner(update);
            read1.useDelimiter(",");

            String add_man_data[][] = {{manager_id,manager_name,manager_ph_no,manager_address}};


            //Array to store string for text file
            ArrayList<String[]> lines_man = new ArrayList<>();


            while (read1.hasNextLine()){
                String row[] = read1.nextLine().split(",");
                lines_man.add(row);
                read1.hasNextLine();
            }

            read1.close();

            String[][] man_data = new String[lines_man.size()][3];

            int n =0;
            do{
                man_data[n]= lines_man.get(n);

                if (man_data[n][0].equals(add_man_data[0][0])){

                    man_data[n] = add_man_data[0];

                    System.out.println("Found it" + man_data);

                }
                n++;
            }while (n <lines_man.size());

            System.out.print("found it");

            Overwrite_man_data(man_data);

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public void Overwrite_man_data(String[][] new_man_data){

        //To open login text file
        File fold = new File("src/managerdata.txt");

        //To delete the file
        fold.delete();

        //To overwrite a new file
        File fnew = new File("src/managerdata.txt");


        //String [][] cust_data = new_cust_data;    //To split the string into userid,username,ph_no and address

        try{
            BufferedWriter save3 = new BufferedWriter(new FileWriter(fnew, true));

            StringJoiner sj = new StringJoiner(System.lineSeparator());

            for(String[] row : new_man_data){
                String s = Arrays.toString(row);
                sj.add(s.substring(1, s.length()-1));
                System.out.print(new_man_data);

            }
            String result = sj.toString();
            save3.write(result);
            save3.close();



        }catch(IOException e){
            e.printStackTrace();
        }


    }



}
