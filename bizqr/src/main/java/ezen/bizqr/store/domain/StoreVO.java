package ezen.bizqr.store.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class StoreVO {
    private Long storeId;
    private String email;
    private Long registerNum;
    private String storeName;
    private String storeAddress;
    private String storeNumber;
    private String storeHours;
    private String storeType;
    private String company;
    private String regAt;
}
