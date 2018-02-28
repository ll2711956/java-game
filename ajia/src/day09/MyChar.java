package day09;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MyChar {
	// 定义字母的属性
	int x;
	int y;
	char a;
	int speed;
	Color CharColor;
	int size;

	public MyChar(int x, int y, char a, int speed, Color CharColor, int size) {
		this.x = x;
		this.y = y;
		this.a = a;
		this.speed = speed;
		this.CharColor = CharColor;
		this.size = size;
	}

	public void drawChar(Graphics g) {
		g.setColor(CharColor);
		g.setFont(new Font("黑体", Font.BOLD, size));
		g.drawString("" + a, x, y);// 双引号是因为这里是String类型,括号里必须要有字符串
	}

	public void addChar() {

		// 重新生成一个新的字母
		x = (int) (Math.random() * 700);
		y = -36;
		speed = (int) (Math.random() * 6 + 1);
		int r = (int) (Math.random() * 256);
		int g = (int) (Math.random() * 256);
		int b = (int) (Math.random() * 256);
		CharColor = new Color(r, g, b);
		a = (char) (Math.random() * 26 + 97);
	}

	public void move() {
		y += speed;
	}

}
