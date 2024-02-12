package ezen.bizqr.store.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@RequestMapping("/store/*")
@Slf4j
@Controller
public class StoreController {
    @GetMapping("/register")
    public void storeRegister(){}
    @GetMapping("/create")
    public void storeCreate(){}


}
