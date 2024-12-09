package net.daum.vo;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@SequenceGenerator(
			name = "bno_seq_gename_test",
			sequenceName = "bno_seq8_test",
			initialValue = 1,
			allocationSize = 1
		)
@Table(name="tbl_boards3_test")

public class BoardVO {
	
	@Id //기본키 컬럼;
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE, // 사용할 전략을 시퀀스로 선택
			generator="bno_seq8_gename" // 시퀀스 생성기에 설정해 놓은 시퀀스 제너레이터 이름
			)
	private int bno;
	private String writer;
	private String title;
	
	@Column(length = 4000)
	private String cont;
	
	@CreationTimestamp
	private String regdate;
	
	@UpdateTimestamp
	private String updatedate;
	
}
