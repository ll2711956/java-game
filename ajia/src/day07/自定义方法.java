package day07;

public class 自定义方法 {

	public static void main(String[] args) {
		自定义方法 方法=new 自定义方法();
		方法.diaoyu();
		方法.fish("鱼竿");
	}
     public void diaoyu(){
    	 System.out.println("二狗今天去钓鱼了");
     }
     public void fish(String yugan){
    	 System.out.println("二狗今天拿着鱼竿去钓鱼了");
     }
     public String daisan(){
    	 return "记得带伞";
     }
     public boolean height(double a,double b){
    	 return a>b;
     }
}
