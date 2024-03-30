package ezen.bizqr.store.domain;

import lombok.*;

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
}
