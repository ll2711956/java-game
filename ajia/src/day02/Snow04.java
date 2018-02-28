package day02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snow04 {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setLocationRelativeTo(null);
		jf.setSize(800, 800);
		jf.setTitle("ÏÂÑ©");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SnowJPanel08 sj08 = new SnowJPanel08();
		jf.add(sj08);
		sj08.movesnow();
	}
}

class SnowJPanel08 extends JPanel {
	int x[] = new int[180];
	int y[] = new int[180];

	public SnowJPanel08() {
		for (int i = 0; i < x.length; i++) {
			x[i] = (int) (Math.random() * 800);
			y[i] = (int) (Math.random() * 800);
		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		setBackground(Color.BLACK);
		g.setColor(Color.WHITE);
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
					for (int i = 0; i < y.length; i++) {
						y[i]++;
						if (y[i] >= 800) {
							y[i] = 0;
						}
					}
					repaint();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}.start();
	}
}
