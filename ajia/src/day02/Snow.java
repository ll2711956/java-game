package day02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snow {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setBounds(50, 50, 800, 600);
		jf.setTitle("下雪啦");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SnowJpanel sj = new SnowJpanel();
		jf.add(sj);
		sj.moveSnow();
	}
}

class SnowJpanel extends JPanel {
	int[] x = new int[500];
	int[] y = new int[500];

	public SnowJpanel() {
		for (int i = 0; i < x.length; i++) {
			x[i] = (int) (Math.random() * 800);
			y[i] = (int) (Math.random() * 600);
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(Color.cyan);
		g.setColor(Color.WHITE);
		Font f = new Font("行楷", Font.ITALIC, 66);
		g.setFont(f);
		for (int i = 0; i < x.length; i++) {
			g.drawString("*", x[i], y[i]);
		}
	}

	public void moveSnow() {
		new Thread() {
			public void run() {
				while (true) {
					// 只要有数组一定有for循环
					for (int i = 0; i < y.length; i++) {
						y[i]++;
						if (y[i] >= 600) {
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
