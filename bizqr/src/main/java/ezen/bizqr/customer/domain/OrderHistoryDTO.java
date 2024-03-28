package ezen.bizqr.customer.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderHistoryDTO {
    private OrderVO ovo;
    private List<OrderItemVO> oilist;
}
