package egovframework.job.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResumeRegionVO {
	
	private Long region_id;
	private Long r_id;
	private String region;
}