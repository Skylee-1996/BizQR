package ezen.bizqr.store.controller;

import ezen.bizqr.board.domain.BoardVO;
import ezen.bizqr.board.domain.PagingVO;
import ezen.bizqr.board.handler.PagingHandler;
import ezen.bizqr.board.service.BoardService;

import ezen.bizqr.file.FileHandler;
import ezen.bizqr.file.FileVO;
import ezen.bizqr.store.domain.MenuItemVO;
import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.domain.StoreVO;
import ezen.bizqr.store.service.StoreService;
<<<<<<< HEAD
=======

import ezen.bizqr.user.security.UserVO;
import ezen.bizqr.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

>>>>>>> origin/main
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @GetMapping("/table")
    public void table(){}

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

<<<<<<< HEAD

=======
<<<<<<< HEAD
=======
>>>>>>> origin/main
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
>>>>>>> ee1333c4cc976e641a85dfa8dc81a49950c81313

<<<<<<< HEAD

=======
>>>>>>> origin/main
}


