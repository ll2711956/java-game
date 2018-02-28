package day06;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BallJFrame extends JFrame {
	public BallJFrame() {
		this.setVisible(true);
		this.setResizable(false);
		this.setBounds(50, 50, 400, 600);
		this.setTitle("������");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BallJPanel bj = new BallJPanel();
		this.add(bj);
		bj.moveBall();
	}

	public static void main(String[] args) {
		new BallJFrame();
	}
}

class BallJPanel extends JPanel implements MouseMotionListener {
	int px = 150;
	int py = 500;
	int pw = 400;
	//����һ��ֻ��װ����ļ���
	ArrayList<Ball> balls = new ArrayList<Ball>();
	//����һ��ball����
	Ball ball;
	int score = 0;

	// ��ִ���������ʱ,���������������һ����
	public void addBall() {
		int d = (int) (Math.random() * 100 + 20);
		int speed = (int) (Math.random() * 3 + 2);
		int direction = (int) (Math.random() * 4);
		int x = (int) (Math.random() * 400);
		int y = (int) (Math.random() * 300);
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		Color ballColor = new Color(r, g, b);
		//ʵ����ball�������
		ball = new Ball(d, ballColor, direction, speed, x, y);
		// �Ѳ�������ŵ���������ȥ
		balls.add(ball);
	}

	public BallJPanel() {
		// ����Ϸһ��ʼ���е�ʱ��,��Ӧ����һ����
		addBall();
		addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		//����balls�����е�ÿ����balls.size()�����ϵĴ�С
		for (int i = 0; i < balls.size(); i++) {
			//get()��ȡ�����е���,������ÿ����
			balls.get(i).drawBall(g);
		}
		// ǿ��ת��: �Ⱥŵ���������
		// ������֮��ȴ�Сǰ�� ������Ĺ�ϵ
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));
		g2.drawLine(px, py, px + pw, py);
		Font f=new Font("����",Font.BOLD,30);
		g.setFont(f);
		g.setColor(Color.BLUE);
		g.drawString("�÷�:"+score,20,50);
	}

	public void moveBall() {
		new Thread() {
			public void run() {
				while (true) {
					for (int i = 0; i < balls.size(); i++) {
						//�ü����е�ÿ���򶼶�����
						balls.get(i).move(px,py,pw);
						//�������������û����ס,��ֹͣ
						if (balls.get(i).gameover) {
							this.stop();
						}
						// ÿ��20�ּ���
						if (balls.get(i).flag) {
							balls.get(i).flag = false;
							score += 5;
							if (score % 20 == 0) {
								// ����
								addBall();
							}
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
