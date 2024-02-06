package ezen.bizqr.service;

import ezen.bizqr.domain.BoardDTO;
import ezen.bizqr.domain.BoardVO;
import ezen.bizqr.domain.PagingVO;

import java.util.List;

public interface BoardService {

    void register(BoardDTO boardDTO);

    List<BoardVO> getList(PagingVO pgvo);

    BoardDTO getDetail(long bno);

    void modify(BoardDTO boardDTO);

    void remove(long bno);

    int getTotalCount(PagingVO pgvo);

    int removeToFile(String uuid);
}
