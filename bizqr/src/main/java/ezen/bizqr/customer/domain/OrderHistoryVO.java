package ezen.bizqr.customer.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderHistoryVO {
    private String orderId;
    private String tableId;
    private long storeId;
    private long menuId;
    private String menuName;
    private long menuPrice;
    private long menuAmount;
    private int orderStatus;
    private long totalPrice;
    private String userRequest;
    private String orderDate;
}
