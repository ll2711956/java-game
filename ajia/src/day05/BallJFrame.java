package day05;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BallJFrame extends JFrame {
	//���췽��
	public BallJFrame() {
		this.setTitle("һ������");
		this.setVisible(true);
		this.setResizable(false);
		this.setBounds(50, 50, 800, 600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BallJPanel bj = new BallJPanel();
		this.add(bj);
		bj.moveball();
	}

	public static void main(String[] args) {
		new BallJFrame();//���췽����new��ʱ����Ѿ�ִ����
	}
}

class BallJPanel extends JPanel {
	// ����һ���ܹ��洢20���������
	Ball[] balls = new Ball[20];

	// ����������Է���
	public BallJPanel() {
		for (int i = 0; i < balls.length; i++) {
			//�������ֱ��20-120
			int d = (int) (Math.random() * 100 + 20);
			//��������ٶ�2-12
			int speed = (int) (Math.random() * 10 + 2);
			//������ķ�����0-4(������4)
			int direction = (int) (Math.random() * 4);
			//�������x,y���귶Χ0-800(������800)
			int x = (int) (Math.random() * 800);
			int y = (int) (Math.random() * 600);
			// ���������ɫ���
			int r = (int) (Math.random() * 256);
			int g = (int) (Math.random() * 256);
			int b = (int) (Math.random() * 256);
			//����Color��Ķ��󲢸�����ֵ
			Color ballColor = new Color(r, g, b);
			balls[i] = new Ball(d, ballColor, direction, speed, x, y);
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < balls.length; i++) {//�������һ����forѭ��
			//����balls�����е�ÿ����
			balls[i].drawBall(g);
		}
	}

	public void moveball() {
		new Thread() {
			public void run() {
				while (true) {
					for (int i = 0; i < balls.length; i++) {
						balls[i].move();//��ÿ���򶼶�����
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
