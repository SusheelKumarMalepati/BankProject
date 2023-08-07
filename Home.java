import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class Home {
	Home(){
		inHomeComp();
	}
	private void inHomeComp() {
		//using awt and swing for outlook
		JFrame f=new JFrame("Home");
		JButton reg,bal,wd,dep,log,b1,b2,b3,b4;
		b1=new JButton("");
		b2=new JButton("");
		b3=new JButton("");
		b4=new JButton("");
		reg=new JButton("Create Account");
		bal=new JButton("Check Balance");
		wd=new JButton("WithDraw Amount");
		dep=new JButton("DepositAmount");
		log=new JButton("Logout");
		reg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//actionPerformed(e);
				new AccountDetails();
				f.dispose();
			}
		});
		bal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//actionPerformed(e);
				new Balance();
				f.dispose();
			}
		});
		wd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//actionPerformed(e);
				new withdrawAmount();
				f.dispose();
			}
		});
		dep.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//actionPerformed(e);
				new depositAmount();
				f.dispose();
			}
		});
		log.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				f.dispose();
				JOptionPane.showMessageDialog(null,"Logged Out Successfully");
				new Login();
			}
		});
		f.add(dep);
		f.add(b1);
		f.add(wd);
		f.add(b2);
		f.add(bal);
		f.add(b3);
		f.add(reg);
		f.add(b4);
		f.add(log);
		f.setSize(1200,900);
		f.setLayout(new GridLayout(3,3));
		f.setVisible(true);
	}
}
