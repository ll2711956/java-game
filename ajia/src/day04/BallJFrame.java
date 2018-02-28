package day04;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

//1.继承JFrame  2.看见首字母大写的ctrl+shift+o
public class BallJFrame extends JFrame {
	// 3.定义构造方法
	/*
	 * public + 当前类的名称(){ 怎么怎么干 }
	 */
	public BallJFrame() {
		// 设置窗口的属性
		this.setTitle("玩球球");// 设置标题
		this.setVisible(true);// 让窗口显示
		this.setResizable(false);// 禁止窗口变大变小
		this.setBounds(50, 50, 400, 600);// 设置窗口的大小和位置
		this.setLocationRelativeTo(null);// 设置窗口居中
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 点击X号默认关闭
		BallJPanel bj = new BallJPanel();
		this.add(bj);
		bj.moveball();
	}

	public static void main(String[] args) {
		// 如何执行构造方法:创建一个类的对象的时候就执行了构造方法
		new BallJFrame();
	}
}

// 在一个类文件中,写两个类或者多个类的时候,一定要注意类的大括号,出了类的大括号外面写
// JFrame是窗口类 JPanel是画布类
// 给类命名的时候,见名知意:看见名字就知道这是个啥
class BallJPanel extends JPanel implements MouseMotionListener {
	// 尽量避免直接使用数字--->要使用变量的形式,
	// 变量一定要写在类的大括号里面,方法的大括号外面
	int x = 120;
	int y = 220;
	int d = 80;
	int f = 0;
	int speed = 1;
	int px = 100;
	int py = 550;
	int pw = 180;
	int score = 0;
	boolean flag = false;
	Color bgColor;
	Color ballColor;
	// 变量--->数据类型--->基本数据类型,引用数据类型
	// 如何区分数据类型,先找等号,等号前面那个就是变量名,你就看变量名称前面的字母
	// JFrame jf = new JFrame();jf为变量名,前面那个就是引用类型

	public BallJPanel() {
		addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		setBackground(bgColor);
		g.setColor(ballColor);
		g.fillOval(x, y, d, d);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(8));
		g2.drawLine(px, py, px + pw, py);
		g.setColor(Color.ORANGE);
		g.setFont(new Font("微软雅黑", Font.BOLD, 28));
		g.drawString("得分:" + score, 20, 40);
		g.setColor(Color.red);
		g.setFont(new Font("黑体", Font.ROMAN_BASELINE, 55));
		if (flag) {
			g.drawString("GAME_OVER", 70, 300);
		}
	}
	/*
	 * 自定义方法的格式
	 * 方法:根据这个方法名称知道这个方法能干什么事儿
	 * 参数:根据参数的名称知道这个参数是干这个事儿用到的东西
	 * public void 方法名称(参数){
	 * 
	 * }
	 */
	// 自定义一个方法来创建线程,用来移动球
	public void moveball() {
		// 1.新建一个线程
		new Thread() {
			// 3.让CPU知道这个线程的任务是什么-->执行run方法里面的内容
			public void run() {
				// 任务-->想让这个线程干什么
				while (true) { // 一、创建一个死循环
					//四、在repaint上面写你想要写的逻辑代码
					//==判断两个数字是否相等
					if (f == 0) {//写完左边大括号立马按回车
						x -= speed;
						y -= speed;
					}
					if (f == 1) {
						x += speed;
						y -= speed;
					}
					if (f == 2) {
						x += speed;
						y += speed;
					}
					if (f == 3) {
						x -= speed;
						y += speed;
					}
					if (x <= 0) { // 左
						if (f == 0) {
							f = 1;
						} else {
							f = 2;
						}
					}
					if (x >= 400 - d) {// 右
						if (f == 1) {
							f = 0;
						} else {
							f = 3;
						}
					}
					if (y <= 0) {// 上
						if (f == 0) {
							f = 3;
						} else {
							f = 2;
						}
					}
					if (y >= py - d) {// 下
						if (x + d / 2 > px && x + d / 2 < px + pw) {
							//球的圆点坐标要大于横杆左边X坐标,小于横杆右边X坐标
							if (f == 2) {
								f = 1;
							} else {
								f = 0;
							}
							speed++;
							if (speed >= 10) {
								speed = 10;
							}
							score += 5;
							if (score % 5 == 0) {
								pw += 15;
							}
							pw -= 10;
							if (pw <= 180) {
								pw = 180;
							}
							int r = (int) (Math.random() * 256);
							int g = (int) (Math.random() * 256);
							int b = (int) (Math.random() * 256);
							bgColor = new Color(r, g, b);
							ballColor = new Color(b, r, g);
						} else {//没接到球
							flag = true;
							repaint();
							this.stop();//让线程进入死亡状态
						}

					}
					// 四、在repaint上面写你想要写的逻辑代码
					repaint();// 二、重画
					try {
						Thread.sleep(10);// 三、让线程休眠，运行慢一点
						// 4.让线程进入阻塞状态
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();// 2.让线程进入到可运行状态
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		px = e.getX() - pw / 2;//让鼠标移动到横杆的中点
	}
}
