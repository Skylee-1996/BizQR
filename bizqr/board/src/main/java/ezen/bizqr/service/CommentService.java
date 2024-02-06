package ezen.bizqr.service;

import ezen.bizqr.domain.CommentVO;
import ezen.bizqr.domain.PagingVO;
import ezen.bizqr.handler.PagingHandler;

public interface CommentService {

    int post(CommentVO cvo);

    PagingHandler getList(long bno, PagingVO pgvo);

    int edit(CommentVO cvo);

    int deleteComment(long cno, String writer);
}
