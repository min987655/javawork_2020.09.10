package ch09;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimpleSwingFrame extends JFrame {

	public SimpleSwingFrame() {
		setTitle("Open Challenge 9"); // 타이틀
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		
		// 두 개의 패널을 붙인다.
		c.add(new NorthPanel(), BorderLayout.NORTH);
		c.add(new CenterPanel(), BorderLayout.CENTER);
		setSize(300, 300);
		setVisible(true);
	}
	
	class NorthPanel extends JPanel {
		public NorthPanel() {
			setBackground(Color.LIGHT_GRAY);
			setLayout(new FlowLayout());
			add(new JButton("Open"));
			add(new JButton("Read"));
			add(new JButton("Close"));
		}
	}
	
	class CenterPanel extends JPanel {
		public CenterPanel() {
			setLayout(null); // 배치관리자 삭제, 절대 위치에 컴포넌트 삽입
			
			JLabel a = new JLabel("Hello");
			a.setSize(100, 20);
			a.setLocation(100, 10);
			
			JLabel b = new JLabel("Java");
			b.setSize(100, 20);
			b.setLocation(20, 150);
			
			JLabel c = new JLabel("Love");
			c.setSize(200, 20);
			c.setLocation(200, 120);
			
			add(a);
			add(b);
			add(c);
			
		}
	}
	
	public static void main(String[] args) {
		new SimpleSwingFrame();
	}

}
