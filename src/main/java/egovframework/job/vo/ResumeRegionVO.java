package egovframework.job.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class ResumeRegionVO {
	private long region_id;
	private long r_id;
	private String region;
}