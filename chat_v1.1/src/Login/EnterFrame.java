package Login;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Action.Protocol;
import CoControl.CoprocessFrame;
import Room.RoomFrame;
import Room.RoomMake;

public class EnterFrame extends JFrame implements ActionListener, KeyListener, Runnable {
	private JPasswordField pwT;
	private JTextField idT;
	private JButton idB, pwB, accessB, membershipB;
	private JLabel loginL, logoutL;
	private ImageIcon loginC, logoutC, modifiedC;
	private Socket socket;
	private BufferedReader br;
	private PrintWriter pw;

	MembershipB menbersShipF; // 회원가입
	RoomFrame RoomF; // 대기실
	RoomMake rMakeF; // 방만들기
	CoprocessFrame chattingF;

//	private boolean condition_Id = false; // ID 중복체크

	public EnterFrame() {
		network(); // Enter 쓰레드 실행

		menbersShipF = new MembershipB();
		RoomF = new RoomFrame(br, pw);
		rMakeF = new RoomMake();
		chattingF = new CoprocessFrame();

		idB = new JButton("아이디");
		idT = new JTextField("test1",15);
		pwB = new JButton("패스워드");
		pwT = new JPasswordField("1111",15);
		pwT.setEchoChar('*');

		JPanel p2 = new JPanel(new FlowLayout());
		p2.add(idB);
		p2.add(idT);
		p2.add(pwB);
		p2.add(pwT);

		membershipB = new JButton("회원가입");
		accessB = new JButton("입장");

		JPanel p3 = new JPanel();
		p3.add(membershipB);
		p3.add(accessB);

		loginC = new ImageIcon("img/loginButton.png");
		loginL = new JLabel(loginC);
		
		JPanel p4 = new JPanel();
		p4.add(loginL);

		Container contentPane = this.getContentPane();
		contentPane.add("Center", p2);
		contentPane.add("South", p3);
		contentPane.add("East", p4);

		// 메인페이지 화면 생성
		setVisible(true);
		setResizable(false); 
		setBounds(400, 200, 1000, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		event(); 

	}

	public void event() {
		// this EnterFrame
		// --------------------회원가입----------------------------------
		membershipB.addActionListener(this); // 회원가입(버튼)
		menbersShipF.calneB.addActionListener(this); // 회원가입 취소(로그인화면으로)
		menbersShipF.joinB.addActionListener(this); // 회원가입 화면에서 join
		menbersShipF.idoverlapB.addActionListener(this);// 회원가입 화면 중복확인

		// --------------------로그인----------------------------------
		accessB.addActionListener(this); // 입장(Login)
		RoomF.exitB.addActionListener(this); // Room -> 로그인Page

		// ---------------------메세지---------------------------------
		RoomF.sendB.addActionListener(this); // 대기방에서 전송
		RoomF.chattxt.addKeyListener(this); // 엔터치면 전송

		// ----------------------대기방------------------------------------
		RoomF.makeB.addActionListener(this);
		rMakeF.makeB.addActionListener(this);
		rMakeF.canB.addActionListener(this);
		chattingF.exitB.addActionListener(this);
		chattingF.sendB.addActionListener(this);
		// ----------------------채팅방------------------------------------
		chattingF.field.addKeyListener(this); // 엔터치면 전송
	}

	public void network() {

		// 소켓 생성
		try {
			socket = new Socket("localhost", 9500);
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

		} catch (UnknownHostException e) {
			System.out.println("서버를 찾을 수 없습니다");
			e.printStackTrace();
			System.exit(0);
		} catch (IOException e) {
			System.out.println("서버와 연결이 안되었습니다");
			e.printStackTrace();
			System.exit(0);
		}

		// 스레드 생성
		Thread t = new Thread(this); // this : EnterFrame
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == membershipB) { // 메인 페이지 -> 회원가입버튼
			this.setVisible(true); // 메인화면 창 유지
			menbersShipF.setVisible(true); // 회원가입 창 열림

		} else if (e.getSource() == menbersShipF.joinB) { // 회원가입 페이지 -> 가입 버튼
			String name = menbersShipF.nameT.getText(); // 회원가입 이름 받음
			String id = menbersShipF.idT.getText(); // 회원가입 아이디 받고
			String pw1 = menbersShipF.pwT.getText(); // 비밀번호 받고

			if (name.length() == 0 || id.length() == 0 || pw1.length() == 0) { // 입력된 길이가 없을 떄
				JOptionPane.showMessageDialog(menbersShipF, "빈 칸을 입력해주세요");
			} 
//			  else if (!condition_Id) { // 아이디 중복확인
//				JOptionPane.showMessageDialog(this, "ID 중복 확인해주세요");
//			} 
			  else { 
				String line = ""; 
				line += (menbersShipF.idT.getText() + "%" + menbersShipF.pwT.getText() + "%"
						+ menbersShipF.nameT.getText());
				System.out.println(line);

				// *** 회원가입 (pw : 프로토콜 | idName % password % name)
				pw.println(Protocol.REGISTER + "|" + line); 
				pw.flush();
				JOptionPane.showMessageDialog(this, "회원가입 완료");
				menbersShipF.setVisible(false);
				this.setVisible(true);
				
				// 초기화
				menbersShipF.nameT.setText("");
				menbersShipF.idT.setText("");
				menbersShipF.pwT.setText("");
//				condition_Id = false;

			} 

		} else if (e.getSource() == menbersShipF.calneB) { // 회원가입페이지 -> 취소 버튼
			menbersShipF.setVisible(false);
			this.setVisible(true);
//			condition_Id = false;

		} else if (e.getSource() == menbersShipF.idoverlapB) { // 회원가입 페이지ID -> 중복확인
			if (menbersShipF.idT.getText().length() == 0) {
				JOptionPane.showMessageDialog(menbersShipF, "아이디 입력하세요");
			} else {
				// ***ID중복체크(사용가능) (pw 프로토콜 | userId)
				pw.println(Protocol.IDSEARCHCHECK_OK + "|" + menbersShipF.idT.getText());
				pw.flush();
			}

		} else if (e.getSource() == accessB) { // 메인페이지 --> 대기실 (Login 했을 때)

			String id = idT.getText(); //아이디, 비밀번호 값을 받고
			String pwss = pwT.getText();

			if (id.length() == 0 || pwss.length() == 0) {
				JOptionPane.showMessageDialog(this, "빈칸을 입력해주세요");
			// ***로그인OK (pw : 프로토콜 | userId % password)
			} else {
				String line = id + "%" + pwss;
				pw.println(Protocol.ENTERLOGIN_OK + "|" + line);
				pw.flush();
			}
			//초기화
			idT.setText("");
			pwT.setText("");

		} else if (e.getSource() == RoomF.exitB) { // 대기실 -> 로그인Page (로그아웃)

			RoomF.setVisible(false); // 대기실 창 꺼짐
			this.setVisible(true); // 메인화면 창 나옴

			// ***로그아웃(대기방.exit) (pw : 프로토콜 | message)
			pw.println(Protocol.EXITWAITROOM + "|" + "message"); // ~님이 퇴장하셨습니다 문구 뜸
			pw.flush();

		} else if (e.getSource() == RoomF.sendB) {// 대기실 페이지 -> 대기실채팅 메세지 전송
			String line = RoomF.chattxt.getText(); // 입력받는 문구 받음
			if (RoomF.chattxt.getText().length() != 0) { // 들어오는 문구가 있을 때
				// ***대기실채팅 (pw : 프로토콜 | 입력받는문구)
				pw.println(Protocol.SENDMESSAGE + "|" + line);
				pw.flush();
				RoomF.chattxt.setText(""); // 입력값 초기화
			}

		} else if (e.getSource() == RoomF.makeB) { // 대기실 페이지 -> 방 만들기 버튼
			RoomF.setVisible(false); // 대기실 사라지고
			rMakeF.setVisible(true); // 방 만들기 창 나오고
			
		} else if (e.getSource() == rMakeF.makeB) { // 방만들기 페이지 -> 방 생성 버튼
			String title = rMakeF.tf.getText(); // 방 제목 입력 받고
			String userCount = (String) rMakeF.combo1.getSelectedItem(); // 인원 수 콤보박스

			if (title.length() == 0) {
				JOptionPane.showMessageDialog(rMakeF, "방 제목을 입력해주세요");
			} else {
				String line = "";
				line += (title + "%" + userCount);
				// ***방만들기 (pw : 프로토콜 | 방제목 % 방최대인원)
				pw.println(Protocol.ROOMMAKE + "|" + line);
				pw.flush();
				RoomF.setVisible(false); // 대기실 창 사라짐
	
				// 초기화 : 방 타이틀, 인원 수
				rMakeF.tf.setText("");
				rMakeF.combo1.setSelectedIndex(0);
			}
		} else if (e.getSource() == rMakeF.canB) { // 방 만들기 페이지 -> 취소버튼
			rMakeF.setVisible(false); // 방 만들기 창 꺼지고
			RoomF.setVisible(true); // 대기방 만들고
			
			rMakeF.tf.setText("");
			rMakeF.combo1.setSelectedIndex(0);
			
		} else if (e.getSource() == chattingF.exitB) { // 게임방 페이지 -> 나가기 버튼

			chattingF.setVisible(false); // 게임방 창 꺼짐
			RoomF.setVisible(true); // 대기방 창 열림
			
			// ***방만들기 (pw : 프로토콜 | 방제목 % 방최대인원)
			pw.println(Protocol.EXITCHATTINGROOM + "|" + "Message");
			pw.flush();

//			chattingF.partList.setText("asd"); // ??? 

		} else if (e.getSource() == chattingF.sendB) { // 게임방 페이지 -> 채팅 메세지 전송
			pw.println(Protocol.CHATTINGSENDMESSAGE + "|" + chattingF.field.getText());
			pw.flush();
			chattingF.field.setText("");
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) { // 엔터 쳤을 때 대화 넘어감
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (RoomF.chattxt.getText().length() != 0) {
				RoomF.sendB.doClick();
			} else if (chattingF.field.getText().length() != 0) {
				chattingF.sendB.doClick();
			}
		}
	}

	@Override
	public void run() {
		// 받는쪽
		String line[] = null; // 파싱한 데이터를 배열에 담음
		while (true) {
			try {
				line = br.readLine().split("\\|"); // 받은 데이터를 파싱
				
				// line[1] : 방번호 % 방이름 % % 최대인원 % 방장 % 방에있는인원
				// line[0] Pro(121) / [1] 방장 / [2] 님이 *번 방을 만들었습니다. / [3] 방장 :
				if (line == null) { // 들어오는 데이터가 없으면 br, pw, 소켓 닫힘
					br.close();
					pw.close();
					socket.close();

					System.exit(0);
					
				} else if (line[0].compareTo(Protocol.IDSEARCHCHECK_OK) == 0) { // ***ID중복체크(사용가능)
					JOptionPane.showMessageDialog(menbersShipF, "사용 가능");
//					condition_Id = true;
					
				} else if (line[0].compareTo(Protocol.IDSEARCHCHECK_NO) == 0) { // ***ID중복체크(사용불가능)
					JOptionPane.showMessageDialog(menbersShipF, "사용 불가능");
//					condition_Id = false;
					
				} else if (line[0].compareTo(Protocol.ENTERLOGIN_OK) == 0) // ***로그인 성공
				{
					this.setVisible(false); // 메인화면 사라지고
					RoomF.setVisible(true); // 대기창 뜨고
					// line[1] userId | line[2] "님이 입장하였습니다." 
					RoomF.chatarea.append(line[1] + line[2] + '\n'); // 대기창 : 채팅방에 누가 입장했다는 문구 뜸

					// line[3] userline을 : 으로 파싱(대기실 입장 유저 리스트)
					String text[] = line[3].split(":");
					String userlist = "";
					for (int i = 0; i < text.length; i++) {
						userlist += (text[i] + "\n");
					}
					// 파싱 한 리스트를 대기실 인원에 뿌려줌
					RoomF.usertxt.setText(userlist);  

				} else if (line[0].compareTo(Protocol.ENTERLOGIN_NO) == 0) // 로그인 실패
				{
					JOptionPane.showMessageDialog(this, "로그인에 실패했어요"); // 메인화면에 문구 뜸
					System.out.println("로그인실패");
					
				} else if (line[0].compareTo(Protocol.EXITWAITROOM) == 0) // 대기실 -> 로그인 페이지(로그아웃)
				{
					RoomF.chatarea.append(line[1] + line[2] + '\n'); // 대기실 창에 메세지 뜸 - 메인핸들러 연동

					//대기실 인원 수 나가기
					String text[] = line[3].split(":");
					String userlist = "";
					for (int i = 0; i < text.length; i++) {
						userlist += (text[i] + "\n");
					}
					RoomF.usertxt.setText(userlist); // 유저 리스트 화면에 뿌려 줌

				} else if (line[0].compareTo(Protocol.SENDMESSAGE_ACK) == 0) // 대기실 메세지 보내기
				{
					RoomF.chatarea.append("[" + line[1] + "] :" + line[2] + '\n'); // [유저 name] : 메세지

				} else if (line[0].compareTo(Protocol.ROOMMAKE_OK) == 0) // 방 생성
				{

					System.out.println("게임방 생성");
					// 게임방에 유저 1명 입장 시 배열 2번지에 "" 생김
					// roomList[0] : 방번호 % 방이름 % 최대인원 % 방장 % 방에있는인원
					// roomList[0] : 방번호 % 방이름 % % 최대인원 % 방장 % 방에있는인원
					String roomList[] = line[1].split("-"); // 방 생성시 대기실 화면에 나타나는 roomList
					for (int i = 0; i < roomList.length; i++) { // roomList[0]
						System.out.print(roomList[i] + "/");
					}

					String roomListDetail[]; // 방 세부 : 방 번호, 방 제목, 인원 수, 방장 
					System.out.println("RoomList size : " + roomList.length); // 방 몇개인지 콘솔창에 뜸

					RoomF.containPanelClear(); // 대기실 프레임에 컨테이너 비움 - 방 생성 동기화
					for (int i = 0; i < roomList.length; i++) { 

						RoomF.dp[i].init(); // 게임방 리스트 다시 생성 (DetailPanel = dp)
						roomListDetail = roomList[i].split("%");
						String userNumber = ""; // 한 방의 인원 수

						if (roomListDetail.length == 6) // 0(방번호),1(방제목),2(최대인원수),3(방장),4():뭐지?,5(들어온인원)
						{
							userNumber += (roomListDetail[5] + "/" + roomListDetail[2]); // 들어가있는 유저 수 / 방장이 설정한 유저 수
							RoomF.dp[i].labelArray[1].setText(roomListDetail[0]); // 방번호
							RoomF.dp[i].labelArray[3].setText(roomListDetail[1]); // 방제목
							RoomF.dp[i].labelArray[5].setText(userNumber); // 인원수
							RoomF.dp[i].labelArray[6].setText("개설자 : " + roomListDetail[3]); // 방장
						}
						// #####
						System.out.println("userNumber : " + userNumber);

					}
					// 초기화
					chattingF.md = null;
					chattingF.area1.setText("");
					rMakeF.setVisible(false); // 방 만들기 창 꺼짐
					RoomF.setVisible(true); // 대기실 창 뜸

				} else if (line[0].compareTo(Protocol.ROOMMAKE_OK1) == 0) // 게임 방 생성 - 방장 입장
				{
					rMakeF.setVisible(false); // 방 만들기 창 꺼지고
					chattingF.md = null;
					chattingF.setVisible(true); // 게임 창 채팅 뜨고
					chattingF.partList.setText(line[1] + "\n"); // 참여 인원 리스트

				} else if (line[0].compareTo(Protocol.ENTERROOM_OK1) == 0) // 게임 방 유저 입장
				{
					System.out.println("유저가 게임방으로 입장");
					RoomF.setVisible(false); // 대기실 창 꺼짐
					chattingF.area1.setText("");
					chattingF.md = null;
					chattingF.setVisible(true); // 게임 채팅 창 뜸

				} else if (line[0].compareTo(Protocol.ENTERROOM_USERLISTSEND) == 0) // 게임방의 유저 리스트 새로고침
				{

					String roomMember[] = line[1].split("%");// 게임방에 들어온 유저들
					String lineList = "";
					for (int i = 0; i < roomMember.length; i++) {
						lineList += (roomMember[i] + "\n");
					}

//					chattingF.partList.setText(lineList);
//					chattingF.area1.append(line[2] + "\n");
//
//					if (line.length == 4) {
//						String fileList[] = line[3].split("%");
//						chattingF.model.removeAllElements();
//						for (int i = 0; i < fileList.length; i++) {
//							chattingF.model.addElement(fileList[i]);
//						}
//					}

				} else if (line[0].compareTo(Protocol.CHATTINGSENDMESSAGE_OK) == 0) {
					chattingF.area1.append("[" + line[1] + "] :" + line[2] + "\n");
				}

			} catch (IOException io) {
				io.printStackTrace();
			}

		} // while
	}
}