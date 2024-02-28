package ezen.bizqr.admin.controller;

import ezen.bizqr.admin.service.AdminService;
import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.domain.StoreVO;
import ezen.bizqr.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/admin/*")
@Controller
@Slf4j
public class AdminController {

    private final AdminService asv;
    private final StoreService ssv;

    @GetMapping({"/index", "/adminRegisterList", "/adminStatistics"})
    public void index(RegisterVO rvo, Model m) {
        List<RegisterVO> list;
        list = asv.getList();

        m.addAttribute("list", list);
    }

//    @RequestMapping("/")
//    public void Main() {
//        //일 방문자수 ++ - 02.19
//        asv.addVisit();
//    }

    @PostMapping("/approve")
    @ResponseBody
    public String post(@RequestBody RegisterVO rvo) {
        log.info(">>>>> RegisterVO >>> {}", rvo);
        int isOk = asv.update(rvo);
        log.info(">>>>>> update success >>> {}", isOk);
        if(isOk > 0 ){
            RegisterVO registeredRvo = ssv.getDetail(rvo.getRegisterNum());
            StoreVO svo = new StoreVO();

            svo.setEmail(registeredRvo.getEmail());
            svo.setRegisterNum(registeredRvo.getRegisterNum());
            svo.setStoreName(registeredRvo.getStoreName());
            svo.setStoreAddress(registeredRvo.getStoreAddress());
            svo.setStoreNumber(registeredRvo.getStoreNum());
            svo.setStoreType(registeredRvo.getStoreType());
            svo.setCompany(registeredRvo.getCompany());

            log.info(">>>>>> svo >>>>  {}", svo);
            int isRegistered =  ssv.insertStore(svo);

            log.info(">>> store insert >>> {}", (isRegistered > 0) ? "success" : "failed");





        }

        return isOk > 0 ? "1" : "0";
    }


}
