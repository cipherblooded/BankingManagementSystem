package BankUI;

import AppController.AppController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Withdrawal implements ActionListener {

    private static Button bt_confirm;
    private static TextField tf_userID, tf_userPassword, tf_amount;
    private final Frame frame;

    public Withdrawal() {

        Label l_userID, l_userPassword, l_amount;

        frame = new Frame("Cash Withdrawal");
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

        Label label = new Label("Cash Withdrawal");
        label.setBounds(175, 60, 250, 50);
        label.setFont(new Font("TimesRoman", 1, 20));
        label.setForeground(Color.black);
        frame.add(label);

        l_userID = new Label("User ID: ");
        l_userID.setFont(new Font("Arial", 0, 14));
        l_userID.setBounds(120,120,100,20);
        frame.add(l_userID);

        tf_userID = new TextField();
        tf_userID.setFont(new Font("Arial", 0, 13));
        tf_userID.setBounds(220, 120, 175, 20);
        frame.add(tf_userID);

        l_userPassword = new Label("Password:");
        l_userPassword.setFont(new Font("Arial", 0, 14));
        l_userPassword.setBounds(120, 145, 100, 20);
        frame.add(l_userPassword);

        tf_userPassword = new TextField();
        tf_userPassword.setFont(new Font("Arial", 0, 13));
        tf_userPassword.setBounds(220, 145, 175, 20);
        frame.add(tf_userPassword);

        l_amount = new Label("Amount :");
        l_amount.setFont(new Font("Arial", 0, 14));
        l_amount.setBounds(120, 170, 100, 20);
        frame.add(l_amount);

        tf_amount = new TextField();
        tf_amount.setFont(new Font("Arial", 0, 13));
        tf_amount.setBounds(220, 170, 175, 20);
        frame.add(tf_amount);

        bt_confirm = new Button("Confirm");
        bt_confirm.setBounds(153, 205, 200, 30);
        bt_confirm.addActionListener(this);
        frame.add(bt_confirm);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bt_confirm) {

            String userID = tf_userID.getText().trim();
            String userPassword = tf_userPassword.getText().trim();
            String amount = tf_amount.getText().trim();

            if(userID.contains("@cipherbank")){

                if(AppController.isValidBalance(amount)){

                    if(AppController.isValidPassword(userPassword)){

                        AppController.startConnection();

                        if(AppController.isValidUser(userID,userPassword)){

                            String accountNo = AppController.getAccountNo(userID);
                            long currentBalance = Long.parseLong(AppController.getBalance(accountNo));
                            long amountToDeduct = Long.parseLong(amount);

                            if (currentBalance >= amountToDeduct) {

                                currentBalance = currentBalance - amountToDeduct;
                                AppController.setBalance(accountNo, Long.toString(currentBalance));

                                new DialogBox("Transaction Competed","Thank you, Your transaction has been Completed",400,150);
                                AppController.printAccountDetail(accountNo);
                                frame.dispose();


                            } else {
                                new DialogBox("Insufficient Balance","Insufficient Balance, Please enter Amount within the limits",400,150);
                                tf_amount.setText("");
                            }

                        }else{
                            new DialogBox("Invalid Credential","Please enter a valid userID or Password",400,150);
                            tf_userPassword.setText("");
                        }

                        AppController.endConnection();

                    }else {
                        new DialogBox("Invalid Password","    Password must be of 8 digit, and must contain a Uppercase Letter, "+
                                "Lowercase Letter, "+
                                "Digit "+
                                "and Character.     ", 650,150);
                        tf_userPassword.setText("");

                    }

                }else {
                    new DialogBox("Invalid Amount","Please enter a valid Amount",400,150);
                    tf_amount.setText("");
                }

            }else {
                new DialogBox("Invalid UserID","Please enter a valid UserID",400,150);
                tf_userID.setText("");
            }

        }

    }
}
