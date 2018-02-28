package day01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snow04 {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setBounds(80, 80, 800, 600);
		jf.setResizable(false);
		jf.setTitle("ÏÂÑ©");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SnowJpanel3 sj3 = new SnowJpanel3();
		jf.add(sj3);

	}
}

class SnowJpanel3 extends JPanel {
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		setBackground(Color.ORANGE);
		g.setColor(Color.white);
		Font f = new Font("ÐÐ¿¬", Font.BOLD, 78);
		g.setFont(f);
		g.drawString("*", 256, 148);
	}
}