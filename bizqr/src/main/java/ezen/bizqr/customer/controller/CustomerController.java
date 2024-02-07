package ezen.bizqr.customer.controller;


import ezen.bizqr.customer.domain.OrderVO;
import ezen.bizqr.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/customer/*")
public class CustomerController {

    private final CustomerService csv;

    @PostMapping("/basket")
    public ResponseEntity<OrderVO> basket (){


        return new ResponseEntity<OrderVO>(HttpStatus.OK);
    }
}
