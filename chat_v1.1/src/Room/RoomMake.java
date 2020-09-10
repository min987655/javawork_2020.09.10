package Room;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RoomMake extends JFrame {

	private JLabel title, num;
	public JTextField tf;
	public JButton makeB, canB;
	public JComboBox<String> combo1;

	public RoomMake() {

		title = new JLabel("방제목 :");
		num = new JLabel("인원수 :");
		tf = new JTextField(15);

		String[] com1 = { "2", "3", "4", "5", "6", "7", "8", "9", };
		combo1 = new JComboBox<String>(com1);

		makeB = new JButton("만들기");
		canB = new JButton("취 소");

		JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel1.add(title);
		panel1.add(tf);

		JPanel panel4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panel4.add(num);
		panel4.add(combo1);

		JPanel totpanel = new JPanel(new GridLayout(3, 1, 0, 0));
		totpanel.add(panel1);
		totpanel.add(panel4);

		JPanel btpanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btpanel.add(makeB);
		btpanel.add(canB);

		Container c = this.getContentPane();
		c.add("Center", totpanel);
		c.add("South", btpanel);

		setResizable(false);
		setBounds(400, 200, 400, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}
}