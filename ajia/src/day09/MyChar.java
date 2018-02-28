package day09;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class MyChar {
	// ������ĸ������
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
		g.setFont(new Font("����", Font.BOLD, size));
		g.drawString("" + a, x, y);// ˫��������Ϊ������String����,���������Ҫ���ַ���
	}

	public void addChar() {

		// ��������һ���µ���ĸ
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
