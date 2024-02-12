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
    private String tabName;
    private String itemName;
    private long itemPrice;
}
