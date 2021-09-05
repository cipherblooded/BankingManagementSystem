package BankUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AccountDetail implements ActionListener {

    private static Button bt_okay;
    private final Frame frame;

    public AccountDetail(String name, String accountNo, String userID, String accountType, String accountBalance){

        Label l_name, l_accountNo, l_userID, l_accountType, l_accountBalance;

        frame = new Frame("Account Detail");
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

        Label label = new Label("Account Detail");
        label.setBounds(175, 60, 250, 50);
        label.setFont(new Font("TimesRoman", 1, 20));
        label.setForeground(Color.black);
        frame.add(label);

        l_name = new Label("Name                   : "+ name);
        l_name.setFont(new Font("Arial", 0, 14));
        l_name.setBounds(120,120,300,20);
        frame.add(l_name);

        l_accountNo = new Label("Account No          : " + accountNo);
        l_accountNo.setFont(new Font("Arial", 0, 14));
        l_accountNo.setBounds(120, 145, 300, 20);
        frame.add(l_accountNo);

        l_userID = new Label("User ID                : "+ userID);
        l_userID.setFont(new Font("Arial", 0, 14));
        l_userID.setBounds(120,170,300,20);
        frame.add(l_userID);

        l_accountType = new Label("Account Type      : "+ accountType);
        l_accountType.setFont(new Font("Arial", 0, 14));
        l_accountType.setBounds(120,195,300,20);
        frame.add(l_accountType);

        l_accountBalance = new Label("Account Balance : "+ accountBalance);
        l_accountBalance.setFont(new Font("Arial", 0, 14));
        l_accountBalance.setBounds(120,220,300,20);
        frame.add(l_accountBalance);

        bt_okay = new Button("Okay");
        bt_okay.setBounds(153, 255, 200, 30);
        bt_okay.addActionListener(this);
        frame.add(bt_okay);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt_okay){
            frame.dispose();
        }
    }
}
