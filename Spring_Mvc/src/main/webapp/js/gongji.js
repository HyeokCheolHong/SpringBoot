/**
 *  2024-12-04 공지사항 작성용 기능(js)
 */

function gongji_check() {
	if($.trim($("#gongji_writer").val()) == "") {
		// alert("글쓴이를 입력하세요");
		$("#error_gongji_writer").html("<font size='1' color='red'>글쓴이를 입력하세요</font>");
		$("#gongji_writer").val("").focus();
		
		return false;
	}else { // 글쓴이를 입력한 경우
		// 에러메시지 지우기
		$("#error_gongji_writer").text("");
	}
	
	if($.trim($("#gongji_title").val()) == "") {
		// alert("제목을 입력하세요");
		$("#error_gongji_title").html("<font size='1' color='red'>제목을 입력하세요</font>");
		$("#gongji_title").val("").focus();
		
		return false;
	}else { // 제목을 입력한 경우
		// 에러메시지 지우기
		$("#error_gongji_title").text("");
	}
	
	if($.trim($("#gongji_content").val()) == "") {
		// alert("내용 입력하세요");
		$("#error_gongji_content").html("<font size='1' color='red'>내용을 입력하세요</font>");
		$("#gongji_content").val("").focus();
		
		return false;
	}else { // 내용을 입력한 경우
		// 에러메시지 지우기
		$("#error_gongji_content").text("");
	}
	
}

function gongji_reset() {
	$("#gongji_writer").focus(); // 글쓴이 입력필드로 포커스 이동
	$("error_gongji_writer").text(""); // 글쓴이 에러메시지 초기화
	$("error_gongji_title").text(""); // 제목 에러메시지 초기화
	$("error_gongji_content").text(""); // 내용 에러메시지 초기화
}