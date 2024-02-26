package ezen.bizqr.pay.controller;

import ezen.bizqr.store.domain.RegisterVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/payment/*")
public class PayController {


    @GetMapping("/pay")
    public String pay(@ModelAttribute("rvo") RegisterVO rvo, Model m){

        m.addAttribute("rvo", rvo);

        return "/payment/pay";
    }

}
