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
		jf.setTitle("ѩ��");
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);// ���ô��ھ���
		SnowJPanel05 sj05 = new SnowJPanel05();// ʵ��������
		jf.add(sj05);// ���÷���
		sj05.movesnow();// �����ƶ���ѩ����
	}
}

class SnowJPanel05 extends JPanel {
	int x[] = new int[100];
	int y[] = new int[100];

	// ���췽�� public ��ǰ������Ҫ��ȫһ��(){}
	public SnowJPanel05(){
		for (int i = 0; i < x.length; i++) {
			x[i]=(int) (Math.random()*700);
			y[i]=(int) (Math.random()*700);
		}
	}
	@Override
	// �������ܸ�ʲô�¶�       ��������ʲô���õ��Ķ���
	// �Զ��巽�� public void ��������(����){}
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		setBackground(Color.DARK_GRAY);
		g.setColor(Color.pink);
		Font f = new Font("����", Font.ROMAN_BASELINE, 36);
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
