package ch12;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class paintJPanelEx extends JFrame {
	private MyPanel pane1 = new MyPanel();

	public paintJPanelEx() {
		setTitle("Jpanel의 paintComponent() 예제");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(pane1); // 생성한 pane1 패널을 컨텐트팬으로 사용
		setSize(250, 200);
		setVisible(true);
	}

	// JPanel을 상속받는 새 패널 구현
	public class MyPanel extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // JPanel의 paintComponent() 호출
			g.setColor(Color.blue); // 파란색 선택
			g.drawRect(10, 10, 50, 50); // (10,10) 위치에 50X50 크기의 사격형 그리기
			g.drawRect(50, 50, 50, 50); // (50,50) 위치에 50X50 크기의 사격형 그리기
			g.setColor(Color.MAGENTA); // 마젠타색 선택
			g.drawRect(90, 90, 50, 50); // (90,90) 위치에 50X50 크기의 사격형 그리기
		}

	}

	public static void main(String[] args) {
		new paintJPanelEx();
	}

}
