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

//�̳д�����,�ͱ���˴�����Ķ���
public class Soccer extends JFrame {
	// Soccer�����Ĺ��췽��
	public Soccer() {
		// thisָ����ǰ���Լ��Ķ���
		this.setTitle("�����");// ���ñ���
		this.setVisible(true);// �ô�����ʾ
		this.setResizable(false);// ��ֹ����С
		this.setBounds(50, 50, 400, 600);// ���ô�С
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���X��Ĭ�Ϲر�
		SoccerJPanel sj = new SoccerJPanel();// ������������Ķ���
		this.add(sj);
		sj.moveball();
	}

	/* ���߳� */
	// ���췽�����ִ��? ����ĳ����Ķ����ʱ���ִ����
	public static void main(String[] args) {
		new Soccer();// ����Soccer�����Ĺ��췽��
	}
}

// ��������ĸΪ��д�ľ�ctrl+shift+o
// û�¶��Ͱ�alt+/
class SoccerJPanel extends JPanel implements MouseMotionListener {
	int x = 100;
	int y = 200;
	int d = 80;
	// �������˵����� pw��˵ĳ���
	int px = 100;
	int py = 500;
	int pw = 150;
	// ����һ������������������ƶ�����
	int f = 3;
	int speed = 1;// ��������ٶ�
	int score = 0;// �÷�
	boolean flag = false;// ������Ϸ������
	Color ballColor;// �����ɫ
	Color bgColor;// ����ɫ
	// ����Ϸһ��ʼ��ʱ��Ϳ�ʼ����(��ʼ��)

	public SoccerJPanel() {
		// ��ǰ��Ķ���
		addMouseMotionListener(this);
	}

	// ��������Ǵ�����{}
	// Java���о�����Ҫֱ��ʹ������,��Ӧ��ʹ�ñ�������ʽ
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(bgColor);
		g.setColor(ballColor);
		g.fillOval(x, y, d, d);
		// �����������͵Ĵ�С�Ƚ�,ͨ���ֽڵĴ�С
		// 4�ֽ� < 8�ֽ�
		// int a=(int) (Math.random()*100);
		// ǿ������ת��---->�Ⱥ��������ߵĴ�С��һ��, С=(С)��
		// ������������ͨ���ֽڱȴ�С ��������ͨ��������ȴ�С
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));// �ѻ��ʱ��
		g.setColor(Color.BLACK);
		g.drawLine(px, py, px + pw, py);
		// �÷�
		g.setColor(Color.blue);
		g.setFont(new Font("΢���ź�", Font.BOLD, 30));
		g.drawString("�÷�:" + score, 20, 50);
		g.setColor(Color.red);
		if (flag) {
			g.drawString("GAME_OVER", 120, 300);
		}
	}

	// �Զ���һ���������������߳�
	public void moveball() {
		new Thread() {
			public void run() {
				// ����ѭ��
				while (true) {// ����������
					if (f == 0) {
						x -= speed;
						y -= speed;
					}
					if (f == 1) {// ����������
						x += speed;
						y -= speed;
					}
					if (f == 2) {// ����������
						x += speed;
						y += speed;
					}
					if (f == 3) {// ����������
						x -= speed;
						y += speed;
					}
					if (y <= 0) { // ��
						if (f == 0) {
							f = 3;
						} else {
							f = 2;
						}
					}
					if (y >= py - d) { // ��
						if (px < x + d / 2 && x + d / 2 < px + pw) { // �ӵ���
							if (f == 2) {
								f = 1;
							} else {
								f = 0;
							}
							if(score>=Integer.MAX_VALUE){
								score=0;
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
							// ��ɫ
							int r = (int) (Math.random() * 256);
							int g = (int) (Math.random() * 256);
							int b = (int) (Math.random() * 256);
							ballColor = new Color(r, g, b);
							bgColor = new Color(b, r, g);
						} else {// û�ӵ���
							// �û���֪�������flag���ֵ
							flag = true;
							repaint();// �ٻ�һ��
							// �߳���Ķ���
							this.stop(); // ��Ϸ����
						}
					}
					if (x <= 0) { // ��
						if (f == 0) {
							f = 1;
						} else {
							f = 2;
						}
					}
					if (x >= 400 - d) { // ��
						if (f == 1) {
							f = 0;
						} else {
							f = 3;
						}
					}
					// �ظ���
					repaint();
					// ���߳���һ��
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	// ��ק
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	// �ƶ�
	@Override // ���
	public void mouseMoved(MouseEvent e) {
		px = e.getX() - pw / 2;// �Ѻ�˵����껻����������
	}
}
