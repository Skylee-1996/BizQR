package ezen.bizqr.customer.service;

import ezen.bizqr.customer.domain.ItemVO;
import ezen.bizqr.customer.domain.OrderItemVO;
import ezen.bizqr.customer.domain.OrderVO;

import java.util.List;

public interface CustomerService{
    int basket(OrderItemVO oivo);

    List<OrderItemVO> basketList(String tableId);

    int order(OrderVO ovo);

    int basketUpdate(OrderItemVO oivo);

    int basketCount(String tableId);

    List<ItemVO> itemList(long storeId, String tabName);
}
