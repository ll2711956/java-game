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
	// ���췽�����ڳ�ʼ����Ϸ
	public CharGame() {
		t=new Timee();
		timer=new Timer();
		this.setTitle("����ĸ");
		this.setBounds(50, 50, 800, 600);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		// ����CharJPanel��Ķ���
		CharJPanel cp = new CharJPanel(t);
		// ��������Ķ���ŵ�������
		this.add(cp);
		// ��moveChar�����ŵ�������
		cp.moveChar();
	}

	public static void main(String[] args) {
		// ���췽����ε���,�ڴ��������Ķ����ʱ���ִ����
		new CharGame();
		//������ʱ�����߳�
				timer.schedule(t, new Date(), 1000);
	}
}

// ����һ����ĸ������ �̳�JPanel
class CharJPanel extends JPanel implements KeyListener {
	int score = 0;
	Timer timer;
	int second = 60;
	// ����һ������װ10����ĸ������
	MyChar[] mc = new MyChar[10];

	// ���컭����������ÿ����ĸ�ķ���
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
			// ���������еĶ���
			mc[i] = new MyChar(x, y, a, speed, CharColor, size);
		}
		this.timer=t;
	}

	@Override
	// ����һ�����ʷ���
	public void paint(Graphics g) {
		super.paint(g);
		for (int i = 0; i < mc.length; i++) {
			mc[i].drawChar(g);// ���������е�ÿ����ĸ
		}
		g.setColor(Color.BLACK);
		g.setFont(new Font("����", Font.BOLD, 50));
		g.drawString("�÷�: " + score, 50, 50);
		g.drawString("����ʱ:"+timer.second,50,200);
	}


	public void moveChar() {
		new Thread() {// �߳�
			public void run() {// ���߳���������
				while (true) {
					for (int i = 0; i < mc.length; i++) {
						mc[i].move();// �������е�ÿ����ĸ������
						if (mc[i].y >= 580) {
							mc[i].addChar();
							score -= 20;
						}
					}
					repaint();// һֱ�������ж�������Ч��
					try {
						Thread.sleep(10);// CPU�����ٶȹ���,��������
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