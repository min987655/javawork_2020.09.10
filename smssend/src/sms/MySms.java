package sms;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class MySms extends JFrame {
	
	private JLabel jl1, jl2;
	private JTextField jt1, jt2;
	private JButton jb;
	private Font f1, f2;

	public MySms() {
		init();
		
		setTitle("SMS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 6));
				
		jl1.setFont(f1);
		jl1.setForeground(Color.DARK_GRAY);
		jt1.setFont(f2);
		jt1.setBorder(null);
		jt1.setBackground(new Color(246,246,246));
		jt1.setPreferredSize(new Dimension(200, 22));
		jl2.setFont(f1);
		jl2.setForeground(Color.DARK_GRAY);
		jt2.setFont(f2);
		jt2.setPreferredSize(new Dimension(200, 100));
		jt2.setBorder(null);
		jt2.setBackground(new Color(246,246,246));
		jb.setFont(f1);
		jb.setForeground(Color.DARK_GRAY);
		
		c.add(jl1);
		c.add(jt1);
		c.add(jl2);
		c.add(jt2);
		c.add(jb);
		c.setBackground(new Color(225,225,225));
	
		
		jb.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String phone = jt1.getText();
				String msg = jt2.getText();
				SmsSend.sms(phone, msg);
			}
		});
		
		setSize(210, 265);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	
	private void init() {
		f1 = new Font("나눔바른고딕", Font.BOLD, 15);
		f2 = new Font("나눔바른고딕", Font.PLAIN, 15);
		jl1 = new JLabel("전화번호");
		jt1 = new JTextField(12);
		jl2 = new JLabel("메세지");
		jt2 = new JTextField(12);
		jb = new JButton("전송");
	}
	
	public static void main(String[] args) {
		new MySms();
	}

}
