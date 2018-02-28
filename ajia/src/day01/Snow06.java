package day01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snow06 {
public static void main (String[] args){
	 JFrame jf = new JFrame();
	 jf.setVisible(true);
	 jf.setBounds(45,45,800,900);
	 jf.setResizable(false);
	 jf.setTitle("ÏÂÑ©");
	 jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 SnowJpanel7 sj7 = new SnowJpanel7();
	 jf.add(sj7);
}
}
class SnowJpanel7 extends JPanel{
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		setBackground(Color.black);
		g.setColor(Color.cyan);
		Font f = new Font("²ÝÊé",Font.BOLD,66);
		g.setFont(f);
		g.drawString("*", 66, 88);
	}
}