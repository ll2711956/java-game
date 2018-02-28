package day09;

public class Array {
	public static void main(String[] args) {
		char[] a = new char[3];
		a[0] = 'b';
		a[1] = 'c';
		a[2] = 'v';
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		char word[] = { 'ÎÒ', 'ºÍ', 'Äã' };
		for (int j = 0; j < word.length; j++) {
			System.out.println(word[j]);
		}

		int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}

		double sum = 0;
		double[] arr2 = { 66.3, 77.2, 88.5, 99.5, 112.4, 115.6, 130.7, 145.8, 149.9, 120.9 };
		for (int i = 0; i < arr2.length; i++) {
			sum = sum + arr2[i];
		}
		System.out.println(sum);
		System.out.println(sum / 10);
		
		String[] stu=new String[5];
		stu[0]="¹ÏÆ¤";
		stu[1]="¹ûÆ¤";
		stu[2]="ÏºÆ¤";
		stu[3]="ÖíÆ¤";
		stu[4]="¹·Æ¤¸àÒ©";
		for (int i = 0; i < stu.length; i++) {
		       System.out.println(stu[i]);
		}
		
		int []shu=new int[10];
		for (int i = 0; i < shu.length; i++) {
			System.out.println(shu[i]);
		}
		
	

	}
}
