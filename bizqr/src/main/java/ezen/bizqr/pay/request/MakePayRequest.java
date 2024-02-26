//package ezen.bizqr.pay.request;
//
//import ezen.bizqr.pay.domain.PayInfoDto;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//import org.springframework.util.LinkedMultiValueMap;
//
//@Component
//@RequiredArgsConstructor
//public class MakePayRequest {
//
//    public PayRequest getReadyRequest(Long id, PayInfoDto payInfoDto){
//        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//
//        String memberId = id + "";
//        String orderId = "point" + id;
//
//        //테스트 코드는 TC0ONETIME
//        map.add("cid", "TC0ONETIME");
//
//        // 결제 승인 요청시 아래 partner_order_id, partner_user_id가 동일해야 함.
//        map.add("partner_order_id", orderId);
//        map.add("partner_user_id", "bizQR");
//
//        // 클라이언트로부터 받아온 payInfoDto로 결제 주문서의 item 이름을 지어줌
//        map.add("item_name", payInfoDto.getItemName());
//
//        //수량
//        map.add("quantity", "1");
//
//        //가격
//        map.add("total_amount", payInfoDto.getPrice() + "");
//
//        //결제 url에서 결제를 성공, 실패, 취소시 redirect 할 url 설정
//        map.add("approval_url", "http://localhost:8090/payment/success"+ "/" + id); // 성공시 redirect url
//        map.add("cancel_url", "http://localhost:8090/payment/cancel"); // 취소시 redirect url
//        map.add("fail_url", "http://localhost:8090/payment/fail"); // 실패시 redirect url
//
//        return new PayRequest("https://open-api.kakaopay.com/online/v1/payment/ready", map);
//    }
//
//    public PayRequest getApproveRequest(String tid, Long id, String pgToken){
//        LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//
//        String orderId = "point" + id;
//
//        map.add("cid", "TC0ONETIME");
//
//        //getReadyRequest 에서 받아온 tid
//        map.add("tid", tid);
//        map.add("partner_order_id", orderId);
//        map.add("partner_user_id", "bizQR");
//
//        // getReadyRequest 에서 받아온 redirect url
//        //"http://localhost:8090/payment/success" + "/" + id
//        // 뒤에 &pg_token(토큰값)이 붙어서 redirect 됨.
//        map.add("pg_token", pgToken);
//
//        return new PayRequest("https://open-api.kakaopay.com/online/v1/payment/approve", map);
//    }
//
//}
