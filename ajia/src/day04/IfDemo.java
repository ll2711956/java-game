package day04;

import java.util.Scanner;

public class IfDemo {
	public static void main(String[] args) {
			Scanner scan = new Scanner(System.in);
			while (true) {
			System.out.println("请输入您的价格:");
			float total = scan.nextFloat();
			if (total > 0 && total <= 200) {
				System.out.println(total * 1);
			} else if (total > 200 && total < 300) {
				System.out.println(total * 0.98);
			} else if (total >= 300 && total <= 500) {
				System.out.println(total * 0.88);
			} else if (total > 500 && total <= 700) {
				System.out.println(total * 0.80);
			} else if (total > 700 && total <= 1000) {
				System.out.println(total * 0.70);
			} else if (total > 1000) {
				System.out.println(total * 0.50);
			}

		}
	}
}