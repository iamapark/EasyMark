package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.ibatis.sqlmap.client.SqlMapClient;

import dto.BookMark;

public class BookMarkListDAO {
	private static BookMarkListDAO instance = null;
	private SqlMapClient sqlMapper = null;

	public static BookMarkListDAO getInstance() {
		if (instance == null)
			instance = new BookMarkListDAO();
		return instance;
	}

	private BookMarkListDAO() {
		sqlMapper = DAOParser.getParser();
	}

	@SuppressWarnings("unchecked")
	public ArrayList<BookMark> viewBookMarkList(String userId) {

		ArrayList<BookMark> viewBookMarkList = null;

		try {
			viewBookMarkList = (ArrayList<BookMark>) sqlMapper.queryForList(
					"viewBookMarkList", userId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return viewBookMarkList;
	}
}
