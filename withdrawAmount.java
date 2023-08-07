package bank;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class withdrawAmount {
	//creating objects
	Connection con=null;
	PreparedStatement insert,insert1=null;
	ResultSet rs=null;
	withdrawAmount(){
		inDrawComp();
	}
	private void inDrawComp() {
		//using awt and swing for outlook
		JFrame f=new JFrame("Withdraw Amount");
		JLabel l1,l2=null;
		l1=new JLabel("Enter Account Number:");
		l1.setBounds(100,100,200,30);
		l2=new JLabel("Enter Amount To be Withdrawed");
		l2.setBounds(100,150,200,30);
		JTextField accno=new JTextField();
		accno.setBounds(290,100,180,30);
		JTextField draw=new JTextField();
		draw.setBounds(290,150,180,30);
		JButton b1=new JButton("WithDraw Amount");
		b1.setBounds(100,200,170,40);
		JButton b2=new JButton("Back");
		b2.setBounds(280,200,170,40);
		b1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				//actionPerformed(e);
				try {
					//connecting to database
					Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
					con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/bank","root","bobby");
					insert=con.prepareStatement("SELECT c.accountno,a.balance FROM customer c,account a where a.accountno=c.accountno and c.accountno=?");
					insert.setString(1,accno.getText());
					rs=insert.executeQuery();
					if(!rs.next()) {
						JOptionPane.showMessageDialog(null,"Account not found");
						accno.setText("");
						draw.setText("");
						accno.requestFocus();
					}
					else {
						//withdraw from account
						int accno=rs.getInt(1);
						int balance=rs.getInt(2);
						String drawtxt=draw.getText();
						int draw=Integer.parseInt(drawtxt);
						if(balance<draw) {
							JOptionPane.showMessageDialog(null,"Insufficient Balance");
						}else {
							balance=balance-draw;
							insert1=con.prepareStatement("update account set balance=? where accountno=?");
							insert1.setInt(1,balance);
							insert1.setInt(2,accno);
							insert1.executeUpdate();
							JOptionPane.showMessageDialog(null,"amount "+draw+" withdrawed successfully from "+accno);
							new Home();
							f.dispose();
						}
					}
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Enter Amount to be withdrawed");
				}
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
		f.add(accno);
		f.add(draw);
		f.add(b1);
		f.add(b2);
		f.setSize(600,400);
		f.setLayout(null);
		f.setVisible(true);
	}
}
