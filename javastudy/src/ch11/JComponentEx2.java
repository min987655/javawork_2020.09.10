package ch11;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JComponentEx2 extends JFrame implements ActionListener {

	private JButton b1, b2, b3;

	// 타겟
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource(); // 클릭된 버튼을 언마샬링(오브젝트로 바꾸어)해 줌.
		b.setText("Hello");
	}

	public JComponentEx2() {
		Container c = getContentPane();
		c.setLayout(new FlowLayout());

		b1 = new JButton("b1");
		b2 = new JButton("b2");
		b3 = new JButton("b3");

		b1.addActionListener(this); // 타겟이 this
		b2.addActionListener(this); // 타겟이 this
		b3.addActionListener(this); // 타겟이 this

		c.add(b1);
		c.add(b2);
		c.add(b3);
		setSize(260, 200);
		setVisible(true);
	}

	public static void main(String[] args) {
		new JComponentEx2();
	}

}
