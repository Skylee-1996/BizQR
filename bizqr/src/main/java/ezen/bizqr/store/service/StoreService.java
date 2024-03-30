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

>>>>>>> 0de22025aca83ea0ae5e1c4e38cd2bc3e257943c
    List<StoreVO> selectEmail(String userEmail);
    int insertStore(StoreVO svo);


    StoreVO getDetailFromStore(String storeId);

    List<RegisterVO> getRegisterList();
    void updateStore(StoreVO svo);


<<<<<<< HEAD
    List<StoreVO> getStoreListByType(String storeType);

    int alterRegisterInfo(long registerNum, int isRegistered);
=======
    List<StoreVO> getStoreList();
    


    int alterRegisterInfo(long registerNum, int isRegistered);


    List<StoreVO> getStoreListByType(String storeType);

    int insertTables(int storeId, int tableNum);
>>>>>>> 0de22025aca83ea0ae5e1c4e38cd2bc3e257943c
}
