package day02;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MoonJFrame {
	public static void main(String[] args) {
        JFrame jf = new JFrame();
        jf.setVisible(true);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setTitle("ÔÂÊ³");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setSize(800,800);
        MoonJPanel mj = new MoonJPanel();
        jf.add(mj);
        mj.movemoon();
	}
}
class MoonJPanel extends JPanel{
	int a = 580; int b =360;
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		super.paint(g);
		setBackground(Color.blue);
		g.setColor(Color.yellow);
		g.fillOval(280, 360, 100, 100);
		g.setColor(Color.black);
		g.fillOval(a, b, 100,100);		
	}
	public void movemoon(){
		new Thread(){
			public void run(){
				while(true){
					a--;
					if(a<=0){
						a=800;
					}
					repaint();
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
}
