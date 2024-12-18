import { Route, Routes } from "react-router-dom";
import BoardEdit from "./board/BoardEdit";
import BoardListPaging from "./board/BoardListPaging";
import BoardCont from "./board/BoardCont";
import BoardWrite from "./board/BoardWrite";
import BoardList from "./board/BoardList"; // 페이징 안되는 리액트 게시판 목록 컴포넌트 임포트

const App = () => {
  return (
    <Routes>
      <Route path="/" element={<BoardListPaging />} />
      {/* path="/" : root 경로 (paging이 되는 경로 지정해야함) */}
      <Route path="board/:no" element={<BoardCont />} />
      <Route path="board_Write" element={<BoardWrite />} />
      <Route path="board_edit/:no" element={<BoardEdit />} />
    </Routes>
  );
}

export default App;
