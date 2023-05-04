package egovframework.job.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="COMPANY")
@Getter
@Builder
public class Company {
	@Id 
	@Column(nullable = false)
	private String c_name;
	private String c_id;
	private String password;
	private String rnum;
	@Column(nullable = false)
	private String address;
	private String logo;
	@Column(nullable = false)
	private String company_type;
	@Column(nullable = false)
	private String phone;
}
