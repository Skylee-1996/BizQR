package ezen.bizqr.customer.handler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class IdHandler {

    public String orderIdHandler(long storeId, String tableId){
        // 현재 날짜 구하기
        LocalDate today = LocalDate.now();
        // 날짜를 "yyyyMMdd" 형식으로 포맷팅
        String formattedDate = today.format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // 현재 시간 구하기
        LocalTime now = LocalTime.now();
        // 시간을 "HHmmss" 형식으로 포맷팅
        String formattedTime = now.format(DateTimeFormatter.ofPattern("HHmmss"));

        // 포맷된 날짜와 시간을 결합
        String result = formattedDate + "-" + formattedTime + "(" + storeId + "["+ tableId + "])";

        return result;
    }

}
