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
@Table(name="COMPANYRESUME")
@Getter
@Builder
public class Companyresume {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cr_id;
	private int age;
    private String place;
    private String job;
    private String payment;
    private String payment_type;
    private String ob_type;
    private String disease;
    private String career;
    private String pr;
    private Long m_num;
    private String title;

}
