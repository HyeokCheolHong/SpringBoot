<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>

<!-- 2024-12-03 댓글 수정 View 작성 HomeController -->



<html>
	<head>
		<meta charset="UTF-8">
		<title>Ajax 댓글 연습</title>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<style>
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
				z-index:1000;
				/* position absolute나 fixed로 설정된 곳에서 사용한다 */
				/* 이 속성은 요소가 겹쳐지는 순서를 제어할 수 있다. 값이 큰것이 먼저 나온다. */
			}
		</style>
	</head>
	<body>
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
				let bno = 25;	// 게시판 번호
				
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
								getAllList(); // 댓글 목록함수 호출
							}
						}
					});
				});
			</script>
	</body>
</html>