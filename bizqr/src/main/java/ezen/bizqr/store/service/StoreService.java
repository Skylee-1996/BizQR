package ezen.bizqr.store.service;

import ezen.bizqr.store.domain.MenuItemVO;
import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.domain.StoreVO;

import java.util.List;

public interface StoreService {
    int insertRegister(RegisterVO rvo);

    long insertMenu(MenuItemVO mvo);

    RegisterVO getDetail(long registerNum);

<<<<<<< HEAD
    List<StoreVO> selectEmail(String userEmail);
    int insertStore(StoreVO svo);

    StoreVO getDetailFromStore(String storeId);

    void updateStore(StoreVO svo);

    List<StoreVO> getStoreList();
=======

    List<StoreVO> selectEmail(String userEmail);
    int insertStore(StoreVO svo);


    StoreVO getDetailFromStore(String storeId);

<<<<<<< HEAD
    List<RegisterVO> getRegisterList();
=======
    void updateStore(StoreVO svo);

    List<StoreVO> getStoreList();

>>>>>>> 8ef0bd2f7ce085aa44ec78da0b13e0c073407d02
>>>>>>> origin/main
}
