<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아작스 댓글연습</title>
<style type="text/css">
#modDiv { /* 댓글 수정폼 */
   width: 300px;
   height: 100px;
   background-color: gray;
   position: absolute;
   top: 50%;
   left: 50%;
   margin-top: -50px;
   margin-left: -150px;
   padding: 10px;
   z-index: 1000;
   /*position이 absolute나 fixed로 설정된 곳에서 사용한다. 이 속성을 요소가 겹치는 순서를 제어할 수 있다. 값이 큰것이 먼저 나온다.*/
}
</style>
</head>
<body>
   <%-- 댓글 수정 화면 --%>
   <div id="modDiv" style="display: none;">
      <%-- display:none; css는 해당 요소 안보이게 한다. --%>
      <div class="modal-title"></div>
      <%--댓글 번호--%>
      <div>
         <textarea rows="3" cols="30" id="replytext"></textarea>
      </div>

      <div>
         <button type="button" id="replyModBtn">댓글수정</button>
         <button type="button" id="replyDelBtn">댓글삭제</button>
         <button type="button" id="closeBtn" onclick="modDivClose();">닫기</button>
      </div>
   </div>

   <h2>아작스 댓글 연습페이지</h2>
   <div>
      <div>
         댓글작성자: <input type="text" name="replyer" id="newReplyWriter" />
      </div>
      <br />
      <div>
         댓글 내용:
         <textarea rows="5" cols="30" name="replytext" id="newReplyText"></textarea>
      </div>
      <br />
      <button type="button" id="replyAddBtn">댓글등록</button>
   </div>
   <br />
   <hr />
   <br />

   <%-- 댓글목록이 출력되는 부분 --%>
   <ul id="replies"></ul>
   <script src="https://code.jquery.com/jquery-latest.min.js"></script>
   <script type="text/javascript">
      let bno = 6; //게시판 번호

      getAllList();
      function getAllList() {
         $
               .getJSON(
                     "/replies/all/" + bno,
                     function(data) {
                        let str = "";
                        $(data)
                              .each(
                                    function() {//jQuery each()함수로 반복
                                       str += "<li data-rno='"+this.rno+"' class='replyLi'>"
                                             + this.rno
                                             + " : <span class='com' style='color:red; font-weight:bold;'>"
                                             + this.replytext
                                             + "</span>"
                                             + "<button>댓글수정</button></li><br/>";
                                    });
                        $('#replies').html(str); //jQuery html함수로 해당영역에 문자와 태그를 변경적용
                     });
      }
      getAllList()

      //댓글추가
      $('#replyAddBtn').on('click', function() {
         let replyer = $('#newReplyWriter').val();
         let replytext = $('#newReplyText').val();

         $.ajax({
            type : 'post',
            url : '/replies/insertReply',
            headers : {
               "Content-Type" : "application/json",
               "X-HTTP-Method-Override" : "POST"
            },
            dataType : "text",
            data : JSON.stringify({
               bno : bno,
               replyer : replyer,
               replytext : replytext
            }),
            success : function(result) {//받아오는 것이 성공시 호출되는 콜백함수
               if (result === 'SUCCESS') {
                  alert('댓글이 등록되었습니다.');
                  getAllList(); //댓글목록 함수 호출
               }
            }
         });
      });

      //댓글수정 화면
      $('#replies').on('click', '.replyLi button', function() {
         let reply = $(this).parent(); //parent() 부모요소를 선택 => li태그를 가리킴
         let rno = reply.attr('data-rno'); //data-rno속성값을 가져옴. =>결국 댓글번호
         let replytext = reply.children('.com').text(); //li태그 자식요소의 클래스 선택자 .com은 span 태그를 가리킴. text()함수로 문자 댓글내용만 가져옴

         $('.modal-title').html(rno); //modal-title 클래스선택자에 댓글번호를 표시
         $('#replytext').val(replytext); //replytext 아이디선택자 textarea입력박스에 기존댓글 내용을 표시
         $('#modDiv').show('slow'); //댓글수정화면 보이기
      });

      // 댓글수정 화면 닫기
      function modDivClose() {
         $('#modDiv').hide('slow');
      }

      // 댓글 수정 완료
      $("#replyModBtn").on("click", function() {
         let rno = $(".modal-title").html(); // 댓글 번호
         let replytext = $("#replytext").val(); // 수정할 댓글 내용

         $.ajax( // jQuery 비동기식 Ajax 함수
         {
            type : "put", // 댓글 내용 수정 시 메소드 방식  
            url : "/replies/" + rno,
            headers : {
               "Content-type" : "application/json",
               "X-HTTP-Method-Override" : "PUT"
            },
            data : JSON.stringify({
               replytext : replytext
            }), // 수정할 JSON 타입의 객체 전달
            dataType : "text", // Ajax로 받아오는 자료형
            success : function(result) {
               if (result === "SUCCESS") {
                  alert("댓글이 수정되었습니다!");
                  // 댓글 수정요소 천천히 숨기기
                  $("#modDiv").hide("slow");
                  getAllList();

               }
            }
         });
      });
   </script>
</body>
</html>













