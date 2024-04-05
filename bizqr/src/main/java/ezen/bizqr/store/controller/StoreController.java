package ezen.bizqr.store.controller;

import ezen.bizqr.file.FileHandler;
import ezen.bizqr.file.FileMapper;
import ezen.bizqr.file.FileVO;
import ezen.bizqr.store.domain.*;
import ezen.bizqr.store.service.StoreService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Store;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/store/*")
@Slf4j
@Controller
@RequiredArgsConstructor
public class StoreController {
    private final StoreService ssv;
    private final FileHandler fh;
    private final FileMapper fm;

    @GetMapping("/register")
    public void storeRegister(){}

    @PostMapping("/register")
    public String storeRegister(RegisterVO rvo, Model m) {
        log.info(">>>>> svo 들어온지 확인하자 >>>>> {} " , rvo);
        //결제 완료 후 db 저장해야함으로 주석처리함 2024-02-26 - cbj
        //ssv.insertRegister(rvo);
        m.addAttribute("rvo", rvo);
        return "/payment/pay";
    }

    @GetMapping("/create")
    public String createStoreForm(Model m, @RequestParam("storeId") String storeId) {
        StoreVO svo = ssv.getDetailFromStore(storeId);
        m.addAttribute("svo", svo);
        return "/store/create";
    }

    @GetMapping("/modify")
    public String modify(Model model, @RequestParam("storeId") String storeId) {
        StoreVO svo = ssv.getDetailFromStore(storeId);
        model.addAttribute("svo", svo);
        return "/store/modify";
    }


    @GetMapping("/table")
    public void table(Model model, @RequestParam("storeId") String storeId){
        StoreVO svo = ssv.getDetailFromStore(storeId);
        model.addAttribute("svo", svo);
    }

    @GetMapping("/posPage/{storeId}")
    public String posPage(Model model, @PathVariable("storeId") long storeId) {
        log.info("storeId >>> {}", storeId);

        List<TablesVO> list = ssv.getTablesList(storeId);

        for(TablesVO table : list){
            String tableIdString = table.getTableId();
            List<OrderHistoryVO> ohlist = ssv.getTableOrderHistory(table.getStoreId(), table.getTableId());
            table.setOrderHistory(ohlist);
            int money = 0;
            for(OrderHistoryVO oh : ohlist){
                money += oh.getTotalPrice();
            }
            table.setTotalMoney(money);
            String[] parts = tableIdString.split("_");
            if(parts.length > 1){
                table.setTableId(parts[1]);
            }
        }
        log.info("table list >>> : " + list);

        model.addAttribute("list", list);

        return "/store/posPage";
    }

    @DeleteMapping("/deleteTableOrderHistory/{storeId}/{combinedTableId}")
    @ResponseBody
    public String deleteTableOrder(@PathVariable("storeId") long storeId, @PathVariable("combinedTableId") String combinedTableId){
        log.info("storeId >>> : " + storeId);
        log.info("tableId >>> : " + combinedTableId);

        int isOk = ssv.deleteTableOrderHistory(storeId, combinedTableId);

        return isOk > 0 ? "1" : "0";
    }



    @PostMapping("/addMenu")
    public ResponseEntity<String> addMenu(@ModelAttribute MenuItemVO mvo, @RequestParam(name="image", required = false) MultipartFile imageFile) {

        log.info(">>>>>>>>>>mvo >>>>>>> {}", mvo);
        long MenuId = ssv.insertMenu(mvo);
           FileVO fvo = fh.uploadFile(imageFile);
           fvo.setMenuId(MenuId);
            if (!imageFile.isEmpty()) {
                log.info(">>>>>>>>>>>Received file>>>>>>>>>>>>: " + imageFile.getOriginalFilename());
                fm.insertFile(fvo);
            }
            return ResponseEntity.ok("menu add success");
        }

    @GetMapping("/store/myStoreList")
    public String myStoreList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        log.info(">>> userEmail >>> : " + userEmail);
        List<StoreVO> userStore = ssv.selectEmail(userEmail);
        log.info(">>> userStore >>> {}", userStore);
        model.addAttribute("userStore", userStore);
        return "/store/myStoreList";
    }

    @PostMapping("/registerSuccess")
    @ResponseBody
    public String postRegisterSuccess(@RequestBody RegisterVO rvo){

        log.info(">>> RegisterVO rvo >>> {}", rvo);

        int isOk = ssv.insertRegister(rvo);

        return isOk > 0 ? "1" : "0";
    }

    @PostMapping("/modify")
    public String modifyStore(StoreVO svo, @RequestParam("file") MultipartFile file, Model m) {
        log.info(">>>>>>>>> svo >>>{}", svo);
          FileVO fvo= fh.uploadStoreImage(file, svo.getStoreId());
          svo.setLogoImage(fvo.getFileName());
          ssv.updateStore(svo);
          m.addAttribute("svo", svo);
        return "/store/create";
    }


    @GetMapping("/insertTable/{storeId}/{tableNum}")
    @ResponseBody
    public String insertTable(@PathVariable("storeId") int storeId, @PathVariable("tableNum") int tableNum){
        log.info(">>> storeId  >>> {}", storeId);
        log.info(">>> tableNum  >>> {}", tableNum);
        int isOk = ssv.insertTables(storeId,tableNum);
        return isOk > 0 ? "1" : "0";
    }

    @PostMapping("/saveTablePayHistory")
    @ResponseBody
    public String saveTablePay(@RequestBody tablePayHistoryVO tphvo){
        log.info("saveTablePayHistoryVO >>> tphvo >>> {}", tphvo);

        int isOk = ssv.saveTablePay(tphvo);

        return isOk > 0 ? "1" : "0";
    }
}


