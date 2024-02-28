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

    @GetMapping("/{storeId}/{tableId}")
    public String index (@PathVariable("tableId") String tableId, @PathVariable("storeId") long storeId, Model m){
        log.info("tableId >>> {}", tableId);
        log.info("storeId >>> {}", storeId);

        m.addAttribute("tableId", tableId);
        m.addAttribute("storeId", storeId);

        return "/customer/customerIndex";
    }

    @GetMapping(value="/itemList/{storeId}/{tabName}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<ItemVO>> itemList (@PathVariable("storeId") long storeId, @PathVariable("tabName") String tabName){
        log.info("storeId >>> {}", storeId);
        log.info("tabName >>> {}", tabName);

        List<ItemVO> ivo = new ArrayList<ItemVO>(csv.itemList(storeId, tabName));
        log.info("ivo >>> {}", ivo);

        return new ResponseEntity<List<ItemVO>>(ivo, HttpStatus.OK);
    }

    @GetMapping(value="/basketCount/{storeId}/{tableId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Integer> basketCount (@PathVariable("tableId") String tableId, @PathVariable("storeId") long storeId){
        log.info("storeId >>> {}", storeId);
        log.info("tableId >>> {}", tableId);

        return new ResponseEntity<Integer>(csv.basketCount(tableId, storeId), HttpStatus.OK);
    }

    @GetMapping(value="/tabList/{storeId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<String>> tabList (@PathVariable("storeId") long storeId){
        log.info("storeId >>> {}", storeId);

        List<String> tabList = new ArrayList<String>(csv.tabList(storeId));

        return new ResponseEntity<List<String>>(tabList, HttpStatus.OK);
    }

    @PostMapping("/customerIndex")
    public String index(OrderItemVO oivo, Model m){
        log.info("oivo >>> {}", oivo);

        int isOk = csv.basket(oivo);

        log.info("basket 실행 결과 >>> {}", isOk>0?"성공":"실패");

        log.info("tableId >>> {}", oivo.getTableId());
        log.info("storeId >>> {}", oivo.getStoreId());

        m.addAttribute("tableId", oivo.getTableId());
        m.addAttribute("storeId", oivo.getStoreId());

        return "/customer/customerIndex";
    }

    @GetMapping("/customerBasket")
    public void basket(Model m, @RequestParam("tableId") String tableId, @RequestParam("storeId") long storeId){
        long menuMainTotal = 0;

        log.info("tableId >>> {}", tableId);
        log.info("storeId >>> {}", storeId);

        List<OrderItemVO> oilist = new ArrayList<OrderItemVO>(csv.basketList(tableId, storeId));
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
