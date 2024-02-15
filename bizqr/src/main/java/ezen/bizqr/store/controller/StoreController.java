package ezen.bizqr.store.controller;

import ezen.bizqr.file.FileHandler;
import ezen.bizqr.file.FileVO;
import ezen.bizqr.store.domain.MenuItemVO;
import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.service.StoreService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

    @GetMapping("/register")
    public void storeRegister(){}

    @PostMapping("/register")
    public String storeRegister(RegisterVO rvo) {
        log.info(">>>>> svo 들어온지 확인하자 >>>>> {} " , rvo);

        ssv.storeRegister(rvo);

        return "index";
    }

    @GetMapping("/create")
    public void storeCreate(){}


    @PostMapping("/addMenu")
    public ResponseEntity<String> addMenu(@ModelAttribute MenuItemVO mvo, @RequestParam(name="image", required = false) MultipartFile imageFile) {

            log.info(">>>>>>>>>>mvo >>>>>>> {}", mvo);



            ssv.insertMenu(mvo);


            fh.uploadFile(imageFile);

            if (!imageFile.isEmpty()) {

                log.info(">>>>>>>>>>>Received file>>>>>>>>>>>>: " + imageFile.getOriginalFilename());
            }


            return ResponseEntity.ok("menu add success");
        }


}
