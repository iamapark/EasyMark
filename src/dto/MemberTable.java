package dto;

public class MemberTable {
	private String userId;
	private String name;
	private String registerDate;
	private String email;
	private int bookMarkCount;
	
	
	
	public MemberTable() {
		super();
		// TODO Auto-generated constructor stub
	}



	public MemberTable(String userId, String name, String registerDate,
			String email, int bookMarkCount) {
		super();
		this.userId = userId;
		this.name = name;
		this.registerDate = registerDate;
		this.email = email;
		this.bookMarkCount = bookMarkCount;
	}



	public String getUserId() {
		return userId;
	}



	public void setUserId(String userId) {
		this.userId = userId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getRegisterDate() {
		return registerDate;
	}



	public void setRegisterDate(String registerDate) {
		this.registerDate = registerDate;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public int getBookMarkCount() {
		return bookMarkCount;
	}



	public void setBookMarkCount(int bookMarkCount) {
		this.bookMarkCount = bookMarkCount;
	}
	
	
}
