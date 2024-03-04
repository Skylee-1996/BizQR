package ezen.bizqr.pay.controller;

import ezen.bizqr.pay.domain.StorePaymentVO;
import ezen.bizqr.pay.service.PayService;
import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/payment/*")
public class PayController {

    private final PayService psv;
    private final StoreService ssv;

    @GetMapping("/pay")
    public String pay(){


        return "/payment/pay";
    }

    @PostMapping("/pay")
    public String pay(RegisterVO rvo, Model m){
        log.info("registerVO rvo >>>>> {}", rvo);

        m.addAttribute("rvo", rvo);

        return "/payment/pay";
    }

    @GetMapping("/list")
    public String list(Model m){

        List<RegisterVO> list = ssv.getRegisterList();

        log.info("asd list" + list);

        m.addAttribute("list", list);

        return "/payment/list";
    }

    @PostMapping("/storePay/success")
    @ResponseBody
    public String paySuccess(@RequestBody StorePaymentVO spvo){
        log.info("StorePaymentVO >>>>>>>>>> spvo >>> {}", spvo);

        int isOk = psv.savePayment(spvo);

        return isOk > 0 ? "1" : "0";
    }

}
