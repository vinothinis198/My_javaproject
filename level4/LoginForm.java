package level4;

import javax.swing.*;

import java.awt.Font;
import java.awt.event.*;

public class LoginForm extends JFrame implements ActionListener
{
    JLabel tit,l1,l2;
    JTextField t1;
    JPasswordField p1;
    JButton b1,b2;
    Font myfont=new Font("arial",Font.BOLD,35);

    LoginForm()
    {
        setTitle("Vino app Login form");
        setLayout(null);

        tit = new JLabel("Login Form");
        tit.setFont(myfont);
        l1 = new JLabel("Enter user name");
        l2 = new JLabel("Password");

        t1 = new JTextField();
        p1 = new JPasswordField();

        b1 = new JButton("Login/SignIn");
        b2 = new JButton("Reset");

        tit.setBounds(300,100,250,50);

        l1.setBounds(200,200,150,40);
        l2.setBounds(200,260,150,40);

        t1.setBounds(400,200,200,40);
        p1.setBounds(400,260,200,40);

        b1.setBounds(250,350,120,40);
        b2.setBounds(450,350,120,40);

        b1.addActionListener(this);
        b2.addActionListener(this);

        add(tit);
        add(l1);
        add(l2);
        add(t1);
        add(p1);
        add(b1);
        add(b2);
    }

    public static void main(String[] args)
    {
        JFrame f1 = new LoginForm();

        f1.setSize(800,500);
        f1.setEnabled(true);
        f1.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b1)
        {
            String username = t1.getText();
            String password = new String(p1.getPassword());

            if(t1.getText().equals("vino") && p1.getText().equals("12345"))
            {
               // JOptionPane.showMessageDialog(null, "Valid User!!");
            	this.setVisible(false);
            	JFrame obj=new SimpleGui();
            	obj.setSize(100,700);
            	obj.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Invalid user/password");
            }
        }

        else
        {
            t1.setText("");
            p1.setText("");
            t1.requestFocus();
        }
    }
}