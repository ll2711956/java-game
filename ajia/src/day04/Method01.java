package day04;

public class Method01 {
     public static void main(String[] args) {
		new Food();
		Food f = new Food();
		f.fish();
	}
}
class Food{
	public Food(){
		System.out.println("�����ȸ���");
	}
	public void fish(){
		System.out.println("�����");
	}
}
