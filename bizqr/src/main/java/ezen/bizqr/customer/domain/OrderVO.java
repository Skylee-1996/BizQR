package ezen.bizqr.customer.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderVO {
    private String orderId;
    private String tableId;
    private long storeId;
    private int orderStatus;
    private long totalPrice;
    private String userRequest;
    private String regAt;
}
