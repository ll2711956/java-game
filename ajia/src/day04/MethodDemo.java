package day04;

public class MethodDemo {
	public static void main(String[] args) {
		//构造方法如何执行
		//当我们创建某个类的对象的时候，就已经开始在执行了
		new Aa();
		//自定义方法如何执行
		//1.创建该自定义方法所在的类的对象  2.用对象名称点出来它的方法名称
		Aa a = new Aa();
		a.tianqi();
	}
}

class Aa {
	//构造方法：方法名称是固定的，就是当前类的名称
	public Aa() {
		System.out.println("今天昆明的雪有点小");
	}
    //自定义方法：名字自己起，但要见名知意
	public void tianqi() {
		System.out.println("昆明怎么这么冷");
	}
}
