package day01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snow02 {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setBounds(80, 80, 600, 800);
		jf.setResizable(false);
		jf.setTitle("ÏÂÑ©");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SnowJpanel1 sj1 = new SnowJpanel1();
		jf.add(sj1);
	}
}

class SnowJpanel1 extends JPanel {
	@Override
	public void paint(Graphics g) {

		super.paint(g);
		setBackground(Color.DARK_GRAY);
		g.setColor(Color.MAGENTA);
		Font f = new Font("ËÎÌå", Font.BOLD, 58);
		g.setFont(f);
		g.drawString("*", 550, 678);
	}
}
