package day02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snow01 {
	public static void main(String[] args) {
          JFrame jf = new JFrame();
          jf.setVisible(true);
          jf.setResizable(false);
          jf.setBounds(50,50,800,800);
          jf.setTitle("ÏÂÑ©À²");
          jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          SnowJpanel1 sj1 = new SnowJpanel1();
          jf.add(sj1);
          sj1.moveSnow();
	}
}
class SnowJpanel1 extends JPanel{
	int x[]=new int[300]; 
	int y[]=new int[300];

	public SnowJpanel1(){
		for (int i = 0; i < x.length; i++) {
			x[i]=(int) (Math.random()*800);
			y[i]=(int) (Math.random()*800);
		}
	}
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.orange);
		g.setColor(Color.white);
		Font f = new Font("Î¢ÈíÑÅºÚ",Font.BOLD,58);
		g.setFont(f);
		for (int i = 0; i < x.length; i++) {
			g.drawString("*", x[i], y[i]);
		}

	}
	public void moveSnow(){
		new Thread(){
			public void run(){
				while(true){
				for (int i = 0; i < y.length; i++) {
					y[i]++;
					if(y[i]>=800){
						y[i]=0;
					}
				}
					repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
