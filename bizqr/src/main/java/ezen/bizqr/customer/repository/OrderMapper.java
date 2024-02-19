package ezen.bizqr.customer.repository;

import ezen.bizqr.customer.domain.OrderItemVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int basket(OrderItemVO oivo);

    List<OrderItemVO> basketList(String tableId);
}
