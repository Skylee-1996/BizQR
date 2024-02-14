package ezen.bizqr.board.controller;

import ezen.bizqr.board.domain.BoardVO;
import ezen.bizqr.board.domain.PagingVO;
import ezen.bizqr.file.FileHandler;
import ezen.bizqr.board.handler.PagingHandler;
import ezen.bizqr.board.service.BoardService;
import ezen.bizqr.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/board/*")
@RequiredArgsConstructor
@Controller
public class BoardController {
    private final Logger log = LoggerFactory.getLogger(BoardController.class);
    private final BoardService bsv;
    private final CommentService csv;
    private final FileHandler fh;

    @GetMapping("/register")
    public void register() {}

    //서비스페이지이동
    @GetMapping("/service")
    public String service() {

        return "/info/serviceDetails";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody BoardVO bvo)  {
        log.info(">>>>> bvo >> {}", bvo);
        bsv.register(bvo);
        return ResponseEntity.ok("redirect:/board/list");
    }
    @GetMapping("/list")
    public void list(Model m, PagingVO pgvo) {
        log.info(">>> pgvo >> {}", pgvo);
        int totalCount = bsv.getTotalCount(pgvo);
        PagingHandler ph = new PagingHandler(pgvo, totalCount);
        List<BoardVO> boardList = bsv.getList(pgvo);
        m.addAttribute("list", boardList);
        m.addAttribute("ph", ph);
    }

    @GetMapping({"/detail","/modify"})
    public void detail(Model m, @RequestParam("bno")long bno) {
        m.addAttribute("bdto", bsv.getDetail(bno));
    }

    @PostMapping(value="/modify", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String modify(RedirectAttributes re, @RequestBody BoardVO bvo) {
        bsv.modify(bvo);
        return "redirect:/board/detail?bno="+bvo.getBno();
    }

    @GetMapping("/remove")
    public String remove(@RequestParam("bno")long bno) {
        bsv.remove(bno);
        return "redirect:/board/list";
    }
}
