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

//1.�̳�JFrame  2.��������ĸ��д��ctrl+shift+o
public class BallJFrame extends JFrame {
	// 3.���幹�췽��
	/*
	 * public + ��ǰ�������(){ ��ô��ô�� }
	 */
	public BallJFrame() {
		// ���ô��ڵ�����
		this.setTitle("������");// ���ñ���
		this.setVisible(true);// �ô�����ʾ
		this.setResizable(false);// ��ֹ���ڱ���С
		this.setBounds(50, 50, 400, 600);// ���ô��ڵĴ�С��λ��
		this.setLocationRelativeTo(null);// ���ô��ھ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���X��Ĭ�Ϲر�
		BallJPanel bj = new BallJPanel();
		this.add(bj);
		bj.moveball();
	}

	public static void main(String[] args) {
		// ���ִ�й��췽��:����һ����Ķ����ʱ���ִ���˹��췽��
		new BallJFrame();
	}
}

// ��һ�����ļ���,д��������߶�����ʱ��,һ��Ҫע����Ĵ�����,������Ĵ���������д
// JFrame�Ǵ����� JPanel�ǻ�����
// ����������ʱ��,����֪��:�������־�֪�����Ǹ�ɶ
class BallJPanel extends JPanel implements MouseMotionListener {
	// ��������ֱ��ʹ������--->Ҫʹ�ñ�������ʽ,
	// ����һ��Ҫд����Ĵ���������,�����Ĵ���������
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
	// ����--->��������--->������������,������������
	// ���������������,���ҵȺ�,�Ⱥ�ǰ���Ǹ����Ǳ�����,��Ϳ���������ǰ�����ĸ
	// JFrame jf = new JFrame();jfΪ������,ǰ���Ǹ�������������

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
		g.setFont(new Font("΢���ź�", Font.BOLD, 28));
		g.drawString("�÷�:" + score, 20, 40);
		g.setColor(Color.red);
		g.setFont(new Font("����", Font.ROMAN_BASELINE, 55));
		if (flag) {
			g.drawString("GAME_OVER", 70, 300);
		}
	}
	/*
	 * �Զ��巽���ĸ�ʽ
	 * ����:���������������֪����������ܸ�ʲô�¶�
	 * ����:���ݲ���������֪����������Ǹ�����¶��õ��Ķ���
	 * public void ��������(����){
	 * 
	 * }
	 */
	// �Զ���һ�������������߳�,�����ƶ���
	public void moveball() {
		// 1.�½�һ���߳�
		new Thread() {
			// 3.��CPU֪������̵߳�������ʲô-->ִ��run�������������
			public void run() {
				// ����-->��������̸߳�ʲô
				while (true) { // һ������һ����ѭ��
					//�ġ���repaint����д����Ҫд���߼�����
					//==�ж����������Ƿ����
					if (f == 0) {//д����ߴ����������س�
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
					if (x <= 0) { // ��
						if (f == 0) {
							f = 1;
						} else {
							f = 2;
						}
					}
					if (x >= 400 - d) {// ��
						if (f == 1) {
							f = 0;
						} else {
							f = 3;
						}
					}
					if (y <= 0) {// ��
						if (f == 0) {
							f = 3;
						} else {
							f = 2;
						}
					}
					if (y >= py - d) {// ��
						if (x + d / 2 > px && x + d / 2 < px + pw) {
							//���Բ������Ҫ���ں�����X����,С�ں���ұ�X����
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
						} else {//û�ӵ���
							flag = true;
							repaint();
							this.stop();//���߳̽�������״̬
						}

					}
					// �ġ���repaint����д����Ҫд���߼�����
					repaint();// �����ػ�
					try {
						Thread.sleep(10);// �������߳����ߣ�������һ��
						// 4.���߳̽�������״̬
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();// 2.���߳̽��뵽������״̬
	}

	@Override
	public void mouseDragged(MouseEvent e) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		px = e.getX() - pw / 2;//������ƶ�����˵��е�
	}
}
