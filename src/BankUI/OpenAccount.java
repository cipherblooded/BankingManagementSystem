package BankUI;

import AppController.AppController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.Random;

public class OpenAccount extends Frame implements ActionListener {

    private static Button bt_createYourAccount;
    private static TextField tf_name, tf_password, tf_accountBalance;
    private static String accountNo, userID;
    private final Frame frame;

    public OpenAccount(){

        Label l_name, l_accountNo, l_userID, l_password;

        accountNo = Integer.toString(Math.abs((new Random()).nextInt()));
        userID = accountNo+"@cipherbank";

        frame = new Frame("Open Bank Account");
        frame.setSize(500, 350);
        frame.setBackground(Color.white);
        frame.setLayout((LayoutManager)null);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                frame.dispose();
            }
        });

        Label label = new Label("Open Your Bank Account");
        label.setBounds(130, 60, 250, 50);
        label.setFont(new Font("TimesRoman", 1, 20));
        label.setForeground(Color.black);
        frame.add(label);

        l_accountNo = new Label("Account No  :   " + accountNo);
        l_accountNo.setFont(new Font("Arial", 0, 14));
        l_accountNo.setBounds(120, 120, 250, 20);
        frame.add(l_accountNo);

        l_name = new Label("Name: ");
        l_name.setFont(new Font("Arial", 0, 14));
        l_name.setBounds(120,145,100,20);
        frame.add(l_name);

        tf_name = new TextField();
        tf_name.setFont(new Font("Arial", 0, 13));
        tf_name.setBounds(220, 145, 175, 20);
        frame.add(tf_name);

        l_userID = new Label("User ID :          " + userID);
        l_userID.setFont(new Font("Arial", 0, 14));
        l_userID.setBounds(120, 170, 300, 20);
        frame.add(l_userID);

        l_password = new Label("Password:");
        l_password.setFont(new Font("Arial", 0, 14));
        l_password.setBounds(120, 195, 100, 20);
        frame.add(l_password);

        tf_password = new TextField();
        tf_password.setFont(new Font("Arial", 0, 13));
        tf_password.setBounds(220, 195, 175, 20);
        frame.add(tf_password);

        l_password = new Label("Balance :");
        l_password.setFont(new Font("Arial", 0, 14));
        l_password.setBounds(120, 220, 100, 20);
        frame.add(l_password);

        tf_accountBalance = new TextField();
        tf_accountBalance.setFont(new Font("Arial", 0, 13));
        tf_accountBalance.setBounds(220, 220, 175, 20);
        frame.add(tf_accountBalance);

        bt_createYourAccount = new Button("Create your account");
        bt_createYourAccount.setBounds(153, 255, 200, 30);
        bt_createYourAccount.addActionListener(this);
        frame.add(bt_createYourAccount);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bt_createYourAccount) {

            String name = tf_name.getText().trim();
            String password = tf_password.getText().trim();
            String accountBalance = tf_accountBalance.getText().trim();

            if(!name.equals("")){

                if(AppController.isValidBalance(accountBalance)){

                    if(AppController.isValidPassword(password)){

                        AppController.startConnection();

                        try {
                            AppController.openBankAccount(accountNo, name, userID, password, "Saving Bank Account", accountBalance);
                            new DialogBox("Account Created Successfully","Welcome to Cipher Bank Family",400,150);
                            frame.dispose();

                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }

                        AppController.endConnection();

                    }else {
                        new DialogBox("Invalid Password","    Password must be of 8 digit, and must contain a Uppercase Letter, "+
                                "Lowercase Letter, "+
                                "Digit "+
                                "and Character.     ", 650,150);
                        tf_password.setText("");
                    }

                }else {
                    new DialogBox("Invalid Amount","Please enter a valid amount",400,150);
                    tf_accountBalance.setText("");
                }

            }else {
                new DialogBox("Invalid Name","Please enter a valid Name",400,150);
                tf_name.setText("");
            }

        }

    }

}
