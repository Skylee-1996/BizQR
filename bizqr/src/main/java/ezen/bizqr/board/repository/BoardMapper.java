package ezen.bizqr.board.repository;

import ezen.bizqr.board.domain.BoardVO;
import ezen.bizqr.board.domain.PagingVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    int insert(BoardVO bvo);

    List<BoardVO> getList(PagingVO pgvo);

    BoardVO getDetail(long bno);

    int update(BoardVO bvo);

    void delete(long bno);

    int getTotalCount(PagingVO pgvo);

    long getBno();

}
