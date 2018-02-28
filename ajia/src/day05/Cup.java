package day05;

public class Cup {
     String 颜色;
     String 价格;
     String 牌子;
     public Cup(String 颜色,String 价格,String 牌子){
    	 this.颜色=颜色;
    	 this.价格=价格;
    	 this.牌子=牌子;
     }
     public static void main(String[] args) {
		Cup baowenbei =new Cup("yellow","180","不知道");
		System.out.println(baowenbei.颜色+","+baowenbei.价格+","+baowenbei.牌子);
		Cup bolibei =new Cup("white","18","muji");
		System.out.println(bolibei.牌子);
	}
}
