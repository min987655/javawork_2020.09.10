package ch14;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class EventEx04 extends JFrame {

	private JLabel la;

	public EventEx04() {
		la = new JLabel("Hello");
		setTitle("MouseEvent 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.addMouseListener(new MyMouseListener());
		c.setLayout(null); // absoulte 레이아웃
		la.setSize(50, 20); // 라벨의 사이즈
		la.setLocation(30, 30); // 라벨의 위치
		c.add(la);
		setSize(250, 250);
		setVisible(true);
	}

	class MyMouseListener extends MouseAdapter {

		// 마우스를 클릭하고 땠을 때
		@Override
		public void mousePressed(MouseEvent e) {
			int x = e.getX(); // X 좌표
			int y = e.getY(); // Y 좌표
			la.setLocation(x, y); // 라벨 위치 변경 = repaint()
		}
	}

	public static void main(String[] args) {
		new EventEx04();
	}

}
