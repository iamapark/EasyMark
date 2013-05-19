package dto;

public class User {
	private String loginId;
	private String keyword;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String loginId, String keyword) {
		super();
		this.loginId = loginId;
		this.keyword = keyword;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginrId(String loginId) {
		this.loginId = loginId;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}	
}
