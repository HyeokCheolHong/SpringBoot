import React, { useState, useEffect } from "react";
import axios from "axios";

function BoardList() {
  const [boardList, setBoardList] = useState([]);

  useEffect(() => {
    /*
    이는 React 프로젝트에서 Axios를 사용하려면 먼저 해당 모듈을 설치해야 한다는 것을 의미합니다.
터미널 또는 명령 프롬프트에서 프로젝트 디렉토리로 이동한 다음 아래 명령을 실행하여 Axios를 설치할 수 있습니다:

코드 복사
npm install axios

또는 Yarn을 사용하는 경우:
코드 복사
yarn add axios

Axios를 설치한 후에는 React 프로젝트에서 이를 가져와서 사용할 수 있습니다.
 보통은 src 디렉토리 내의 파일에서 Axios를 가져오게 됩니다.

javascript
코드 복사
import axios from 'axios';
그러면 이제 Axios를 사용하여 데이터를 가져오는 데 필요한 모듈이 로드됩니다.
*/
    const fetchBoardList = async () => {
      try {
        const response = await axios.get(
          "http://localhost:8063/board_FormData_List"
        );

        // 서버로부터 받은 데이터의 등록일을 대한민국 시간대로 변환
        const koreanTimeBoardList = response.data.map((board) => ({
          ...board,
          regdate: new Date(board.regdate).toLocaleString("ko-KR", {
            timeZone: "Asia/Seoul",
          }),
        }));

        setBoardList(koreanTimeBoardList);
      } catch (error) {
        console.error("Error fetching data: ", error);
      }
    };

    fetchBoardList();
  }, []);

  return (
    <div>
      <h1>목록</h1>
      <ul>
        {boardList.map((board) => (
          <li key={board.no}>{board.name}</li>
        ))}
      </ul>

      <div>
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
            {boardList.map((board) => (
              <tr key={board.id}>
                <td>{board.no}</td>
                <td>{board.title}</td>
                <td>{board.name}</td>
                <td>{board.pwd}</td>
                <td>{board.regdate}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}

export default BoardList;
