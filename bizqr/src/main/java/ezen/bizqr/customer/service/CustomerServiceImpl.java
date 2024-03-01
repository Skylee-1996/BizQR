package ezen.bizqr.customer.service;

import ezen.bizqr.customer.domain.ItemVO;
import ezen.bizqr.customer.domain.OrderItemVO;
import ezen.bizqr.customer.domain.OrderVO;
import ezen.bizqr.customer.repository.OrderMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService{

    private final OrderMapper om;

    @Override
    public int basket(OrderItemVO oivo) {
        log.info("basket service impl");

        List<OrderItemVO> dupOivo = new ArrayList<OrderItemVO>(om.basketList(oivo.getTableId(), oivo.getStoreId()));

        for(OrderItemVO checkOivo : dupOivo){
            if(oivo.getMenuName().equals(checkOivo.getMenuName())){     //메뉴ID로 바꿀 예정
                oivo.setMenuAmount(oivo.getMenuAmount() + checkOivo.getMenuAmount());

                return om.basketUpdate(oivo);
            }
        }

        return om.basket(oivo);
    }

    @Override
    public List<OrderItemVO> basketList(String tableId, long storeId) {

        return om.basketList(tableId, storeId);
    }

    @Override
    public int order(OrderVO ovo) {
        log.info("order service impl");

        List<OrderItemVO> oivo = new ArrayList<OrderItemVO>(om.basketList(ovo.getTableId(), ovo.getStoreId()));

        for(OrderItemVO orderItemVO : oivo){
            om.insertOrderHistory(orderItemVO);
        }

        om.deleteOrderBasket(ovo.getTableId());

        return om.order(ovo);
    }

    @Override
    public int basketUpdate(OrderItemVO oivo) {
        log.info("basket update service impl");

        return om.basketUpdate(oivo);
    }

    @Override
    public int basketCount(String tableId, long storeId) {
        log.info("basketCount service impl");

        return om.basketCount(tableId, storeId);
    }

    @Override
    public List<ItemVO> itemList(long storeId, String tabName) {
        log.info("itemList service impl");

        return om.itemList(storeId, tabName);
    }

    @Override
    public List<String> tabList(long storeId) {
        log.info("tabList service impl");

        return om.tabList(storeId);
    }

    @Override
    public int basketDel(long menuId, String tableId, long storeId) {
        log.info("basketDel service impl");

        return om.basketDel(menuId, tableId, storeId);
    }

}
