package dto;

import java.io.Serializable;

public class BookMark implements Serializable {

	private static final long serialVersionUID = 1L;
	private int bookMarkId;
	private String bookMarkName;
	private String bookMarkUrl;
	private String bookMarkDescript;
	private String userId;
	private String status;
	private int posX;
	private int posY;
	private String imgUrl;
	private int frequency;
	private String category;
	
	
	public BookMark() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookMark(int bookMarkId, String bookMarkName, String bookMarkUrl,
			String bookMarkDescript, String userId, String status, int posX,
			int posY) {
		super();
		this.bookMarkId = bookMarkId;
		this.bookMarkName = bookMarkName;
		this.bookMarkUrl = bookMarkUrl;
		this.bookMarkDescript = bookMarkDescript;
		this.userId = userId;
		this.status = status;
		this.posX = posX;
		this.posY = posY;
	}
	
	public BookMark(int bookMarkId, String bookMarkName, String bookMarkUrl,
			String bookMarkDescript, String userId, String status, int posX,
			int posY, String imgUrl, int frequency, String category) {
		super();
		this.bookMarkId = bookMarkId;
		this.bookMarkName = bookMarkName;
		this.bookMarkUrl = bookMarkUrl;
		this.bookMarkDescript = bookMarkDescript;
		this.userId = userId;
		this.status = status;
		this.posX = posX;
		this.posY = posY;
		this.imgUrl = imgUrl;
		this.frequency = frequency;
		this.category = category;
	}
	
	public BookMark(int bookMarkId, String bookMarkName){
		super();
		this.bookMarkId = bookMarkId;
		this.bookMarkName = bookMarkName;
	}
	
	public BookMark(int bookMarkId, String bookMarkName, String category){
		this.bookMarkId = bookMarkId;
		this.bookMarkName = bookMarkName;
		this.category = category; 
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((bookMarkUrl == null) ? 0 : bookMarkUrl.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookMark other = (BookMark) obj;
		if (bookMarkUrl == null) {
			if (other.bookMarkUrl != null)
				return false;
		} else if (!bookMarkUrl.equals(other.bookMarkUrl))
			return false;
		return true;
	}
	public int getBookMarkId() {
		return bookMarkId;
	}
	public void setBookMarkId(int bookMarkId) {
		this.bookMarkId = bookMarkId;
	}
	public String getBookMarkName() {
		return bookMarkName;
	}
	public void setBookMarkName(String bookMarkName) {
		this.bookMarkName = bookMarkName;
	}
	public String getBookMarkUrl() {
		return bookMarkUrl;
	}
	public void setBookMarkUrl(String bookMarkUrl) {
		this.bookMarkUrl = bookMarkUrl;
	}
	public String getBookMarkDescript() {
		return bookMarkDescript;
	}
	public void setBookMarkDescript(String bookMarkDescript) {
		this.bookMarkDescript = bookMarkDescript;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
}
