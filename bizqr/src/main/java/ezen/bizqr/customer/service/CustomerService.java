package ezen.bizqr.customer.service;

import ezen.bizqr.customer.domain.OrderItemVO;

import java.util.List;

public interface CustomerService{
    int basket(OrderItemVO oivo);

    List<OrderItemVO> basketList(String tableId);
}
