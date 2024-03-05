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
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> ef545580000efe1070f540661582351283e5f263
>>>>>>> origin/main
    List<StoreVO> selectEmail(String userEmail);
    int insertStore(StoreVO svo);
    

    List<StoreVO> getStoreList();
    
    
    StoreVO getDetailFromStore(String storeId);

    
    List<RegisterVO> getRegisterList();

    int alterRegisterInfo(long registerNum, int isRegistered);

<<<<<<< HEAD
    void updateStore(StoreVO svo);


    List<StoreVO> getStoreListByType(String storeType);
=======
<<<<<<< HEAD
=======
=======

>>>>>>> origin/main
    List<StoreVO> selectEmail(String userEmail);
    int insertStore(StoreVO svo);


    StoreVO getDetailFromStore(String storeId);

<<<<<<< HEAD

    List<RegisterVO> getRegisterList();
    void updateStore(StoreVO svo);


=======
    int alterRegisterInfo(long registerNum, int isRegistered);
>>>>>>> origin/main
    List<StoreVO> getStoreList();
>>>>>>> 3ba1bda8cea9d55069c672bc9bde2f3e8c18b4be

>>>>>>> ef545580000efe1070f540661582351283e5f263
    List<StoreVO> getStoreListByType(String storeType);

<<<<<<< HEAD
=======
    void updateStore(StoreVO svo);
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======

    List<RegisterVO> getRegisterList();
>>>>>>> 3ba1bda8cea9d55069c672bc9bde2f3e8c18b4be
>>>>>>> origin/main
>>>>>>> ef545580000efe1070f540661582351283e5f263
>>>>>>> origin/main
}
