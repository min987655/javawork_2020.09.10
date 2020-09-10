package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Shape;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import lombok.Setter;

@SuppressWarnings("serial")
public class GameFrame extends JFrame {
	
	static ArrayList<Shape> ss = new ArrayList<Shape>();
	private JButton sendButton, exitButton;
	private JLabel uList;
	private JList<String> userList;
	private JPanel sp1, sp2, sp1_1, sp1_2, sp2_1, sp2_2, sp2_3, sp2_1_1, sp2_1_2, sp2_2_1, sp2_2_2;
	private JTextField chatText;
	private JTextArea chatField;
	private DefaultListModel<String> dlm;
	public boolean isTurn;
	public SaveGraphics sg;
	Object c;
	
	public GameFrame(Object c) {
		this.c = c;
		sg = new SaveGraphics();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		isTurn = false;
		
		Object[][] dd;
		
		sp1 = new JPanel();
		sp2 = new JPanel();
		sp1_2 = new JPanel();
		sp1_1 = new JPanel();
		sp2_1 = new JPanel();
		sp2_2 = new JPanel();
		sp2_3 = new JPanel();
		sp2_1_1 = new JPanel();
		sp2_1_2 = new JPanel();
		sp2_2_1 = new JPanel();
		sp2_2_2 = new JPanel();
		
		userList = new JList<String>(new DefaultListModel<String>());
		dlm = (DefaultListModel<String>) userList.getModel();
		uList = new JLabel("접속자 목록");
		
		sendButton = new JButton("전송");
		exitButton = new JButton("나  가  기");
		chatText = new JTextField(24);
		chatField = new JTextArea();
		
		exitButton.addActionListener((ActionListener) c);
	
		JScrollPane js = new JScrollPane(chatField);
		
		setBounds(350, 180, 1200, 735);
		setLayout(null);
		add(sp1);
		add(sp2);
		
		sp1.setBounds(0, 0, 800, 700);
		sp2.setBounds(800, 0, 400, 700);
		//왼쪽
		sp1.setLayout(new BorderLayout());
		sp1.add(sp1_1);
		sp1.add(sp1_2, BorderLayout.SOUTH);
		sp1.add(sp1_1, BorderLayout.CENTER);
		sp1_2.setBounds(0, 600, 800, 190);
		sp1_1.setBounds(0, 0, 800, 600);
		
		sg.setSize(sp1_1.getSize());
		sp1_1.setLayout(new BorderLayout());
		sp1_1.setBackground(Color.white);
		sp1_1.add(sg);
		
		sp2.setLayout(null);
		sp2.add(sp2_1);
		sp2.add(sp2_2);
		sp2.add(sp2_3); // 오른쪽
		sp2_1.setBounds(0, 0, 400, 150);
		sp2_2.setBounds(0, 150, 400, 450);
		sp2_3.setBounds(0, 600, 400, 100);	
		// 유저
		sp2_1.setLayout(new BorderLayout());
		sp2_1.add(sp2_1_1, BorderLayout.NORTH);
		sp2_1.add(sp2_1_2, BorderLayout.CENTER);
		sp2_1_1.add(uList);
		sp2_1_2.add(userList);
		// 텍스트
		sp2_2.setLayout(new BorderLayout());
		sp2_2.add(sp2_2_1, BorderLayout.CENTER);
		sp2_2.add(sp2_2_2, BorderLayout.SOUTH);
		sp2_2_1.setLayout(new GridLayout());
		sp2_2_1.add(js);
		sp2_2_2.add(chatText);
		sp2_2_2.add(sendButton);
		chatField.setEditable(false);
		sp2_3.setLayout(new GridLayout());
		sp2_3.add(exitButton);
		// 테두리
		sp1_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		sp2_1_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		sp2_1_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		sp2_2_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
		sp2_2_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
	}
	
	public void setListener() {
		if (isTurn == true) {
			sg.addMouseListener((MouseListener) c);
			sg.addMouseMotionListener((MouseMotionListener) c);
		} else {
			sg.removeMouseListener((MouseListener) c);
			sg.removeMouseMotionListener((MouseMotionListener) c);
		}
	}
	
	public void setTitle(String a) {
		setTitle(a);
	}
	
	public void setSendButtonListener(Object o) {
		sendButton.addActionListener((ActionListener) o);
	}

	public String getMsg() {
		return chatText.getText();
	}

	public void setMsg(String msg) {
		chatField.append(msg + "\n");
	}

	public String getsendbuttonactioncommend() {
		return sendButton.getActionCommand();
	}

	public String getexitbuttonacctioncommend() {
		return exitButton.getActionCommand();
	}

	public void UserListAdd(String s) {
		dlm.addElement(s);
	}
	
	public void UserListRemove(String s) {
		String name = s;
		for (int i = 0; i < dlm.size(); i++) {
			String h = String.valueOf(dlm.getElementAt(i));
			if (h.equals(name)) {
				dlm.remove(i);
				break;
			}
		}
	}
	
	public void RoomClear() {
		dlm.clear();
		chatField.setText(" ");
		chatText.setText(" ");
		ss.removeAll(ss);
	}
	
	public void setTextClear() {
		chatText.setText(" ");
	}
	
	public void Drawing(String h) {
		String[] t = h.split("/");
		Integer[] j = new Integer[4];
		for (int i = 0; i < t.length; i++) {
			j[i] = Integer.valueOf(t[i]);
		}
		sg.setPoint(j[0], j[1], j[2], j[3]);
	}
	
	public class SaveGraphics extends JComponent {
		
		int[] Xpoints;
		int[] Ypoints;
		ArrayList<Integer> XpointsList = new ArrayList<Integer>();
		ArrayList<Integer> YpointsList = new ArrayList<Integer>();
		
		SaveGraphics() {
		}
		
		public void setPoint(int x, int y, int width, int height) {
			XpointsList.add(x);
			YpointsList.add(y);
			Xpoints = new int[XpointsList.size()];
			
			for (int i = 0; i < XpointsList.size(); i++) {
				Xpoints[i] = XpointsList.get(i);
			}
			
			Ypoints = new int[YpointsList.size()];

			for (int i = 0; i < YpointsList.size(); ++i) {
				Ypoints[i] = YpointsList.get(i);
			}
			repaint();
		}
		
		public void DrawShape() {
			GeneralPath g = new GeneralPath();
			g.moveTo(XpointsList.get(0), YpointsList.get(0));
			for (int i = 0; i < XpointsList.size(); i++) {
				g.lineTo(XpointsList.get(i), YpointsList.get(i));
			}
			ss.add(g);
			XpointsList.removeAll(XpointsList);
			YpointsList.removeAll(YpointsList);
		}
		
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			if ((Xpoints == null) == false) {
				g2.drawPolyline(Xpoints, Ypoints, Xpoints.length);
			}
			for (Shape s : ss) {
				g2.draw(s);
			}
		}
		
	}

}



















