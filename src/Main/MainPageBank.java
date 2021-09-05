package Main;

import BankUI.Deposit;
import BankUI.OpenAccount;
import BankUI.SearchAccount;
import BankUI.Withdrawal;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainPageBank extends Frame implements ActionListener {

    private static Button openBankAccount, searchAccount, deposit, withdrawal;

    public MainPageBank(){

        final Frame frame = new Frame("Bank");
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

        Label label = new Label("Welcome to Cipher Bank");
        label.setBounds(120, 60, 250, 50);
        label.setFont(new Font("TimesRoman", 1, 20));
        label.setForeground(Color.black);
        frame.add(label);

        openBankAccount = new Button("Open Your Bank Account");
        openBankAccount.setBounds(140, 120, 200, 30);
        openBankAccount.addActionListener(this);
        frame.add(openBankAccount);

        searchAccount = new Button("Search Account");
        searchAccount.setBounds(140, 155, 200, 30);
        searchAccount.addActionListener(this);
        frame.add(searchAccount);

        deposit = new Button("Deposit");
        deposit.setBounds(140, 190, 200, 30);
        deposit.addActionListener(this);
        frame.add(deposit);

        withdrawal = new Button("Withdrawal");
        withdrawal.setBounds(140, 225, 200, 30);
        withdrawal.addActionListener(this);
        frame.add(withdrawal);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == openBankAccount) {

            new OpenAccount();

        } else if(e.getSource() == searchAccount){

            new SearchAccount();

        } else if(e.getSource() == deposit){

            new Deposit();

        } else if(e.getSource() == withdrawal){

            new Withdrawal();

        }
    }

    public static void main(String[] args) {
        new MainPageBank();
    }

}
