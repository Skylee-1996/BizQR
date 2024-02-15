package ezen.bizqr.store.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MenuItemVO {
    private String menuId;
    private String storeId;
    private String tabName;
    private String menuName;
    private long  menuPrice;

}
