package service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilderFactory;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import dao.FinedustDAO;
import dto.Finedust;

import java.io.BufferedReader;
import java.io.IOException;

public class FinedustService {
	private FinedustDAO finedustDAO = new FinedustDAO(); 
	
    public int dustParsing() {
    	
    	//반환값
    	List<Map<String, String>> dustlist = new ArrayList<>();
    	
    	try {
    		String serviceKey = "/PTKUhnX/6GTwbizpNxq4mDmSgvmkfbqMdCK3sniyN08SK9iSosrnk/qu0axaK+gTpr4jga6Zdr5nHzIw3tvKg==";
            StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo"); /*URL*/
            urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + URLEncoder.encode(serviceKey, "UTF-8")); /*Service Key*/
            urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*xml 또는 json*/
            urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
            urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
            urlBuilder.append("&" + URLEncoder.encode("year","UTF-8") + "=" + URLEncoder.encode("2020", "UTF-8")); /*측정 연도*/
            urlBuilder.append("&" + URLEncoder.encode("itemCode","UTF-8") + "=" + URLEncoder.encode("PM10", "UTF-8")); /*미세먼지 항목 구분(PM10, PM25), PM10/PM25 모두 조회할 경우 파라미터 생략*/
            System.out.println(urlBuilder.toString());
            URL url = new URL(urlBuilder.toString());
            
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            System.out.println("Response code: " + conn.getResponseCode());
            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();
            System.out.println(sb.toString());
            
            //json파싱
            //파싱라이브러리 : json-simple-1.1.1.jar
//            JSONParser parser = new JSONParser();
//            JSONObject object = (JSONObject)parser.parse(sb.toString()); //문자열을 json객체로 변경
//            object = (JSONObject) object.get("response");
//            object = (JSONObject) object.get("body");
//            System.out.println(object.get("items"));
            
//            JSONArray array =  (JSONArray)(object.get("items"));

//            System.out.println(array);
//            System.out.println("--------------------------");
//            for(int i=0; i<array.size(); i++) {
//            	System.out.println(i + " --------------------------");
//            	Map<String, String> map = new HashMap<>();//한건을 넣을 맵 생성
//            	object = (JSONObject) array.get(i);
//            	
//            	Set<String> kset =object.keySet();
//            	for(String key :kset) {
//            		//System.out.println(key +":" + object.get(key));
//            		map.put(key, (String)object.get(key));
//            	}
//            	clist.add(map);
//            }
          //xml파싱
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(urlBuilder.toString());
            NodeList nlist = doc.getElementsByTagName("item");
            System.out.println("데이터갯수:" + nlist.getLength());
            
            for(int i=0; i < nlist.getLength(); i++) {
            	NodeList clist1 =  nlist.item(i).getChildNodes(); //자식노드들
            	//맵생성(한건)
            	Map<String, String> cmap = new HashMap<>();
            	for(int j=0; j<clist1.getLength(); j++) {
            		Node node = clist1.item(j);
                	//System.out.println(node.getNodeName() + ":" + node.getTextContent());
            		cmap.put(node.getNodeName(),node.getTextContent() );
            	}
            	//System.out.println("-----------------------------------------");
            	dustlist.add(cmap);
            }
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    	System.out.println(dustlist);
    	//dao에 clist넘기기
    	int cnt = finedustDAO.insert(dustlist);
        System.out.println(cnt+"건 저장");
        return cnt;
    }
         
    	
    public List<Finedust> selectList() {
		//iso조회
		return finedustDAO.selectList();
	}
    public List<Finedust> selectList_name(String districtName) {
		return finedustDAO.selectList_name(districtName);
	}
}