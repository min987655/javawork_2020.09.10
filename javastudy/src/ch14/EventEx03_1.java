package ch14;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class EventEx03_1 extends JFrame {

	public EventEx03_1() {
		setTitle("Action 이벤트 리스너 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JButton btn = new JButton("Action");
		c.add(btn);

		// 익명클래스로 Action 리스너 생성
		btn.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton)e.getSource();
				if (b.getText().equals("Action")) {
					b.setText("액션");
				} else {
					b.setText("Ation");
				}
				
				// EventEx04의 멤버나 JFrame의 멤버를 호출할 수 있음.
				setTitle(b.getText());
			}
		});
				
		setSize(350, 150);
		setVisible(true);
	}

	public static void main(String[] args) {
		new EventEx03_1();
	}
}