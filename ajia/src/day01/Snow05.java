package day01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Snow05 {
	public static void main(String[] args) {
		// ����һ��JFrame�����ڣ���Ķ������ֽ���jf
		JFrame jf = new JFrame();
		// ���ô��ڿ��ӻ�
		jf.setVisible(true);
		// ���ô��ڴ�С
		jf.setBounds(60, 60, 780, 820);
		// ���ô��ڲ�������Ķ���С
		jf.setResizable(false);
		// ���ñ���
		jf.setTitle("��ѩ");
		// ���õ��X�ر�
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
		Font f = new Font("����",Font.BOLD,88);
		g.setFont(f);
		g.drawString("*", 286, 138);
	}
}



