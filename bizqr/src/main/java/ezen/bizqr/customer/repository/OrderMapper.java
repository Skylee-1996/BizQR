package ezen.bizqr.customer.repository;

import ezen.bizqr.customer.domain.ItemVO;
import ezen.bizqr.customer.domain.OrderHistoryVO;
import ezen.bizqr.customer.domain.OrderItemVO;
import ezen.bizqr.customer.domain.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface OrderMapper {
    int basket(OrderItemVO oivo);

    List<OrderItemVO> basketList(@Param("tableId") String tableId, @Param("storeId") long storeId);

    int order(OrderVO ovo);

    void insertOrderHistory(@Param("ovo") OrderVO ovo, @Param("oivo") OrderItemVO orderItemVO);

    void deleteOrderBasket(@Param("tableId") String tableId, @Param("storeId") long storeId);

    int basketUpdate(OrderItemVO oivo);

    int basketCount(@Param("tableId") String tableId, @Param("storeId") long storeId);

    List<ItemVO> itemList(@Param("storeId") long storeId, @Param("tabName") String tabName);

    List<String> tabList(long storeId);

    int basketDel(@Param("menuId") long menuId, @Param("tableId") String tableId, @Param("storeId") long storeId);

    List<OrderItemVO> menuPrice(@Param("storeId") long storeId, @Param("tableId") String tableId);

    List<OrderHistoryVO> orderHistory(@Param("storeId") long storeId, @Param("tableId") String tableId, @Param("orderId") String orderId);

    List<String> orderHistoryOrderId(@Param("storeId") long storeId, @Param("tableId") String tableId);

    String isTable(@Param("storeId") long storeId, @Param("tableId") String tableId);
}
