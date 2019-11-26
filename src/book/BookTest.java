package book;
import java.util.Scanner;

public class BookTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BookManager book = new BookManager();
		boolean flag=true;
		//Integer temp = 0;
		do {
//			if(temp == 0) {
//				System.out.println("Please enter your account:");
//				String userName = sc.next();
//				System.out.println("Please enter your password:");
//				String password = sc.next();
//				if(!("admin".equals(userName) && "admin".equals(password))) {
//					System.out.println("account or password is wrong!");
//					continue;
//				}
//				temp = 1;
//			}
			
			//界面
			System.out.println("Welcome to VMA Mini Library Management System!");
			System.out.println("------------------------------------------------------");
			System.out.println("-> 1.Check Book");
			System.out.println("-> 2.Add Book");
			System.out.println("-> 3.Delete Book");
			System.out.println("-> 4.Lend Book");
			System.out.println("-> 5.Return Book");
			System.out.println("-> 6.Exit");
			System.out.println("------------------------------------------------------");			
			System.out.println("Please Select Function:");
			
			
			int choice = sc.nextInt();			
			//   try/catch
			try {
				if(choice!=6) {
					book.Manage(choice);
					System.out.println("");
					System.out.println("");
					System.out.println("Enter 0 to return to the main menu, please enter:");
					System.out.println("******************************************************");
					int exit = sc.nextInt();
					if(exit==0) {
						flag = false;
					}else {
						flag = true;
					}
				}else {
					book.Manage(choice);
					flag = true;
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}while(!flag);
		sc.close();
	}
 
}
