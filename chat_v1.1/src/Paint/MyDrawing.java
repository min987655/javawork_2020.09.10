package Paint;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

// 메인 컴포넌트가 있는 메인 함수 : 실제 마우스 이벤트랑 버튼 이벤트 처리되는 곳이다.

public class MyDrawing extends /*JFrame*/ JPanel {

	JPanel p1, p2;
	JButton btR, btG, btB, btOpen;
	Canvas can; // 부모타입
	PaintToolFrame pt;

	public MyDrawing() {
//		super("::MyDrawing::"); // JFrame 생성자를 호출 
		pt = new PaintToolFrame();
		p1 = new JPanel();
		add(p1, "North");

		p2 = new JPanel() { // 여백주기
			public Insets getInsets() {
				return new Insets(40, 10, 10, 10);
			}
		};
		add(p2, "Center");
		p2.setBackground(Color.LIGHT_GRAY);
		
		btR = new JButton(new ImageIcon("src/images/red.png"));
		p1.add(btR);
		btR.setPreferredSize(new Dimension(30,30));
		btG = new JButton(new ImageIcon("src/images/green.png"));
		btG.setPreferredSize(new Dimension(30, 30));
		p1.add(btG);
		btB = new JButton(new ImageIcon("src/images/blue.png"));
		btB.setPreferredSize(new Dimension(30, 30));
		p1.add(btB);
		btOpen = new JButton("Paint Tool");
		p1.add(btOpen);

		can = new MyCanvas(); // 도화지 역할을 하는 컴포넌트 MyCanvas는 can을 상속 받는 자식 -> 원이 갑자기 생긴다.
		can.setSize(300, 300); // 도화지 크기
		can.setBackground(Color.WHITE); // 도화지 배경색
		p2.add(can);

		// 리스너 부착
		MyHandler my = new MyHandler();
		can.addMouseMotionListener(my); // 캔버스 객체에 마우스 모션 리스너를 부착
		btR.addActionListener(my);
		btG.addActionListener(my);
		btB.addActionListener(my);
		btOpen.addActionListener(my);

		// pt 버튼 (PaintToolFrame 클래스꺼)에도 리스너 부착
		pt.btPlus.addActionListener(my);
		pt.btMinus.addActionListener(my);
		pt.btClear.addActionListener(my);
		pt.btAllClear.addActionListener(my);
		pt.btColor.addActionListener(my);
		pt.btClose.addActionListener(my);
		
		setSize(500, 500);
		setVisible(true);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 나중에 눈치보고 지우기
	}
	
	// 이벤트 소스 : 캔버스 
	// 이벤트 : MouseEvent
	// 이벤트 핸들러 : MouseMotionListener를 구현
	
	class MyHandler implements MouseMotionListener, ActionListener {
		
		private PaintToolFrame f1;


		public void mouseDragged(MouseEvent e) {
//			setTitle("Drag");
			// 마우스를 드래그한 지점의 x좌표, y좌표를 얻어와서 can의 x,y 좌표값에 전달한다.
			int xx = e.getX();
			int yy = e.getY();
//			setTitle("xx" + xx + ",yy" + yy);
			((MyCanvas)can).x = xx; // 여기서 지금 뻑나고 있음 이 위에꺼 지워서 그런갑다 나중에 수정 
			((MyCanvas)can).y = yy;
			
			// paint()는 JVM이 호출해주는 메소드로 변경x, repaint를 써서 재사용
			can.repaint();
		}
		
		
		public void mouseMoved(MouseEvent e) {
			
		}
		
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			MyCanvas can2 = (MyCanvas)can;
			
			if(o == btR) {
				can2.cr = Color.RED;
			} else if (o == btG) {
				can2.cr = Color.GREEN;
			} else if (o ==btB) {
				can2.cr = Color.BLUE;
			} else if ( o == btOpen) {
				// 새로운 'paintToolJFrame' 생성해서 창 열기
				// PaintToolFrame pt = new PaintToolFrame(); 여기서 코드 놓으면 매번 플레임을 생성하게된다 주의
				pt.pack(); // 크기를 압축해서 보여준다.
				pt.setLocation(getWidth(), 0); // x축만큼 오른쪽으로 창 이동
				pt.setVisible(true);
			} else if ( o == pt.btPlus) {
				can2.w += 10;
				can2.h += 10;
			} else if ( o == pt.btMinus) {
				if(can2.w > 3) {
					// 버튼을 계속 누르면 아예 안 나온다. 최소한의 크기 설정
					can2.w -= 10;
					can2.h -= 10;
				} 
			} else if (o==pt.btClear) {
				// 드래그한 지점만 부분 지우기
				can2.cr = can.getBackground();
			} else if ( o== pt.btAllClear ) {
				// 캔버스를 모두 지우기
				// Graphics 클래스의 clearRect(x, y, w, h)
				Graphics g = can2.getGraphics();
				g.clearRect(0, 0, can.getWidth(), can.getHeight());
			} else if (o == pt.btColor) {
				// (Swing에 있음) JColorChooser를 띄워서 선택한 색상으로 그려지도록
				Color selCr = JColorChooser.showDialog(null, "색선정", Color.BLUE); // null 스크린 중양에 화면 나옴 
				
				can2.cr = selCr;
			} else if (o == pt.btClose) {
				// pt만 닫혀지도록
				// pt.setVisible(false); -> 눈에 안 보이는것뿐임
				pt.dispose(); // 시스템 자원을 반납해준다.
			}
		}
	}


}
