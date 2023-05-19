package egovframework.job.vo;

import java.util.List;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyResumeVO {
	private String job;
	private Long cr_num;
	private int payment;
	private String payment_type;
	private String ob_type;
	private String disease;
	private String career;
	private String pr;
	private Long m_num;
	private String education;
	
	
	private List<CompanyResumeRegionVO> region;

}