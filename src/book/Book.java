package book;

public class Book {
	
	private int ID;
	private String Name;
	private String State;
	private int BorrowedCount;
	private String BorrowedDate; 
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(int ID, String Name, String State, int BorrowedCount, String BorrowedDate) {
		super();
		this.ID = ID;
		this.Name = Name;
		this.State = State;
		this.BorrowedCount = BorrowedCount;
		this.BorrowedDate = BorrowedDate;
	}
	@Override
	public String toString() {
		return "Book [ID=" + ID + ", Name=" + Name + ", State=" + State + ", BorrowedCount=" + BorrowedCount + ", BorrowedDate=" + BorrowedDate + "]";
	}
	public int getID() {
		return ID;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public String getName() {
		return Name;
	}
	public void setName(String Name) {
		this.Name = Name;
	}
	public String getState() {
		return State;
	}
	public void setState(String State) {
		this.State = State;
	}
	public int getBorrowedCount() {
		return BorrowedCount;
	}
	public void setBorrowedCount(int BorrowedCount) {
		this.BorrowedCount = BorrowedCount;
	}
	public String getBorrowedDate() {
		return BorrowedDate;
	}
	public void setBorrowedDate(String BorrowedDate) {
		this.BorrowedDate = BorrowedDate;
	}
}
 
