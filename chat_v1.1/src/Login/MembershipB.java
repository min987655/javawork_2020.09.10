package Login;

import java.awt.Container;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MembershipB extends JFrame {
	JPasswordField pwT, pwT2;
	JTextField nameT, idT; 
	private JLabel nameL, idL, pwL, pwL2; 
	JButton joinB, calneB, idoverlapB;

	public MembershipB() {
		setTitle("회원가입");

		nameL = new JLabel("이름");
		nameT = new JTextField(15);
		JPanel p1 = new JPanel();
		p1.add(nameL);
		p1.add(nameT);

		idL = new JLabel("아이디");
		idT = new JTextField(15);
		idoverlapB = new JButton("중복확인");
		JPanel p2 = new JPanel();
		p2.add(idL);
		p2.add(idT);
		p2.add(idoverlapB);

		pwL = new JLabel("비밀번호");
		pwT = new JPasswordField(15);
		pwT.setEchoChar('*');
		JPanel p3 = new JPanel();
		p3.add(pwL);
		p3.add(pwT);
		
		pwL2 = new JLabel("비밀번호 확인");
		pwT2 = new JPasswordField(15);
		pwT2.setEchoChar('*');
		JPanel p4 = new JPanel();
		p4.add(pwL2);
		p4.add(pwT2);

		joinB = new JButton("가입");
		calneB = new JButton("취소");
		JPanel p8 = new JPanel();
		p8.add(joinB);
		p8.add(calneB);

		JPanel p = new JPanel(new GridLayout(8, 1));
		p.add(p1);
		p.add(p2);
		p.add(p3);
		p.add(p4);
		p.add(p8);

		JPanel joinp = new JPanel();
		joinp.add(joinB);
		joinp.add(calneB);

		Container contentPane = this.getContentPane();
		contentPane.add("Center", p);
		contentPane.add("South", joinp);

		setResizable(false);
		setBounds(400, 200, 1000, 800);
	}

}