package egovframework.job.domain;

import javax.persistence.Column;
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
@Table(name="MEMBER")
@Getter
@Builder
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private long m_num ;
	@Column(nullable = false)
	private String id;
	@Column(nullable = false)
	private String password;
	private String email;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String phone;
	@Column(nullable = false)
	private String profil; 
	private boolean deleted;
}
