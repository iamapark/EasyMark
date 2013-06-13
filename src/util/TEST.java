package util;

import java.util.HashMap;
import java.util.Iterator;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import dto.BookMark;

public class TEST {
	
	public static HashMap<String, String> idMap = new HashMap<String, String>();

	public static void main(String[] args) {
		
		String data = "{\"0\":{\"children\":[{\"children\":[{\"dateAdded\":1371029222035,\"id\":\"4\",\"index\":0,\"parentId\":\"1\",\"title\":\"Daum - 모으다 잇다 흔들다\",\"url\":\"http://www.daum.net/\"},{\"children\":[{\"dateAdded\":1371029235628,\"id\":\"5\",\"index\":0,\"parentId\":\"6\",\"title\":\"네이버 개발자센터\",\"url\":\"http://dev.naver.com/\"},{\"dateAdded\":1371038807578,\"id\":\"7\",\"index\":1,\"parentId\":\"6\",\"title\":\"Facebook에 오신 것을 환영합니다. 로그인, 가입 및 더 알아보기\",\"url\":\"https://www.facebook.com/\"}],\"dateAdded\":1371029246259,\"dateGroupModified\":1371044893931,\"id\":\"6\",\"index\":1,\"parentId\":\"1\",\"title\":\"개발\"},{\"children\":[],\"dateAdded\":1371044918145,\"dateGroupModified\":1371054399476,\"id\":\"9\",\"index\":2,\"parentId\":\"1\",\"title\":\"빈 폴더\"}],\"dateAdded\":1371029206678,\"dateGroupModified\":1371029235628,\"id\":\"1\",\"index\":0,\"parentId\":\"0\",\"title\":\"북마크바\"},{\"children\":[{\"dateAdded\":1371054399476,\"id\":\"10\",\"index\":0,\"parentId\":\"2\",\"title\":\"FMA - Future Media Architects\",\"url\":\"http://kaka.com/\"}],\"dateAdded\":1371029206678,\"dateGroupModified\":1371054405311,\"id\":\"2\",\"index\":1,\"parentId\":\"0\",\"title\":\"기타 북마크\"}],\"dateAdded\":1371044872256,\"id\":\"0\",\"title\":\"\"},\"length\":1}";
		
		JSONObject jObj = JSONObject.fromObject(data);
		
		JSONObject root = jObj.getJSONObject("0");
		chromeBookmarkAdd(root);
		
		System.out.println("1: " + idMap.get("1"));
		System.out.println("6: " + idMap.get("6"));
		System.out.println("2: " + idMap.get("2"));
	}
	
	public static boolean isCategory(JSONObject jObj){
		boolean flag = false;

		if(jObj.get("url") == null)
			flag = true;
		
		return flag;
	}
	
	public static JSONArray getChildren(JSONObject jObj){
		return jObj.getJSONArray("children");
	}
	
	public static void chromeBookmarkAdd(JSONObject root){
		objectAdd(getChildren(root).getJSONObject(0));
		objectAdd(getChildren(root).getJSONObject(1));
	}
	
	public static void objectAdd(JSONObject jObj){
		if(!jObj.getString("id").equals("0")){
			// 루트가 아니기 때문에 데이터를 추가해야 함.

			if(isCategory(jObj)){
				// URL이 없으면 카테고리로 추가
				System.out.println("category 추가: " + jObj.getString("title") + ", id: " + jObj.getString("id") + ", parentId: " + jObj.getString("parentId"));
				//idMap.put(jObj.getString("id"), INSERT 후 반환된 아이디-추가된 카테고리 아이디);
				idMap.put(jObj.getString("id"), jObj.getString("parentId"));
				arrayAdd(jObj);
			}else{
				// URL이 있으면 북마크로 추가
				System.out.println("bookmark 추가: " + jObj.getString("url")  + ", id: " + jObj.getString("id") + ", parentId: " + jObj.getString("parentId"));
				//idMap.get(jObj.getString("parentId")); -> 추가하는 북마크의 parentId
			}
		}else{
			// 루트이기 때문에 데이터를 추가하면 안됨.
			System.out.println("root");
			
		}
	}
	
	public static void arrayAdd(JSONObject jObj){
		JSONArray arrayJ = jObj.getJSONArray("children");
		int size = arrayJ.size();
		
		for(int i=0; i<size; i++){
			objectAdd(arrayJ.getJSONObject(i));
		}
	}

}
