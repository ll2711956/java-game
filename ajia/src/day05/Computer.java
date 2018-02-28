package day05;

public class Computer {
    //属性
	String 显卡;
    String 主机;
    String 显示器;
    public Computer(String 显卡,String 主机,String 显示器){
    	this.显卡=显卡;
    	this.主机=主机;
    	this.显示器=显示器;
    }
    //行为
    public void play(){}
    public void music(){}
    public static void main(String[] args){
    	Computer 戴尔灵越燃= new Computer("MX150 2G独显","母鸡","母鸡");
    	System.out.println(戴尔灵越燃.显卡);
    	Computer 华硕= new Computer("940MX 2G独显","不知道","不知道");
    	System.out.println(华硕.显卡);
    }
}
