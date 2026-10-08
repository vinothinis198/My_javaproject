package level4;

import javax.swing.*;

import java.awt.event.*;

import java.sql.*;

public class SimpleGui extends JFrame implements ActionListener
{
    JLabel l1,l2,l3;

    JTextField t1,t2,t3;

    JButton b1,b2,b3,b4,b5;

    SimpleGui()
    {
        setLayout(null);

        setTitle("Vino Application");

        l1=new JLabel("Enter register number:");

        l2=new JLabel("Enter Student number:");

        l3=new JLabel("Enter mark:");

        t1=new JTextField(20);

        t2=new JTextField(20);

        t3=new JTextField(20);

        b1=new JButton("Search/Find");

        b2=new JButton("Insert/Save");

        b3=new JButton("Delete/Remove");

        b4=new JButton("Update/Edit");

        b5=new JButton("Clear");


        l1.setBounds(0,100,200,30);

        l2.setBounds(0,200,200,30);

        l3.setBounds(0,300,200,30);

        t1.setBounds(250,100,200,30);

        t2.setBounds(250,200,200,30);

        t3.setBounds(250,300,200,30);

        b1.setBounds(600,100,150,30);

        b2.setBounds(600,200,150,30);

        b3.setBounds(600,300,150,30);

        b4.setBounds(600,400,150,30);

        b5.setBounds(600,500,150,30);

        b1.addActionListener(this);

        b2.addActionListener(this);

        b3.addActionListener(this);

        b4.addActionListener(this);

        b5.addActionListener(this);

        add(l1);

        add(l2);

        add(l3);

        add(t1);

        add(t2);

        add(t3);

        add(b1);

        add(b2);

        add(b3);

        add(b4);

        add(b5);
    }

    public static void main(String[] args) {

        JFrame f1=new SimpleGui();

        f1.setSize(800,700);

        f1.setEnabled(true);

        f1.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==b1) {

            if(t1.getText().length()>0)
            {
                int regno = Integer.parseInt(t1.getText());

                search(regno);
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "must fill the register number");
            }
        }

        if(e.getSource()==b2) {

            JOptionPane.showMessageDialog(this,
                    "you clicked button2 insert");

            int rno = Integer.parseInt(t1.getText());

            String sname = t2.getText();

            float mark = Float.parseFloat(t3.getText());

            insert(rno,sname,mark);
        }

        if(e.getSource()==b3) {

            if(t1.getText().length()>0)
            {
                int regno=Integer.parseInt(t1.getText());

                delete(regno);
            }
            else
            {
                JOptionPane.showMessageDialog(this,
                        "Must fill the register number");
            }
        }

        if(e.getSource()==b4) {

            JOptionPane.showMessageDialog(this,
                    "you clicked button4 update");

            if(t1.getText().length()>0)
            {
                int regno=Integer.parseInt(t1.getText());

                String sname=t2.getText();

                float mark=Float.parseFloat(t3.getText());

                update(regno,sname,mark);
            }
            else
            {
                JOptionPane.showMessageDialog(this,
                        "Must fill the register number");
            }
        }

        if(e.getSource()==b5) {

            clear();

        }
    }

    Statement dbConnection()
    {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/chettinad",
                    "root",
                    "12345");

            Statement st=con.createStatement();

            return st;

        }
        catch(Exception e) {
        }

        return null;
    }

    void search(int regno)
    {
        try
        {
            Statement st=dbConnection();

            ResultSet rs=st.executeQuery(
                    "select * from student where regno="+regno);

            if(rs.next())
            {
                t2.setText(rs.getString(2));

                t3.setText(rs.getString(3));
            }
            else
            {
                JOptionPane.showMessageDialog(this,
                        "Record not found");
            }

            rs.close();

            st.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,e.toString());
        }
    }

    void insert(int rno,String sname,float mark)
    {
        try
        {
            Statement st=dbConnection();

            int result = st.executeUpdate(
                    "insert into student values(" + rno + ",'" +
                    sname + "'," + mark + ")");

            if(result>0)
            {
                JOptionPane.showMessageDialog(this,
                        "Successfully inserted");
            }
            else {
                JOptionPane.showMessageDialog(this,
                        "not inserted");
            }

            st.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,e.toString());
        }
    }

    void delete(int regno)
    {
        try {

            Statement st=dbConnection();

            int result = st.executeUpdate(
                    "delete from student where regno=" + regno);

            if(result>0)
            {
                JOptionPane.showMessageDialog(this,
                        "successfully deleted");
            }
            else
            {
                JOptionPane.showMessageDialog(this,
                        "Record not found");
            }

            st.close();

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,e.toString());
        }
    }

    void update(int regno,String sname,float mark)
    {
        try
        {
            Statement st= dbConnection();

            int result=st.executeUpdate(
                    "Update student set sname='"+sname+
                    "', mark="+mark +" where regno="+regno);

            if(result>0)
            {
                JOptionPane.showMessageDialog(this,
                        "successfully updated");
            }
            else
            {
                JOptionPane.showMessageDialog(this,
                        "Record not found");
            }

            st.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this,
                    e.toString());
        }
    }

    void clear()
    {
        t1.setText("");

        t2.setText("");

        t3.setText("");

        t1.requestFocus();
    }
}