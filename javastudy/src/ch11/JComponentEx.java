package ch11;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class JComponentEx extends JFrame {

	private JButton b1,b2,b3;	
	
	public JComponentEx() {
		setTitle("JComponent의 공통 메소드 예제");
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		
		b1 = new JButton("Magenta/Yellow Button");
		b2 = new JButton("   Disable Button   ");
		b3 = new JButton("getX(), getY()");
		
		b1.setBackground(Color.YELLOW); // 배경색 설정
		b1.setForeground(Color.MAGENTA); // 글자색 설정
		b1.setFont(new Font("Arial", Font.ITALIC, 20)); // Arial, 20픽셀 폰트 설정
		b2.setEnabled(false); // 버튼 비활성화
		b3.addActionListener(new ActionListener() {
			
			// 타겟(콜백되는)
			@Override
			public void actionPerformed(ActionEvent e) { // 뭐가 들어올지 모르기 때문에
				//JButton b = (JButton)e.getSource(); // Object를 리턴. 내가 직접 다운캐스팅하여 줌
				b3.setText("Hello"); // b3만 이벤트 활성화시켰기 때문에 이렇게 실행 가능.
			}
		});
		
		c.add(b1);
		c.add(b2);
		c.add(b3);
		setSize(260, 200);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new JComponentEx();
	}

}
