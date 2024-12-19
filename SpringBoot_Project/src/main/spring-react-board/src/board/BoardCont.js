import React, { useState, useEffect } from "react";
import axios from "axios";
import { useParams, useNavigate } from "react-router-dom";

function BoardCont() {
  const [board, setBoard] = useState(null);
  const { no } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    const fetchPost = async () => {
      try {
        const response = await axios.get(
          `http://localhost:8068/board_FormData_cont/${no}`
          //"http://localhost:8053/board_FormData_cont/75"
        );
        setBoard(response.data);
      } catch (error) {
        console.error("Error fetching data: ", error);
      }
    };

    fetchPost();
  }, [no]);

  const handleEdit = () => {
    // 수정 기능 구현
    navigate(`/board_edit/${no}`);
  };

  const handleDelete = async () => {
    try {
      await axios.delete(`http://localhost:8068/board_FormData_Del/${no}`);
      console.log("Post deleted successfully");
      // 삭제가 완료되면 어떤 작업을 수행할지 여기에 추가할 수 있습니다.

      alert("삭제 성공했습니다!");
      navigate("/"); //목록으로 이동
    } catch (error) {
      console.error("Error deleting post: ", error);
    }
  };

  const handleList = () => {
    // 목록으로 돌아가는 기능 구현
    navigate("/");
  };

  if (!board) {
    return <div></div>;
  }

  return (
    <div>
      <h2 style={{ marginTop: "100px", textAlign: "center" }}>
        게시판 내용보기
      </h2>
      <table style={{ margin: "auto" }} border="1">
        <tbody>
          <tr>
            <th>글쓴이</th>
            <td>{board.name}</td>
          </tr>
          <tr>
            <th>제목</th>
            <td>{board.title}</td>
          </tr>
          <tr>
            <th>내용</th>
            <td>
              {board.content.split("\n").map((line, index) => (
                <React.Fragment key={index}>
                  {line}
                  <br />
                </React.Fragment>
              ))}
            </td>
          </tr>
          <tr>
            <th>작성자</th>
            <td>{board.name}</td>
          </tr>
          <tr>
            <td colSpan="2" style={{ textAlign: "center" }}>
              <button onClick={handleEdit}>수정</button>&nbsp;
              <button onClick={handleDelete}>삭제</button>&nbsp;
              <button onClick={handleList}>목록</button>&nbsp;
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}

export default BoardCont;
