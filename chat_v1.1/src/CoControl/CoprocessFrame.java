package CoControl;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Paint.MyDrawing;

public class CoprocessFrame extends JFrame {

	// GUI
	public JButton quizB, quizcB, startB, deleteB, exitB, sendB;
	public JTextArea area1, partList, scoreList, screen;
	public JTextField field;
	public MyDrawing md;

	public CoprocessFrame() {

		quizB = new JButton("제시어");
		quizcB = new JButton("제시어나올칸");
		exitB = new JButton("나가기");
		startB = new JButton("게임 시작");

		quizB.setEnabled(true);
		quizcB.setEnabled(true);
		exitB.setEnabled(true);
		startB.setEnabled(true);

		// 상단 버튼 - 제시어 버튼, 문제 area, 나가기 버튼
		JPanel buttonpanel = new JPanel(new GridLayout(1, 5, 10, 10)); // 상단 버튼
		buttonpanel.add(quizB);
		buttonpanel.add(quizcB);
		buttonpanel.add(exitB);

		// 그림판 영역
		JPanel wpanel1 = new JPanel();
		md = new MyDrawing();
		
		JScrollPane scroll = new JScrollPane(md);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(700, 735));
		wpanel1.add(scroll);

		JPanel totwpanel = new JPanel(new BorderLayout());
		totwpanel.add("North", buttonpanel);
		totwpanel.add("Center", wpanel1);

		// 우측 - 참여 인원, 스코어, 게임시작 버튼, 채팅 공간
		JPanel userlistpanel = new JPanel(new BorderLayout());
		JPanel p1 = new JPanel();
		JLabel user = new JLabel("                                   참여 유저");
		p1.add(user);

		partList = new JTextArea(); // 참여 인원 창
		partList.setEditable(false);
		JScrollPane scroll1 = new JScrollPane(partList);
		scroll1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		userlistpanel.add("North", user); // 참여 인원 라벨
		userlistpanel.add("Center", scroll1); // 스크롤
		userlistpanel.setPreferredSize(new Dimension(100, 120));

		JPanel scorepanel = new JPanel(new BorderLayout());
		JPanel p2 = new JPanel();
		JLabel score = new JLabel("                              	 스 코 어"); // 디비 연결
		p2.add(score);

//		list2 = new JList<String>(new DefaultListModel<String>());
//		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

//		model = (DefaultListModel<String>) list2.getModel();
		// list2.setSelectedIndex(0);

		scoreList = new JTextArea(); // 스코어 창
		scoreList.setEditable(false);
		JScrollPane scroll2 = new JScrollPane(scoreList);
		scroll2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scorepanel.add("North", score);
		scorepanel.add("Center", scroll2);
		scorepanel.setPreferredSize(new Dimension(100, 120));

		JPanel totalpanel = new JPanel(new GridLayout(3, 1, 0, 10));
		totalpanel.add(userlistpanel);
		totalpanel.add(scorepanel);
		totalpanel.add(startB);

		JPanel epanel3 = new JPanel(new BorderLayout());

		JPanel chatpanel = new JPanel();
		JLabel chat = new JLabel("                                        채 팅");
		chatpanel.add(chat);

		area1 = new JTextArea(); // 채팅 공간
		JScrollPane scroll3 = new JScrollPane(area1);
		scroll3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		area1.setEditable(false); // 채팅창에 텍스트 수정 불가
		epanel3.add("North", chat);
		epanel3.add("Center", scroll3);

		// 채팅 입력 창
		JPanel epanel4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		field = new JTextField(18);
		sendB = new JButton("Enter");
		epanel4.add(field);
		epanel4.add(sendB);

		JPanel totepanel = new JPanel(new BorderLayout());
		totepanel.add("North", totalpanel);
		totepanel.add("Center", epanel3);
		totepanel.add("South", epanel4);
		// *************************************************
		Container c = this.getContentPane();
		c.add("Center", totwpanel);
		c.add("East", totepanel);

		setResizable(false);
		setBounds(400, 200, 1000, 800);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}

class FileRead {
	private File file;
	private final String dir = "src" + File.separator + "Game" + File.separator + "answer.txt";
	private ArrayList<String> list;

	public void read() {
		makeList();
		readstart();
	}

	private void makeList() {
		list = new ArrayList<String>();
	}

	private void readstart() {
		try {
			file = new File(dir);
			FileReader filereader = new FileReader(file);
			BufferedReader bufReader = new BufferedReader(filereader);
			String line = "";
			while ((line = bufReader.readLine()) != null) {
				list.add(line);
			}
			bufReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Working Directory = " + System.getProperty("user.dir"));
		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public ArrayList<String> getAnswer() {
		return this.list;
	}
}

 class Game {

	private FileRead file;
	private ArrayList<String> answerList;
	private String answer;

	public void start() {
		readFile();
		saveAnswer();
	}

	private void readFile() {
		file = new FileRead();
		file.read();
	}

	private void saveAnswer() {
		answerList = file.getAnswer();
	}

	public void print() {
		for (int i = 0; i < answerList.size(); i++) {
			System.out.println(answerList.get(i));
		}
	}

	public boolean hasMoreAnswer() {
		if (answerList.size() != 0) {
			int index = (int) (Math.random() * answerList.size());
			answer = answerList.get(index);
			answerList.remove(index);
			return true;
		}
		else return false;
	}

	public String getAnswer() {
		return this.answer;
	}
}
 
 
