package service;

import java.util.ArrayList;
import java.util.List;

import dto.BookMark;
import dto.Category;
import dto.ForBookMarkList;
import dto.Position;

/**
 * 개인페이지 서비스
 * @author 박건준
 */

public interface IndividualPageServiceIF {
	
	/**
	 * 사용자로부터 직접 url을 입력받아 즐겨찾기 페이지에 추가한다.
	 * @param BookMark 즐겨찾기 페이지에 추가하려는 웹 페이지 정보
	 * @return int 추가한 북마크 아이디를 리턴
	 */
	public int addBookMark(BookMark bookMark);
	
	/**
	 * 북마크 아이디에 해당되는 북마크 정보를 불러온다.
	 * @param int 북마크 아이디
	 * @return BookMark 북마크 정보
	 */
	public BookMark getBookMark(int bookMarkId);
	
	/**
	 * 즐겨찾기 페이지 아이콘을 drag&drop 하여 위치를 조정한다.
	 * @param BookMark 위치가 이동된 즐겨찾기 페이지
	 */
	public void arrangeIcon(BookMark bookMark);
	
	/**
	 * 북마크 삭제
	 * @param int 북마크 아이디
	 * @return void
	 */
	public void deleteIcon(int bookMarkId);
	
	/**
	 * 북마크 수정
	 * @param BookMark 북마크 정보
	 * @return void
	 */
	public void modifyMark(BookMark bookMark);

	/**
	 * 카테고리 추가
	 * @param Category 카테고리 정보
	 * @return int 추가한 카테고리 아이디
	 */
	public int addCategory(Category category);
	
	/**
	 * 사용자가 카테고리 아이콘을 더블 클릭했을 때 호출
	 * 해당 카테고리의 북마크 리스트를 불러온다.
	 * @param ForBookMarkList 사용자 아이디와 카테고리 아이디를 담고 있는 변수
	 * @return ArrayList<BookMark> 카테고리 리스트 
	 */
	ArrayList<BookMark> bookMarkList(ForBookMarkList forListData);
	
	/**
	 * 북마크 또는 카테고리를 추가할 때 사용하는 메소드
	 * 인자로 넘겨받는 카테고리 안에 있는 아이콘들의 좌표를 받아온다.
	 * @param Category 카테고리 정보
	 * @return ArrayList<Position> 좌표 정보가 담겨 있는 변수
	 */
	ArrayList<Position> bookMarkPos(Category category);
	
	/**
	 * 북마크를 한꺼번에 여러 개 지울 때 사용
	 * @param ArrayList<Integer> 지우고자 하는 북마크 아이디
	 */
	public void deleteIcons(ArrayList<Integer> selectedIdList);
	
	/**
	 * 북마크 아이콘을 더블 클릭하면 해당 웹 페이지에 접속할 경우 테이블의 frequency 컬럼을 1 증가시킨다.
	 * @param String 해당 북마크 아이디
	 */
	public void increaseFrequency(String bookmarkId);
	
	/**
	 * 사용자의 카테고리 리스트를 받아온다.
	 * @param String 사용자 아이디
	 * @return ArrayList<Category> 카테고리 리스트
	 */
	public ArrayList<Category> getCategoryList(String userId);
	
	/**
	 * 인자로 넘겨받은 카테고리 아이디의 부모 카테고리 아이디를 받아온다.(상위 카테고리)
	 * @param int 카테고리 아이디
	 * @return int 부모 카테고리 아이디
	 */
	public int getParentId(int categoryId);
	
	/**
	 * 카테고리 수정
	 * @param Category 카테고리 정보
	 */
	public void modifyCategory(Category category);

	/**
	 * 카테고리 삭제
	 * 해당 카테고리를 삭제할 때는 자식 카테고리(하위 카테고리)도 같이 삭제해야 한다.
	 * @param List 삭제하려는 카테고리 아이디 리스트
	 */
	public void deleteBookMarkCategory(List target);
	
	/**
	 * 카테고리 추가
	 * @param Category 카테고리 정보
	 * @return int 추가한 카테고리 아이디
	 */
	public int addMark(Category category);
	
	/**
	 * 카테고리를 지울 때 자식 카테고리도 같이 지워야 하기 때문에 자식 카테고리 아이디를 받아온다.
	 * @param String 테이블, 
	 * @param int 지우고자 하는 카테고리 아이디
	 * @return ArrayList<Integer> 카테고리 아이디 리스트
	 */
	public ArrayList<Integer> getDeleteTargetList(String table, int categoryId);
	
	/**
	 * 사용자의 카테고리 리스트를 불러온다.
	 * @param String 사용자 아이디
	 * @return ArrayList<Category> 카테고리 리스트
	 */
	public ArrayList<Category> categoryList(String userId);
}
