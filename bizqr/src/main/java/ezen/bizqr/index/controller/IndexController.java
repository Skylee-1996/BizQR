package ezen.bizqr.index.controller;

import ezen.bizqr.store.controller.StoreController;
import ezen.bizqr.store.domain.StoreVO;
import ezen.bizqr.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final StoreService ssv;

    @GetMapping("/")
    public String main(Model m) {
        List<StoreVO> slist = ssv.getStoreList();

        m.addAttribute("slist", slist);

        return "index";
    }

}
