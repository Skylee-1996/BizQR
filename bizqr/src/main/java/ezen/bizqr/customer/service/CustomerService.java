package ezen.bizqr.customer.service;

import ezen.bizqr.customer.domain.ItemVO;
import ezen.bizqr.customer.domain.OrderItemVO;
import ezen.bizqr.customer.domain.OrderVO;

import java.util.List;

public interface CustomerService{
    int basket(OrderItemVO oivo);

    List<OrderItemVO> basketList(String tableId, long storeId);

    int order(OrderVO ovo);

    int basketUpdate(OrderItemVO oivo);

    int basketCount(String tableId, long storeId);

    List<ItemVO> itemList(long storeId, String tabName);

    List<String> tabList(long storeId);

    int basketDel(long menuId, String tableId, long storeId);
}
