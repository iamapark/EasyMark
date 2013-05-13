package service;

import dao.SpeechDAO;

public class SpeechServiceImpl implements SpeechServiceIF {

	@Override
	public String getSearchEngine(String speech) {
		return SpeechDAO.getInstance().getSearchEngine(speech);
	}

}
