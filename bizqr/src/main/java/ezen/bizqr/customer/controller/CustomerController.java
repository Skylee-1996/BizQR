package ezen.bizqr.customer.controller;


import ezen.bizqr.customer.domain.ItemVO;
import ezen.bizqr.customer.domain.OrderVO;
import ezen.bizqr.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/customer/*")
public class CustomerController {

    private final CustomerService csv;

    @GetMapping("/customerIndex")
    public void index(){}

    @PostMapping("/customerIndex")
    public String basket (ItemVO ivo){
        log.info("ivo >>> {}", ivo);

        return "/customer/customerIndex";
    }

    @GetMapping("/customerBasket")
    public void basket(){}

}
