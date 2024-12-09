package net.daum.vo;

import org.springframework.web.bind.annotation.GetMapping;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(exclude = {"val03"}) //exclude속성을 사용하여 val03변수를 toString메서드에서 제외

public class SampleVO {
	
	private String val01;
	private String val02;
	private String val03;
	
	
}
