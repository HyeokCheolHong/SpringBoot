package net.daum.vo;

// 데이터 저장빈 클래스
public class ProductVO {
	private String name; // 상품명
	private int price; // 상품가격
	
	public ProductVO(String name, int price) {
		this.name = name;
		this.price = price;
	}// 생성자 오버로딩

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}
	
	
}
