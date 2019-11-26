package jdbc;

import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import book.Book;

public class Dao {
	
	/**
	 * 根据书名判定书籍是否已存在
	 */
	public boolean judgeExist(String targetName){
	Connection conn = null; 
	PreparedStatement stmt = null;	
	ResultSet rs = null;
	int count = 0;
	try {
		conn = MySQLUtils.getConn();
		String sql = "select count(*) as count from book where BookName = ?";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, targetName);
		rs = stmt.executeQuery();
		while(rs.next()){
			count = rs.getInt("count");
		}
	} 
	catch (SQLException e) {
		e.printStackTrace();
	}
	finally{
		MySQLUtils.closeConn(conn, stmt, rs);
	}
	if(count==0) return false;
	else
		return true;
   }
	
	/**
	 * 获取所有书籍列表
	 */
	public List<Book> getAllBooks(){
		Connection conn = null; 
		Statement stmt = null;	
		ResultSet rs = null;
		List<Book> bookList = new ArrayList<>();
		try {
			
			conn = MySQLUtils.getConn();
			String sql = "select * from book";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int ID = rs.getInt("BookID");
				String Name = rs.getString("BookName");
				String State = rs.getString("State");
				int BorrowedCount = rs.getInt("BorrowedCount");
				String BorrowedDate = rs.getString("BorrowedDate");
				Book book = new Book(ID, Name, State, BorrowedCount, BorrowedDate);
				bookList.add(book);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MySQLUtils.closeConn(conn, stmt, rs);
		}
		return bookList;
	}

	
	/**
	 * 添书
	 */
	public void AddBook(Book book){
		Connection conn = null; 
		PreparedStatement stmt = null;	
		try {
			conn = MySQLUtils.getConn();
			String sql = "insert into book(BookName, State, BorrowedCount,BorrowedDate) values(?,?,?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, book.getName());
			stmt.setString(2, book.getState());
			stmt.setInt(3, book.getBorrowedCount());
			stmt.setString(4, book.getBorrowedDate());
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MySQLUtils.closeConn(conn, stmt);
		}
	}
	
	/**
	 * 删书
	 */
	public void DeleteBook(String bookName){
		Connection conn = null; 
		PreparedStatement stmt = null;	
		try {
			conn = MySQLUtils.getConn();
			String sql = "delete from book where BookName = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, bookName);
			stmt.executeUpdate();
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MySQLUtils.closeConn(conn, stmt);
		}
	}
	
	/**
	 * 借书
	 */
	public void LendBook(String targetName){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = MySQLUtils.getConn();
			//得到原来被借次数
			//String sql = "select BorrowedCount from book where BookName = ?";
			//stmt = conn.prepareStatement(sql);
			//stmt.setString(1, targetName);
			//ResultSet tmpres = stmt.executeQuery();
			//int oldBorrowedCount = tmpres.getInt("BorrowedCount");
			int oldBorrowedCount = FindBorrowedCount(targetName);
			Date date=new Date();//此时date为当前的时间
			SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");//设置当前时间的格式，为年-月-日
			
			String sql = "update book set State = ?, BorrowedCount= ?, BorrowedDate=? where BookName = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "Borrowed");
			stmt.setInt(2, oldBorrowedCount+1);
			stmt.setString(3, dateFormat.format(date));
			stmt.setString(4, targetName);
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MySQLUtils.closeConn(conn, stmt);
		}
	}
	
	/**
	 * 还书
	 */
	public void ReturnBook(String targetName){
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			conn = MySQLUtils.getConn();
			String sql = "update book set State = ?, BorrowedDate=? where BookName = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, "Available");
			stmt.setString(2, "");
			stmt.setString(3, targetName);
			stmt.executeUpdate();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MySQLUtils.closeConn(conn, stmt);
		}
	}
	
	/**
	 * 根据书名查找书籍的借阅状态
	 */
	public String FindState(String bookName){
		Connection conn = null; 
		PreparedStatement stmt = null;	
		String state = "";
		try {
			conn = MySQLUtils.getConn();
			String sql = "select State from book where BookName = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, bookName);
			ResultSet tmpres = stmt.executeQuery();
			while(tmpres.next()){
				state = tmpres.getString("State");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MySQLUtils.closeConn(conn, stmt);
		}
		return state;
	}
	
	/**
	 * 根据书名查找书籍的借阅次数
	 */
	public int FindBorrowedCount(String bookName){
		Connection conn = null; 
		PreparedStatement stmt = null;	
		int BorrowedCount = 0;
		try {
			conn = MySQLUtils.getConn();
			String sql = "select BorrowedCount from book where BookName = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, bookName);
			ResultSet tmpres = stmt.executeQuery();
			while(tmpres.next()){
				BorrowedCount = tmpres.getInt("BorrowedCount");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MySQLUtils.closeConn(conn, stmt);
		}
		return BorrowedCount;
	}
	
	/**
	 * 根据书名查找书籍的借出日期
	 */
	public String FindBorrowedDate(String bookName){
		Connection conn = null; 
		PreparedStatement stmt = null;	
		String BorrowedDate = "";
		try {
			conn = MySQLUtils.getConn();
			String sql = "select BorrowedDate from book where BookName = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, bookName);
			ResultSet tmpres = stmt.executeQuery();
			while(tmpres.next()){
				BorrowedDate = tmpres.getString("BorrowedDate");
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			MySQLUtils.closeConn(conn, stmt);
		}
		return BorrowedDate;
	}
}
