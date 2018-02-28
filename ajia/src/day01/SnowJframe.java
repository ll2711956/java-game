package day01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnowJframe {
	public static void main(String[] args) {
		// 创建一个JFrame（窗口）类的对象，名字叫做jf
		// ctrl+shift+O 自动引入想要的类
		JFrame jf = new JFrame();
		// 让窗口显示
		jf.setVisible(true);
		// 设置窗口的大小和位置
		jf.setBounds(50, 50, 800, 600);
		// 禁止窗口随意拖动
		jf.setResizable(false);
		// 设置窗口的标题
		jf.setTitle("下雪");
		// 设置窗口程序点击X关闭
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗口居中
		jf.setLocationRelativeTo(null);
		// 创建出画布类的对象
		SnowJpanel sj = new SnowJpanel();
		// 将画布放到窗体上
		jf.add(sj);
		// 将移动的雪放到画布上
		sj.moveSnow();
	}
}

// 继承(extends):代码不用自己写，直接拿来用
class SnowJpanel extends JPanel {
	// 变量:存储数据的地方,代词,指代你存的那个数据
	int[] x = new int[500];
	int[] y = new int[500];

	// 构造方法:程序的初始化操作
	// 构造方法:
	// public+当前类的名称(参数){
	// 你想干嘛
	// }
	// 自定义方法: public void 方法名称(参数){}
	public SnowJpanel() {
		for (int i = 0; i < x.length; i++) {
			x[i] = (int) (Math.random() * 800);
			y[i] = (int) (Math.random() * 600);
		}
	}

	@Override
	// 画画的英文：paint alt+/
	public void paint(Graphics g) {
		// 执行父类的paint方法
		super.paint(g);
		setBackground(Color.BLACK);// 设置背景色为黑色
		g.setColor(Color.WHITE);// 设置画笔颜色为白色
		Font f = new Font("黑体", Font.BOLD, 32);// 创建字体对象
		g.setFont(f);// 设置字体大小
		for (int i = 0; i < x.length; i++) {
			g.drawString("*", x[i], y[i]);
		}
	}

	// 方法：能干什么事儿 参数：干什么事用到的东西
	// 自定义方法 public void 方法名称(参数){}
	public void moveSnow() {// 自定义方法去创建一条线程
		new Thread() {// 匿名内部类的方式去创建一条线程
			public void run() {// 核心方法---->一定要有的方法
				while (true) {
					for (int i = 0; i < y.length; i++) {
						y[i]++;
						if (y[i] >= 600) {// 如果
							y[i] = 0;
						}
					}
					repaint();// 一直画画布
					try {// 异常捕获的机制:帮助我们找错
							// 因为CPU运行速度太快，只能让线程慢下来
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();

	}
}
