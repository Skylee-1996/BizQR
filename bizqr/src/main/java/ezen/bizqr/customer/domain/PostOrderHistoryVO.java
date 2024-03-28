package ezen.bizqr.customer.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostOrderHistoryVO {
    private List<OrderHistoryVO> ohlist;
}
