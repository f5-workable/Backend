package egovframework.hbz.web;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.hbz.service.impl.EgovHbzScrapServiceImpl;

@Controller
public class EgovHbzScrapController {
	
	Logger logger = LoggerFactory.getLogger(EgovHbzScrapController.class);
	
	@Autowired
	EgovHbzScrapServiceImpl egovHbzScrapServiceImpl;
	
	/*
	 * @Scheduled(fixedDelay = 1000, initialDelay = 5000) public void
	 * scheduledTest() throws Exception {
	 * 
	 * Date date = new Date();
	 * 
	 * SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	 * 
	 * String nowDate = dateFormat.format(date);
	 * 
	 * System.out.println("스케줄러 현재시간 : " + nowDate ); }
	 */
	
	// 네이버 뉴스 랭킹
	@RequestMapping(value="/hbz/newsRank.do")
	public String newsRank(Model model) throws Exception{
		
		System.out.println("newsRank Start");
		
		egovHbzScrapServiceImpl.newsRank(model);
		
		return "/scrp/newsRank";
	}
	
	// 웹툰 페이지 스크래핑
	@RequestMapping(value="/hbz/webtoonScrap.do")
	public String webtoonScrap(Model model) throws Exception{
		
		egovHbzScrapServiceImpl.webtoonScrap(model);
		
		return "/scrp/webtoonScrap";
	}
	
	// DB조회 후 스크래핑 페이지로 전환
	@RequestMapping(value="/hbz/scrap.do", method=RequestMethod.GET)
	public String scrapPage(@RequestParam int pageNo, Model model) throws Exception{
		
		logger.info("### scrapPage Start");

		List<Map<String, Object>> list = egovHbzScrapServiceImpl.scrapView(pageNo,model);
		
		model.addAttribute("list", list);
		
		return "/scrp/ScrapPage";
	}
	
	// 공공데이터포털 - 기상청 [초단기실황] 데이터 스크래핑
	@RequestMapping(value="/hbz/datapotal.do", method=RequestMethod.GET)
	public String getUltraSrtNcst(Model model) throws Exception{
		
		logger.info("### dataPotalScrap Start");
		
		egovHbzScrapServiceImpl.getUltraSrtNcst();
		
		int pageNo = 1;
		
		List<Map<String, Object>> list = egovHbzScrapServiceImpl.scrapView(pageNo,model);
		
		model.addAttribute("list", list);
		
		return "/scrp/ScrapPage";
	}
	
	
	// 공공데이터포털 -기상청 [단기예보]
	@RequestMapping(value="/hbz/getVilageFcst.do", method=RequestMethod.GET)
	public String getVilageFcst(@RequestParam String in_area,@RequestParam String in_time, Model model) throws Exception{
		
		System.out.println("in_area : " + in_area);
		System.out.println("in_time : " + in_time);
		
		Map<String, Object> map = egovHbzScrapServiceImpl.getVilageFcst(in_area, in_time);
		
		model.addAttribute("map",map);
		
		return "/scrp/getVilageFcst";
	}
}