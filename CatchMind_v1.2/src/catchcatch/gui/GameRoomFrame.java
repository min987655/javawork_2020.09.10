package catchcatch.gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import catchcatch.client.MainClient;
import catchcatch.util.Protocol;

public class GameRoomFrame extends JFrame {

	private final static String TAG = "GameroomFrame : ";

	private GameRoomFrame gameroomFrame = this;
	public JTextField tfCard, tfChat;
	public JButton btEnter, btCard, btGstart;
	public JTextArea taChat, taUserList;
	public JLabel LuserList;
	public JPanel Canvas;
//	public Drawing dw;
//	public MyCanvas2 cv2;
	
	private BufferedReader br;
	private PrintWriter pw;

	public GameRoomFrame(BufferedReader br, PrintWriter pw) {
		this.br = br;
		this.pw = pw;
	}
	
	
	
	public GameRoomFrame() {
		initObject();
//		initData();
		initDesign();
		initListener();
		setVisible(true);		
	}

	// 객체생성
	public void initObject() {
		btCard = new JButton("제시어");
		btGstart = new JButton("게임시작");
		tfCard = new JTextField();
		Canvas = new JPanel();
		LuserList = new JLabel("User List");
		taUserList = new JTextArea();
		tfChat = new JTextField();
		btEnter = new JButton("Enter");
		taChat = new JTextArea();
	}
//
//	// 데이터초기화
//	private void initData() {
//
//	}

	// 디자인
	public void initDesign() {
		// 1. 기본세팅
		setTitle("Game Room");
		setBounds(100, 100, 962, 738);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		// 2. 패널세팅
		btCard.setBounds(40, 46, 112, 34);
		tfCard.setBounds(160, 46, 262, 34);
		tfCard.setColumns(10);
		btGstart.setBounds(580, 46, 323, 63);
		LuserList.setBounds(579, 121, 308, 27);
		taUserList.setBounds(580, 157, 323, 120);
		Canvas.setBounds(40, 106, 502, 541);
		taChat.setBounds(580, 292, 323, 305);
		tfChat.setBounds(580, 609, 229, 38);
		tfChat.setColumns(10);
		btEnter.setBounds(823, 609, 80, 38);

		// 3. 패널에 컴포넌트 추가
		getContentPane().add(btCard);
		getContentPane().add(tfCard);
		getContentPane().add(Canvas);
		getContentPane().add(btGstart);
		getContentPane().add(LuserList);
		getContentPane().add(taUserList);
		getContentPane().add(taChat);
		getContentPane().add(tfChat);
		getContentPane().add(btEnter);
	}

	// 리스너 등록
	private void initListener() {
		
		btEnter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("EnterButton");
				System.out.println(TAG + "통신X 이벤트 : " + tfChat.getText());
				taChat.append(tfChat.getText() + "\n");
				String msg = Protocol.CHAT + ":" + tfChat.getText();
//				 taChat.setText(taChat.getText()+tfChat.getText()+ "\n");
				System.out.println("msg : " + Protocol.CHAT + ":" + tfChat.getText());
//				mainClient.send(msg);		
				pw.println(msg);
				pw.flush();
				tfChat.setText("");
			}
		});
		
//		btEnter.addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent keyE) {
//				if (keyE.getKeyCode() == KeyEvent.VK_ENTER) {
//					System.out.println(TAG + "엔터이벤트");
//					btEnter.doClick();
//				}
//			}
//		});
	}

}