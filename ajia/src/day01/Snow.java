package day01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snow {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setBounds(50, 50, 600, 800);
		jf.setResizable(false);
		jf.setTitle("ÏÂÑ©");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SnowJpanel0 sj = new SnowJpanel0();
		jf.add(sj);
	}
}

class SnowJpanel0 extends JPanel {
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		setBackground(Color.LIGHT_GRAY);
		g.setColor(Color.green);
		Font f = new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 68);
		g.setFont(f);
		g.drawString("*", 300, 600);
	}
}
