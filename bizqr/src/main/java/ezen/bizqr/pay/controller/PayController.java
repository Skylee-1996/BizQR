package ezen.bizqr.pay.controller;

import ezen.bizqr.pay.domain.StorePaymentVO;
import ezen.bizqr.pay.service.PayService;
import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.domain.StoreVO;
import ezen.bizqr.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/payment/*")
public class PayController {

    private final PayService psv;
    private final StoreService ssv;

    @GetMapping("/pay")
    public String pay(){


        return "/payment/pay";
    }

    @PostMapping("/pay")
    public String pay(RegisterVO rvo, Model m){
        log.info("registerVO rvo >>>>> {}", rvo);

        m.addAttribute("rvo", rvo);

        return "/payment/pay";
    }

    @GetMapping("/list")
    public String list(Model m){

        List<RegisterVO> list = ssv.getRegisterList();

        log.info("asd list" + list);

        m.addAttribute("list", list);

        return "/payment/list";
    }

    @PostMapping("/storePay/success")
    @ResponseBody
    public String paySuccess(@RequestBody StorePaymentVO spvo){
        log.info("StorePaymentVO >>>>>>>>>> spvo >>> {}", spvo);

        int isOk = psv.savePayment(spvo);

        return isOk > 0 ? "1" : "0";
    }
//    @PostMapping("/takeUserInfo/{registerNum}")
//    public ResponseEntity<Object> takeUserInfo(@PathVariable("registerNum") long registerNum){
//
//        RegisterVO rvo = ssv.getDetail(registerNum);
//        String merchantUid = rvo.getMerchantUid();
//        log.info(">>> merchantUid of rvo >>> " + merchantUid);
//
//        StorePaymentVO spvo = psv.getImpUidWithMerchantUid(merchantUid);
//        String impUid = spvo.getImpUid();
//        log.info(">>> impUid of spvo >>> " + impUid);
//
//        Map<String, String> responseData = new HashMap<>();
//        responseData.put("merchantUid", merchantUid);
//        responseData.put("impUid", impUid);
//
//        return new ResponseEntity<>(responseData, HttpStatus.OK);
//    }

    @PostMapping("/alterRegisterInfo/{registerNum}/{isRegistered}")
    public ResponseEntity<String> alterRegisterInfo(@PathVariable("registerNum") long registerNum, @PathVariable("isRegistered") int isRegistered){
        log.info(">>> registerNum >>> " + registerNum);
        log.info(">>> isRegistered >>> " + isRegistered);

        int isOk = ssv.alterRegisterInfo(registerNum, isRegistered);

        if(isRegistered == 1){
            RegisterVO registeredRvo = ssv.getDetail(registerNum);
            StoreVO svo = new StoreVO();

            svo.setEmail(registeredRvo.getEmail());
            svo.setRegisterNum(registeredRvo.getRegisterNum());
            svo.setStoreName(registeredRvo.getStoreName());
            svo.setStoreAddress(registeredRvo.getStoreAddress());
            svo.setStoreNumber(registeredRvo.getStoreNum());
            svo.setStoreType(registeredRvo.getStoreType());
            svo.setCompany(registeredRvo.getCompany());

            log.info(">>>>>> svo >>>>  {}", svo);
            ssv.insertStore(svo);
        }
        return isOk > 0 ?
                new ResponseEntity<String>("0", HttpStatus.OK) :
                    new ResponseEntity<String>("1", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/getToken")
    @ResponseBody
    public ResponseEntity<String> getToken() {
        // IAMPORT API의 URL
        String url = "https://api.iamport.kr/users/getToken";

        // IAMPORT API에 전송할 데이터
        String impKey = "6553563087088256";
        String impSecret = "de2gHskYCWVk1WnKiIohvR3eNvKKKA6RIQO9DD8XJBivTLrIuLhbu09SMdtl5AxUbsUA71xn8FOI4kkq";
        String requestBody = "{\"imp_key\": \"" + impKey + "\", \"imp_secret\": \"" + impSecret + "\"}";

        // IAMPORT API 호출을 위한 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // RestTemplate을 사용하여 IAMPORT API 호출
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        // IAMPORT API의 응답을 클라이언트에게 반환
        return response;
    }

    @PostMapping("/cancel/{registerNum}/{accessToken}")
    public ResponseEntity<String> cancel(@PathVariable("registerNum") long registerNum, @PathVariable("accessToken") String accessToken) {
        RegisterVO rvo = ssv.getDetail(registerNum);
        String merchantUid = rvo.getMerchantUid();
        log.info(">>> merchantUid of rvo >>> " + merchantUid);

        StorePaymentVO spvo = psv.getImpUidWithMerchantUid(merchantUid);
        String impUid = spvo.getImpUid();
        log.info(">>> impUid of spvo >>> " + impUid);

        String url = "https://api.iamport.kr/payments/cancel";
        String requestBody = "{\"imp_uid\": \"" + impUid + "\", \"merchant_uid\": \"" + merchantUid + "\"}";

        // IAMPORT API 호출을 위한 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);

        // RestTemplate을 사용하여 IAMPORT API 호출
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        // IAMPORT API의 응답을 클라이언트에게 반환
        return response;
    }

    /*
    @PostMapping("/getToken")
    @ResponseBody
    public ResponseEntity<String> getToken() {
        // IAMPORT API의 URL
        String url = "https://api.iamport.kr/users/getToken";

        // IAMPORT API에 전송할 데이터
        String impKey = "6553563087088256";
        String impSecret = "de2gHskYCWVk1WnKiIohvR3eNvKKKA6RIQO9DD8XJBivTLrIuLhbu09SMdtl5AxUbsUA71xn8FOI4kkq";
        String requestBody = "{\"imp_key\": \"" + impKey + "\", \"imp_secret\": \"" + impSecret + "\"}";

        // IAMPORT API 호출을 위한 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // RestTemplate을 사용하여 IAMPORT API 호출
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        // IAMPORT API의 응답을 클라이언트에게 반환
        return response;
    }

    @PostMapping("/cancel/{accessToken}")
    public ResponseEntity<String> cancel(@PathVariable("accessToken") String accessToken) {
        String url = "https://api.iamport.kr/payments/cancel";
        String impUid = "imp_690956879488";
        String merchantUid = "bizqr1709480955844";
        String requestBody = "{\"imp_uid\": \"" + impUid + "\", \"merchant_uid\": \"" + merchantUid + "\"}";

        // IAMPORT API 호출을 위한 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", accessToken);

        // RestTemplate을 사용하여 IAMPORT API 호출
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);

        // IAMPORT API의 응답을 클라이언트에게 반환
        return response;
    }
     */
}
