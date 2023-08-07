package bank;

import java.awt.event.*;
import java.sql.*;

import javax.swing.*;

public class Balance {
	//creating objects
	Connection con=null;
	PreparedStatement insert;
	ResultSet rs=null;
	Balance(){
		inBalanceComp();
	}
	private void inBalanceComp() {
		//using awt and swing for outlook
		JFrame f=new JFrame("View Balance");
		JLabel l1,l2,l3,l4,l5=null;
		l1=new JLabel("Enter Account Number:");
		l1.setBounds(100,100,180,30);
		l2=new JLabel("Account Number");
		l2.setBounds(100,230,180,30);
		l3=new JLabel("First Name");
		l3.setBounds(100,280,180,30);
		l4=new JLabel("Last Name");
		l4.setBounds(100,330,180,30);
		l5=new JLabel("Balance");
		l5.setBounds(100,380,180,30);
		JLabel d1=new JLabel("XXXXXXXXXXXXXXXX");
		d1.setBounds(270,230,180,30);
		JLabel d2=new JLabel("Firstname");
		d2.setBounds(270,280,180,30);
		JLabel d3=new JLabel("Lastname");
		d3.setBounds(270,330,180,30);
		JLabel d4=new JLabel("Balence amount");
		d4.setBounds(270,380,180,30);
		JTextField accno=new JTextField();
		accno.setBounds(270,100,180,30);
		JButton b1=new JButton("Fetch Balance");
		b1.setBounds(100,150,170,40);
		JButton b2=new JButton("Back");
		b1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				//actionPerformed(e);
				try {
					//connecting to database
					Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
					con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/bank","root","bobby");
					insert=con.prepareStatement("SELECT c.accountno,c.fname,c.lname,a.balance FROM customer c,account a where a.accountno=c.accountno and c.accountno=?");
					insert.setString(1,accno.getText());
					rs=insert.executeQuery();
					if(!rs.next()) {
						JOptionPane.showMessageDialog(null,"Account not found");
					}
					else {
						//checking balance
						String id = rs.getString(1);
						String firstname = rs.getString(2);
						String lastname = rs.getString(3);
						String balance=rs.getString(4);
						d1.setText(id.trim());
						d2.setText(firstname.trim());
						d3.setText(lastname.trim());
						d4.setText(balance.trim());
					}
				}
				catch(Exception ex) {
					System.out.println("failed "+ex);
				}
			}
		});
		b2.setBounds(280,150,170,40);
		b2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				new Home();
				f.dispose();
				//mouseClicked(e);
			}
		});
		f.add(l1);
		f.add(l2);
		f.add(l3);
		f.add(l4);
		f.add(l5);
		f.add(accno);
		f.add(d1);
		f.add(d2);
		f.add(d3);
		f.add(d4);
		f.add(b1);
		f.add(b2);
		f.setSize(600,520);
		f.setLayout(null);
		f.setVisible(true);
	}
}