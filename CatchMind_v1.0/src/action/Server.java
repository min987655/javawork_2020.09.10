package action;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

import dao.DBConnection;
import oracle.security.o5logon.d;
import protocol.CodeList;

public class Server extends Thread implements CodeList {

	ServerSocket ss;
	HashMap<String, UserInfo> ui = new HashMap<String, UserInfo>(); // 멀티쓰레드 사용시 동기화 빠름
	Vector<RoomInfo> ri = new Vector<RoomInfo>();
	DBConnection db;
	
	Server() {
		Collections.synchronizedMap(ui);
		try {
			ss = new ServerSocket(9900);
			System.out.println("서버연결 완료");
		} catch (Exception e) {
			e.printStackTrace();
		}
		start();
	} 
	
	public void start() {
		while (true) {
			try {
				Thread th = new Thread(new UserInfo(ss.accept()));
				th.start();
			} catch (Exception e) {
			}
		}
	}
	
	public RoomInfo getRoomInfo(String s) {
		int size = ri.size();
		String roomName = s;
		RoomInfo regInfo = null;
		for (int i = 0; i < size; i++) {
			regInfo = ri.get(i);
			if (regInfo.getRoomName() == roomName) {
				break;
			}
		}
		return regInfo;
	}
	
	public String FRoomUpdate(int i) {
		return ri.get(i).getRoomInfo();
	}
	
	public void sendToAll(String s) {
		String msg = s;
		Iterator it = ui.keySet().iterator();
		while (it.hasNext()) {
			DataOutputStream reg = ui.get(it.next()).dOut;
			try {
				reg.writeUTF(msg);
			} catch (Exception e) {
			}
		}
	}
	
	class UserInfo implements Runnable {
		Socket userSocket;
		DataOutputStream dOut;
		String id;
		RoomInfo room;
		
		UserInfo(Socket s) {
			try {
				userSocket = s;
				dOut = new DataOutputStream(s.getOutputStream());
			} catch (Exception e) {
			}
		}
		@Override
		public void run() {
			DataInputStream dIn;
			try {
				dIn = new DataInputStream(userSocket.getInputStream());
				while (dIn != null) {
					String h = dIn.readUTF();
					processing(h);
				}
			} catch (Exception e) {
				if (room.getRoomSize() == 0) {
					sendToAll(room.getRoomName() + "#" + ROOMLISTREMOVE);
					ri.remove(room);
				} else {
					room.broadcast(id + "#" + USERLISTREMOVE);
					room.remove(id);
					if (room.host == id) {
						room.tokenChange(id);
					}
				} 
			} finally {
				ui.remove(id);
				userSocket = null;
				dOut = null;
				room = null;
			}
		}
		
		public void requsetmsg(String s) {
			String msg = s;
			try {
				dOut.writeUTF(msg);
			} catch (Exception e) {
			}
		}
		
		public void processing(String s) {
			String msg = s;
			String[] g = msg.split("#");
			
			switch (Integer.valueOf(g[1])) {
			case LOGIN:
				id = g[0];
				ui.put(id, this);
				requsetmsg(" #" + LOGIN);
				if (ri.size() != 0) {
					for (int i = 0; i < ri.size(); i++) {
						requsetmsg(FRoomUpdate(i) + "#" + ROOMLISTADD);
					}
				}
				break;
			case CREROOM:
				room = new RoomInfo(g[0], id);
				ri.add(room);
				room.setUserInfo(this);
				requsetmsg(" #" + ROOMIN);
				requsetmsg("true#" + TOKENCHANGE);
				sendToAll(room.getRoomInfo() + "#" + ROOMLISTADD);
				room.broadcast(id + "#" + USERLISTADD);
				break;
			case ROOMIN:
				boolean t = true;
				room = getRoomInfo(g[0]);
				try {
					for (int i = 0; i < room.getRoomSize(); i++) {
						requsetmsg(room.roomUser.get(i).id + "#" + USERLISTADD);
					}
				} catch (Exception e) {
					requsetmsg(g[0] + "#" + USERLISTREMOVE);
					t = false;
				}
				if (t) {
					room.setUserInfo(this);
					requsetmsg(" #" + ROOMIN);
					sendToAll(room.getRoomInfo() + "#" + ROOMLISTADD);
					room.broadcast(id + "#" + USERLISTADD);
				}
				break;
			case EXITROOM:
				room.tokenChange(id);
				requsetmsg("false" + "#" + TOKENCHANGE);
				room.remove(id);
				if (room.getRoomSize() == 0) {
					sendToAll(room.getRoomName() + "#" + ROOMLISTREMOVE);
					requsetmsg(" #" + ROOMCLEAR);
					ri.remove(room);
				} else {
					sendToAll(room.getRoomInfo() + "#" + ROOMLISTADD);
					requsetmsg(" #" + ROOMCLEAR);
					room.broadcast(id + "#" + USERLISTREMOVE);
				}
				break;
			case TOKENCHANGE:
				room.tokenChange(id);
				requsetmsg("false" + "#" + TOKENCHANGE);
				break;
			case CHAT:
				room.broadcast(msg);
				break;
			case DRAW:
				room.broadcast(msg);
				break;
			default:
				room.broadcast(msg);
				break;
			}
		}
	}
	
	class RoomInfo {
		private Vector<UserInfo> roomUser = new Vector<UserInfo>();
		private String roomName;
		private String host;
		private int roomSize;
		
		RoomInfo(String s, String host) {
			roomName = s;
			this.host = host;
		}
		
		public void setUserInfo(UserInfo i ) {
			roomUser.add(i);
			roomSize = roomUser.size();
		}
		
		public void tokenChange(String name) {
			for (int i = 0; i < roomUser.size(); i++) {
				if (roomUser.get(i).id != name) {
					roomUser.get(i).requsetmsg("true" + "#" + TOKENCHANGE);
					host = roomUser.get(i).id;
					break;
				}
			}
		}

		public String getRoomName() {
			return roomName;
		}

		public int getRoomSize() {
			return roomSize;
		}

		public String getRoomInfo() {
			return roomName + "/" + host + "/" + roomSize;
		}
		
		public void remove(String s) {
			String removeName = s;
			for (int i = 0; i < roomSize; i++) {
				if (removeName.equals(roomUser.elementAt(i).id)) {
					roomUser.remove(i);
					break;
				}
			}
			roomSize = roomUser.size();
		}
		
		public void broadcast(String s) {
			String msg = s;
			for (int i = 0; i < roomSize; i++) {
				try {
					roomUser.get(i).dOut.writeUTF(msg);
				} catch (Exception e) {
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}
	
}
