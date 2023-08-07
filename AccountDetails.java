package bank;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;

public class AccountDetails {
	//creatig objects
	int i=0;
	Connection con=null;
	ResultSet rs=null;
	PreparedStatement insert,insert1,insert2;
	AccountDetails(){
		inAccDetComponents(null);
	}
	private void inAccDetComponents(java.awt.event.KeyEvent evt){
		//using awt and swing for outlook
		JFrame f=new JFrame("Register Customer");
		JLabel l1,l2,l3,l4,l5=null;
		l1=new JLabel("First Name");
		l1.setBounds(100,100,100,30);
		l2=new JLabel("Last Name");
		l2.setBounds(100,150,100,30);
		l3=new JLabel("City");
		l3.setBounds(100,200,100,30);
		l4=new JLabel("Branch");
		l4.setBounds(100,250,100,30);
		l5=new JLabel("Mobile");
		l5.setBounds(100,300,100,30);
		JTextField Fname=new JTextField();
		Fname.setBounds(200,100,150,30);
		JTextField Lname=new JTextField();
		Lname.setBounds(200,150,150,30);
		JTextField City=new JTextField();
		City.setBounds(200,200,150,30);
		JTextField Branch=new JTextField();
		Branch.setBounds(200,250,150,30);
		JTextField Mobile=new JTextField();
		Mobile.setBounds(200,300,150,30);
		JButton b1=new JButton("Add");
		JButton b2=new JButton("Back");
		b1.setBounds(100,400,125,40);
		b2.setBounds(235,400,125,40);
		b1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				try {
					//connecting to database
					Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
					con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/bank","root","bobby");
					//registering details
					String mobiletxt=Mobile.getText();
					if(isValidMobileNo(mobiletxt)) {
						long mobilelg=Long.parseLong(Mobile.getText());
						insert=con.prepareStatement("insert into customer(fname,lname,city,branch,mobile) values(?,?,?,?,?)");
						insert.setString(1, Fname.getText());
						insert.setString(2, Lname.getText());
						insert.setString(3, City.getText());
						insert.setString(4, Branch.getText());
						insert.setLong(5, mobilelg);
						i=insert.executeUpdate();
						if(i==1) {
							insert1=con.prepareStatement("select accountno from customer where fname=? and lname=? and city=? and branch=? and mobile=?");
							insert1.setString(1, Fname.getText());
							insert1.setString(2, Lname.getText());
							insert1.setString(3, City.getText());
							insert1.setString(4, Branch.getText());
							insert1.setLong(5, mobilelg);
							rs=insert1.executeQuery();
							rs.next();
							insert2=con.prepareStatement("insert into account values(?,?)");
							insert2.setInt(1,rs.getInt("accountno"));
							insert2.setInt(2, 0);
							insert2.executeUpdate();
							JOptionPane.showMessageDialog(null," Registered Successfully\nPlease note Accountno"+rs.getInt("accountno"));
							new Home();
							f.dispose();
						}
						else {
							JOptionPane.showMessageDialog(null, "user cannot be registered");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Invalid Mobile Number ");
					}
				}
				catch(Exception ex) {
					
				}
				//actionPerformed(e);
			}
		});
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
		f.add(Fname);
		f.add(Lname);
		f.add(City);
		f.add(Branch);
		f.add(Mobile);
		f.add(b1);
		f.add(b2);
		f.setSize(500,550);
		f.setLayout(null);
		f.setVisible(true);
	}
	public static boolean isValidMobileNo(String str)  
	{  
		Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");  
		Matcher match = ptrn.matcher(str);  
		return (match.find() && match.group().equals(str));  
	}
}