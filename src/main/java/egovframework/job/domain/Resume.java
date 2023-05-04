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
@Table(name="RESUME")
@Getter
@Builder
public class Resume {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long r_id;
	private int age;
    private String place;
    private String job;
    private String payment;
    private String payment_type;
    private String ob_type;
    private String disease;
    private String career;
    private String pr;
    private boolean r_default;
    private boolean r_open;
    private Long m_num;
    private String title;

}
