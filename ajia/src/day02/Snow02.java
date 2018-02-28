package day02;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snow02 {
	public static void main(String[] args) {
		JFrame jf = new JFrame();
		jf.setVisible(true);
		jf.setResizable(false);
		jf.setBounds(60, 60, 700, 700);
		jf.setTitle("雪花");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);// 设置窗口居中
		SnowJPanel05 sj05 = new SnowJPanel05();// 实例化对象
		jf.add(sj05);// 调用方法
		sj05.movesnow();// 调用移动的雪方法
	}
}

class SnowJPanel05 extends JPanel {
	int x[] = new int[100];
	int y[] = new int[100];

	// 构造方法 public 当前类名称要完全一样(){}
	public SnowJPanel05(){
		for (int i = 0; i < x.length; i++) {
			x[i]=(int) (Math.random()*700);
			y[i]=(int) (Math.random()*700);
		}
	}
	@Override
	// 方法：能干什么事儿       参数：干什么事用到的东西
	// 自定义方法 public void 方法名称(参数){}
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		setBackground(Color.DARK_GRAY);
		g.setColor(Color.pink);
		Font f = new Font("黑体", Font.ROMAN_BASELINE, 36);
		g.setFont(f);
		for (int i = 0; i < x.length; i++) {
			g.drawString("*", x[i], y[i]);
		}
	}

	public void movesnow() {
		new Thread() {
			public void run() {
				while (true) {
					for (int i = 0; i < y.length; i++) {
						y[i]++;
						if (y[i] >= 700) {
							y[i] = 0;
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
}
