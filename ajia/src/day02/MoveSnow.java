package day02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MoveSnow {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setSize(1000, 1000);
		jf.setTitle("ÏÂÑ©");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SnowJpanel02 sj2 = new SnowJpanel02();
		jf.add(sj2);
		sj2.movesnow();
	}
}

class SnowJpanel02 extends JPanel {
	int a[] = new int[220];
	int b[] = new int[220];

	public SnowJpanel02() {
		for (int i = 0; i < a.length; i++) {
			a[i] = (int) (Math.random() * 1000);
			b[i] = (int) (Math.random() * 1000);
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.gray);
		g.setColor(Color.ORANGE);
		Font f = new Font("ÐÐÊé", Font.ITALIC, 80);
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
						if (b[i] >= 1000) {
							b[i] = 0;
						}
					}
					repaint();
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
