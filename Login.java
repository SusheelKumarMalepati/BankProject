import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class Login {
	//creating objects
	Connection con=null;
	PreparedStatement insert;
	ResultSet rs=null;
	Login(){
		inLoginComp();
	}
	@SuppressWarnings("deprecation")
	private void inLoginComp(){
		//using awt and swing for outlook
		JFrame f=new JFrame("Login");
		JLabel l1,l2=null;
		l1=new JLabel("Username:");
		l1.setBounds(100,50,80,30);
		l2=new JLabel("Password:");
		l2.setBounds(100,100,80,30);
		JTextField user=new JTextField();
		user.setBounds(200,50,150,30);
		JPasswordField pass=new JPasswordField();
		pass.setBounds(200,100,150,30);
		JButton b1,b2;
		b1=new JButton("Login");
		b1.setBounds(100,150,100,40);
		b2=new JButton("Cancel");
		b2.setBounds(220,150,100,40);
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//actionPerformed(e);
				try {
					//jdbc connection
					Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
					con=DriverManager.getConnection("jdbc:mysql://127.0.0.1/bank","root","root");
					//checking for login details in database
					insert=con.prepareStatement("select*from admin where username=? and password=?");
					insert.setString(1,user.getText());
					insert.setString(2,pass.getText());
					rs=insert.executeQuery();
					if(rs.next()) {						
						new Home();
						f.dispose();
					}
					else {
						JOptionPane.showMessageDialog(null, "Username and Password donot matched");
						user.setText("");
						pass.setText("");
						user.requestFocus();
					}
				}
				catch(Exception ex) {}
			}
		});
		b2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//mouseClicked(e);
				f.dispose();
			}
		});
		f.add(l1);
		f.add(l2);
		f.add(user);
		f.add(pass);
		f.add(b1);
		f.add(b2);
		f.setSize(450,290);
		f.setLayout(null);
		f.setVisible(true);
	}
}
