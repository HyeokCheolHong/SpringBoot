/**
 *   Spring Security 실습
 */

function join_check() {
	if($.trin($('#mem_id').val()) === '') {
		alert('회원 아이디를 입력하세요!');
		$('#mem_id').val('').focus(); // 아이디 입력박스 초기화 및 포커스 이동
		return false;
	}
	
	let mem_pwd = $.trim($('#mem_pwd').val());
	let mem_pwd2 = $.trim($('#mem_pwd2').val());
	if(mem_pwd.length === 0) {
		alert("비밀번호를 입력하세요!");
		$('#mem_pwd').val('').focus();
		return false;
	}
	
	if(mem_pwd2.length === 0) {
		alert("비밀번호 확인을 입력하세요!");
		$('#mem_pwd2').val('').focus();
		return false;
	}
	
	if(mem_pwd != mem_pwd2) {
		alert('비밀번호가 다릅니다!');
		$('mem_pwd').val('');
		$('mem_pwd2').val('');
		$('mem_pwd').focus();
		return false;
	}
	
	if($.trim($('#mem_name').val()) == '') {
		alert('회원이름을 입력하세요!');
		$('#mem_name').val('').focus();
		return false;
	}
} // join_check()


// 아이디 중복검색
function id_check() {
	$('#idcheck').hide(); // idcheck 영역을 숨김
	$mem_id = $.trim($('#mem_id').val());
	
	// 아이디 입력길이 체크
	if($mem_id.length < 4) { 
		$newtext = "<font color='red' size='3' ><b>아이디는 4글자 이상이여야 합니다!</b></font>";
		duplication_check();
		$('#mem_id').val('').focus();
		return false;
	}
	
	if($mem_id.length > 12) {
		$newtext = "<font color='red' size='3' ><b>아이디는 12글자 이하이여야 합니다!</b></font>";
		duplication_check();
		$('#mem_id').val('').focus();
		return false;
	}
	
	// 정규표현식으로 아이디 유효성 검증
	if(!(validate_userid($mem_id))){
		$newtext = "<font color='red' size='3' ><b>아이디는 12글자 이하이여야 합니다!</b></font>";
		duplication_check();
		$('#mem_id').val('').focus();
		return false;
	}
	
	/*
		비동기식 Ajax 통신 시 403 forbidden(권한없음) 에러 발생 해결법
		에러 원인 -> csrf(Cross-origin request forgery) 의 token 누락으로 발행한다.
		
		member_join.jsp에 head태그 내에 csrf meta tag를 추가해 준다.
		<meta name="_csrf_header" content="${_csrf.headerName}" >
		<meta name="_csrf" content="${_csrf.token}" >
		
		let header = $("meta[name='_csrf_header']").attr('content')
		let token = $('meta[name='_csrf']").attr('content');
		
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		}
		코드를 추가하면 403 접근 금지 에러가 해결된다.
	*/
	
	let header = $("meta[name='_csrf_header']").attr('content')
	let token = $("meta[name='_csrf']").attr('content');
	
	// 비동기식 아이디 중복확인
	$.ajax({
		type:'POST', // 데이터를 보내는 방법
		url:'member_idcheck', // 매핑주소
		beforSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		data: {"id": $mem_id}, // id 파라미터 이름에 회원아이디를 저장
		datatpye: int, // 서버로부터 받아오는 데이터타입 지정
		success:function(result){ // 받아오는 것에 성공시 호출되는 콜백함수. 받아온 데이터는 매개변수 result에 저장
			if(result === 1) { // 중복 아이디인 경우
				$newtext = "<font color='red' size='3'><b>중복 아이디 입니다</b></font>";
				duplication_check();
				$('#mem_id').val('').focus();
				return false;
			} else { // 중복 아이디가 아니면 즉 사용가능한 아이디면
				$newtext = "<font color='blue' size='3' ><b>사용 가능한 아이디입니다.</b></font>";
				duplication_check();
				$('#mem_pwd').val('').focus();
			}
		},
		error:function() { // 비동기식 Ajax로 백엔드 서버 데이터를 못받아 와서 에러가 발생했을때
			alert('data error');
		}
	})
	
} // idcheck();

function duplication_check() {
	$('#idcheck').text(''); // idcheck 영역 초기화
	$('#idcheck').show(''); // idcheck 영역을 보이게한다.
	$('#idcheck').append($newtext); // 경고 문자열 추가

}


// 아이디를 정규표현식으로 유효성 검증
function validate_userid($id) {
	let pattern = new RegExp(/^[a-z0-9_]+%/);
	// 아이디를 영문소문자와 숫자 와 _ 조합으로 처리
	return pattern.test($id);
}