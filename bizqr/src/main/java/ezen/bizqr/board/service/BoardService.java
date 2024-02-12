package ezen.bizqr.board.service;

import ezen.bizqr.board.domain.BoardDTO;
import ezen.bizqr.board.domain.BoardVO;
import ezen.bizqr.board.domain.PagingVO;

import java.util.List;

public interface BoardService {

    void register(BoardVO bvo);

    List<BoardVO> getList(PagingVO pgvo);

    BoardDTO getDetail(long bno);

    void modify(BoardVO bvo);

    void remove(long bno);

    int getTotalCount(PagingVO pgvo);

    int removeToFile(String uuid);

}
