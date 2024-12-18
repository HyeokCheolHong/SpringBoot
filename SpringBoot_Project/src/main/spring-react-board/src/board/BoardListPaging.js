import React, { useState, useEffect } from "react";
import axios from "axios";
import { Link, useNavigate } from "react-router-dom";

function BoardListPaging() {
  const [posts, setPosts] = useState([]);
  const [currentPage, setCurrentPage] = useState(1);
  const [postsPerPage] = useState(7); //한페이지 보여지는 목록개수
  const navigate = useNavigate();
  /* useNavigate Hook은 Link 컴포넌트를 사용하지 않고 다른 페이지로 이동해야 하는 상황에 사용하는 Hook 이다.
   */

  /* React에서는 JSTL(JSP Standard Tag Library)과 EL(Expression Language)
  과 같은 기능을 직접적으로 지원하지 않습니다. 
  */

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8063/board_FormData_List"
        );

        // 서버로부터 받은 데이터의 등록일을 대한민국 시간대로 변환
        const koreanTimeRegdate = response.data.map((post) => ({
          ...post,
          regdate: new Date(post.regdate).toLocaleString("ko-KR", {
            timeZone: "Asia/Seoul",
          }),
        }));

        setPosts(koreanTimeRegdate);
      } catch (error) {
        console.error("Error fetching data: ", error);
      }
    };

    fetchPosts();
  }, []);

  // 현재 페이지의 게시글을 가져오는 함수
  const indexOfLastPost = currentPage * postsPerPage;
  const indexOfFirstPost = indexOfLastPost - postsPerPage;
  const currentPosts = posts.slice(indexOfFirstPost, indexOfLastPost);

  // 페이지 변경 핸들러
  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  const handleWrite = () => {
    navigate("/board_write"); //게시판 글쓰기 폼으로 이동
  };

  return (
    <div
      style={{ display: "flex", flexDirection: "column", alignItems: "center" }}
    >
      {/*
        display: flex는 요소를 플렉스 컨테이너로 만들어 자식 요소를 배치할 수 있도록 합니다. 
        flexDirection: column은 자식 요소들을 세로 방향으로 배치하도록 합니다. alignItems: center는 
        수직 가운데 정렬을 담당합니다. 이렇게 함으로써 리스트 부분이 수평으로 가운데 정렬될 것입니다.
      */}
      <h1>리액트 게시판 목록</h1>
      <table>
        <thead>
          <tr>
            <th>번호</th>
            <th>글제목</th>
            <th>글쓴이</th>
            <th>비밀번호</th>
            <th>등록날짜</th>
          </tr>
        </thead>
        <tbody>
          {/* 게시글 목록을 매핑하여 테이블로 표시 */}
          {currentPosts.map((board) => (
            <tr key={board.id}>
              <td>{board.no}</td>
              <td>
                <Link to={`http://localhost:3000/board/${board.no}`}>
                  {board.title}
                </Link>
              </td>
              <td>{board.name}</td>
              <td>{board.pwd}</td>
              <td>{board.regdate}</td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* 페이지네이션 */}
      <table>
        <tr>
          {Array.from(
            { length: Math.ceil(posts.length / postsPerPage) },
            (_, index) => (
              <th key={index}>
                <button onClick={() => paginate(index + 1)}>{index + 1}</button>
              </th>
            )
          )}
        </tr>
      </table>
      <br />
      <div>
        <button type="button" onClick={handleWrite}>
          게시판 글쓰기
        </button>
      </div>
    </div>
  );
}

export default BoardListPaging;
