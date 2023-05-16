package egovframework.job.vo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ResumeVO {

	/** 지원자 아이디 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long r_id;

	/** 지원자 나이 */
	private int age;

	/** 지원자 거주지역 */
	private String place;

	/** 지원자 희망직업 */
	private String job;

	/** 지원자 임금형태 */
	private String payment_type;

	/** 지원자 임금 */
	private int payment;

	/** 지원자 장애유형 */
	private String ob_type;

	/** 지원자 장애 */
	private String disease;

	/** 지원자 경력 */
	private String career;

	/** 지원자 소개 */
	private String pr;

	private boolean r_default;
	private boolean r_open;

	/** 지원자 회원번호 */
	private Long m_num;

	/** 지원자 제목 */
	private String title;
}