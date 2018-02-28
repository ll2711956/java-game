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

//接口名称:ActionListener
public class ChessFrame extends JFrame implements ActionListener {
	// 布局的容器 背景图片
	JLabel mainLabel = new JLabel(new ImageIcon("case06/back.png"));
	// 3.玩家图片
	JLabel player = new JLabel(new ImageIcon("case06/blackmsg.png"));
	JLabel comp = new JLabel(new ImageIcon("case06/whitemsg.png"));
	// 设置悔棋,开始,最小化,关闭等按钮
	JButton huiqi = new JButton(new ImageIcon("case06/huiqi.png"));
	JButton start = new JButton(new ImageIcon("case06/start.png"));
	JButton min = new JButton(new ImageIcon("case06/min.png"));
	JButton close = new JButton(new ImageIcon("case06/close.png"));
	// 创建菜单需要三个类
	JMenuBar bar;// 告诉界面在某个区域内我是要放菜单的
	JMenu game;// 这个才是我们真正的菜单
	JMenuItem savegame, loadgame;// 主菜单的两个子菜单
	// 创建画布类的对象
	ChessJPanel panel = new ChessJPanel();
	// 用来控制下子的先后顺序
	boolean isBlack = false;
	// 用来控制游戏的输赢
	boolean isWin = false;

	public ChessFrame() {
		// 将画布放到背景图上
		panel.setBounds(170, 85, 592, 592);
		panel.setOpaque(false);// 去掉画布的白底
		mainLabel.add(panel);
		// 设置菜单的区域
		bar = new JMenuBar();
		bar.setBounds(10, 10, 100, 36);
		mainLabel.add(bar);
		// 在区域内放入主菜单
		game = new JMenu();
		game.setIcon(new ImageIcon("case06/bar.png"));
		game.add(savegame = new JMenuItem("保存游戏"));
		game.add(loadgame = new JMenuItem("加载游戏"));
		bar.add(game);
		// 4.加载玩家图片
		player.setBounds(30, 90, 110, 110);
		mainLabel.add(player);
		comp.setBounds(770, 510, 110, 110);
		mainLabel.add(comp);
		// 设置悔棋按钮
		huiqi.setBorderPainted(false);
		huiqi.setContentAreaFilled(false);
		huiqi.setBounds(30, 450, 100, 42);
		mainLabel.add(huiqi);
		// 设置重置按钮
		start.setBorderPainted(false);
		start.setContentAreaFilled(false);
		start.setBounds(430, 10, 34, 34);
		mainLabel.add(start);
		// 设置最小化按钮
		min.setBorderPainted(false);
		min.setContentAreaFilled(false);
		min.setBounds(830, 20, 14, 14);
		mainLabel.add(min);
		// 设置关闭按钮
		close.setBorderPainted(false);
		close.setContentAreaFilled(false);
		close.setBounds(870, 20, 14, 14);
		mainLabel.add(close);
		// 2.将背景图放在窗口上
		this.add(mainLabel);
		this.setUndecorated(true);
		this.setBounds(50, 30, 900, 700);
		// 1.设置程序运行的小图标
		this.setIconImage(new ImageIcon("case06/icon.jpg").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

		// 给画布添加鼠标点击事件同时添加监听
		// MouseAdapter这个类它自己本身实现了
		// MouseListener和MouseMotionListerner
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (isWin) {
					return;
				}
				// 获得鼠标的X,Y坐标
				int x = e.getX();
				int y = e.getY();
				// 对鼠标的点进行处理 将像素转换成数组的下标
				int i = Math.round((float) (y - ChessJPanel.LEFT_SPACE) / ChessJPanel.COL_SPACE);
				int j = Math.round((float) (x - ChessJPanel.UP_SPACE) / ChessJPanel.ROW_SPACE);
				panel.boradX = i;
				panel.boradY = j;
				// 判断是否有棋子
				if (panel.chess[i][j] != 0) {// 如果此处有棋子
					JOptionPane.showMessageDialog(ChessFrame.this, "此处有棋子,禁止下棋");
					return;// 1.返回值 2.结束后面的代码执行
				}
				// 三目运算法
				panel.chess[i][j] = isBlack ? 1 : 2;
				// 当我的鼠标点击一下之后,就记录步数
				String step=i+":"+j+":"+(isBlack?1:2);
				System.out.println(step);
				//往集合当中去添加步数
				panel.stepchess.add(step);
				panel.repaint();
				// 判断输赢
				if (panel.isUpAndDown(i, j) || panel.isLeftAndRight(i, j) || panel.isLeftUpAndRightDown(i, j)
						|| panel.isRightUpAndLeftDown(i, j)) {
					JOptionPane.showMessageDialog(ChessFrame.this, (isBlack ? "黑方胜利" : "白方胜利"));
					isWin = true;
				}
				isBlack = !isBlack;
			}
		});

		// 监听
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
	//接口名称:ActionListener
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
			//1.弹出一个保存文件的对话框
			JFileChooser jfc=new JFileChooser();
			jfc.showSaveDialog(this);
			//2.获取用户选择的文件
			File f=jfc.getSelectedFile();
			panel.saveFile(f);
		}
		if(e.getSource()==loadgame){
			JFileChooser jfc=new JFileChooser();
			jfc.showOpenDialog(this);
			//2.获得用户选择的文件
			File f=jfc.getSelectedFile();
			panel.loadFile(f);
		}
		if (e.getSource()==huiqi) {
			ArrayList<String> step=panel.stepchess;
			if(step.size()==0){
				return;
			}
			String s=step.get(step.size()-1);
			//split返回值为String类型的数组
			String str[]=s.split(":");
			//将字符串转化为整数Integer.parseInt
			int i=Integer.parseInt(str[0]);
			int j=Integer.parseInt(str[1]);
			//悔棋第一步把二维数组的值变为0
			panel.chess[i][j]=0;
			isBlack=!isBlack;
			if(step.size()==1){
				panel.boradX=-1;
				panel.boradY=-1;
				panel.repaint();
				//删除集合中的最后一位
				step.remove(step.size()-1);
				return;
			}
			//挪动红点到倒数第二位
			String hd=step.get(step.size()-2);
			String []strs=hd.split(":");
			panel.boradX=Integer.parseInt(strs[0]);
			panel.boradY=Integer.parseInt(strs[1]);
			//删除集合中的最后一位
			step.remove(step.size()-1);
			panel.repaint();
		}
	}
}
