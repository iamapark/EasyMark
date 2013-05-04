package service;

import java.util.ArrayList;
import java.util.HashMap;

import dto.BookMark;
import dto.Design;

/**
 * 개인페이지 서비스
 * @author 박건준
 */

public interface IndividualPageServiceIF {
	
	/**
	 * 사용자로부터 직접 url을 입력받아 즐겨찾기 페이지에 추가한다.
	 * @param String 사용자 아이디
	 * @param BookMark 즐겨찾기 페이지에 추가하려는 웹 페이지 정보
	 * @return void
	 */
	public void addBookMark(BookMark bookMark);	
	public ArrayList<BookMark> bookMarkList(String userId);
	public BookMark getBookMark(int bookMarkId);
	public int bookMarkPosx(String userId);
	public int bookMarkPosy(HashMap<String, Object> pos);
	/**
	 * 즐겨찾기 페이지 아이콘을 drag&drop 하여 위치를 조정한다.
	 * @param String 사용자 아이디
	 * @param BookMark 위치가 이동된 즐겨찾기 페이지
	 * @return void
	 */
	
	public void arrangeIcon(BookMark bookMark);
	public void deleteIcon(int bookMarkId);
	public void modifyMark(BookMark bookMark);
	
	/**
	 * 사용자가 개인페이지 디자인을 변경
	 * @param String 사용자 아이디
	 * @param 변경된 디자인에 관한 정보가 담긴 변수
	 * @return void
	 */
	public void changeDesign(String userId, Design design);
	
	/**
	 * EasyMark 웹 사이트에 대한 광고를 미투데이에 게재한다.
	 * @param String 사용자 아이디
	 * @return void
	 */
	public void informEasyMark(String userId);
}
