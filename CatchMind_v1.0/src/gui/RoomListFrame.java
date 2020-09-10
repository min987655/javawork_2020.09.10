package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RoomListFrame extends JFrame {
	private JPanel mainPanel, roomListPanel, buttonPanel;
	private JLabel roomListLabel;
	private JButton roomCreateButton, goRoomButton;
	private JTable roomListTable;
	private DefaultTableModel dtm;
	private JScrollPane jsp;
	
	public RoomListFrame(Object c) {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}		
		});
		
		setBounds(580, 300, 520, 580);
		
		Object[][] roomList = {};
		String[] colume = {"방 제목", "host", "인원"};
		dtm = new DefaultTableModel(roomList, colume);
		
		mainPanel = new JPanel();
		roomListPanel = new JPanel();
		buttonPanel = new JPanel();
		roomListLabel = new JLabel("Room List");
	
		roomCreateButton = new JButton("방생성");
		goRoomButton = new JButton("방입장");
		roomCreateButton.addActionListener((ActionListener) c);
		goRoomButton.addActionListener((ActionListener) c);
		roomListTable = new JTable(dtm);
		jsp = new JScrollPane(roomListTable);
		
		add(mainPanel);
		mainPanel.add(roomListPanel);
		mainPanel.add(buttonPanel);
		mainPanel.setLayout(null);
		roomListPanel.setBounds(0, 0, 500, 500);
		buttonPanel.setBounds(0, 500, 500, 50);

		roomListPanel.setLayout(new BorderLayout());
		roomListPanel.add(roomListLabel, BorderLayout.NORTH);
		roomListPanel.add(jsp, BorderLayout.CENTER);

		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(roomCreateButton);
		buttonPanel.add(goRoomButton);
	}
	
	public void setRoomList(String s) {
		String[] addrow = s.split("/");
		dtm.addRow(addrow);
	}

	public String getRoomName() {
		int x = roomListTable.getSelectedRow();
		String s = (String) roomListTable.getValueAt(x, 1);
		return s;
	}

	public DefaultTableModel getTbModel() {
		return dtm;
	}

	public String getroomcreatebuttonactioncommend() {
		return roomCreateButton.getActionCommand();
	}

	public String getGoRoomButtonactioncommend() {
		return goRoomButton.getActionCommand();
	}
	
	public void RoomListUpdate(String s) {
		String[] g = s.split("/");
		boolean j = false;
		int d = dtm.getRowCount();
		if (d != 0) {
			for (int i = 0; i < d; i++) {
				String h = String.valueOf(dtm.getValueAt(i, 0));
				if (h.equals(g[0])) {
					dtm.removeRow(i);
					dtm.addRow(g);
					j = true;
					break;
				}
			}
		}
		if (j == false || d == 0) {
			dtm.addRow(g);
		}
	}
	
	public void RoomListRemove(String s) {
		String name = s;
		int h = dtm.getRowCount();
		for (int i = 0; i < h; i++) {
			System.out.println(s);
			String g = String.valueOf(dtm.getValueAt(i, 0));
			if (g.equals(s)) {
				dtm.removeRow(i);
				break;
			}
		}
	}

}



























