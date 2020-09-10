package address.test;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class JTextAreaTest extends JFrame{
	
	private JTextArea jta;
	
	public JTextAreaTest() {
		setSize(300, 300);
		jta = new JTextArea("안녕");
		setLayout(new BorderLayout());
		add(jta, BorderLayout.CENTER);
		jta.setText("");
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new JTextAreaTest();
	}
}
