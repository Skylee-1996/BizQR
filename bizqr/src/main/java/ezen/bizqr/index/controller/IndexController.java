package ezen.bizqr.index.controller;
import ezen.bizqr.store.domain.StoreVO;
import ezen.bizqr.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
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

    @GetMapping("/filter")
    public ResponseEntity<List<StoreVO>> filterStoresByType(@RequestParam("type") String storeType) {
        log.info(storeType);

        List<StoreVO> slist = null;
        if(storeType.equals("All")){
            slist = ssv.getStoreList();
        }else {
            slist = ssv.getStoreListByType(storeType);
        }
            log.info(">>>>>>>>slist>>>>>>>{}", slist);

        return ResponseEntity.ok(slist);
    }

}
