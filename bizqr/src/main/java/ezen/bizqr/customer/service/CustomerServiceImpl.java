package ezen.bizqr.customer.service;

import ezen.bizqr.customer.domain.*;
import ezen.bizqr.customer.handler.IdHandler;
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

        IdHandler ih = new IdHandler();
        String orderId = ih.orderIdHandler(ovo.getStoreId(), ovo.getTableId());
        ovo.setOrderId(orderId);

        OrderHistoryDTO odto = new OrderHistoryDTO();
        odto.setOilist(om.basketList(ovo.getTableId(), ovo.getStoreId()));

        OrderVO historyOvo = new OrderVO();
        odto.setOvo(historyOvo);
        odto.getOvo().setOrderId(ovo.getOrderId());

        if(odto.getOilist().isEmpty()){         //단순 직원호출
            if(ovo.getUserRequest() == null){   //아무것도 요청 안한경우 및 장바구니에서 그냥 주문하기를 눌렀을경우
                return 3;
            }
            om.order(ovo);

            log.info("only request service impl");
            OrderItemVO oivo = new OrderItemVO();
            oivo.setTableId(ovo.getTableId());
            oivo.setStoreId(ovo.getStoreId());
            oivo.setMenuName("요청사항");

            om.insertOrderHistory(ovo, oivo);

            om.deleteOrderBasket(ovo.getTableId(), ovo.getStoreId());

            return 2;
        }

        om.order(ovo);

        for(int i=0; i<odto.getOilist().size(); i++){
            if(i==odto.getOilist().size()-1){
                log.info("마지막 메뉴 저장 완료");
                odto.setOvo(ovo);
                om.insertOrderHistory(odto.getOvo(), odto.getOilist().get(i));

                break;
            }
            om.insertOrderHistory(odto.getOvo(), odto.getOilist().get(i));
            log.info((i+1)+"번째 메뉴 저장 완료");
        }

        om.deleteOrderBasket(ovo.getTableId(), ovo.getStoreId());

        return 1;
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

    @Override
    public String menuPrice(long storeId, String tableId) {
        log.info("menuPrice service impl");

        long menuPrice = 0;

        List<OrderItemVO> oilist = om.menuPrice(storeId, tableId);
        OrderItemVO oivo = new OrderItemVO();

        for(OrderItemVO orderItemVO : oilist){
            menuPrice += (orderItemVO.getMenuPrice() * orderItemVO.getMenuAmount());
        }

        return oivo.getMenuMainTotalComma(menuPrice);
    }

    @Override
    public List<PostOrderHistoryVO> orderHistory(long storeId, String tableId) {
        log.info("orderHistory service impl");

        List<String> orderIdList = om.orderHistoryOrderId(storeId, tableId);

        List<PostOrderHistoryVO> ohlist = new ArrayList<PostOrderHistoryVO>();

        for (String orderId : orderIdList) {
            PostOrderHistoryVO pohvo = new PostOrderHistoryVO(om.orderHistory(storeId, tableId, orderId));

            ohlist.add(pohvo);
        }

        return ohlist;
    }

}
