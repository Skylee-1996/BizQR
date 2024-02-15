package ezen.bizqr.admin.controller;

import ezen.bizqr.admin.service.AdminService;
import ezen.bizqr.board.domain.CommentVO;
import ezen.bizqr.store.domain.RegisterVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/*")
@Controller
@Slf4j
public class AdminController {

    private final AdminService asv;

    @GetMapping({"/index", "/adminRegisterList"})
    public void index(RegisterVO rvo, Model m) {
        List<RegisterVO> list;
        list = asv.getList();

        m.addAttribute("list", list);
    }

    @PostMapping("/approve")
    @ResponseBody
    public String post(@RequestBody RegisterVO rvo) {
        log.info(">>>>> RegisterVO >>> {}", rvo);
        int isOk = asv.post(rvo);
        return isOk > 0? "1" : "0";
    }


}
