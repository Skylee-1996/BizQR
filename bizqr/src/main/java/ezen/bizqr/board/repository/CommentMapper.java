package ezen.bizqr.board.repository;

import ezen.bizqr.board.domain.CommentVO;
import ezen.bizqr.board.domain.PagingVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface CommentMapper {

    int post(CommentVO cvo);
    List<CommentVO> getList(@Param("bno") long bno, @Param("pgvo") PagingVO pgvo);
    int edit(CommentVO cvo);
    int bnoTotalCount(long bno);
    int deleteComment(@Param("cno") long cno,@Param("writer") String writer);
}
