package day09;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class CharGame extends JFrame {
	static Timee t;
	static Timer timer;
	// 构造方法用于初始化游戏
	public CharGame() {
		t=new Timee();
		timer=new Timer();
		this.setTitle("接字母");
		this.setBounds(50, 50, 800, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		// 创建CharJPanel类的对象
		CharJPanel cp = new CharJPanel(t);
		// 将画布类的对象放到窗口上
		this.add(cp);
		// 将moveChar方法放到画布上
		cp.moveChar();
	}

	public static void main(String[] args) {
		// 构造方法如何调用,在创建这个类的对象的时候就执行了
		new CharGame();
		//启动定时器的线程
				timer.schedule(t, new Date(), 1000);
	}
}

// 创建一个字母画布类 继承JPanel
class CharJPanel extends JPanel implements KeyListener {
	int score = 0;
	Timer timer;
	int second = 60;
	// 定义一个可以装10个字母的数组
	MyChar[] mc = new MyChar[10];

	// 构造画布类数组中每个字母的方法
	public CharJPanel(Timee t) {
		for (int i = 0; i < mc.length; i++) {
			int x = (int) (Math.random() * 700);
			int y = -36;
			int speed = (int) (Math.random() * 6) + 2;
			int r = (int) (Math.random() * 256);
			int g = (int) (Math.random() * 256);
			int b = (int) (Math.random() * 256);
			Color CharColor = new Color(r, g, b);
			char a = (char) (Math.random() * 26 + 97);
			int size = (int) (Math.random() * 66 + 33);
			// 创建数组中的对象
			mc[i] = new MyChar(x, y, a, speed, CharColor, size);
		}
		this.timer=t;
	}

	@Override
	// 创建一个画笔方法
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < mc.length; i++) {
			mc[i].drawChar(g);// 画出数组中的每个字母
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font("黑体", Font.BOLD, 50));
		g.drawString("得分: " + score, 50, 50);
		g.drawString("倒计时:"+timer.second,50,200);
	}


	public void moveChar() {
		new Thread() {// 线程
			public void run() {// 让线程运行起来
				while (true) {
					for (int i = 0; i < mc.length; i++) {
						mc[i].move();// 让数组中的每个字母动起来
						if (mc[i].y >= 580) {
							mc[i].addChar();
							score -= 20;
						}
					}
					repaint();// 一直画才能有动起来的效果
					try {
						Thread.sleep(10);// CPU运行速度过快,让其休眠
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int index = -1;
		for (int i = 0; i < mc.length; i++) {
			if (e.getKeyChar() == mc[i].a) {
				index = i;
			}
		}
		if(index!=-1){
			mc[index].addChar();
			score+=10;
		}else{
			score-=20;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}
}