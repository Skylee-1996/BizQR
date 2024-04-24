package ezen.bizqr.store.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class tablePayHistoryVO {
    String impUid;
    String merchantUid;
    long storeId;
    String tableId;
    long totalPrice;
    String paidTime;
}
