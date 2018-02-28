package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

//�ӿ�����:ActionListener
public class ChessFrame extends JFrame implements ActionListener {
	// ���ֵ����� ����ͼƬ
	JLabel mainLabel = new JLabel(new ImageIcon("case06/back.png"));
	// 3.���ͼƬ
	JLabel player = new JLabel(new ImageIcon("case06/blackmsg.png"));
	JLabel comp = new JLabel(new ImageIcon("case06/whitemsg.png"));
	// ���û���,��ʼ,��С��,�رյȰ�ť
	JButton huiqi = new JButton(new ImageIcon("case06/huiqi.png"));
	JButton start = new JButton(new ImageIcon("case06/start.png"));
	JButton min = new JButton(new ImageIcon("case06/min.png"));
	JButton close = new JButton(new ImageIcon("case06/close.png"));
	// �����˵���Ҫ������
	JMenuBar bar;// ���߽�����ĳ������������Ҫ�Ų˵���
	JMenu game;// ����������������Ĳ˵�
	JMenuItem savegame, loadgame;// ���˵��������Ӳ˵�
	// ����������Ķ���
	ChessJPanel panel = new ChessJPanel();
	// �����������ӵ��Ⱥ�˳��
	boolean isBlack = false;
	// ����������Ϸ����Ӯ
	boolean isWin = false;

	public ChessFrame() {
		// �������ŵ�����ͼ��
		panel.setBounds(170, 85, 592, 592);
		panel.setOpaque(false);// ȥ�������İ׵�
		mainLabel.add(panel);
		// ���ò˵�������
		bar = new JMenuBar();
		bar.setBounds(10, 10, 100, 36);
		mainLabel.add(bar);
		// �������ڷ������˵�
		game = new JMenu();
		game.setIcon(new ImageIcon("case06/bar.png"));
		game.add(savegame = new JMenuItem("������Ϸ"));
		game.add(loadgame = new JMenuItem("������Ϸ"));
		bar.add(game);
		// 4.�������ͼƬ
		player.setBounds(30, 90, 110, 110);
		mainLabel.add(player);
		comp.setBounds(770, 510, 110, 110);
		mainLabel.add(comp);
		// ���û��尴ť
		huiqi.setBorderPainted(false);
		huiqi.setContentAreaFilled(false);
		huiqi.setBounds(30, 450, 100, 42);
		mainLabel.add(huiqi);
		// �������ð�ť
		start.setBorderPainted(false);
		start.setContentAreaFilled(false);
		start.setBounds(430, 10, 34, 34);
		mainLabel.add(start);
		// ������С����ť
		min.setBorderPainted(false);
		min.setContentAreaFilled(false);
		min.setBounds(830, 20, 14, 14);
		mainLabel.add(min);
		// ���ùرհ�ť
		close.setBorderPainted(false);
		close.setContentAreaFilled(false);
		close.setBounds(870, 20, 14, 14);
		mainLabel.add(close);
		// 2.������ͼ���ڴ�����
		this.add(mainLabel);
		this.setUndecorated(true);
		this.setBounds(50, 30, 900, 700);
		// 1.���ó������е�Сͼ��
		this.setIconImage(new ImageIcon("case06/icon.jpg").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

		// ���������������¼�ͬʱ��Ӽ���
		// MouseAdapter��������Լ�����ʵ����
		// MouseListener��MouseMotionListerner
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (isWin) {
					return;
				}
				// �������X,Y����
				int x = e.getX();
				int y = e.getY();
				// �����ĵ���д��� ������ת����������±�
				int i = Math.round((float) (y - ChessJPanel.LEFT_SPACE) / ChessJPanel.COL_SPACE);
				int j = Math.round((float) (x - ChessJPanel.UP_SPACE) / ChessJPanel.ROW_SPACE);
				panel.boradX = i;
				panel.boradY = j;
				// �ж��Ƿ�������
				if (panel.chess[i][j] != 0) {// ����˴�������
					JOptionPane.showMessageDialog(ChessFrame.this, "�˴�������,��ֹ����");
					return;// 1.����ֵ 2.��������Ĵ���ִ��
				}
				// ��Ŀ���㷨
				panel.chess[i][j] = isBlack ? 1 : 2;
				// ���ҵ������һ��֮��,�ͼ�¼����
				String step=i+":"+j+":"+(isBlack?1:2);
				System.out.println(step);
				//�����ϵ���ȥ��Ӳ���
				panel.stepchess.add(step);
				panel.repaint();
				// �ж���Ӯ
				if (panel.isUpAndDown(i, j) || panel.isLeftAndRight(i, j) || panel.isLeftUpAndRightDown(i, j)
						|| panel.isRightUpAndLeftDown(i, j)) {
					JOptionPane.showMessageDialog(ChessFrame.this, (isBlack ? "�ڷ�ʤ��" : "�׷�ʤ��"));
					isWin = true;
				}
				isBlack = !isBlack;
			}
		});

		// ����
		min.addActionListener(this);
		close.addActionListener(this);
		start.addActionListener(this);
		savegame.addActionListener(this);
		loadgame.addActionListener(this);
		huiqi.addActionListener(this);
	}

	public static void main(String[] args) {
		new ChessFrame();
	}
	//�ӿ�����:ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == min) {
			ChessFrame.this.setState(1);
		}
		if (e.getSource() == close) {
			System.exit(0);
		}
		if(e.getSource()==start){
			for (int i = 0; i < panel.chess.length; i++) {
				for (int j = 0; j < panel.chess.length; j++) {
					panel.chess[i][j]=0;
				}
			}
			panel.boradX=-1;
			panel.boradY=-1;
			isWin=false;
			panel.repaint();
		}
		if(e.getSource()==savegame){
			//1.����һ�������ļ��ĶԻ���
			JFileChooser jfc=new JFileChooser();
			jfc.showSaveDialog(this);
			//2.��ȡ�û�ѡ����ļ�
			File f=jfc.getSelectedFile();
			panel.saveFile(f);
		}
		if(e.getSource()==loadgame){
			JFileChooser jfc=new JFileChooser();
			jfc.showOpenDialog(this);
			//2.����û�ѡ����ļ�
			File f=jfc.getSelectedFile();
			panel.loadFile(f);
		}
		if (e.getSource()==huiqi) {
			ArrayList<String> step=panel.stepchess;
			if(step.size()==0){
				return;
			}
			String s=step.get(step.size()-1);
			//split����ֵΪString���͵�����
			String str[]=s.split(":");
			//���ַ���ת��Ϊ����Integer.parseInt
			int i=Integer.parseInt(str[0]);
			int j=Integer.parseInt(str[1]);
			//�����һ���Ѷ�ά�����ֵ��Ϊ0
			panel.chess[i][j]=0;
			isBlack=!isBlack;
			if(step.size()==1){
				panel.boradX=-1;
				panel.boradY=-1;
				panel.repaint();
				//ɾ�������е����һλ
				step.remove(step.size()-1);
				return;
			}
			//Ų����㵽�����ڶ�λ
			String hd=step.get(step.size()-2);
			String []strs=hd.split(":");
			panel.boradX=Integer.parseInt(strs[0]);
			panel.boradY=Integer.parseInt(strs[1]);
			//ɾ�������е����һλ
			step.remove(step.size()-1);
			panel.repaint();
		}
	}
}
