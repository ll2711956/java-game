package chess;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ChessJPanel extends JPanel {
	static final int ROW_NUMBER = 18;// ��
	static final int COL_NUMBER = 18;// ��
	static final int ROW_SPACE = 30;// �м��
	static final int COL_SPACE = 30;// �м��
	static final int UP_SPACE = 30;// �ϼ��
	static final int LEFT_SPACE = 30;// ����
	int[][] chess = new int[ROW_NUMBER][COL_NUMBER];// ����
	static final int CHESS_RADIO = 13;// �������������̵Ľ�����,Ҫ��ȥ����ͼƬ�İ뾶
	//����һ�������������沽��
	ArrayList<String> stepchess=new ArrayList<String>();
	// ��ǵ�����(���鵱�е��±�)
	int boradX = -1;
	int boradY = -1;

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(new ImageIcon("case06/panel.png").getImage(), 0, 0, this);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));

		for (int i = 0; i < ROW_NUMBER; i++) {
			// g.drawLine(30, 30*1, 30+30*17, 30*1);
			// g.drawLine(30, 30*2, 30+30*17, 30*2);
			// g.drawLine(30, 30*3, 30+30*17, 30*3);
			// g.drawLine(30, 30*4, 30+30*17, 30*4);
			// g.drawLine(30, 30*5, 30+30*17, 30*5);
			// g.drawLine(30, 30*6, 30+30*17, 30*6);
			g.drawLine(LEFT_SPACE, UP_SPACE + COL_SPACE * i, LEFT_SPACE + COL_SPACE * (COL_NUMBER - 1),
					UP_SPACE + COL_SPACE * i);
			// g.drawLine(30*1, 30, 30*1, 30+30*17);
			// g.drawLine(30*2, 30, 30*2, 30+30*17);
			// g.drawLine(30*3, 30, 30*3, 30+30*17);
			// g.drawLine(30*4, 30, 30*4, 30+30*17);
			// g.drawLine(30*5, 30, 30*5, 30+30*17);
			// g.drawLine(30*6, 30, 30*6, 30+30*17);
			g.drawLine(LEFT_SPACE + ROW_SPACE * i, UP_SPACE, LEFT_SPACE + ROW_SPACE * i,
					UP_SPACE + ROW_SPACE * (ROW_NUMBER - 1));
		}
		// ������:��ά�������ÿһ��ֵĬ��Ϊ0
		for (int i = 0; i < chess.length; i++) {
			for (int j = 0; j < chess.length; j++) {
				if (chess[i][j] == 0) {
					continue;
				} else if (chess[i][j] == 1) {// ����chess[i][j]==1 ������
					g.drawImage(new ImageIcon("case06/black.png").getImage(), j * COL_SPACE + LEFT_SPACE - CHESS_RADIO,
							i * ROW_SPACE + UP_SPACE - CHESS_RADIO, this);
				} else if (chess[i][j] == 2) {// ����chess[i][j]==2 ������
					g.drawImage(new ImageIcon("case06/white.png").getImage(), j * COL_SPACE + LEFT_SPACE - CHESS_RADIO,
							i * ROW_SPACE + UP_SPACE - CHESS_RADIO, this);
				}
			}
		}

		if (boradX != -1) {// ��Ϊ-1��ʾԲ�ĵ����걻�޸���
			g.setColor(Color.RED);
			g.fillOval(boradY * COL_SPACE + LEFT_SPACE - 5, boradX * ROW_SPACE + UP_SPACE - 5, 10, 10);
		}
	}

	// �ж���Ӯ
	public boolean isUpAndDown(int i, int j) {
		int i1 = i - 1;
		// ����������һ��//�ж��Ƿ񵽶�����
		while (true) {
			if (i1 < 0 || chess[i1][j] != chess[i][j]) {
				break;// ����ѭ��
			}
			i1--;
		} // ���¼���
		i1++;
		int count = 0;
		while (true) {
			if (i1 >= ROW_NUMBER || chess[i1][j] != chess[i][j]) {
				break;
			}
			i1++;
			count++;// ��ɫһ�������
		}
		return count >= 5;
	}

	public boolean isLeftAndRight(int i, int j) {
		int j1 = j - 1;
		while (true) {
			if (j1 < 0 || chess[i][j1] != chess[i][j]) {
				break;
			}
			j1--;
		} // ���Ҽ���
		j1++;
		int count = 0;
		while (true) {
			if (j1 >= COL_NUMBER || chess[i][j1] != chess[i][j]) {
				break;
			}
			j1++;
			count++;// ��ɫһ������
		}
		return count >= 5;
	}

	public boolean isLeftUpAndRightDown(int i, int j) {
		int i1 = i - 1;
		int j1 = j - 1;
		while (true) {
			if (i1 < 0 || j1 < 0 || chess[i1][j1] != chess[i][j]) {
				break;
			}
			i1--;
			j1--;
		} // �����¼���
		i1++;
		j1++;
		int count = 0;
		while (true) {
			if (i1 >= ROW_NUMBER || j1 >= COL_NUMBER || chess[i1][j1] != chess[i][j]) {
				break;
			}
			i1++;
			j1++;
			count++;
		}
		return count >= 5;
	}

	public boolean isRightUpAndLeftDown(int i, int j) {
		int i1 = i - 1;
		int j1 = j + 1;
		while (true) {
			if (i1 < 0 || j1 >= COL_NUMBER || chess[i1][j1] != chess[i][j]) {
				break;
			}
			i1--;
			j1++;
		} // �����¼���
		i1++;
		j1--;
		int count = 0;
		while (true) {
			if (j1 < 0 || i1 >= ROW_NUMBER || chess[i1][j1] != chess[i][j]) {
				break;
			}
			i1++;
			j1--;
			count++;
		}
		return count >= 5;
	}
	//�����ļ�
	public void saveFile(File f){
		PrintWriter out;//���������
		try {
			out=new PrintWriter(
					new OutputStreamWriter(//�ֽ������
									//�ļ������ 
							new FileOutputStream(f)));
			for (int i = 0; i < stepchess.size(); i++) {
				out.println(stepchess.get(i));
				out.flush();//��������������һ�������
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	//�����ļ�
	public void loadFile(File f){
		BufferedReader in;//����������
		try {
			in=new BufferedReader(
					new InputStreamReader(
							new FileInputStream(f)));
			String line="";
			while((line=in.readLine())!=null){
				stepchess.add(line);
				parseDate(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void parseDate(String strs) {
		String []str=strs.split(":");
		int i=Integer.parseInt(str[0]);
		int j=Integer.parseInt(str[1]);
		int chessColor=Integer.parseInt(str[2]);
		boradX=i;
		boradY=j;
		chess[i][j]=chessColor;
		repaint();
	}
}
