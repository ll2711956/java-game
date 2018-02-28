package day01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SnowJframe {
	public static void main(String[] args) {
		// ����һ��JFrame�����ڣ���Ķ������ֽ���jf
		// ctrl+shift+O �Զ�������Ҫ����
		JFrame jf = new JFrame();
		// �ô�����ʾ
		jf.setVisible(true);
		// ���ô��ڵĴ�С��λ��
		jf.setBounds(50, 50, 800, 600);
		// ��ֹ���������϶�
		jf.setResizable(false);
		// ���ô��ڵı���
		jf.setTitle("��ѩ");
		// ���ô��ڳ�����X�ر�
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���ô��ھ���
		jf.setLocationRelativeTo(null);
		// ������������Ķ���
		SnowJpanel sj = new SnowJpanel();
		// �������ŵ�������
		jf.add(sj);
		// ���ƶ���ѩ�ŵ�������
		sj.moveSnow();
	}
}

// �̳�(extends):���벻���Լ�д��ֱ��������
class SnowJpanel extends JPanel {
	// ����:�洢���ݵĵط�,����,ָ�������Ǹ�����
	int[] x = new int[500];
	int[] y = new int[500];

	// ���췽��:����ĳ�ʼ������
	// ���췽��:
	// public+��ǰ�������(����){
	// �������
	// }
	// �Զ��巽��: public void ��������(����){}
	public SnowJpanel() {
		for (int i = 0; i < x.length; i++) {
			x[i] = (int) (Math.random() * 800);
			y[i] = (int) (Math.random() * 600);
		}
	}

	@Override
	// ������Ӣ�ģ�paint alt+/
	public void paint(Graphics g) {
		// ִ�и����paint����
		super.paint(g);
		setBackground(Color.BLACK);// ���ñ���ɫΪ��ɫ
		g.setColor(Color.WHITE);// ���û�����ɫΪ��ɫ
		Font f = new Font("����", Font.BOLD, 32);// �����������
		g.setFont(f);// ���������С
		for (int i = 0; i < x.length; i++) {
			g.drawString("*", x[i], y[i]);
		}
	}

	// �������ܸ�ʲô�¶� ��������ʲô���õ��Ķ���
	// �Զ��巽�� public void ��������(����){}
	public void moveSnow() {// �Զ��巽��ȥ����һ���߳�
		new Thread() {// �����ڲ���ķ�ʽȥ����һ���߳�
			public void run() {// ���ķ���---->һ��Ҫ�еķ���
				while (true) {
					for (int i = 0; i < y.length; i++) {
						y[i]++;
						if (y[i] >= 600) {// ���
							y[i] = 0;
						}
					}
					repaint();// һֱ������
					try {// �쳣����Ļ���:���������Ҵ�
							// ��ΪCPU�����ٶ�̫�죬ֻ�����߳�������
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
