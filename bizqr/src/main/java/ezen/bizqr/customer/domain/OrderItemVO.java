package ezen.bizqr.customer.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.util.Locale;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderItemVO {
    private String menuId;
    private String storeId;
    private String tableId;
    private String menuName;
    private long menuPrice;
    private long menuAmount;
    private String regAt;

    public String getMenuSubTotalComma(){
        NumberFormat formatKR = NumberFormat.getNumberInstance(Locale.KOREA);

        return formatKR.format(this.menuPrice * this.menuAmount);
    }

    public long getMenuSubTotal(){
        return this.menuPrice * this.menuAmount;
    }

    public String getMenuMainTotalComma(long menuMainTotal){
        NumberFormat formatKR = NumberFormat.getNumberInstance(Locale.KOREA);

        return formatKR.format(menuMainTotal);
    }

    public long getMenuMainTotal(){
        return this.menuPrice * this.menuAmount;
    }
}
