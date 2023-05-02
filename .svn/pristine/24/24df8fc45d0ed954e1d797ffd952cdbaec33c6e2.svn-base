package egovframework.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import egovframework.hbz.service.impl.EgovHbzScrapServiceImpl;

@Component
public class Scheduler {
	
	@Autowired
	EgovHbzScrapServiceImpl egovHbzScrapServiceImpl;
	
//	@Scheduled(cron = "10 * * * * *")
	public void scheduler() throws Exception {
		System.out.println("scheduler start....");
		
		egovHbzScrapServiceImpl.getUltraSrtNcst();
		
		System.out.println("스케줄러 끗~!");

	}
}