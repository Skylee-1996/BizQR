package ezen.bizqr.controller;

import ezen.bizqr.domain.BoardDTO;
import ezen.bizqr.domain.BoardVO;
import ezen.bizqr.domain.FileVO;
import ezen.bizqr.domain.PagingVO;
import ezen.bizqr.handler.FileHandler;
import ezen.bizqr.handler.PagingHandler;
import ezen.bizqr.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequestMapping("/board/*")
@RequiredArgsConstructor
@Controller
public class BoardController {
    private final Logger log = LoggerFactory.getLogger(BoardController.class);
    private final BoardService bsv;
    private final FileHandler fh;

    @GetMapping("/register")
    public void register() {}

    @PostMapping("/register")
    public String register(BoardVO bvo,
                           @RequestParam(name="files", required = false) MultipartFile[] files) {
        log.info(">>>>> bvo >> {}", bvo);
        //파일 업로드에 대한 부분 추가
        List<FileVO> flist = null;
        if(files[0].getSize()>0 || files != null) {
            flist = fh.uploadFiles(files);
            log.info(">>>flist >>> {}",flist);
        }
        bsv.register(new BoardDTO(bvo, flist));
        return "redirect:/board/list";
    }

    @GetMapping("/list")
    public void list(Model m, PagingVO pgvo) {
        log.info(">>> pgvo >> {}", pgvo);
        int totcalCount = bsv.getTotalCount(pgvo);
        PagingHandler ph = new PagingHandler(pgvo, totcalCount);
        m.addAttribute("list", bsv.getList(pgvo));
        m.addAttribute("ph", ph);
    }

    @GetMapping("/detail")
    public void detail(Model m, @RequestParam("bno")long bno) {

        m.addAttribute("bdto", bsv.getDetail(bno));
    }

    @PostMapping("/modify")
    public String modify(RedirectAttributes re, BoardVO bvo
            , @RequestParam(name="files", required = false) MultipartFile[] files) {
        log.info(">> files >> {} ",files);
        List<FileVO> flist = null;
        if(files[0].getSize()>0 && files != null){
            flist = fh.uploadFiles(files);
        }
        bsv.modify(new BoardDTO(bvo, flist));
        return "redirect:/board/detail?bno="+bvo.getBno();
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno")long bno) {
        bsv.remove(bno);
        return "redirect:/board/list";
    }

    //첨부파일 삭제
    @DeleteMapping(value="/file/{uuid}", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> delete(@PathVariable("uuid") String uuid) {
        log.info("delete file id >>> {} ", uuid);
        int isOk = bsv.removeToFile(uuid);
        return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) :
                new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
