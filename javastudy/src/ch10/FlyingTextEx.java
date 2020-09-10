package ch10;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class FlyingTextEx extends JFrame {
	private final int FLYING_UNIT = 10; // 레이블이 한번 움직이는 단위는 10픽셀
	private JLabel la = new JLabel("HELLO"); // 키 입력에 따라 움직일 레이블 컴포넌트
	
	public FlyingTextEx() {
		setTitle("상,하,좌,우 키를 이용하여 텍스트 움직이기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(null); // 컨텐트팬의 배치관리자 삭제
		c.addKeyListener(new MyKeyListener());
		la.setLocation(50, 50); // la 초기위치
		la.setSize(100, 20);
		c.add(la);
		
		setSize(300, 300);
		setVisible(true);
		c.setFocusable(true);
		c.requestFocus(); // 컨텐트팬이 키 입력 받을 수 있도록 포커스 강제 지정
		
		// 다음 코드는 컨텐트팬에 포커스를 잃은 경우 마우스를 클릭하면 다시 포커스 얻게 함
		c.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component com = (Component)e.getSource(); // 마우스가 클릭된 컴포넌트
				com.setFocusable(true);
				com.requestFocus(); // 마우스가 클릭된 컴포넌트에게 포커스 설정
			}
		});
	}
	
	class MyKeyListener extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int keyCode = e.getKeyCode();
			
			// 키 코드 값(keyCode)에 따라 상,하,좌,우 키를 판별하고 la의 위치를 이동시킨다.
			switch (keyCode) {
			case KeyEvent.VK_UP:
				la.setLocation(la.getX(), la.getY()-FLYING_UNIT);
				break;
			case KeyEvent.VK_DOWN:
				la.setLocation(la.getX(), la.getY()+FLYING_UNIT);
				break;
			case KeyEvent.VK_LEFT:
				la.setLocation(la.getX()-FLYING_UNIT, la.getY());
				break;
			case KeyEvent.VK_RIGHT:
				la.setLocation(la.getX()+FLYING_UNIT, la.getY());
				break;
			}
		}
	}
	
	public static void main(String[] args) {
		new FlyingTextEx();
	}

}
