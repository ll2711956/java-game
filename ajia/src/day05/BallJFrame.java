package day05;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BallJFrame extends JFrame {
	//构造方法
	public BallJFrame() {
		this.setTitle("一堆球球");
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
		new BallJFrame();//构造方法在new的时候就已经执行了
	}
}

class BallJPanel extends JPanel {
	// 定义一个能够存储20个球的数组
	Ball[] balls = new Ball[20];

	// 构造球的属性方法
	public BallJPanel() {
		for (int i = 0; i < balls.length; i++) {
			//定义球的直径20-120
			int d = (int) (Math.random() * 100 + 20);
			//定义球的速度2-12
			int speed = (int) (Math.random() * 10 + 2);
			//定义球的方向随0-4(不包括4)
			int direction = (int) (Math.random() * 4);
			//定义球的x,y坐标范围0-800(不包括800)
			int x = (int) (Math.random() * 800);
			int y = (int) (Math.random() * 600);
			// 定义球的颜色随机
			int r = (int) (Math.random() * 256);
			int g = (int) (Math.random() * 256);
			int b = (int) (Math.random() * 256);
			//创建Color类的对象并给它赋值
			Color ballColor = new Color(r, g, b);
			balls[i] = new Ball(d, ballColor, direction, speed, x, y);
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < balls.length; i++) {//有数组就一定有for循环
			//画出balls数组中的每个球
			balls[i].drawBall(g);
		}
	}

	public void moveball() {
		new Thread() {
			public void run() {
				while (true) {
					for (int i = 0; i < balls.length; i++) {
						balls[i].move();//让每个球都动起来
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
