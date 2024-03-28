package ezen.bizqr.customer.service;

import ezen.bizqr.customer.domain.*;

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

    String menuPrice(long storeId, String tableId);

    List<PostOrderHistoryVO> orderHistory(long storeId, String tableId);

}
