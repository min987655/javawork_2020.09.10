package ch11;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TextFieldEx  extends JFrame {

	private JTextField tf;
	private JTextArea ta;
	private ScrollPane sp; // JScrollPane은 색깔을 잡아먹어버림. 사용 X
	
	public TextFieldEx() {
		init(); // new의 순서가 안맞아 오류날 수 있음.
		
		setTitle("텍스트 에디터, 텍스트 박스 연습");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		
		// 텍스트필드 디자인
		tf.setFont(new Font("Arial", Font.BOLD, 30));
		tf.setPreferredSize(new Dimension(100,100)); // x,y 모든 컨포넌트 크기 조정
		

		ta.setBackground(Color.ORANGE);
		
		ta.setEditable(false); // 글자 못들어가게 함.
		ta.setFont(new Font("Arial", Font.BOLD, 60));
		ta.setForeground(Color.GRAY);
		
		sp.add(ta); // ta를 스크롤에 넣음. 순서 중요
		
		
		tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String value = tf.getText();
					ta.append(value+"\n");
					tf.setText("");
				}
				
			}
		});
		c.add(tf, BorderLayout.SOUTH);
		c.add(sp, BorderLayout.CENTER);
		
		setSize(400, 500);
		setVisible(true);
	}
	
	private void init() { // GUI프로그램에는 init을 만들어 new띄우는 것을 모아놓음. new 순서의 오류가 없게 함.
		tf = new JTextField("", 20);
		sp = new ScrollPane();
		ta = new JTextArea("", 5, 20);
	}
	
	public static void main(String[] args) {
		new TextFieldEx();
	}

}
