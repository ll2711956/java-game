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

// 继承窗口类，就变成了窗口类的儿子
public class BallJFrame extends JFrame {
	// BallJFrame这个类的构造方法
	public BallJFrame() {
		// this指代当前类自己的对象
		this.setBounds(50, 50, 600, 800);
		this.setVisible(true);
		this.setResizable(false);
		this.setTitle("玩个球球");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BallJPanel bj = new BallJPanel();// 创建出BallJPanel类的对象
		this.add(bj);
		bj.moveball();
	}

	// 主线程
	// 构造方法如何执行？ 创建某个类的对象的时候就执行了
	public static void main(String[] args) {
		new BallJFrame();// 调用BallJFrame这个类的构造方法
	}
}

// 看到首字母大写的就Ctrl+shift+O
// 没事儿就按alt+/
class BallJPanel extends JPanel implements MouseMotionListener {
	int x = 180, y = 220, d = 100, px = 100, py = 730, pw = 220, f = 3, speed = 1, score = 0;
	boolean flag = false;
	Color ballColor;
	Color bgColor;

	public BallJPanel() {
		addMouseMotionListener(this);
	}

	// 作用域就是大括号{}
	// Java当中尽量不要直接使用数字，而应该使用变量的形式
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(bgColor);
		g.setColor(ballColor);
		g.fillOval(x, y, d, d);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(8));
		g2.drawLine(px, py, px + pw, py);
		g.setColor(Color.blue);
		g.setFont(new Font("微软雅黑", Font.BOLD, 58));
		g.drawString("得分：" + score, 20, 50);
		g.setColor(Color.red);
		if (flag) {
			g.drawString("GAME_OVER", 100, 400);
		}
	}

	// 自定义一个方法用来创建线程
	public void moveball() {
		new Thread() {
			public void run() {
				// 创建循环
				while (true) {
					if (f == 0) { // 方向左上
						x -= speed;
						y -= speed;
					}
					if (f == 1) { // 方向右上
						x += speed;
						y -= speed;
					}
					if (f == 2) { // 方向右下
						x += speed;
						y += speed;
					}
					if (f == 3) {// 方向左下
						x -= speed;
						y += speed;
					}
					if (y <= 0) { // 往上走反弹
						if (f == 1) {
							f = 2;
						} else {
							f = 3;
						}
					}
					if (y >= py - d) { // 往下走反弹
						if (px < x + d / 2 && x + d / 2 < px + pw) {
							if (f == 3) {
								f = 0;
							} else {
								f = 1;
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
							int r = (int) (Math.random() * 256);
							int g = (int) (Math.random() * 256);
							int b = (int) (Math.random() * 256);
							ballColor = new Color(r, g, b);
							bgColor = new Color(b, r, g);
						} else { // 没接到球
							flag = true;
							// 让画布知道你改了flag这个值
							repaint();// 重新画
							// 线程类的对象
							this.stop(); // 游戏结束
						}
					}
					if (x <= 0) { // 往左走反弹
						if (f == 0) {
							f = 1;
						} else {
							f = 2;
						}
					}
					if (x >= 600 - d) { // 往右边走反弹
						if (f == 2) {
							f = 3;
						} else {
							f = 0;
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
