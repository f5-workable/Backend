package egovframework.job.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="JOBINFO")
@Getter
@Builder
public class Jobinfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long j_id;
	
	private String d_date;
	private String j_name;
	private String job_type;
	private String employment_type;
	private String payment_type;
	private String payment;
	private String career;
	private String edu;
	private String r_date;
	private String detail;
}