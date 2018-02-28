package day04;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Ball extends JFrame{
    public Ball(){
    	this.setVisible(true);
    	this.setResizable(false);
    	this.setBounds(50,50,400,600);
    	this.setTitle("玩个球");
    	this.setLocationRelativeTo(null);
    	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	BallJPanel03 bj3=new BallJPanel03();
    	this.add(bj3);
    	bj3.moveball();
    }
    public static void main(String[] args) {
		new Ball();//构造方法如何执行,在创建类的对象的时候就开始执行了
	}
}
class BallJPanel03 extends JPanel implements MouseMotionListener{
	int x=120;int y=420;int d=68;int f=3;int px=120;int py=550;int pw=150;
	boolean flag=false;
	public BallJPanel03(){
		addMouseMotionListener(this);
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.fillOval(x, y, d, d);
		Graphics2D g2=(Graphics2D) g;
		g2.setStroke(new BasicStroke(8));
		g2.drawLine(px, py, px+pw, py);
	}
	public void moveball(){
		new Thread(){
			public void run(){
				while(true){
					if(f==0){
						x--;y--;
					}
					if(f==1){
						x++;y--;
					}
					if(f==2){
						x++;y++;
					}
					if(f==3){
						x--;y++;
					}
					if(x<=0){ // 往左
						if(f==0){
							f=1;
						}else{
							f=2;
						}
					}
					if(x>=400-d){ // 往右
						if(f==1){
							f=0;
						}else{
							f=3;
						}
					}
					if(y<=0){ // 往上
						if(f==1){
							f=2;
						}else{
							f=3;
						}
					}
					if(y>=py-d){ //往下
					if(x+d/2>px&&x+d/2<px+pw){
						if(f==2){
							f=1;
						}else{
							f=0;
						}
					}else{
						this.stop();
					}
					}
					repaint();
					try {
						Thread.sleep(8);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		px=e.getX()-pw/2;
	}
}