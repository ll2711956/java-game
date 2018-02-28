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

//继承窗口类,就变成了窗口类的儿子
public class Soccer extends JFrame {
	// Soccer这个类的构造方法
	public Soccer() {
		// this指代当前类自己的对象
		this.setTitle("玩个球");// 设置标题
		this.setVisible(true);// 让窗口显示
		this.setResizable(false);// 禁止变大变小
		this.setBounds(50, 50, 400, 600);// 设置大小
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点击X号默认关闭
		SoccerJPanel sj = new SoccerJPanel();// 创建出画布类的对象
		this.add(sj);
		sj.moveball();
	}

	/* 主线程 */
	// 构造方法如何执行? 创建某个类的对象的时候就执行了
	public static void main(String[] args) {
		new Soccer();// 调用Soccer这个类的构造方法
	}
}

// 看到首字母为大写的就ctrl+shift+o
// 没事儿就按alt+/
class SoccerJPanel extends JPanel implements MouseMotionListener {
	int x = 100;
	int y = 200;
	int d = 80;
	// 定义出横杆的坐标 pw横杆的长度
	int px = 100;
	int py = 500;
	int pw = 150;
	// 定义一个变量用来控制球的移动方向
	int f = 3;
	int speed = 1;// 定义球的速度
	int score = 0;// 得分
	boolean flag = false;// 控制游戏结束的
	Color ballColor;// 球的颜色
	Color bgColor;// 背景色
	// 在游戏一开始的时候就开始监听(初始化)

	public SoccerJPanel() {
		// 当前类的对象
		addMouseMotionListener(this);
	}

	// 作用域就是大括号{}
	// Java当中尽量不要直接使用数字,而应该使用变量的形式
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(bgColor);
		g.setColor(ballColor);
		g.fillOval(x, y, d, d);
		// 基本数据类型的大小比较,通过字节的大小
		// 4字节 < 8字节
		// int a=(int) (Math.random()*100);
		// 强制类型转换---->等号左右两边的大小不一样, 小=(小)大
		// 基本数据类型通过字节比大小 引用类型通过父子类比大小
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(5));// 把画笔变粗
		g.setColor(Color.BLACK);
		g.drawLine(px, py, px + pw, py);
		// 得分
		g.setColor(Color.blue);
		g.setFont(new Font("微软雅黑", Font.BOLD, 30));
		g.drawString("得分:" + score, 20, 50);
		g.setColor(Color.red);
		if (flag) {
			g.drawString("GAME_OVER", 120, 300);
		}
	}

	// 自定义一个方法用来创建线程
	public void moveball() {
		new Thread() {
			public void run() {
				// 创建循环
				while (true) {// 定义左上走
					if (f == 0) {
						x -= speed;
						y -= speed;
					}
					if (f == 1) {// 定义右上走
						x += speed;
						y -= speed;
					}
					if (f == 2) {// 定义右下走
						x += speed;
						y += speed;
					}
					if (f == 3) {// 定义左下走
						x -= speed;
						y += speed;
					}
					if (y <= 0) { // 上
						if (f == 0) {
							f = 3;
						} else {
							f = 2;
						}
					}
					if (y >= py - d) { // 下
						if (px < x + d / 2 && x + d / 2 < px + pw) { // 接到球
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
							// 变色
							int r = (int) (Math.random() * 256);
							int g = (int) (Math.random() * 256);
							int b = (int) (Math.random() * 256);
							ballColor = new Color(r, g, b);
							bgColor = new Color(b, r, g);
						} else {// 没接到球
							// 让画布知道你改了flag这个值
							flag = true;
							repaint();// 再画一次
							// 线程类的对象
							this.stop(); // 游戏结束
						}
					}
					if (x <= 0) { // 左
						if (f == 0) {
							f = 1;
						} else {
							f = 2;
						}
					}
					if (x >= 400 - d) { // 右
						if (f == 1) {
							f = 0;
						} else {
							f = 3;
						}
					}
					// 重复画
					repaint();
					// 让线程慢一点
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	// 拖拽
	@Override
	public void mouseDragged(MouseEvent e) {

	}

	// 移动
	@Override // 鼠标
	public void mouseMoved(MouseEvent e) {
		px = e.getX() - pw / 2;// 把横杆的坐标换成鼠标的坐标
	}
}
