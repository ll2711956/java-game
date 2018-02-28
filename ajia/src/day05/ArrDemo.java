package day05;

import java.awt.Color;

public class ArrDemo {

	public static void main(String[] args) {
        //定义了一个能够存储20个球的数组 
		Ball[] balls=new Ball[20];
         for (int i = 0; i < balls.length; i++) {
			int d=(int) (Math.random()*100+20);
			int speed=(int) (Math.random()*10+2);
			int direction =(int) (Math.random()*4);
			int x=(int) (Math.random()*800);
			int y=(int) (Math.random()*600);
			int r=(int) (Math.random()*256);
			int g=(int) (Math.random()*256);
			int b=(int) (Math.random()*256);
			Color ballColor=new Color(r,g,b);
            balls[i]=new Ball(d,ballColor,direction,speed,x,y);
		}
	}

}
