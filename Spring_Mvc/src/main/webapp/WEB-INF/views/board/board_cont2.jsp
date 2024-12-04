<!-- 2024-12-03 댓글이 포함된 게시판 -->
<%@ page contentType="text/html; charset=UTF-8"%>

<!-- 2024-12-04 댓글 추가관련 taglib추가 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>스프링 MVC 게시판 내용보기</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				let formObj = $("form[role='form']");
				
				// 수정폼으로 매핑이동 ($표시는 전부 jQuery)
				$('.btn-edit').on("click", function() {
					formObj.attr("action", "/board/board_edit");
					// form action 속성 부여하기
					formObj.attr("method", "get");
					// 속성을 지정
					
					formObj.submit();
					// 히든 번호값 제출
				});
				
				
				// 삭제 매핑으로 이동
				$('.btn-del').on('click', function() {
					formObj.attr("action", "/board/board_del");
					// 액션 속성에 매핑주소 설정
					
					formObj.submit();
					// 별도의 메서드 방식을 지정하지 않아 폼태그에서 지정된 post방식으로 전송된다.
				});
			});
		</script>
		
		<!-- 팝업창이 사용자화면 정가운데 위치하게 position:fixed 진행 -->
		<style type="text/css">
    	#modDiv { /* 댓글 수정폼 */
        width: 300px;
        height: 100px;
        background-color: gray;
        position: fixed; /* fixed로 설정하면 뷰포트 기준으로 위치가 고정됨 */
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%); /* 요소의 중심을 기준으로 이동 */
        padding: 10px;
        z-index: 1000; /* 요소 겹침 순서 제어 */
    	}
    	
    	/* 2024-12-04 댓글 카운트 span css 속성 코드 */
    	span.reply_color {
    		color:red;
    		background:gold;
    		font-size:20px;
    		border-radius:10px;
    		padding:5px;
    		box-shadow:5px 5px 5px gray;
    	}
		</style>
		
		
		<!-- 댓글삭제 팝업창 위치가 최상단 50%에만 위치하므로 불편함 사용자 화면 50%에 위치가 고정되도록 하기위한 주석처리
		<style type="text/css">
			#modDiv{ /* 댓글 수정폼 */
		  	width:300px;
		  	height:100px;
		  	background-color:gray;
		  	position: absolute;
		  	top:50%;
		  	left:50%;
		  	margin-top:-50px;
		  	margin-left:-150px;
		  	padding:10px;
		  	z-index:1000; /* position이 absolute나 fixed로 설정된 곳에서 사용한다. 이 속성은 요소가 겹쳐지는 순서를 제어할 수 있다. 값이 큰것이 먼저 나온다. */
		}
		 -->
	
	</head>
	<body>
		<form method="post" role="form">
			<input type="hidden" name="bno" value="${bc.bno}" />
			<%-- hidden은 사용자 뷰 화면에는 보이지 않지만 bno 파라미터 이름에 value속성 값인 게시판 번호를 담아서 post로 전달한다. --%>
			<%-- hidden은 Page Source 에서는 보여지므로 보안산 중요한 Data는 hidden으로 보내면 안된다. --%>
			
			<input type="hidden" name="page" value="${page}"/>
			<%-- 페이징에서 책갈피 기능 구현 때문에 쪽번호 생성--%>
		</form>
		<table border="1">
			<tr>
				<th colspan="2">스프링 MVC 게시판 내용</th>
			</tr>
			<tr>
				<th>제목</th>
				<td>${bc.title}</td>
			</tr>
			<tr>
				<th>내용</th>
				<td>${bcont}</td>
				<!-- bc.cont는 줄바꿈이 되어있지 않기때문에 bcont를 가져옴 -->
			</tr>
			<tr>
				<th>조회수</th>
				<td>${bc.viewcnt}</td>
			</tr>
			<tr>
				<th colspan="2">
					<button type="submit" class="btn-edit">수정</button>
					<button type="submit" class="btn-del">삭제</button>
					<button type="button" onclick="location='board_list?page=${page}';">목록</button>
				</th>
			</tr>
		</table>
		
		<br/>
		<hr/>
		<br/>
		
		<%-- 댓글 수정 화면 --%>
		<div id="modDiv" style="display:none;"> <%-- display:none; css는 해당 요소 안보이게 한다. --%>
			<div class="modal-title"></div> <%-- 댓글 번호 --%>
			<div>
				<textarea rows="3" cols="30" id="replytext"></textarea>
			</div>
			<div>
				<button type="button" id="replyModBtn">댓글수정</button>
				<button type="button" id="replyDelBtn">댓글삭제</button>
				<button type="button" id="closeBtn" onClick="modDivClose();">취소</button>
			</div>
		</div>
		
		<!-- 2024-12-04 댓글 카운트 관련 추가 코드작업 -->
		<c:if test="${bc.replycnt != 0}">
			[댓글 개수 : ]<span class="reply_color">${bc.replycnt} 개</span>	
		</c:if>
		<!-- 2024-12-04 댓글 카운트 관련 추가 코드작업 -->
		
		<h2>Ajax 댓글 연습페이지</h2>
		<div>
			<div>
				댓글 작성자 : <input type="text" name="replyer" id="newReplyWriter" />
			</div> <br/>
			<div>
				댓글 내용 : <textarea rows="5" cols="30" name="replytext" id="newReplyText"></textarea>
			</div> <br/>
			<button type="button" id="replyAddBtn">댓글등록</button>
		</div> <br/> <hr/> <br/>
		
		<%-- 댓글 목록 화면 --%>
		<ul id="replies">
			
		</ul>
			<script>
				// let bno = 25;	// 게시판 번호
				let bno = ${bc.bno}; // 자바스크립트 코드 내에서 JSP문법인 EL(Expression lnguage:표현언어)을 적용
				
				getAllList(); // 댓글 목록 함수 호출
				function getAllList() {
					$.getJSON("/replies/all/"+bno, function(data) {
						let str="";
						$(data).each(function(){ // jQuery each()함수로 반복
							str += "<li data-rno='"+this.rno+"' class ='replyLi'>" + this.rno+
							" : <span class='com' style='color:red;font-weight:bold;'>"+this.replytext+"</span>"+"<button>댓글수정</button></li><br/>";
						});
						$('#replies').html(str); //jQuery html()함수로 해당영역에 문자와 태그를 변경적용
					});
				}; // getAllList
				
				// 댓글추가
				$('#replyAddBtn').on('click',function() {
					let replyer = $('#newReplyWriter').val(); // 댓글 작성자
					let replytext = $('#newReplyText').val(); // 댓글 내용
					
					$.ajax({ // jQuery 비동기식 Ajax 함수
						type:'post',
						url:'/replies/insertReply', // 댓글 저장 매핑주소
						headers:{
							"Content-Type":"application/json",
							"X-HTTP-Method-Override":"POST"
						},
						dataType:'text',
						data:JSON.stringify({
							bno:bno,
							replyer:replyer,
							replytext:replytext
						}),
						success:function(result){ // 받아오는데 성공시 호출되는 콜백함수
							if(result === 'SUCCESS') {
								alert('댓글이 등록되었습니다.');
								location.reload(); // 새로고침
								getAllList(); // 댓글 목록 함수 호출
							};
						}
					});
				});
				
				// 댓글 수정화면
				$('#replies').on('click','.replyLi button', function() {
					let reply = $(this).parent(); // parent() 부모요소를 선택 => li태그를 가리킴
					let rno = reply.attr('data-rno'); // data-rno 속성값을 가져옴 => 댓글번호
					let replytext = reply.children('.com').text(); 
					// li태그 자식요소의 클래스 선택자 .com은 span태그를 가리킴. text()함수로 문자 댓글내용만 가져옴
					
					$('.modal-title').html(rno); // modal-title 클래스 선택자에 댓글번호를 표시
					$('#replytext').val(replytext); // replytext 아이디선택자 textarea입력박스에 기존댓글내용을 표시
					$('#modDiv').show('slow'); // 댓글 수정화면 보이기
				});
				
				// 댓글수정 화면 닫기
				function modDivClose(){
				$('#modDiv').hide('slow)');					
				};
				
				// 댓글 수정완료
				$('#replyModBtn').on('click', function(){
					let rno = $('.modal-title').html(); // 댓글번호
					let replytext = $('#replytext').val(); // 수정할 댓글내용
					
					$.ajax({ // jQuery 비동기식 Ajax 함수
						type: 'put', // 댓글내용 수정시 메서드방식은 put
						url:'/replies/'+rno, // 수정완료 매핑주소
						headers:{
							"Content-type":"application/json",
							"X-HTTP-Method-Override":"PUT"
						},
						data:JSON.stringify({replytext:replytext}), // 수정할 내용 JSON자료
						dataType:"text", // Ajax로 받아오는 자료형
						success:function(result){
							if(result === 'SUCCESS') {
								alert('댓글이 수정되었습니다!');
								$('#modDiv').hide('slow'); // 댓글 수정화면 닫기
								getAllList(); // 댓글 목록함수 호출
							};
						}
					});
				});
				
				// 댓글 삭제하기
				$('#replyDelBtn').on('click', function(){
					let rno = $('.modal-title').html(); // 댓글번호
					
					$.ajax({ // jQuery 비동기식 Ajax 함수
						type: 'delete', // 댓글 삭제시 베서드방식은 delete
						url:'/replies/'+rno, // 삭제할 매핑주소
						headers:{
							"Content-type":"application/json",
							"X-HTTP-Method-Override":"DELETE"
						},
						dataType:"text",
						success:function(result){
							if(result === 'SUCCESS') {
								alert('댓글이 삭제되었습니다!');
								$('#modDiv').hide('slow'); // 댓글 삭제화면 닫기
								location.reload(); // 새로고침
								getAllList(); // 댓글 목록함수 호출
							}
						}
					});
				});
			</script>
	</body>
</html>