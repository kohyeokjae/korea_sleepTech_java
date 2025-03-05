package chapter02;

public class Z_Review {

	public static void main(String[] args) {
		// 1.
		//      *****
		//      ****
		//      ***
		//      **
		//      *
		
		// 2.
		//      *****
		//       ****
		//        ***
		//         **
		//          *
		
		// 1.
		for (int i = 0; i < 5; i++) {
			for (int j = 5-i; j > 0; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
		
		System.out.println();
		
		// 2.
		for (int i = 0; i < 5; i++) {
			for (int k = 0; k < i; k++) {
				System.out.print(" ");
			}
			for (int j = 5-i; j > 0; j--) {
				System.out.print("*");
			}
			System.out.println();
		}
	}
}
