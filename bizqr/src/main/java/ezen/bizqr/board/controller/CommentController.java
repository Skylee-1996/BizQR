package ezen.bizqr.board.controller;

import ezen.bizqr.board.domain.CommentVO;
import ezen.bizqr.board.domain.PagingVO;
import ezen.bizqr.board.handler.PagingHandler;
import ezen.bizqr.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/comment/*")
@RestController
public class CommentController {
    private final CommentService csv;

    @PostMapping("/post")
    @ResponseBody
    public String post(@RequestBody CommentVO cvo) {
        log.info(">> cvo >>> {} ", cvo);
        int isOk = csv.post(cvo);
        return isOk > 0 ? "1":"0";
    }

    @GetMapping("/{bno}/{page}")
    @ResponseBody
    public PagingHandler list(@PathVariable("bno") long bno,
                              @PathVariable("page") int page){
        log.info(">> bno >>> {} "+bno+"/ page >>> {} "+page);
        //List<CommentVO> and PagingHandler
        //비동기는 한 객체만 전송 가능함...
        PagingVO pgvo = new PagingVO(page, 5); //1페이지에 5개씩 댓글 출력
        PagingHandler ph = csv.getList(bno, pgvo);
        return ph;
    }

    @PutMapping("/edit")
    @ResponseBody
    public String edit(@RequestBody CommentVO cvo) {
        log.info(">> cvo >>> {} ", cvo);
        int isOk = csv.edit(cvo);
        return isOk > 0 ? "1":"0";
    }

    //댓글삭제
    @DeleteMapping(value="/del/{cno}/{writer}", produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable("cno") long cno
            , @PathVariable("userEmail") String userEmail){
        log.info(">> cno >>> writer >>> {} "+cno+" / "+userEmail);
        int isOk = csv.deleteComment(cno, userEmail);
        return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
                : new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
