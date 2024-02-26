//package ezen.bizqr.pay.service;
//
//import ezen.bizqr.pay.domain.PayInfoDto;
//import ezen.bizqr.pay.repository.PayMapper;
//import ezen.bizqr.pay.request.MakePayRequest;
//import ezen.bizqr.pay.response.PayReadyResDto;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@RequiredArgsConstructor
//@Slf4j
//@Service
//public class PayService {
//
//    private final MakePayRequest makePayRequest;
//    private final PayMapper payMapper;
//
//    @Value("${pay.admin-key}")
//    private String adminKey;
//
//    /*
//        카카오페이 결제 전 상세 정보를 카카오페이 서버에 전달하고 결제 고유 번호(TID)를 받아와야함
//        어드민 키를 헤더에 담아 파라미터 값들과 함께 POST 요청
//        테스트 가맹점 코드로 TC0ONETIME을 사용
//     */
//
//    public PayReadyResDto getRedirectUrl(PayInfoDto payInfoDto) throws Exception{
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String name = authentication.getName();
//
//
//
//        return null;
//    }
//
//}
