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
		this.setTitle("吃球球");
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

	// 构造方法用来初始化游戏,初始化游戏一开始就有10个球
	public BallJPanel() {
		for (int i = 0; i < 10; i++) {
			addBall();
		}
	}

	@Override
	// 自定义画布的方法
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < balls.size(); i++) {
			balls.get(i).drawBall(g);
		}
	}

	// 用来判断两个球是否碰撞到
	public boolean bitBall(Ball b1, Ball b2) {
		boolean flag = false;// 表示两个球没撞到
		// 勾股定理 算出每个点的坐标
		int b1x = b1.x + b1.d / 2;
		int b1y = b1.y + b1.d / 2;
		int b2x = b2.x + b2.d / 2;
		int b2y = b2.y + b2.d / 2;
		// 求两个点(包括x坐标和y坐标)直接的差
		int a = b2x - b1x; // x的坐标之差
		int b = b2y - b1y; // y的坐标之差
		double c = b1.d / 2 + b2.d / 2;// 碰撞的最理想化距离
		double f = a * a + b * b; // Math.pow(a, 2); 平方
									// Math.sqrt(a); 开二次方
		if (f <= c * c) { // 表示两个球撞上了
			flag = true;
		}
		return flag;
	}

	// 添加球的
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
		// 把产生的球放到集合里面去
		balls.add(ball);
	}

	// 自定义球的运动方法
	public void moveBall() {
		new Thread() {
			public void run() {
				while (true) {
					// for循环中外层走一次,内层走全部
					for (int i = 0; i < balls.size(); i++) {
						for (int j = i + 1; j < balls.size(); j++) {
							Ball b1 = balls.get(i);
							Ball b2 = balls.get(j);
							boolean flag = bitBall(b1, b2);
							if (flag == true) {// 两个球撞上了
								if (b1.d > b2.d) { // 球b1大于球b2
									b1.d = b1.d / 2 + b2.d;// 球b1吃掉b2
									balls.remove(j);// 球b2移除
								}
								if (b1.d < b2.d) {// 球b2大于球b1
									b2.d = b2.d / 2 + b1.d;// 球b2吃掉b1
									balls.remove(i);// 球b1移除
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