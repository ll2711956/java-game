package day02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MoveSnow01 {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setSize(800, 800);
		jf.setTitle("Snow");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SnowJpanel03 sj3 = new SnowJpanel03();
		jf.add(sj3);
		sj3.movesnow();
	}
}

class SnowJpanel03 extends JPanel {
	int a[] = new int[380];
	int b[] = new int[380];

	public SnowJpanel03() {
		for (int i = 0; i < a.length; i++) {
			a[i] = (int) (Math.random() * 800);
			b[i] = (int) (Math.random() * 800);
		}
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		setBackground(Color.DARK_GRAY);
		g.setColor(Color.GREEN);
		Font f = new Font("ºÚÌå", Font.BOLD, 66);
		g.setFont(f);
		for (int i = 0; i < a.length; i++) {
			g.drawString("*", a[i], b[i]);
		}
	}

	public void movesnow() {
		new Thread() {
			public void run() {
				while (true) {
					for (int i = 0; i < b.length; i++) {
						b[i]++;
						if (b[i] >= 800) {
							b[i] = 0;
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
