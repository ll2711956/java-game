package chess;

public class ArrDemo {

	public static void main(String[] args) {
		int arr[] = new int[10];
		int arr2[][] = new int[18][18];
		// 18*18=
		// ��λ�����������������������һά���齻�ӵĵط�
		// for (int i = 0; i < arr2.length; i++) {
		// i=0;j=0~17
		// i=1;j=0~17
		// i=2;j=0~17
		// for (int j = 0; j < arr2.length; j++) {
		//
		// }
		// }
		arr2[10][8] = 1;
		arr2[10][17] = 2;
		arr2[11][6] = 1;
		for (int i = 0; i < arr2.length; i++) {
			for (int j = 0; j < arr2.length; j++) {
             if (arr2[i][j]==1) {
				System.out.print("���� ");
			}else if (arr2[i][j]==2) {
				System.out.print("���� ");
			}else if (arr2[i][j]==0) {
				System.out.print("û�� ");
			}
			}
			System.out.println();
		}
	}

}
