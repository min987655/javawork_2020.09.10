package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginFrame  extends JFrame {

	private JPanel mp;
	private JLabel idLabel;
	private JButton loginButton;
	private JTextField idField;
	
	public LoginFrame(Object c) {
		setTitle("Login");
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		mp = new JPanel();
		idLabel = new JLabel("닉네임");
		
		idField = new JTextField(10);
		
		loginButton = new JButton("입장하기");
		loginButton.addActionListener((ActionListener) c);
		
		add(mp);
		
		mp.setLayout(new FlowLayout());
		
		mp.add(idLabel);
		mp.add(idField);
		mp.add(loginButton);
		setBounds(650, 350, 350, 80);
	}
	
	public String getID() {
		return idField.getText();
	}

	public String getloginbuttonactioncommand() {
		return loginButton.getActionCommand();
	}
}







