package day07;

public class MethodDemo {
	public static void main(String[] agrs) {
           MethodDemo md=new MethodDemo();
           md.show();//�޲����޷�������,ֱ�ӵ��
           md.action(18, 18.8);//�в����޷�������
           int a3=md.yunsuan();
           System.out.println(a3);
           String a4=md.play("����");//�в����з���ֵ
           System.out.println(a4);
	}
	//1.�޲����޷���ֵ
	public void show(){
		System.out.println("����������û����");
	}
	//2.�в����޷���ֵ
	public void action(int a, double a1){
		System.out.println(a+a1);
	}
	//3.�޲����з���ֵ:3���ط����ص�����һ��
	public int yunsuan(){
		return 8;
	}
	//4.�в����з���ֵ
	public String play(String thing){
		return "������";
	}
	
}
