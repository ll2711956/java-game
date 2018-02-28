package day06;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Ball {
	// ����--->��������ȥ������
	int d; // ���ֱ��
	Color ballColor;// �����ɫ
	int direction; // ��ķ���
	int speed; // ����ٶ�
	int x; // ���X����
	int y; // ���Y����
	boolean gameover = false; // �������ֵĬ��Ϊfalse,������Ϸ����Ӯ��
	// gameoverֵΪfalse��ʾ��Ϸ���ڽ���
	// gameoverֵΪtrue��ʾ��Ϸ����
	boolean flag = false;// �������Ƶ÷�
	// flag=false ��ʾ��ûײ�������
	// flag=true ��ʾ��ײ�������

	public Ball(int d, Color ballColor, int direction, int speed, int x, int y) {
		this.d = d;
		this.ballColor = ballColor;
		this.direction = direction;
		this.speed = speed;
		this.x = x;
		this.y = y;
	}

	// ��Ϊ
	public void move(int px, int py, int pw) {// ����ƶ�
		if (direction == 0) {// ����
			x -= speed;
			y -= speed;
			if (x <= 0) { // С��ײ�������
				direction = 1;
			}
			if (y <= 0) { // С��ײ���ϱ�
				direction = 3;
			}
		}
		if (direction == 1) { // ����
			x += speed;
			y -= speed;
			if (x >= 400 - d) { // �ұ�
				direction = 0;
			}
			if (y <= 0) { // �ϱ�
				direction = 2;
			}
		}
		if (direction == 2) { // ����
			x += speed;
			y += speed;
			if (x >= 400 - d) {
				direction = 3;
			}
			if (y >= py - d) {
				if (x + d / 2 > px && x + d / 2 < px + pw) { // �ӵ���
					direction = 1;
					flag = true;
				} else { // û�ӵ���
					gameover = true;
				}

			}
		}
		if (direction == 3) { // ����
			x -= speed;
			y += speed;
			if (x <= 0) {
				direction = 2;
			}
			if (y >= py - d) {
				if (x + d / 2 > px && x + d / 2 < px + pw) { // �ӵ���
					direction = 0;
					flag = true;
				} else { // û�ӵ���
					gameover = true;
				}

			}
		}
	}

	// �Զ��巽��?�ȴ���������������Ķ���,Ȼ���ö����������
	public void drawBall(Graphics g) { // ���ǻ�������
		g.setColor(ballColor);// ͨ����������ʵ�ֻ���
		g.fillOval(x, y, d, d);

	}

}
