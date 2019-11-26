package book;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;
import jdbc.Dao;
 
public class BookManager{
	LinkedHashMap<String, Book> stu = new LinkedHashMap<String, Book>();
	Book book = new Book();
	Scanner sc = new Scanner(System.in);
		
	public void Manage(int choice) throws Exception{			
		switch (choice) {
			case 1:
				CheckBook();
				break;
			case 2:
				AddBook();
				break;
			case 3:
				DeleteBook();
				break;
			case 4:
				LendBook();
				break;
			case 5:
				ReturnBook();
				break;
			case 6:
				Exit();
				break;
			default: {
				throw new Exception("Incorrect input, please re-enter: ");
			}		
		}		
	}
	
	//获取所有书籍
	public void CheckBook() {
		Dao dao = new Dao();
		List<Book> list = dao.getAllBooks();
		System.out.println("----->>Check Book: ");
		System.out.printf("%-10s", "BookID"); 
		System.out.printf("%-25s", "BookName"); 
		System.out.printf("%-15s", "State");
		System.out.printf("%-15s", "BorrowedCount");
		System.out.printf("%-15s%n", "BorrowedDate"); 
		for(Book book : list) {
			System.out.printf("%-10d", book.getID()); 
			System.out.printf("%-25s", book.getName()); 
			System.out.printf("%-15s", book.getState());
			System.out.printf("%-15d", book.getBorrowedCount());
			System.out.printf("%-15s%n", book.getBorrowedDate());
		}
		
	}
		
	
	//添加书籍
	public void AddBook() {
		Dao dao = new Dao();
		System.out.println("----->>Add Book: ");
		System.out.println("Please enter name of the New Book:");
		String Name = sc.next();
		if(dao.judgeExist(Name))
			System.out.println("<<"+Name+">> already exists! Please change another book! ");
		else{
			dao.AddBook(new Book(0,Name,"Available",0,""));
			System.out.println("Add new book <<"+Name+">> successfully!");
		}
	}
 
	//删除书籍
	public void DeleteBook() {
		System.out.println("----->>Delete Book: ");
		System.out.println("Please enter name of the book you want to delete:");
		String name = sc.next();
		Dao dao = new Dao();
		if(!dao.judgeExist(name)) {
			System.out.println("There is no book to match what you enter! ");
		}
		else if(dao.FindState(name).equals("Borrowed")){
			System.out.println("The book <<"+name+">> has been borrowed, it cannot be deleted!");
			}
		else{ 
			System.out.println(dao.FindState(name));
			dao.DeleteBook(name);
			System.out.println("Delete book <<"+name+">> sucessfully!");
		}
	}
	
	//借出书籍
	public void LendBook() {
		System.out.println("----->>Lend Book: ");
		System.out.println("Please enter the name of the book you want to lend:");
		String name = sc.next();
		Dao dao = new Dao();
		if (!dao.judgeExist(name)) {
			System.out.println("There is no book to match what you enter!");
		} 
		else if(dao.FindState(name).equals("Borrowed")){
			String borroweddate = dao.FindBorrowedDate(name);
			System.out.println("This book you enter has been borrowed on "+borroweddate+"! Please try another book!");
		}
		else {
			dao.LendBook(name);
			System.out.println("The book <<"+ name +">> has been borrowed successfully!");
		}
	}
	
	//归还书籍
	public void ReturnBook() {
		System.out.println("----->>Return Book: ");
		System.out.println("Please enter the name of the book you want to return:");
		String name = sc.next();
		Dao dao = new Dao();
		if (!dao.judgeExist(name)) {
			System.out.println("There is no book to match what you enter!");
		} 
		else if (dao.FindState(name).equals("Available")) {
			System.out.println("This book <<" + name + ">> is available, it cannot be returned! ");
		} 
		else {
			dao.ReturnBook(name);
			System.out.println("The book <<" + name + ">> has been returned successfully!");
		}
	}
	
	//退出
	public void Exit() {	
		System.out.println("The Library Management System has been shut down.");					
		return;
	}
			
}
