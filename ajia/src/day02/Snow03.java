package day02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snow03 {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setBounds(50, 50, 600, 800);
		jf.setLocationRelativeTo(null);
		jf.setTitle("ÏÂÑ©ÁË");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SnowJPanel06 sj06 = new SnowJPanel06();
		jf.add(sj06);
        sj06.movesnow();
	}
}

class SnowJPanel06 extends JPanel {
	int x[] = new int[320];
	int y[] = new int[320];

	public SnowJPanel06() {
		for (int i = 0; i < x.length; i++) {
			x[i] = (int) (Math.random() * 600);
			y[i] = (int) (Math.random() * 800);
		}
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		setBackground(Color.BLACK);
		g.setColor(Color.white);
		Font f = new Font("ºÚÌå", Font.BOLD, 38);
		g.setFont(f);
		for (int i = 0; i < x.length; i++) {
			g.drawString("*", x[i], y[i]);
		}
	}

	public void movesnow() {
		new Thread() {
			public void run() {
				while (true) {
					for (int i = 0;i < y.length; i++) {
						y[i]++;
						if (y[i] >= 800) {
							y[i] = 0;
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