package ezen.bizqr.board.service;

import ezen.bizqr.board.domain.CommentVO;
import ezen.bizqr.board.domain.PagingVO;
import ezen.bizqr.board.handler.PagingHandler;

public interface CommentService {

    int post(CommentVO cvo);

    PagingHandler getList(long bno, PagingVO pgvo);

    int edit(CommentVO cvo);

    int deleteComment(long cno, String email);

}
