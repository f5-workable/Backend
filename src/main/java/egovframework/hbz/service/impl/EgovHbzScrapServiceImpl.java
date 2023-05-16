package egovframework.hbz.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import egovframework.hbz.dao.EgovHbzScrpDao;
import egovframework.hbz.service.EgovHbzScrapService;

@Service
public class EgovHbzScrapServiceImpl implements EgovHbzScrapService{
	
	@Autowired
	EgovHbzScrpDao egovHbzScrpDao;
	
	final String ENCODING_KEY = "%2BQQEqvWhlZMRsrvKvdg5cL98YwZ%2FTrDWjREJbGcYJzSWxnJh8AHRLe8UWod%2BXvUBkdH1CAArM0eDtM4h3oDfIA%3D%3D";
	final String DECODING_KEY = "+QQEqvWhlZMRsrvKvdg5cL98YwZ/TrDWjREJbGcYJzSWxnJh8AHRLe8UWod+XvUBkdH1CAArM0eDtM4h3oDfIA==";
	
	Logger logger = LoggerFactory.getLogger(EgovHbzScrapServiceImpl.class);
	
	// 네이버 뉴스 랭킹 스크래핑
	public void newsRank(Model model) throws Exception{
		
		Map<String, Object> map = new HashMap<>();
		
		String url = "https://news.naver.com/main/ranking/popularDay.naver";
		
		Document 	doc 			= Jsoup.connect(url).get();
		Element 	ele_div 		= doc.select("div.rankingnews_box").first();
		Elements 	ele_img 		= ele_div.select("span.rankingnews_thumb");
		
		System.out.println("###ele_img : " + ele_img);
		
		Element	ele_company 	= ele_div.getElementsByClass("rankingnews_name").get(0);
		Elements ele_title_all 	= ele_div.getElementsByClass("list_title");
		Elements ele_img_all 	= ele_div.getElementsByClass("list_img");
		
		Element ele_title_list 	= null;
		Element ele_img_list	= null;
		String  ele_aTag 		= null;
		
		int cnt = 1;
		
		for(int i=0; i<5; i++) {
			
			ele_aTag 		= ele_title_all.get(i).attr("href");
			ele_title_list 	= ele_title_all.get(i);
			ele_img_list	= ele_img_all.get(i);
			
			if(ele_img_list.html().contains("동영상기사")) {
				String ele_img_list_rep = ele_img_list.toString().replaceAll("동영상기사", "");
				map.put("img_list_0" + cnt, ele_img_list_rep);
			}else {
				map.put("img_list_0" + cnt	, ele_img_list.html());
			}
			map.put("title_list_0" + cnt, ele_title_list.html());
			map.put("href_list_0" + cnt, ele_aTag);
			
			cnt++;
		}
		
		map.put("img_01"	, ele_img.html());
		map.put("company_01", ele_company.html());
		
		System.out.println("###map : " + map);
		
		model.addAttribute("map",map);

	}
	
	// 웹툰 스크래핑
	public void webtoonScrap(Model model) throws Exception{
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String url = "https://comic.naver.com/webtoon/detail?titleId=783053&no=75";
		
		Document doc = Jsoup.connect(url).get();
		
		System.out.println(doc.html());
		
		Elements ele = doc.select("div.wt_viewer");
		
		map.put("html", ele.html());
		
		model.addAttribute("map", map);
	}
	
	// 공공데이터포털 - 기상청 [단기예보조회]
	public Map<String, Object> getVilageFcst(String in_area, String in_time) throws Exception {
		
		System.out.println("###getVilageFcst service start...");
		
		System.out.println("in_area : " + in_area);
		System.out.println("in_time : " + in_time);
		
		Map<String, Object> areaMap = areaXy(in_area);
		
		String url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";
		
		// 지금 날짜를 기준으로 포맷
		Map<String, Object> dateMap = dateFormatter();

//		String nx 			= "60";
		String nx			= (String)areaMap.get("nx");
		String ny			= (String)areaMap.get("ny");
//		String ny 			= "127";
		String base_date 	= (String)dateMap.get("base_date");
		String base_time	= "0500";
		
		System.out.println("###nx : " + nx );
		System.out.println("###ny : " + ny );
		
		Document doc 		= Jsoup.connect(url).data("ServiceKey"	, DECODING_KEY) 				// 인증키
												.data("pageNo"		, "1")							// 페이지 번호
												.data("numOfRows"	, "1000")						// 한 페이지 결과 수
												.data("dataType"	, "JSON")						// 요청자료형식(XML, JSON)
												.data("base_date"	, base_date)					// 발표일자
												.data("base_time"	, base_time)					// 발표시각
												.data("nx"			, (String)areaMap.get("nx"))	// 예보지점 X 좌표
												.data("ny"			, (String)areaMap.get("ny"))	// 예보지점 Y 좌표
												.timeout(10000)										// 시간초과
												.ignoreContentType(true)							// 콘텐츠 타입 무시
												.get();
		
		String 		jsonStr 		= doc.text();
		JSONParser 	jsonParser 		= new JSONParser();
		JSONObject 	jsonObject	 	= (JSONObject) jsonParser.parse(jsonStr);
		JSONObject  jsonResponse 	= (JSONObject) jsonObject.get("response");
		JSONObject 	json_Body 		= (JSONObject) jsonResponse.get("body");
		JSONObject 	json_items 		= (JSONObject) json_Body.get("items");
		JSONArray 	json_item 		= (JSONArray) json_items.get("item");
		
		Map<String, Object> map 		= new HashMap<String, Object>();
		
		for(int i = 0; i < json_item.size(); i++) {
			JSONObject json_item_get = (JSONObject) json_item.get(i);
			
			String fcstValue 	= (String) json_item_get.get("fcstValue");// 값
			String category 	= (String) json_item_get.get("category");//자료구분코드
			String fcstDate 	= (String) json_item_get.get("fcstDate");// 예보일자
			String fcstTime		= (String) json_item_get.get("fcstTime");// 예보시간
			
			System.out.println(json_item.get(i));
			
			if(base_date.equals(fcstDate)) {
				if(fcstTime.equals(in_time)) {
					if(category.equals("POP")) 		map.put("POP", fcstValue);	// 강수확률		단위:%
					else if(category.equals("PTY"))	map.put("PTY", fcstValue);	// 강수형태		단위:코드값
					else if(category.equals("PCP"))	map.put("PCP", fcstValue);	// 1시간 강수량		단위:범주 (1mm)
					else if(category.equals("REH"))	map.put("REH", fcstValue);	// 습도			단위:%
					else if(category.equals("SNO"))	map.put("SNO", fcstValue);	// 1시간 신적설		단위:범주(1cm)
					else if(category.equals("SKY"))	map.put("SKY", fcstValue);	// 하늘상태		단위:코드값
					else if(category.equals("TMP"))	map.put("TMP", fcstValue);	// 1시간 기온		단위:℃
					else if(category.equals("TMN"))	map.put("TMN", fcstValue);	// 일 최저기온		단위:℃
					else if(category.equals("TMX"))	map.put("TMX", fcstValue);	// 일 최고기온		단위:℃
					else if(category.equals("UUU"))	map.put("UUU", fcstValue);	// 풍속(동서성분)	단위:m/s
					else if(category.equals("VVV"))	map.put("VVV", fcstValue);	// 풍속(남북성분)	단위:m/s
					else if(category.equals("WAV"))	map.put("WAV", fcstValue);	// 파고			단위:M
					else if(category.equals("VEC"))	map.put("VEC", fcstValue);	// 풍향			단위:deg
					else if(category.equals("WSD"))	map.put("WSD", fcstValue);	// 풍속			단위:m/s
				}
			}
		}
		
		System.out.println("###map : " + map);
		
		System.out.println("tmp : " + (String)map.get("TMP"));
		System.out.println("tmn : " + (String)map.get("TMN"));
		System.out.println("tmx : " + (String)map.get("TMX"));
		
		// x축 y축으로 지역 추출
		String area = xyArea(nx, ny);
		
		map.put("AREA"		, area);
		map.put("BASE_TIME"	, base_time);
		map.put("BASE_DATE"	, base_date);
		map.put("NY"		, ny);
		map.put("NX"		, nx);
		map.put("TIME"		,in_time);
		
		return map;
	}
	
	// 공공데이터포털 - 기상청 [초단기실황] 데이터 스크래핑, 매 30분마다 업데이트
	@Override
	public void getUltraSrtNcst() throws Exception {
		
		System.out.println("### dataPotalScrap Start");
		
		String url = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
		
		// 지금 날짜를 기준으로 포맷
		Map<String, Object> dateMap = dateFormatter();

		String nx 			= "60";
		String ny 			= "127";
		String base_date 	= (String)dateMap.get("base_date");
//		String base_time 	= (String)dateMap.get("base_time");
		String base_time	= "0600";
		
		System.out.println("### base_date ### " + base_date);
		System.out.println("### base_time ### " + base_time);
		
		Document doc 		= Jsoup.connect(url).data("ServiceKey"	, DECODING_KEY) 	// 인증키
												.data("pageNo"		, "1")				// 페이지 번호
												.data("numOfRows"	, "1000")			// 한 페이지 결과 수
												.data("dataType"	, "JSON")			// 요청자료형식(XML, JSON)
												.data("base_date"	, base_date)		// 발표일자
												.data("base_time"	, base_time)			// 발표시각
												.data("nx"			, nx)				// 예보지점 X 좌표
												.data("ny"			, ny)				// 예보지점 Y 좌표
												.timeout(10000)							// 시간초과
												.ignoreContentType(true)				// 콘텐츠 타입 무시
												.get();
		
		System.out.println("### doc ###		" + doc.text());
		
		String 		jsonStr 	= doc.text();
		
		JSONParser 	jsonParser 	= new JSONParser();
		JSONObject 	jsonObject	 	= (JSONObject) jsonParser.parse(jsonStr);
		JSONObject  jsonResponse 	= (JSONObject) jsonObject.get("response");
		System.out.println("### jsonResponse ###	" + jsonResponse.toString());
		System.out.println("");
		
		JSONObject json_Body 		= (JSONObject) jsonResponse.get("body");
		
		System.out.println("### json_Body ###	" + json_Body.toString());
		System.out.println("");
		
		JSONObject json_items 		= (JSONObject) json_Body.get("items");
		
		System.out.println("### json_items ###	" + json_items.toString());
		System.out.println("");
		
		JSONArray json_item 		= (JSONArray) json_items.get("item");
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		for(int i = 0; i < json_item.size(); i++) {
			
			System.out.println("### json_item ###	" 	+ json_item.get(i).toString());
			System.out.println("");
			
			JSONObject json_item_get = (JSONObject) json_item.get(i);
			
			// 값
			String obsrValue 	= (String) json_item_get.get("obsrValue");
			System.out.println("### obsrValue ### 	" 	+ obsrValue);
			
			//자료구분코드
			String category 	= (String) json_item_get.get("category");
			System.out.println("### category ### 	" 	+ category);
			System.out.println("");
			
			if(category.equals("PTY")) 		map.put("PTY", obsrValue);	// 강수형태
			else if(category.equals("REH"))	map.put("REH", obsrValue);	// 습도
			else if(category.equals("RN1"))	map.put("RN1", obsrValue);	// 1시간 강수량
			else if(category.equals("T1H"))	map.put("T1H", obsrValue);	// 기온
			else if(category.equals("UUU"))	map.put("UUU", obsrValue);	// 동서바람성분
			else if(category.equals("VEC"))	map.put("VEC", obsrValue);	// 풍향
			else if(category.equals("VVV"))	map.put("VVV", obsrValue);	// 남북바람성분
			else if(category.equals("WSD"))	map.put("WSD", obsrValue);	// 풍속
		}
		
		// x축 y축으로 지역 추출
		String area = xyArea(nx, ny);
		
		map.put("AREA"		, area);
		map.put("BASE_TIME"	, base_time);
		map.put("BASE_DATE"	, base_date);
		map.put("NY"		, ny);
		map.put("NX"		, nx);
		
		egovHbzScrpDao.insertAction(map);
	}
	
	// DB조회 후 스크래핑 페이지로 전환
	@Override
	public List<Map<String, Object>> scrapView(int pageNo, Model model) throws Exception {
		
		System.out.println("###scrapView Start");
		
		// 페이지 수
		int pageSize 	= 0;
		// 페이지당 보여질 게시물의 수
		int recordSize 	= 5;
		// 페이지번호가 0이거나 0보다 작으면 1로 초기화
		if(pageNo == 0 && pageNo < 0 ) pageNo = 1;
		//게시글 수 카운팅
		int cnt = egovHbzScrpDao.getCnt();
		
		System.out.println("###cnt : " +cnt);
		
		// 페이지크기 = 총게시물카운트 / 보여질 게시물 수
		pageSize = cnt/recordSize;
		
		// 나머지가 있으면 1 추가
		if(cnt % recordSize >= 1) pageSize++;
		
		int offset = pageEncoding(pageNo, recordSize);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("offset"	, offset);
		map.put("recordSize", recordSize);
		
		List<Map<String, Object>> list = egovHbzScrpDao.selectAction(map);

		System.out.println("###list = " + list);
		
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		
		// 페이지크기를 모델에 담는다
		model.addAttribute("pageSize", pageSize);
		
		return list;
	}
	
	// 게시물 출력될 번호
	public int pageEncoding(int pageNo, int recordSize) {
		return (pageNo - 1) * recordSize;
	}
	
	// 날짜 포맷 YYMMDD
	public Map<String, Object> dateFormatter() {
		
		Map<String, Object> dateMap = new HashMap<>();
		
		Date nowDate = new Date();
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd"); 
		
		String base_date = simpleDateFormat.format(nowDate);
		
		simpleDateFormat = new SimpleDateFormat("HHmm");
		
		String base_time = simpleDateFormat.format(nowDate);
		
		dateMap.put("base_date", base_date);
		dateMap.put("base_time", base_time);
		
		return dateMap;
	}
	
	// x축 y축으로 지역 추출
	public String xyArea(String nx, String ny) {
		
		if(nx.equals("60") && ny.equals("127")) {
			return "서울특별시 중구";
		}else if(nx.equals("97") && ny.equals("74")) {
			return "부산광역시 중구";
		}else if(nx.equals("89") && ny.equals("90")) {
			return "대구광역시 중구";
		}else if(nx.equals("54") && ny.equals("125")) {
			return "인천광역시 중구";
		}else if(nx.equals("59") && ny.equals("74")){
			return "광주광역시 동구";
		}else if(nx.equals("68") && ny.equals("100")) {
			return "대전광역시 동구";
		}else {
			return null;
		}
	}
	
	// 지역으로 x,y축 추출
	public Map<String, Object> areaXy(String in_area) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(in_area.equals("su")) {
			map.put("nx", "60");
			map.put("ny","127");
		}else if(in_area.equals("bs")) {
			map.put("nx", "97");
			map.put("ny","74");
		}else if(in_area.equals("dg")) {
			map.put("nx", "89");
			map.put("ny","90");
		}else if(in_area.equals("ic")) {
			map.put("nx", "54");
			map.put("ny","125");
		}else if(in_area.equals("gj")) {
			map.put("nx", "59");
			map.put("ny","74");
		}else if(in_area.equals("dj")) {
			map.put("nx", "68");
			map.put("ny","100");
		}
		
		return map;
	}
	
	public Map<String, Object> putfor(JSONArray json_item, String base_date, String fcstTime){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		for(int i = 0; i < json_item.size(); i++) {
			
			JSONObject json_item_get = (JSONObject) json_item.get(i);
			
			// 값
			String fcstValue 	= (String) json_item_get.get("fcstValue");
			
			//자료구분코드
			String category 	= (String) json_item_get.get("category");
			
			// 예보일자
			String fcstDate 	= (String) json_item_get.get("fcstDate");
			
			if(base_date.equals(fcstDate)) {
				if(fcstTime.equals((String) json_item_get.get("fcstTime"))) {
					map = putMap(category, fcstValue, map);
				}
			}
		}
		
		return map;
	}
	
	// 단기예보 if문
	public Map<String, Object> putMap(String category, String fcstValue, Map<String, Object> map){
		
		if(category.equals("POP")) 		map.put("POP", fcstValue);	// 강수확률		단위:%
		else if(category.equals("PTY"))	map.put("PTY", fcstValue);	// 강수형태		단위:코드값
		else if(category.equals("PCP"))	map.put("PCP", fcstValue);	// 1시간 강수량		단위:범주 (1mm)
		else if(category.equals("REH"))	map.put("REH", fcstValue);	// 습도			단위:%
		else if(category.equals("SNO"))	map.put("SNO", fcstValue);	// 1시간 신적설		단위:범주(1cm)
		else if(category.equals("SKY"))	map.put("SKY", fcstValue);	// 하늘상태		단위:코드값
		else if(category.equals("TMP"))	map.put("TMP", fcstValue);	// 1시간 기온		단위:℃
		else if(category.equals("TMN"))	map.put("TMN", fcstValue);	// 일 최저기온		단위:℃
		else if(category.equals("TMX"))	map.put("TMX", fcstValue);	// 일 최고기온		단위:℃
		else if(category.equals("UUU"))	map.put("UUU", fcstValue);	// 풍속(동서성분)	단위:m/s
		else if(category.equals("VVV"))	map.put("VVV", fcstValue);	// 풍속(남북성분)	단위:m/s
		else if(category.equals("WAV"))	map.put("WAV", fcstValue);	// 파고			단위:M
		else if(category.equals("VEC"))	map.put("VEC", fcstValue);	// 풍향			단위:deg
		else if(category.equals("WSD"))	map.put("WSD", fcstValue);	// 풍속			단위:m/s
		
		return map;
	}
}
