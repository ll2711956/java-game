package day01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snow03 {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setBounds(80, 80, 800, 800);
		jf.setResizable(false);
		jf.setTitle("ÏÂÑ©");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SnowJpanel2 sj2 = new SnowJpanel2();
		jf.add(sj2);
	}
}

class SnowJpanel2 extends JPanel {
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		setBackground(Color.cyan);
		g.setColor(Color.yellow);
		Font f = new Font("¿¬Êé", Font.BOLD, 90);
		g.setFont(f);
		g.drawString("*", 500, 500);
	}
}
