package day05;

public class Shoes {
     String brand;
     int price;
     double size;
     public Shoes(String brand,int price,double size){
    	 this.brand=brand;
    	 this.price=price;
    	 this.size=size;
     }
 public static void main(String[] args) {
	Shoes n=new Shoes("nike",888,41);
	 System.out.println(n.brand);
	 Shoes t=new Shoes("timberland",1288,41);
	 System.out.println(t.brand);
}
}
