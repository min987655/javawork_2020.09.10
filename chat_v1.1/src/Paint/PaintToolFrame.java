package Paint;

import java.awt.Container;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PaintToolFrame extends JFrame {

	JButton btPlus, btMinus, btClear, btAllClear, btClose, btColor;
	JPanel p;

	public PaintToolFrame() {
		super("::PaintToolFrame::");
		Container c = getContentPane();

		p = new JPanel();
		c.add(p, "Center");
		p.add(btPlus = new JButton("크게"));
		p.add(btMinus = new JButton("작게"));
		p.add(btClear = new JButton("지우기"));
		p.add(btAllClear = new JButton("모두지우기"));
		p.add(btColor = new JButton("색상"));
		p.add(btClose = new JButton("닫기"));
	}

}