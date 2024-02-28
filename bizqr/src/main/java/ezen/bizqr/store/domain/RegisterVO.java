package ezen.bizqr.store.domain;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterVO {

    private long registerNum;
    private String name;
    private String email;
    private String company;
    private String storeName;
    private String storeAddress;
    private String storeType;
    private String ownerNum;
    private String storeNum;
    private String subscribe;
    private int isRegistered;
    private String paidTime;

}
