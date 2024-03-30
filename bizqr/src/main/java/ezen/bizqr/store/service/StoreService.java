package ezen.bizqr.store.service;

import ezen.bizqr.store.domain.*;

import java.util.List;

public interface StoreService {
    int insertRegister(RegisterVO rvo);

    long insertMenu(MenuItemVO mvo);

    RegisterVO getDetail(long registerNum);

    List<StoreVO> selectEmail(String userEmail);
    int insertStore(StoreVO svo);


    StoreVO getDetailFromStore(String storeId);

    List<RegisterVO> getRegisterList();
    void updateStore(StoreVO svo);

    List<StoreVO> getStoreListByType(String storeType);

    int alterRegisterInfo(long registerNum, int isRegistered);
    List<StoreVO> getStoreList();

    int insertTables(int storeId, int tableNum);

    List<TablesVO> getTablesList(long storeId);

    List<OrderHistoryVO> getTableOrderHistory(long storeId, String tableId);
}
