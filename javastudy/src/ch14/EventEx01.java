package ch14;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class EventEx01 extends JFrame {

	public EventEx01() {
		setTitle("Action 이벤트 리스너 예제");
		// X버튼 클릭시 이벤트 분배 스레드도 종료
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		JButton btn = new JButton("Action");
		// 리스너 생성
		btn.addActionListener(new MyActionListener());
		c.add(btn);

		setSize(350, 150);
		setVisible(true);
	}

	public static void main(String[] args) {
		new EventEx01();
	}

}

//독립된 클래스로 이벤트 리스너를 작성한다.
class MyActionListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource(); // 이벤트 소스 버튼 알아내기
		if (b.getText().equals("Action")) // 버튼의 문자열이 "Action"인지 비교
			b.setText("액션"); // 버튼의 문자열을 "액션"으로 변경
		else
			b.setText("Action"); // 버튼의 문자열을 "Action"으로 변경
	}
}
