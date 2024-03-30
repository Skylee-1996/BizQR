package ezen.bizqr.store.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class OrderHistoryVO {
    long orderHistoryId;
    String orderId;
    String tableId;
    long storeId;
    long menuId;
    String menuName;
    long menuPrice;
    long menuAmount;
    long totalPrice;
    int orderStatus;
    String userRequest;
    String orderDate;
}
