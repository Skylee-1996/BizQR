package ezen.bizqr.store.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TablesVO {

    String tableId;
    long storeId;
    String tableQr;
    long totalPrice;
    int isUsing;
    List<OrderHistoryVO> orderHistory;
    int totalMoney;
}
