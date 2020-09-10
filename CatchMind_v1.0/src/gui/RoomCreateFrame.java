package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RoomCreateFrame extends JFrame {
	
	private JPanel mainPanel;
	private JLabel roomNameLabel;
	private JTextField roomNameTextField;
	private JButton createButton;
	
	public RoomCreateFrame(Object c) {
		setTitle("room");
		mainPanel = new JPanel();
		roomNameLabel = new JLabel("방이름");
		roomNameTextField = new JTextField(10);
		createButton = new JButton("방생성합니다.");
		
		createButton.addActionListener((ActionListener)c);
		add(mainPanel);
		
		mainPanel.setLayout(new FlowLayout());
		mainPanel.add(roomNameLabel);
		mainPanel.add(roomNameTextField);
		mainPanel.add(createButton);
		
		setBounds(500, 400, 200, 100);
	}
	
	public String getRoomName() {
		return roomNameTextField.getText();
	}
	
	public String getcreatebuttonactioncommend() {
		return createButton.getActionCommand();
	}

	public void setTextClear() {
		roomNameTextField.setText(" ");
	}
}






















