package day03;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ball2 extends JFrame {
	public Ball2() {
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(600, 800);
		this.setTitle("Íæ¸öÇòÇò");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BallJPanel2 bj2 = new BallJPanel2();
		this.add(bj2);
		bj2.moveball();
	}

	public static void main(String[] agrs) {
		new Ball2();
	}
}

class BallJPanel2 extends JPanel implements MouseMotionListener {
	int x = 180;
	int y = 300;
	int d = 80;
	int f = 0;
	int px = 100;
	int py = 700;
	int pw = 180;
	int speed = 1;
	int score = 0;
	Color ballcolor;
	boolean flag = false;

	public BallJPanel2() {
		addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(ballcolor);
		g.fillOval(x, y, d, d);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLACK);
		g2.setStroke(new BasicStroke(8));
		g2.drawLine(px, py, px + pw, py);
		g.setColor(Color.MAGENTA);
		g.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 36));
		g.drawString("µÃ·Ö:" + score, 30, 50);
		g.setColor(Color.RED);
		g.setFont(new Font("ºÚÌå", Font.ITALIC, 50));
		if (flag) {
			g.drawString("GAME_OVER", 180, 400);
		}
	}

	public void moveball() {
		new Thread() {
			public void run() {
				while (true) {
					if (f == 0) {
						x -= speed;
						y -= speed;
					}
					if (f == 1) {
						x += speed;
						;
						y -= speed;
					}
					if (f == 2) {
						x += speed;
						y += speed;
					}
					if (f == 3) {
						x -= speed;
						y += speed;
					}
					if (y <= 0) { // ÍùÉÏ
						if (f == 0) {
							f = 3;
						} else {
							f = 2;
						}
					}
					if (y >= py - d) { // ÍùÏÂ
						if (x + d / 2 > px && x + d / 2 < px + pw) {// Ô²ÐÄµÄ×ø±ê´óÓÚºá¸Ë×ó±ßX×ø±êÐ¡ÓÚÓÒ±ßºá¸ËX×ó±ß
							if (f == 2) {
								f = 1;
							} else {
								f = 0;
							}

							speed++;
							if (speed >= 10) {
								speed = 10;
							}
							score += 5;
							if (score % 5 == 0) {
								pw += 5;
							}
							if (pw >= 220) {
								pw = 220;
							}
							int r = (int) (Math.random() * 256);
							int g = (int) (Math.random() * 256);
							int b = (int) (Math.random() * 256);
							ballcolor = new Color(r, g, b);
						} else {
							flag = true;
							repaint();
							this.stop();
						}
					}
					if (x <= 0) { // Íù×ó
						if (f == 3) {
							f = 2;
						} else {
							f = 1;
						}
					}
					if (x >= 600 - d) { // ÍùÓÒ
						if (f == 1) {
							f = 0;
						} else {
							f = 3;
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

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		px = e.getX() - pw / 2;
	}
}
