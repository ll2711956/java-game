package day05;

public class Cup {
     String ��ɫ;
     String �۸�;
     String ����;
     public Cup(String ��ɫ,String �۸�,String ����){
    	 this.��ɫ=��ɫ;
    	 this.�۸�=�۸�;
    	 this.����=����;
     }
     public static void main(String[] args) {
		Cup baowenbei =new Cup("yellow","180","��֪��");
		System.out.println(baowenbei.��ɫ+","+baowenbei.�۸�+","+baowenbei.����);
		Cup bolibei =new Cup("white","18","muji");
		System.out.println(bolibei.����);
	}
}
