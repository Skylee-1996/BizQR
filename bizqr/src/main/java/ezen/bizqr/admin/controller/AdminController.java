package ezen.bizqr.admin.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin/*")
@Controller
@RequiredArgsConstructor
@Slf4j
public class AdminController {

//    private final AdminService asv;

    @GetMapping("/index")
    public String index() {

        return "/admin/index";
    }

}
