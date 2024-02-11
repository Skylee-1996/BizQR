package ezen.bizqr.customer.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemVO {
    private String menuId;
    private String storeId;
    private String tableId;
    private long menuPrice;
    private long menuAmount;
    private String regAt;
    private String modAt;
}
