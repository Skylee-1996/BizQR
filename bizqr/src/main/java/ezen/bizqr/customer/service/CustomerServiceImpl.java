package ezen.bizqr.customer.service;

import ezen.bizqr.customer.domain.OrderItemVO;
import ezen.bizqr.customer.repository.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    private final OrderMapper om;

    @Override
    public int basket(OrderItemVO oivo) {
        log.info("basket service impl");

        int isOk = om.basket(oivo);

        return isOk;
    }

    @Override
    public List<OrderItemVO> basketList(String tableId) {

        return om.basketList(tableId);
    }
}
