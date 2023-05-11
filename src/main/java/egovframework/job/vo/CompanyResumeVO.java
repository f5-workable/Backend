package egovframework.job.vo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CompanyResumeVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cr_num;
	private int age;
	private String place;
	private String job;
	private String payment_type;
	private int payment;
	private String ob_type;
	private String disease;
	private String career;
	private String pr;
	private Long m_num;
	private String title;
}