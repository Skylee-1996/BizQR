package ezen.bizqr.customer.repository;

import ezen.bizqr.customer.domain.ItemVO;
import ezen.bizqr.customer.domain.OrderItemVO;
import ezen.bizqr.customer.domain.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    int basket(OrderItemVO oivo);

    List<OrderItemVO> basketList(String tableId);

    int order(OrderVO ovo);

    void insertOrderHistory(OrderItemVO oivo);

    void deleteOrderBasket(String tableId);

    int basketUpdate(OrderItemVO oivo);

    int basketCount(String tableId);

    List<ItemVO> itemList(@Param("storeId") long storeId, @Param("tabName") String tabName);
}
