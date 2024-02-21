package ezen.bizqr.customer.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ItemVO {
    private long menuId;
    private long storeId;
    private String tabName;
    private String menuName;
    private long menuPrice;
}
