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

// �̳д����࣬�ͱ���˴�����Ķ���
public class BallJFrame extends JFrame {
	// BallJFrame�����Ĺ��췽��
	public BallJFrame() {
		// thisָ����ǰ���Լ��Ķ���
		this.setBounds(50, 50, 600, 800);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("�������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BallJPanel bj = new BallJPanel();// ������BallJPanel��Ķ���
		this.add(bj);
		bj.moveball();
	}

	// ���߳�
	// ���췽�����ִ�У� ����ĳ����Ķ����ʱ���ִ����
	public static void main(String[] args) {
		new BallJFrame();// ����BallJFrame�����Ĺ��췽��
	}
}

// ��������ĸ��д�ľ�Ctrl+shift+O
// û�¶��Ͱ�alt+/
class BallJPanel extends JPanel implements MouseMotionListener {
	int x = 180, y = 220, d = 100, px = 100, py = 730, pw = 220, f = 3, speed = 1, score = 0;
	boolean flag = false;
	Color ballColor;
	Color bgColor;

	public BallJPanel() {
		addMouseMotionListener(this);
	}

	// ��������Ǵ�����{}
	// Java���о�����Ҫֱ��ʹ�����֣���Ӧ��ʹ�ñ�������ʽ
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(bgColor);
		g.setColor(ballColor);
		g.fillOval(x, y, d, d);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(8));
		g2.drawLine(px, py, px + pw, py);
		g.setColor(Color.blue);
		g.setFont(new Font("΢���ź�", Font.BOLD, 58));
		g.drawString("�÷֣�" + score, 20, 50);
		g.setColor(Color.red);
		if (flag) {
			g.drawString("GAME_OVER", 100, 400);
		}
	}

	// �Զ���һ���������������߳�
	public void moveball() {
		new Thread() {
			public void run() {
				// ����ѭ��
				while (true) {
					if (f == 0) { // ��������
						x -= speed;
						y -= speed;
					}
					if (f == 1) { // ��������
						x += speed;
						y -= speed;
					}
					if (f == 2) { // ��������
						x += speed;
						y += speed;
					}
					if (f == 3) {// ��������
						x -= speed;
						y += speed;
					}
					if (y <= 0) { // �����߷���
						if (f == 1) {
							f = 2;
						} else {
							f = 3;
						}
					}
					if (y >= py - d) { // �����߷���
						if (px < x + d / 2 && x + d / 2 < px + pw) {
							if (f == 3) {
								f = 0;
							} else {
								f = 1;
							}
							speed++;
							if (speed >= 10) {
								speed = 10;
							}
							pw -= 10;
							if (pw <= 90) {
								pw = 90;
							}
							score += 5;
							if (score % 5 == 0) {
								pw += 10;
							}
							int r = (int) (Math.random() * 256);
							int g = (int) (Math.random() * 256);
							int b = (int) (Math.random() * 256);
							ballColor = new Color(r, g, b);
							bgColor = new Color(b, r, g);
						} else { // û�ӵ���
							flag = true;
							// �û���֪�������flag���ֵ
							repaint();// ���»�
							// �߳���Ķ���
							this.stop(); // ��Ϸ����
						}
					}
					if (x <= 0) { // �����߷���
						if (f == 0) {
							f = 1;
						} else {
							f = 2;
						}
					}
					if (x >= 600 - d) { // ���ұ��߷���
						if (f == 2) {
							f = 3;
						} else {
							f = 0;
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
