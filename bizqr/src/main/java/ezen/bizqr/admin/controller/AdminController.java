package ezen.bizqr.admin.controller;

import ezen.bizqr.admin.service.AdminService;
import ezen.bizqr.store.domain.RegisterVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@RequestMapping("/admin/*")
@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final AdminService asv;

    @GetMapping({"/index", "/adminRegisterList"})
    public void index(RegisterVO rvo, Model m) {
        List<RegisterVO> list;
        list = asv.getList();

        m.addAttribute("list", list);
    }

//    @GetMapping("/adminRegisterList")
//    public void adminRegisterList() {}


}
