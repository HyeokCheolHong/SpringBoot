import { useState, useRef } from "react";
import { useNavigate } from "react-router-dom";

const BoardWrite = () => {
  const [name, setName] = useState(""); //각 입력 필드에 대한 상태를 useState 훅을 사용하여 관리
  const [title, setTitle] = useState("");
  const [pwd, setPwd] = useState("");
  const [content, setContent] = useState("");
  const navigate = useNavigate();

  const nameInputRef = useRef(null); //입력필드로 포커스를 이동시키기 위한 useRef Hook을 사용
  const titleInputRef = useRef(null);
  const pwdInputRef = useRef(null);
  const contentInputRef = useRef(null);

  const handleSubmit = (e) => {
    e.preventDefault();
    /*
    e.preventDefault()은 이벤트의 기본 동작을 취소하는 함수입니다. 일반적으로 <form> 요소 내에서
     발생하는 submit 이벤트에서 주로 사용됩니다. 이 함수를 호출하면 폼이 제출되는 것을 막고,
      대신에 자바스크립트 코드에서 원하는 동작을 수행할 수 있습니다.
     예를 들어, 위의 코드에서 handleLogin 함수 내에서 e.preventDefault()을 
     호출한 이유는 폼이 제출될 때 브라우저가 새로고침되는 동작을 막기 위해서입니다.
     대신에 자체적인 유효성 검사 로직을 실행하고, 유효성이 통과되면 회원가입을 완료하는 동작을 합니다.
     이렇게 함으로써 브라우저의 기본 동작을 방지하고, 원하는 동작을 수행할 수 있게 됩니다.
    */

    if (name === "") {
      alert("글쓴이를 입력하세요!");
      nameInputRef.current.focus();
      return;
    }

    if (!title) {
      alert("제목을 입력하세요!");
      titleInputRef.current.focus();
      return;
    }

    if (!pwd) {
      alert("비밀번호를 입력하세요!");
      pwdInputRef.current.focus();
      return;
    }

    if (!content) {
      alert("내용을 입력하세요!");
      contentInputRef.current.focus();
      return;
    }

    //전송이후 콘솔모드에서 확인
    console.log("글쓴이 : ", name);
    console.log("글제목 : ", title);
    console.log("비밀번호 : ", pwd);
    console.log("글내용 : ", content);

    //const formData = new FormData(e.target); // Form 데이터 가져오기
    // 서버로 데이터 전송

    /*
    리액트에서 입력한 정보를 서버로 보내는 것은 주로 HTTP 요청을 사용하여 이루어집니다. 
    주로 사용되는 방법은 다음과 같습니다.
Fetch API 또는 Axios 사용: 리액트 애플리케이션에서는 Fetch API나 Axios와 같은
 HTTP 클라이언트를 사용하여 서버에 요청을 보낼 수 있습니다. 이러한 라이브러리를 
 사용하면 간편하게 HTTP 요청을 생성하고 보낼 수 있습니다.
 */
    fetch("http://localhost:8068/board_FormData_ok", {
      //서버 매핑주소
      method: "POST", //보내는 방식
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        //보내는 json 데이터
        name: name,
        title: title,
        pwd: pwd,
        content: content,
      }),
    })
      .then((response) => response.json())
      .then((data) => console.log(data))
      .catch((error) => console.error("Error:", error));

    //데이터 전송후 입력필드 초기화
    setName("");
    setTitle("");
    setPwd("");
    setContent("");

    alert("저장 성공");
    // navigate("/");
    // 페이징 목록(루트경로)로 이동하면 에러발생
  }; //handleSubmit()

  const handleList = () => {
    // 목록으로 돌아가는 기능 구현
    navigate("/");
  };

  return (
    <div>
      <h2>게시판 글작성하기(리액트)</h2>
      <form method="post" onSubmit={handleSubmit}>
        <div>
          <label htmlFor="name">글쓴이:</label>
          <input
            type="text"
            name="name"
            id="name"
            size="14"
            ref={nameInputRef}
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </div>
        <br />
        <div>
          <label htmlFor="title">제목:</label>
          <input
            type="text"
            name="title"
            id="title"
            size="36"
            ref={titleInputRef}
            value={title}
            onChange={(e) => setTitle(e.target.value)}
          />
        </div>
        <br />
        <div>
          <label htmlFor="pwd">비밀번호 : </label>
          <input
            type="password"
            name="pwd"
            id="pwd"
            size="14"
            ref={pwdInputRef}
            value={pwd}
            onChange={(e) => setPwd(e.target.value)}
          />
        </div>
        <br />
        <div>
          <label htmlFor="content">내용:</label>
          <textarea
            rows="10"
            cols="36"
            name="content"
            id="content"
            ref={contentInputRef}
            value={content}
            onChange={(e) => setContent(e.target.value)}
          />
        </div>
        <hr />
        <button type="submit">글쓰기</button>&nbsp;
        <button onClick={handleList}>목록</button>
      </form>
    </div>
  );
};

export default BoardWrite;
