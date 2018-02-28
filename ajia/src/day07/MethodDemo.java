package day07;

public class MethodDemo {
	public static void main(String[] agrs) {
           MethodDemo md=new MethodDemo();
           md.show();//无参数无返回类型,直接点儿
           md.action(18, 18.8);//有参数无返回类型
           int a3=md.yunsuan();
           System.out.println(a3);
           String a4=md.play("球球");//有参数有返回值
           System.out.println(a4);
	}
	//1.无参数无返回值
	public void show(){
		System.out.println("今天天气还没回温");
	}
	//2.有参数无返回值
	public void action(int a, double a1){
		System.out.println(a+a1);
	}
	//3.无参数有返回值:3个地方返回的类型一样
	public int yunsuan(){
		return 8;
	}
	//4.有参数有返回值
	public String play(String thing){
		return "玩球球";
	}
	
}
