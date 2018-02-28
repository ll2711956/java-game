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

// 继承JFrame这个类窗口
public class Ball extends JFrame {
	// Ball这个类的构造方法 ，方法名称必须与类名称相同
	public Ball() {
		this.setTitle("玩球球");
		this.setVisible(true);
		this.setResizable(false);
		this.setSize(600, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		BallJPanel1 bj = new BallJPanel1();
		this.add(bj);
		bj.moveball();
	}

	// 主线程
	// 构造方法在创建某各类的对象的时候就执行
	public static void main(String[] args) {
		new Ball();
	}
}

class BallJPanel1 extends JPanel implements MouseMotionListener {
	int x = 120;
	int y = 180;
	int d = 88;
	int f = 0;
	int px = 200;
	int py = 700;
	int pw = 180;
	int score = 0;
	boolean flag = false;
	int speed=1;
	Color ballcolor;

	public BallJPanel1() {
		addMouseMotionListener(this);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(ballcolor);
		g.fillOval(x, y, d, d);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(8));
		g2.setColor(Color.BLACK);
		g2.drawLine(px, py, px + pw, py);
		g.setColor(Color.ORANGE);
		g.setFont(new Font("黑体", Font.BOLD, 42));
		g.drawString("得分:" + score, 20, 50);
		g.setColor(Color.RED);
		g.setFont(new Font("黑体", Font.BOLD, 50));
		if (flag) {
			g.drawString("GAME_OVER", 160, 400);
		}
	}

	public void moveball() {
		new Thread() {
			public void run() {
				while (true) {
					if (f == 0) {
						x-=speed;
						y-=speed;
					}
					if (f == 1) {
						x+=speed;
						y-=speed;
					}
					if (f == 2) {
						x+=speed;
						y+=speed;
					}
					if (f == 3) {
						x-=speed;
						y+=speed;
					}
					if (y <= 0) { // 往上走
						if (f == 0) {
							f = 3;
						} else {
							f = 2;
						}
					}
					if (y >= py - d) { // 往下走
						if (x + d / 2 > px && x + d / 2 < px + pw) {
							if (f == 2) {
								f = 1;
							} else {
								f = 0;
							}
							speed++;
							if(speed>=10){
								speed=10;
							}
							score += 5;
							if (score % 5 == 0) {
								pw += 10;
							}
							pw -= 5;
							if (pw >= 220) {
								pw = 220;
							}
							int r =(int) (Math.random()*256);
							int g =(int) (Math.random()*256);
							int b =(int) (Math.random()*256);
							ballcolor =new Color(r,g,b);
						} else {
							flag = true;
							repaint();
							this.stop();

						}
					}
					if (x <= 0) { // 往左走
						if (f == 0) {
							f = 1;
						} else {
							f = 2;
						}
					}
					if (x >= 600 - d) { // 往右走
						if (f == 1) {
							f = 0;
						} else {
							f = 3;
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
		px = e.getX() - pw / 2;// 让鼠标移动到横杆的中点
	}
}
