package ezen.bizqr.store.service;

import ezen.bizqr.store.domain.MenuItemVO;
import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.domain.StoreVO;

import java.util.List;

public interface StoreService {
    int insertRegister(RegisterVO rvo);

    long insertMenu(MenuItemVO mvo);

    RegisterVO getDetail(long registerNum);

    List<StoreVO> selectEmail(String userEmail);
    int insertStore(StoreVO svo);

    StoreVO getDetailFromStore(String storeId);

    void updateStore(StoreVO svo);

    List<StoreVO> getStoreList();

    List<RegisterVO> getRegisterList();

    int alterRegisterInfo(long registerNum, int isRegistered);
}
