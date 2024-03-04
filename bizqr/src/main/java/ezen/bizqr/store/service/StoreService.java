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
    

    List<StoreVO> getStoreList();
    
    
    StoreVO getDetailFromStore(String storeId);

    
    List<RegisterVO> getRegisterList();

    int alterRegisterInfo(long registerNum, int isRegistered);

=======

    List<StoreVO> selectEmail(String userEmail);
    int insertStore(StoreVO svo);


    StoreVO getDetailFromStore(String storeId);

    int alterRegisterInfo(long registerNum, int isRegistered);
    List<StoreVO> getStoreList();
>>>>>>> 3ba1bda8cea9d55069c672bc9bde2f3e8c18b4be

    List<StoreVO> getStoreListByType(String storeType);

    void updateStore(StoreVO svo);
<<<<<<< HEAD
=======

    List<RegisterVO> getRegisterList();
>>>>>>> 3ba1bda8cea9d55069c672bc9bde2f3e8c18b4be
}
