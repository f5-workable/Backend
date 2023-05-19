package egovframework.job.vo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyResumeVO {
	private Long cr_num;
	private int payment;
	private String payment_type;
	private String ob_type;
	private String disease;
	private String career;
	private String pr;
	private Long m_num;
	private String education;

}