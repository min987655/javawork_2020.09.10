package action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import gui.GameFrame;
import gui.LoginFrame;
import gui.RoomCreateFrame;
import gui.RoomListFrame;
import protocol.CodeList;

public class Client implements CodeList, ActionListener, MouseListener, MouseMotionListener {

	DataOutputStream dOut;
	int serverPort;
	Socket serverSocket;
	String nickName;
	String serverIp;
	int ox, oy, x, y;
	
	GameFrame gf;
	LoginFrame lf;
	RoomCreateFrame rcf;
	RoomListFrame rlf;
	
	Client(String ip, int port) {
		serverIp = ip;
		serverPort = port;
		
		try {
			serverSocket = new Socket(serverIp, serverPort);
			dOut = new DataOutputStream(serverSocket.getOutputStream());
		} catch (Exception e) {
			System.exit(0);
		} 		
		init();
		start();
	}
	
	public void start() {
		lf.setVisible(true);
		try {
			new Thread(new receiver(new DataInputStream(serverSocket.getInputStream()))).start();
		} catch (Exception e) {
		}
	}
	
	public void init() {
		gf = new GameFrame(this);
		lf = new LoginFrame(this);
		rcf = new RoomCreateFrame(this);
		rlf = new RoomListFrame(this);
		gf.setSendButtonListener(this);
	}
	
	public void sender(String msg) {
		try {
			dOut.writeUTF(msg);
		} catch (Exception e) {
		}
	}
	
	class receiver implements Runnable {
		DataInputStream dIn;
		
		receiver(DataInputStream s) {
			try {
				dIn = s;
			} catch (Exception e) {
			}
		}
		
		public void upDate(String s) {
			String[] g = s.split("#");
			try {
				switch (Integer.parseInt(g[1])) {
				case LOGIN:
					nickName = lf.getID();
					rlf.setVisible(true);
					lf.setVisible(false);
					break;
				case USERLISTADD:
					gf.UserListAdd(g[0]);
					break;
				case USERLISTREMOVE:
					gf.UserListRemove(g[0]);
					break;
				case ROOMLISTADD:
					rlf.RoomListUpdate(g[0]);
					break;
				case ROOMLISTREMOVE:
					rlf.RoomListRemove(g[0]);
					break;
				case CHAT:
					gf.setMsg(g[0]);
					break;
				case DRAW:
					gf.Drawing(g[0]);
					break;
				case ROOMIN:
					gf.setVisible(true);
					rlf.setVisible(false);
					rcf.setTextClear();
					rcf.setVisible(false);
					break;
				case ROOMCLEAR:
					gf.RoomClear();
					break;
				case TOKENCHANGE:
					gf.isTurn = Boolean.valueOf(g[0]);
					gf.setListener();
					break;
				case ENDMOUSE:
					gf.sg.DrawShape();
					break;
				}
			} catch (Exception e) {
			}
		}
		public void run() {
			try {
				while (dIn != null) {
					upDate(dIn.readUTF());
				}
			} catch (Exception e) {
				try {
					serverSocket.close();
					dIn.close();
				} catch (Exception e1) {
				}
			}
		}
	}
	public static void main(String[] args) {
		new Client("localhost", 9900);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		x = e.getX(); y = e.getY();
		sender (String.valueOf(ox) + "/" + oy + "/" + x + "/" + y + "/" + "#" + DRAW);
		ox = x; oy = y;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		ox = e.getX(); oy = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		sender(" #" + ENDMOUSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == lf.getloginbuttonactioncommand()) {
			sender(lf.getID() + "#" + LOGIN);
		} else if (e.getActionCommand() == rlf.getroomcreatebuttonactioncommend()) {
			rcf.setVisible(true);
		} else if (e.getActionCommand() == rlf.getGoRoomButtonactioncommend()) {
			sender(rlf.getRoomName() + "#" + ROOMIN);
		} else if(e.getActionCommand() == rcf.getcreatebuttonactioncommend()) {
			sender(rcf.getRoomName() + "#" + CREROOM);
		} else if (e.getActionCommand() == gf.getsendbuttonactioncommend()) {
			sender(nickName + ": " + gf.getMsg() + "#" + CHAT);
			gf.setTextClear();
		} else if (e.getActionCommand() == gf.getexitbuttonacctioncommend()) {
			sender(" #" + EXITROOM);
			rlf.setVisible(true);
			gf.setVisible(false);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
