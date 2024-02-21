package ezen.bizqr.store.controller;

import ezen.bizqr.file.FileHandler;
import ezen.bizqr.file.FileVO;
import ezen.bizqr.store.domain.MenuItemVO;
import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/store/*")
@Slf4j
@Controller
@RequiredArgsConstructor
public class StoreController {

    private final StoreService ssv;
    private final FileHandler fh;

    @GetMapping("/register")
    public void storeRegister(){}

    @PostMapping("/register")
    public String storeRegister(RegisterVO rvo) {
        log.info(">>>>> svo 들어온지 확인하자 >>>>> {} " , rvo);

        ssv.insertRegister(rvo);

        return "index";
    }

    @GetMapping("/create")
    public void storeCreate(){}

    @GetMapping("/posPage")
    public String posPage() {
        return "/store/posPage";
    }


    @PostMapping("/addMenu")
    public ResponseEntity<String> addMenu(@ModelAttribute MenuItemVO mvo, @RequestParam(name="image", required = false) MultipartFile imageFile) {

            log.info(">>>>>>>>>>mvo >>>>>>> {}", mvo);



        long MenuId = ssv.insertMenu(mvo);


           FileVO fvo = fh.uploadFile(imageFile);
           fvo.setMenuId(MenuId);

            if (!imageFile.isEmpty()) {

                log.info(">>>>>>>>>>>Received file>>>>>>>>>>>>: " + imageFile.getOriginalFilename());
            }


            return ResponseEntity.ok("menu add success");
        }


}


