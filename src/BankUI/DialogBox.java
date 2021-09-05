package BankUI;

import java.awt.*;
import java.awt.event.*;
public class DialogBox implements ActionListener{

    private static Dialog dialog;
    private static Button bt_ok;
    private static Frame frame;

    public DialogBox(String tittle, String message, int width, int height) {

        frame= new Frame();

        dialog = new Dialog(frame , tittle, true);
        dialog.setLayout( new FlowLayout() );

        Label emptyLabel = new Label("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t" +
                "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t ");
        dialog.add(emptyLabel);

        Label label = new Label(message);
        dialog.add(label);

        bt_ok = new Button ("OK");
        bt_ok.setPreferredSize(new Dimension(100,20));
        bt_ok.addActionListener (this);
        dialog.add(bt_ok);

        dialog.setSize(width,height);
        dialog.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == bt_ok) {
            dialog.setVisible(false);
        }
    }

}