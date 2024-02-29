package ezen.bizqr.pay.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class StorePaymentVO {

    private String impUid;
    private String merchantUid;
    private String buyerEmail;
    private String buyerName;
    private String buyerCompany;
    private String buyerAddress;
    private String buyerOwnerTelNum;
    private String buyerStoreTelNum;
    private String itemName;
    private String itemAmount;
    private String paidTime;

}
