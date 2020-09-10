package ch12;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Trade extends JFrame{
	private MyPanel panel = new MyPanel();
	
	public Trade() {
		setTitle("공공데이터 활용");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(panel);
		
		setSize(360, 350);
		setVisible(true);
	}
	
	class MyPanel extends JPanel {
		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(Color.gray);
			g.fillRect(80, 100, 20, 150);
			g.setColor(Color.blue);
			g.fillRect(160, 160, 20, 90);
			g.setColor(Color.ORANGE);
			g.fillRect(240, 50, 20, 200);
		}
	}
	

	public static void main(String[] args) {
		new Trade();
	}

}
