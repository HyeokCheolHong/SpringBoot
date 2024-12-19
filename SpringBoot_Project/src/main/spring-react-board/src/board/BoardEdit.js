import React, { useState, useEffect, useRef } from "react";
import { useParams, useNavigate } from "react-router-dom";

const BoardEdit = () => {
  //const [no, setNo] = useState('');
  const [name, setName] = useState("");
  const [title, setTitle] = useState("");
  const [pwd, setPwd] = useState("");
  const [content, setContent] = useState("");
  const { no } = useParams();
  const navigate = useNavigate();

  const nameInputRef = useRef(null);
  const titleInputRef = useRef(null);
  const pwdInputRef = useRef(null);
  const contentInputRef = useRef(null);

  useEffect(() => {
    // Fetch post data using no
    fetch(`http://localhost:8068/board_FormData_cont/${no}`)
      .then((response) => response.json())
      .then((data) => {
        setName(data.name);
        setTitle(data.title);
        setPwd(data.pwd);
        setContent(data.content);
      })
      .catch((error) => console.error("Error fetching post data:", error));
  }, [no]);

  const handleSubmit = (e) => {
    e.preventDefault();

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

    fetch(`http://localhost:8068/board_FormData_Edit/${no}`, {
      //서버 매핑주소
      method: "PUT", //보내는 방식
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        //보내는 json 데이터
        no: no,
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
    // setName('');
    // setTitle('');
    //setPwd('');
    // setContent('');

    window.alert("게시판 수정에 성공했습니다!");
    navigate(`/board/${no}`);
  }; //handleSubmit()

  const handleCancel = () => {
    // 목록으로 돌아가는 기능 구현
    navigate(`/board/${no}`);
  };

  return (
    <div>
      <h2>게시판 글 수정하기(리액트)</h2>
      <form method="post" onSubmit={handleSubmit}>
        <input type="hidden" name="no" value={no} />
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
        <button type="submit">수정 완료</button>&nbsp;
        <button type="button" onClick={handleCancel}>
          수정취소
        </button>
      </form>
    </div>
  );
};

export default BoardEdit;
