package day04;

public class MethodDemo {
	public static void main(String[] args) {
		//���췽�����ִ��
		//�����Ǵ���ĳ����Ķ����ʱ�򣬾��Ѿ���ʼ��ִ����
		new Aa();
		//�Զ��巽�����ִ��
		//1.�������Զ��巽�����ڵ���Ķ���  2.�ö������Ƶ�������ķ�������
		Aa a = new Aa();
		a.tianqi();
	}
}

class Aa {
	//���췽�������������ǹ̶��ģ����ǵ�ǰ�������
	public Aa() {
		System.out.println("����������ѩ�е�С");
	}
    //�Զ��巽���������Լ��𣬵�Ҫ����֪��
	public void tianqi() {
		System.out.println("������ô��ô��");
	}
}
