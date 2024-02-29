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

    List<RegisterVO> getRegisterList();
<<<<<<< HEAD

=======
>>>>>>> origin/main
    void updateStore(StoreVO svo);

    List<StoreVO> getStoreList();

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> 8ef0bd2f7ce085aa44ec78da0b13e0c073407d02
>>>>>>> origin/main
>>>>>>> bc5c99e711f6fcd142e56671feb9eda1f772b49a
>>>>>>> origin/main
}
