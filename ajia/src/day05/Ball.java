package day05;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import org.omg.CORBA.IdentifierHelper;

public class Ball {
	// ����--->��������ȥ������
	int d;// ���ֱ��
	Color ballColor;// �����ɫ
	int direction;// ��ķ���
	int speed;// ����ٶ�
	int x;// ���X����
	int y;// ���Y����
	int px;
	int py;
	int pw;

	public Ball(int d, Color ballColor, int direction, int speed, int x, int y) {
		this.d = d;
		this.ballColor = ballColor;
		this.direction = direction;
		this.speed = speed;
		this.x = x;
		this.y = y;
	}

	// ��Ϊ
	public void move() {// ����ƶ�
		if (direction == 0) {
			x -= speed;
			y -= speed;
			if (x <= 0) {
				direction = 1;
			}
			if (y <= 0) {
				direction = 3;
			}
		}
		if (direction == 1) {
			x += speed;
			y -= speed;
			if (x >= 800 - d) {
				direction = 0;
			}
			if (y <= 0) {
				direction = 2;
			}
		}
		if (direction == 2) {
			x += speed;
			y += speed;
			if (x >= 800 - d) {
				direction = 3;
			}
			if (y >= 600 - d) {
				direction = 1;
			}
		}
		if (direction == 3) {
			x -= speed;
			y += speed;
			if (x <= 0) {
				direction = 2;
			}
			if (y >= 600 - d) {
				direction = 0;
				int r = (int) (Math.random() * 256);
				int g = (int) (Math.random() * 256);
				int b = (int) (Math.random() * 256);
				ballColor = new Color(r, g, b);
			}
		}
	}

	public void drawBall(Graphics g) {// ���Ǳ���������
		g.setColor(ballColor);// ͨ����������ʵ�ֻ���
		g.fillOval(x, y, d, d);
	}
	public void drawLine(Graphics2D g2){
		g2.setStroke(new BasicStroke(8));
		g2.drawLine(px, py, px+pw, py);
	}
}
