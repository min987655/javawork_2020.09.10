package ch09;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class SwingEx02 extends JFrame {

	public SwingEx02() {
		setTitle("ContentPane과 JFrame"); // 프레임 타이틀 달기
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 프레임 윈도우를 닫으면 메인 프로그램이 종료

		Container contentPane = getContentPane(); // 컨텐트 팬을 알아낸다.
		contentPane.setBackground(Color.BLACK); // 컨텐트팬 백그라운드 컬러 설정.
		contentPane.setLayout(new FlowLayout()); // 컨텐트팬 기본 프레임을 FlowLayout으로

		contentPane.add(new JButton("OK"));
		contentPane.add(new JButton("Cancel"));
		contentPane.add(new JButton("Ignore"));

		setSize(300, 150);
		setVisible(true); // 화면에 프레임 출력
	}

	public static void main(String[] args) {
		new SwingEx02();
	}

}
