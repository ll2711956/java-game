package day01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snow05 {
	public static void main(String[] args) {
		// 创建一个JFrame（窗口）类的对象，名字叫做jf
		JFrame jf = new JFrame();
		// 设置窗口可视化
		jf.setVisible(true);
		// 设置窗口大小
		jf.setBounds(60, 60, 780, 820);
		// 设置窗口不能随意改动大小
		jf.setResizable(false);
		// 设置标题
		jf.setTitle("下雪");
		// 设置点击X关闭
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SnowJpanel4 sj4 = new SnowJpanel4();
		jf.add(sj4);
	}

}

class SnowJpanel4 extends JPanel{
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		setBackground(Color.pink);
		g.setColor(Color.YELLOW);
		Font f = new Font("隶书",Font.BOLD,88);
		g.setFont(f);
		g.drawString("*", 286, 138);
	}
}



