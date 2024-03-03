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
=======
<<<<<<< HEAD
>>>>>>> eb551ee597500a941407327de654c94501c25018



<<<<<<< HEAD
    List<StoreVO> getStoreList();
=======
=======
>>>>>>> origin/main
    List<StoreVO> selectEmail(String userEmail);
    int insertStore(StoreVO svo);


    StoreVO getDetailFromStore(String storeId);
<<<<<<< HEAD


=======
>>>>>>> origin/main
>>>>>>> eb551ee597500a941407327de654c94501c25018

    List<RegisterVO> getRegisterList();

<<<<<<< HEAD
    int alterRegisterInfo(long registerNum, int isRegistered);
=======
<<<<<<< HEAD
=======
    List<StoreVO> getStoreList();

    List<StoreVO> getStoreListByType(String storeType);
>>>>>>> origin/main
>>>>>>> eb551ee597500a941407327de654c94501c25018
}
