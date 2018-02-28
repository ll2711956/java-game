package day07;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import org.omg.CORBA.IdentifierHelper;

public class Ball {
	// 属性--->变量看上去的样子
	int d;// 球的直径
	Color ballColor;// 球的颜色
	int direction;// 球的方向
	int speed;// 球的速度
	int x;// 球的X坐标
	int y;// 球的Y坐标
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

	// 行为
	public void move() {// 球会移动
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
			}
		}
	}

	public void drawBall(Graphics g) {// 球是被画出来的
		g.setColor(ballColor);// 通过参数传递实现画球
		g.fillOval(x, y, d, d);
	}
}
