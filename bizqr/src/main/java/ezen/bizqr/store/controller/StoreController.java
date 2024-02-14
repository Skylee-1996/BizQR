package ezen.bizqr.store.controller;

import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.service.StoreService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/store/*")
@Slf4j
@Controller
@RequiredArgsConstructor
public class StoreController {

    private final StoreService ssv;

    @GetMapping("/register")
    public void storeRegister(){}

    @PostMapping("/register")
    public String storeRegister(RegisterVO rvo) {
        log.info(">>>>> svo 들어온지 확인하자 >>>>> {} " , rvo);

        ssv.storeRegister(rvo);

        return "index";
    }

    @GetMapping("/create")
    public void storeCreate(){}

    @PostMapping("/create")
    public String createStore(){
        return "a";
    }

}
