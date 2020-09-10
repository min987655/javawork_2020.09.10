package Action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DO.Room;
import DO.User;

public class MainHandler extends Thread {
	private BufferedReader br;
	private PrintWriter pw;
	private Socket socket;
	private Connection conn;
	private PreparedStatement pstmt;
	private User user;

	private ArrayList<MainHandler> allUserList; // 전체 사용자
	private ArrayList<MainHandler> waitUserList; // 대기실 사용자
	private ArrayList<Room> roomtotalList; // 전체 방리스트

	private Room priRoom; // 사용자가 있는 방

	// 사용자 연결 소켓, 전체사용자,대기방,방 리스트,JDBC 
	// this 소켓이 연결 된
	public MainHandler(Socket socket, ArrayList<MainHandler> allUserList, ArrayList<MainHandler> waitUserList,
			ArrayList<Room> roomtotalList, Connection conn) throws IOException {
		this.user = new User();
		this.priRoom = new Room();
		this.socket = socket;
		this.allUserList = allUserList;
		// waitUserList : 돌고있는 쓰레드를 배열에 담음
		this.waitUserList = waitUserList;
		this.roomtotalList = roomtotalList;
		this.conn = conn;

		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}

	@Override
	public void run() {
		// 데이터 입력받음 데이터파싱 -> 결과 실행
		try {

			String[] line = null; // 입력받은 데이터를 배열에 담음

			while (true) {
				
				line = br.readLine().split("\\|");
//				line[0] 프로토콜 | line[1] idName % line[2] password % line[3] name

				if (line == null) {
					break; // while 문 빠져나옴
				}
				if (line[0].compareTo(Protocol.REGISTER) == 0) // ***회원가입
				{
					String userContent[] = line[1].split("%"); // 회원 정보를 userContent 배열에 담음

					String sql = "Insert into UserContent values(num.nextval,?,?,?)"; // DB에 회원정보 insert
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, userContent[0]); // ID NAME
					pstmt.setString(2, userContent[1]); // PASSWORD
					pstmt.setString(3, userContent[2]); // NAME

					int su = pstmt.executeUpdate(); // auto flush 자동 커밋
					System.out.println(su + "회원가입 [DB]");

				} else if (line[0].compareTo(Protocol.IDSEARCHCHECK_OK) == 0) // ***ID중복체크(사용가능)
				{
					System.out.println(line[0] + "/" + line[1]); // 프로토콜 + / + 유저ID

					String sql = "select * from UserContent where IDNAME = '" + line[1] + "'"; // 유저 ID select
					pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery(sql);
					
					String name = null;
					int count = 0;
					while (rs.next()) {
						name = rs.getString("IDNAME");
						if (name.compareTo(line[1]) == 0) { // 중복 확인 ID 비교
							count++;
						}
					}
					System.out.println(count); 

					if (count == 0) // 중복안되서 가입가능
					{
						pw.println(Protocol.IDSEARCHCHECK_OK + "|" + "MESSAGE"); // ***ID중복체크(사용가능) 
						pw.flush();
					} else {
						pw.println(Protocol.IDSEARCHCHECK_NO + "|" + "MESSAGE"); // ***ID중복체크(사용불가능)
						pw.flush();
					}
				} else if (line[0].compareTo(Protocol.ENTERLOGIN_OK) == 0) // ***로그인OK
				{
					boolean con = true; // 기존에 로그인 됐는지 안됐는지 확인
					System.out.println("login");
					
					// 유저 정보 userContent에 담음
					// userContent[0] idName % userContent[1] password % userContent[2] name
					String userContent[] = line[1].split("%"); 

					System.out.println(userContent[0] + "/" + userContent[1]); // UserId / 비밀번호

					for (int i = 0; i < waitUserList.size(); i++) {
						if ((waitUserList.get(i).user.getIdName()).compareTo(userContent[0]) == 0) { // 대기방 user 리스트와 비교해서 중복 로그인 확인
							con = false; // 중복 로그인 방지
						}
					}

					if (con) { // con이 true 일 때(로그인 시) 무조건 실행

						String sql = "select * from UserContent where idname = '" + userContent[0]
								+ "' and password = '" + userContent[1] + "'"; // DB 회원정보 select

						pstmt = conn.prepareStatement(sql);
						ResultSet rs = pstmt.executeQuery(sql);
						
						int count = 0;
						// while 문 돌면서 select 한 값과 비교!! DB에 들어가있는 유저의 ID와 비밀번호가 입력한 값과 일치하는지 확인
						while (rs.next()) {
							user.setName(rs.getString("NAME"));
							user.setIdName(rs.getString("IDNAME"));
							user.setPassword(rs.getString("PASSWORD"));
							user.setPryNumber(rs.getInt("pryNumber"));
							count++;
						}

						System.out.println(count);

//						if (count == 0) // ID,PW 틀리면
//						{
//							pw.println(Protocol.ENTERLOGIN_NO + "|" + "로그인에 실패하였습니다");
//							pw.flush();
//
//							user.setName("");
//							user.setIdName("");
//							user.setPassword("");
//							user.setPryNumber(0);
//
//						} else 

						if (count == 1) { // 로그인 되었을때
							waitUserList.add(this); // [Thread[Thread-3,5,main]
							String userline = ""; // 새로운 유저들 담아 줌
							for (int i = 0; i < waitUserList.size(); i++) {
								userline += (waitUserList.get(i).user.getIdName() + ":"); // 새로운 유저의 id 추가 ":" 
							}

							for (int i = 0; i < waitUserList.size(); i++) {
								waitUserList.get(i).pw.println( // EnterFrame 로그인 연동, 대기실 채팅방에 해당 문구 뿌려줌
										Protocol.ENTERLOGIN_OK + "|" + user.getIdName() + "|님이 입장하였습니다.|" + userline);
								waitUserList.get(i).pw.flush();
							}
							System.out.println("[대기방 인원수] : " + waitUserList.size());
							System.out.println("userline : " + userline); // userline : userId 
							System.out.println("waitUserList : " + waitUserList); // waitUserList : [Thread[Thread-3,5,main]

							System.out.println("[Room 정보]");
							for (Room room : roomtotalList) {
								System.out.println(room.toString() + "현재방에 인원수 : " + room.roomInUserList.size());
							}
							System.out.println("[전체 Room 갯수 ]" + roomtotalList.size());

							// RoomtotalList 전체 정보를 콘솔에 뿌림
							String roomListMessage = "";
							for (int i = 0; i < roomtotalList.size(); i++) {
								roomListMessage += (roomtotalList.get(i).getrID() + "%"
										+ roomtotalList.get(i).getTitle() + "%" + "%"
										+ roomtotalList.get(i).getUserCount() + "%"
										+ roomtotalList.get(i).getMasterName() + "%"
										+ roomtotalList.get(i).roomInUserList.size()/* + "-" */); 
							}
							System.out.println(roomListMessage);

							if (roomListMessage.length() != 0) { // 방이 만들어지면
								for (int i = 0; i < waitUserList.size(); i++) {
									waitUserList.get(i).pw.println(Protocol.ROOMMAKE_OK + "|" + roomListMessage);
									waitUserList.get(i).pw.flush();
								}
							}
						}
						System.out.println(user.toString()); // 새로운 유저가 입장하면 유저의 정보가 콘솔창에 뜸
					
					} else { // con이 false 일 때(중복 로그인일 때)
						pw.println(Protocol.ENTERLOGIN_NO + "|" + "이미 로그인 중입니다.");
						pw.flush();
					}

				} else if (line[0].compareTo(Protocol.EXITWAITROOM) == 0) { // 대기실방에서 로그인페이지(로그아웃 할 때);

					String thisName = waitUserList.get(waitUserList.indexOf(this)).user.getIdName(); // 메인화면 창으로 간 유저의 이름(-여기 다시하기)

					waitUserList.remove(this); // 대기실 리스트 유저를 삭제
					System.out.println("[대기방 인원수] :" + waitUserList.size());

					String userline = "";
					for (int i = 0; i < waitUserList.size(); i++) {
						userline += (waitUserList.get(i).user.getIdName() + ":"); // 
					}

					System.out.println("대기 인원 :" + userline);
					for (int i = 0; i < waitUserList.size(); i++) {
						// 대기방에 Message 전송
						waitUserList.get(i).pw
								.println(Protocol.EXITWAITROOM + "|" + thisName + "|님이 퇴장하였습니다.|" + userline);
						waitUserList.get(i).pw.flush();
					}
					// 유저 초기화
					user.setName("");
					user.setIdName("");
					user.setPassword("");
					user.setPryNumber(0);

				} else if (line[0].compareTo(Protocol.SENDMESSAGE) == 0) { // 대기실방에서 메세지 보내기

					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw
								.println(Protocol.SENDMESSAGE_ACK + "|" + user.getIdName() + " |" + line[1]); // line[1] : 메세지유저입력메세지
						waitUserList.get(i).pw.flush();
					}
				} else if (line[0].compareTo(Protocol.ROOMMAKE) == 0) { // 방 만들기
					String userContent[] = line[1].split("%"); // 배열을 한 라인씩 %로 파싱

					Room tempRoom = new Room(); // Room 띄워 줌

					String sql = "Insert into Room values(num.nextval,?,?,?)"; // DB에 방 정보 insert
					pstmt = conn.prepareStatement(sql);

					pstmt.setString(1, userContent[0]); // title
					pstmt.setString(2, userContent[1]); // userCount
					pstmt.setString(3, user.getIdName()); // 방장이름

					tempRoom.setTitle(userContent[0]);
					tempRoom.setUserCount(userContent[1]);
					tempRoom.setMasterName(user.getIdName());

					sql = "select * from Room where title = '" + userContent[0] + "' and  userCount= '" + userContent[1]
							+ "' and  masterName= '" + user.getIdName() + "'";

					int su = pstmt.executeUpdate();
					System.out.println(su + "Room 만듬[DB]");

					pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery(sql);

					int count = 0;
					int priNumber = 0;
					while (rs.next()) {
						count++;
						priNumber = rs.getInt("rID"); // DB
					}

					if (count != 0) { // 방 만들어 짐
						tempRoom.setrID(priNumber); // 방 ID 넣고
						tempRoom.roomInUserList.add(this); // 유저 넣고
						roomtotalList.add(tempRoom);
						priRoom = tempRoom; // 현재 룸을 지정함
					}

					System.out.println("[Room 정보]");
					for (Room room : roomtotalList) {
						System.out.println(room.toString() + "현재 방의 인원 : " + room.roomInUserList.size()); // 콘솔창에 room
																											// 정보 띄움
					}
					System.out.println("[전체 Room 갯수 ]" + roomtotalList.size());

					// RoomtotalList 전체 정보를 Message로 만들어서 보내야된다
					String roomListMessage = "";

					for (int i = 0; i < roomtotalList.size(); i++) {
						roomListMessage += (roomtotalList.get(i).getrID() + "%" + roomtotalList.get(i).getTitle() + "%"
								+ roomtotalList.get(i).getUserCount() + "%" + roomtotalList.get(i).getMasterName() + "%"
								+ "%" + roomtotalList.get(i).roomInUserList.size() /* + "-" */);
					}

					System.out.println(roomListMessage); // 콘솔 창 메세지

					for (int i = 0; i < waitUserList.size(); i++) {
						// 대기방 사람과 방장 아이디 비교(방만든사람에게는 바로 채팅화면으로)
						if (waitUserList.get(i).user.getIdName().compareTo(tempRoom.getMasterName()) == 0) { 
							waitUserList.get(i).pw.println(Protocol.ROOMMAKE_OK1 + "|" + tempRoom.getMasterName());
							waitUserList.get(i).pw.flush();
						} else { // 다른 대기방사람들에게는 대기방만 새로고침
							waitUserList.get(i).pw.println(Protocol.ROOMMAKE_OK + "|" + roomListMessage);
							waitUserList.get(i).pw.flush();
						}

					}

					waitUserList.remove(this); // 대기방에서 나가고
					System.out.println("대기방 인원수 누군가 방만들었을때" + waitUserList.size());

					String userline = "";
					for (int i = 0; i < waitUserList.size(); i++) {
						userline += (waitUserList.get(i).user.getIdName() + ":");
					}
					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw.println(Protocol.ENTERLOGIN_OK + "|" + tempRoom.getMasterName() + "|님이"
								+ tempRoom.getrID() + "번 방을 만들었습니다.|" + userline);
						waitUserList.get(i).pw.flush();
					}

				} else if (line[0].compareTo(Protocol.ENTERROOM) == 0) { // [방 입장버튼]
					System.out.println("================================= 방 입장 함==============================");
					// waitUserList 중 입장버튼을 누른 user.getIdName
					String thisName = waitUserList.get(waitUserList.indexOf(this)).user.getIdName();
					int roomid = Integer.parseInt(line[1]); // 룸ID를 숫자로 변환

					int index = 0;
					// 룸토탈리스트에서 방 하나 선택, 방 id가 같을시 방에 유저 넣고 
					for (int i = 0; i < roomtotalList.size(); i++) {
						if (roomtotalList.get(i).getrID() == roomid) {
							roomtotalList.get(i).roomInUserList.add(this); // 방에 유저 넣고
							priRoom = roomtotalList.get(i); // 전체 방 리스트에서 선택한 방
							index = i; 
						}
					}

					String roomListMessage = "";
					for (int i = 0; i < roomtotalList.size(); i++) {
						roomListMessage += (roomtotalList.get(i).getrID() + "%" + roomtotalList.get(i).getTitle() + "%"
								+ "%" + roomtotalList.get(i).getUserCount() + "%" + roomtotalList.get(i).getMasterName()
								+ "%" + roomtotalList.get(i).roomInUserList.size() /*+ "-"*/);
					}

					System.out.println(roomListMessage);
					System.out.println(thisName); // 방에 들어온 user id

					String roomMember = ""; // ->여기 방마다 방에 유저를 넣어주는 것 추가 방에 입장한 유저를 가지고와야해 몇번방인지 찾아야함
					
					// 선택한 방에 있는 유저수
					for (int i = 0; i < roomtotalList.get(index).roomInUserList.size(); i++) {
						roomMember += (roomtotalList.get(index).roomInUserList.get(i).user.getIdName() + "%");
					} // roomMember = "" + 선택한 방 유저의 id

					for (int i = 0; i < waitUserList.size(); i++) {
						//  대기방 유저 id와 방에들어온 유저의 id 비교 
						if (waitUserList.get(i).user.getIdName().compareTo(thisName) == 0) { // 방들어가는사람에게는 바로 채팅화면으로
							waitUserList.get(i).pw.println(Protocol.ENTERROOM_OK1 + "|" + "message"); // 방입장 성공
							waitUserList.get(i).pw.flush();
						} else { // 다른 대기방사람들에게는 대기방만 새로고침
							System.out.println("MainHandle : roomListMessage : "+roomListMessage);
							System.out.println(roomListMessage);
							// #####
							waitUserList.get(i).pw.println(Protocol.ENTERROOM_OK + "|" + roomListMessage); // 룸리스트 새로고침
							waitUserList.get(i).pw.flush();
							
						}

					}
					
					// 게임방에 들어온 유저 게임방 채팅창에 입장 텍스트 뿌려줌. 
					for (int i = 0; i < roomtotalList.get(index).roomInUserList.size(); i++) {
						roomtotalList.get(index).roomInUserList.get(i).pw.println(Protocol.ENTERROOM_USERLISTSEND + "|"
								+ roomMember + "|" + user.getIdName() + "님이 입장하셨습니다.|");
						roomtotalList.get(index).roomInUserList.get(i).pw.flush();
					}

					waitUserList.remove(this); // 대기방에서 나가고
					System.out.println("방입장동작 부분  -->> [대기실 인원수 ]" + waitUserList.size());

					String userline = ""; // 채팅창에

					for (int i = 0; i < waitUserList.size(); i++) {
						userline += (waitUserList.get(i).user.getIdName() + ":" + ""); // 대기실 인원 수정
					}
					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw.println( // ***로그아웃(대기방.exit)
								Protocol.EXITWAITROOM + "|" + thisName + "|님이 " + roomid + "방에 입장하였습니다.|" + userline);
						waitUserList.get(i).pw.flush();
					}

				} else if (line[0].compareTo(Protocol.EXITCHATTINGROOM) == 0) // 방 나가기 버튼
				{

					int roomIndex = 0;
					boolean con = true;

					for (int i = 0; i < roomtotalList.size(); i++) {
						// 선택한 방번호가 user가 이미 있는 방 일 때 
						if (roomtotalList.get(i).getrID() == priRoom.getrID()) { 

							if (roomtotalList.get(i).roomInUserList.size() == 1)
							{
								System.out.println("나올때 내가 마지막일때");
								roomtotalList.remove(priRoom); // 내가 있던방 리셋
								priRoom = new Room();
								con = false;

							} else { // 최소 2명일 때
								System.out.println("나올때 내가 마지막아닐때!!");
								roomtotalList.get(i).roomInUserList.remove(this); // 방에 나가는 유저 빼고
								priRoom = new Room();// 현재룸을 비워주고
								roomIndex = i;

							}

						}
					}

					if (con) // 남아있는방에 최소 2명이상일때 --- 테스트 불가
					{
						String roomMember = ""; // ->여기 방마다 방에 유저를 넣어주는 것 추가 방에 입장한 유저를 가지고와야함 몇번방인지 찾아야 함

						for (int i = 0; i < roomtotalList.get(roomIndex).roomInUserList.size(); i++) { // 룸안에 유저의 수만큼
							roomMember += (roomtotalList.get(roomIndex).roomInUserList.get(i).user.getIdName() + "%");
						} // 이 방을 선택한사람들 더해짐

						System.out.println("특정방에 사람수 : " + roomtotalList.get(roomIndex).roomInUserList.size());
						System.out.println(roomMember);
						for (int i = 0; i < roomtotalList.get(roomIndex).roomInUserList.size(); i++) {
							roomtotalList.get(roomIndex).roomInUserList.get(i).pw
									.println(Protocol.ENTERROOM_USERLISTSEND + "|" + roomMember + "|" + user.getIdName()
											+ "님이 퇴장하셨습니다.");
							roomtotalList.get(roomIndex).roomInUserList.get(i).pw.flush();
						}
					}

					String roomListMessage = "";

					System.out.println(roomListMessage);

					waitUserList.add(this); // 대기방에서 추가
					if (roomtotalList.size() > 0) { // 생성된 방이 있다면
						roomListMessage = "";
						for (int i = 0; i < roomtotalList.size(); i++) {
							roomListMessage += (roomtotalList.get(i).getrID() + "%" + roomtotalList.get(i).getTitle()
									+ "%" + roomtotalList.get(i).getUserCount() + "%"
									+ roomtotalList.get(i).getMasterName() + "%"
									+ roomtotalList.get(i).roomInUserList.size() /*+ "-"*/);
						}
					} else {
						roomListMessage = "-";
					}

					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw.println(Protocol.ROOMMAKE_OK + "|" + roomListMessage); // 룸리스트 새로고침
						waitUserList.get(i).pw.flush();
					}

					System.out.println("방퇴실동작 부분  -->>[대기실 인원수 ]" + waitUserList.size());
					String userline = ""; // 채팅창에
					for (int i = 0; i < waitUserList.size(); i++) {
						userline += (waitUserList.get(i).user.getIdName() + ":");
					}
					for (int i = 0; i < waitUserList.size(); i++) {
						waitUserList.get(i).pw.println( // 대기방에 바꿔주고 Message 전송
								Protocol.EXITWAITROOM + "|" + user.getIdName() + "|님이 대기실에 입장하였습니다.|" + userline);
						waitUserList.get(i).pw.flush();
					}

				} else if (line[0].compareTo(Protocol.CHATTINGSENDMESSAGE) == 0) // 채팅방에서 메세지 보내기
				{

					int roomUserSize = roomtotalList.get(roomtotalList.indexOf(priRoom)).roomInUserList.size();

					for (int i = 0; i < roomUserSize; i++) {
						roomtotalList.get(roomtotalList.indexOf(priRoom)).roomInUserList.get(i).pw
								.println(Protocol.CHATTINGSENDMESSAGE_OK + "|" + user.getIdName() + "|" + line[1]); // 채팅방
																													// 사람들에게
																													// 메세지
						roomtotalList.get(roomtotalList.indexOf(priRoom)).roomInUserList.get(i).pw.flush();
					}

				}
			} // while

			br.close();
			pw.close();
			socket.close();

		} catch (IOException io) {
			io.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}