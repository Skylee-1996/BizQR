package ezen.bizqr.board.service;

import ezen.bizqr.board.domain.CommentVO;
import ezen.bizqr.board.domain.PagingVO;
import ezen.bizqr.board.handler.PagingHandler;
import ezen.bizqr.board.repository.CommentMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{
    private final CommentMapper mapper;

    @Override
    public int post(CommentVO cvo) {
        // TODO Auto-generated method stub
        return mapper.post(cvo);
    }


    @Override
    public int edit(CommentVO cvo) {
        // TODO Auto-generated method stub
        return mapper.edit(cvo);
    }

    @Transactional
    @Override
    public PagingHandler getList(long bno, PagingVO pgvo) {
        //controller에서 처리해도 되지만, 처리 속도가 더 빨라짐
        //totalCount
        int totalCount = mapper.bnoTotalCount(bno);
        //List
        List<CommentVO> list = mapper.getList(bno, pgvo);

        PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
        return ph;
    }


    @Override
    public int deleteComment(long cno, String nickName) {
        // TODO Auto-generated method stub
        return mapper.deleteComment(cno, nickName);
    }
}
