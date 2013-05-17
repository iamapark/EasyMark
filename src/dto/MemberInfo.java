package dto;

public class MemberInfo {
	private String userId;
	private String name;
	private String registerDate;
	private String email;
	private int bookmarkCount;
	private int loginCount;
	private String imgUrl;
	private String bgImgUrl;
	
	public MemberInfo() {
		super();
	}

	public MemberInfo(String userId, String name, String registerDate,
			String email, int bookmarkCount, int loginCount, String imgUrl,
			String bgImgUrl) {
		super();
		this.userId = userId;
		this.name = name;
		this.registerDate = registerDate;
		this.email = email;
		this.bookmarkCount = bookmarkCount;
		this.loginCount = loginCount;
		this.imgUrl = imgUrl;
		this.bgImgUrl = bgImgUrl;
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

	public int getBookmarkCount() {
		return bookmarkCount;
	}

	public void setBookmarkCount(int bookmarkCount) {
		this.bookmarkCount = bookmarkCount;
	}

	public int getLoginCount() {
		return loginCount;
	}

	public void setLoginCount(int loginCount) {
		this.loginCount = loginCount;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getBgImgUrl() {
		return bgImgUrl;
	}

	public void setBgImgUrl(String bgImgUrl) {
		this.bgImgUrl = bgImgUrl;
	}
}
