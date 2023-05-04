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
@Table(name="WISH")
@Getter
@Builder
public class Wish {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long w_id;
	
	private Long m_num;
	private Long j_num;
}
