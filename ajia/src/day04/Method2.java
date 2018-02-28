package day04;

public class Method2 {
   public static void main(String[] args) {
	new Name();
	Name n = new Name();
	n.son();
}
}
class Name{
	public Name(){
		System.out.println("他是王麻子");
	}
	public void son(){
		System.out.println("他是龟儿子");
	}
}