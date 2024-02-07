package ezen.bizqr.board.handler;

import ezen.bizqr.board.domain.CommentVO;
import ezen.bizqr.board.domain.PagingVO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PagingHandler {
    private int startPage;
    private int endPage;
    private boolean prev, next;

    private int totalCount;
    private PagingVO pgvo;

    private List<CommentVO> cmtList; //댓글 페이징을 위해 추가...

    public PagingHandler(PagingVO pgvo, int totalCount) {
        this.pgvo = pgvo;
        this.totalCount = totalCount;

        this.endPage =
                (int)Math.ceil(pgvo.getPageNo() / (double)pgvo.getQty())*pgvo.getQty();
        this.startPage = endPage - 9; //(pgvo.getQty()-1)

        int realEndPage = (int)Math.ceil(totalCount*1.0/pgvo.getQty());

        if(realEndPage < endPage) {
            this.endPage = realEndPage;
        }
        this.prev = this.startPage > 1;
        this.next = this.endPage < realEndPage;
    }

    public PagingHandler(PagingVO pgvo, int totalCount, List<CommentVO> cmtList) {
        this(pgvo, totalCount);
        this.cmtList = cmtList;
    }
}
