package ezen.bizqr.customer.controller;


import ezen.bizqr.customer.domain.ItemVO;
import ezen.bizqr.customer.domain.OrderItemVO;
import ezen.bizqr.customer.domain.OrderVO;
import ezen.bizqr.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/customer/*")
public class CustomerController {

    private final CustomerService csv;

    @GetMapping("/customerIndex")
    public void index(){}

    @PostMapping("/customerIndex")
    public String index(OrderItemVO oivo){
        log.info("oivo >>> {}", oivo);

        int isOk = csv.basket(oivo);

        log.info("basket 실행 결과 >>> {}", isOk>0?"성공":"실패");

        return "/customer/customerIndex";
    }

    @GetMapping("/customerBasket")
    public void basket(Model m, @RequestParam("tableId") String tableId){
        long menuMainTotal = 0;

        log.info(tableId);

        List<OrderItemVO> oilist = new ArrayList<OrderItemVO>(csv.basketList(tableId));
        log.info("oilist >>> {}", oilist.toString());

        OrderItemVO oivo = new OrderItemVO();

        for (OrderItemVO orderItemVO : oilist) {
            menuMainTotal += orderItemVO.getMenuMainTotal();

            oivo.setTableId(orderItemVO.getTableId());
        }

        String menuMainTotalComma = oivo.getMenuMainTotalComma(menuMainTotal);  //mainTotal의 comma를 추가하는 메서드

        m.addAttribute("menuMainTotal", menuMainTotal);
        m.addAttribute("menuMainTotalComma", menuMainTotalComma);
        m.addAttribute("oivo", oivo);
        m.addAttribute("oilist", oilist);   //상품 목록
    }

    @PostMapping("/customerBasket")
    public String basket(OrderVO ovo){
        log.info("ovo >>> {}", ovo);

        int isOk = csv.order(ovo);
        log.info("order 실행 결과 >>> {}", isOk>0?"성공":"실패");

        return "/customer/customerOrderHistory";
    }

}
