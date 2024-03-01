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



=======
>>>>>>> origin/main
    List<StoreVO> selectEmail(String userEmail);
    int insertStore(StoreVO svo);


    StoreVO getDetailFromStore(String storeId);
<<<<<<< HEAD


=======
>>>>>>> origin/main

    List<RegisterVO> getRegisterList();
    void updateStore(StoreVO svo);

<<<<<<< HEAD
=======
    List<StoreVO> getStoreList();

    List<StoreVO> getStoreListByType(String storeType);
>>>>>>> origin/main
}
