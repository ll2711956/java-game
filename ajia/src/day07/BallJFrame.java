package day07;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BallJFrame extends JFrame {
	public BallJFrame() {
		this.setBounds(50, 50, 800, 600);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		BallJPanel bp = new BallJPanel();
		this.add(bp);
		bp.moveBall();
	}

	public static void main(String[] args) {
		new BallJFrame();
	}
}

class BallJPanel extends JPanel {
	ArrayList<Ball> balls = new ArrayList<Ball>();
	Ball ball;

	// ���췽��������ʼ����Ϸ,��ʼ����Ϸһ��ʼ����10����
	public BallJPanel() {
		for (int i = 0; i < 10; i++) {
			addBall();
		}
	}

	@Override
	// �Զ��廭���ķ���
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < balls.size(); i++) {
			balls.get(i).drawBall(g);
		}
	}

	// �����ж��������Ƿ���ײ��
	public boolean bitBall(Ball b1, Ball b2) {
		boolean flag = false;// ��ʾ������ûײ��
		// ���ɶ��� ���ÿ���������
		int b1x = b1.x + b1.d / 2;
		int b1y = b1.y + b1.d / 2;
		int b2x = b2.x + b2.d / 2;
		int b2y = b2.y + b2.d / 2;
		// ��������(����x�����y����)ֱ�ӵĲ�
		int a = b2x - b1x; // x������֮��
		int b = b2y - b1y; // y������֮��
		double c = b1.d / 2 + b2.d / 2;// ��ײ�������뻯����
		double f = a * a + b * b; // Math.pow(a, 2); ƽ��
									// Math.sqrt(a); �����η�
		if (f <= c * c) { // ��ʾ������ײ����
			flag = true;
		}
		return flag;
	}

	// ������
	public void addBall() {
		int d = (int) (Math.random() * 100 + 20);
		int speed = (int) (Math.random() * 3 + 2);
		int direction = (int) (Math.random() * 4);
		int x = (int) (Math.random() * 800);
		int y = (int) (Math.random() * 600);
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		Color ballColor = new Color(r, g, b);
		ball = new Ball(d, ballColor, direction, speed, x, y);
		// �Ѳ�������ŵ���������ȥ
		balls.add(ball);
	}

	// �Զ�������˶�����
	public void moveBall() {
		new Thread() {
			public void run() {
				while (true) {
					// forѭ���������һ��,�ڲ���ȫ��
					for (int i = 0; i < balls.size(); i++) {
						for (int j = i + 1; j < balls.size(); j++) {
							Ball b1 = balls.get(i);
							Ball b2 = balls.get(j);
							boolean flag = bitBall(b1, b2);
							if (flag == true) {// ������ײ����
								if (b1.d > b2.d) { // ��b1������b2
									b1.d = b1.d / 2 + b2.d;// ��b1�Ե�b2
									balls.remove(j);// ��b2�Ƴ�
								}
								if (b1.d < b2.d) {// ��b2������b1
									b2.d = b2.d / 2 + b1.d;// ��b2�Ե�b1
									balls.remove(i);// ��b1�Ƴ�
								}
							}
						}
						balls.get(i).move();
					}
					if (balls.size() < 8) {
						addBall();
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