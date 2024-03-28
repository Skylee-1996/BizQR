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
    List<StoreVO> selectEmail(String userEmail);
    int insertStore(StoreVO svo);


    StoreVO getDetailFromStore(String storeId);

    List<RegisterVO> getRegisterList();
    void updateStore(StoreVO svo);

    List<StoreVO> getStoreList();

    List<StoreVO> getStoreListByType(String storeType);
=======
>>>>>>> 438cb7534a6da31bf915ee3515e3e405716f4d24
    List<StoreVO> getStoreList();
    
    
    StoreVO getDetailFromStore(String storeId);

    
    List<RegisterVO> getRegisterList();

    int alterRegisterInfo(long registerNum, int isRegistered);

<<<<<<< HEAD
    List<StoreVO> getStoreListByType(String storeType);

    void updateStore(StoreVO svo);
=======
    void updateStore(StoreVO svo);

    List<StoreVO> getStoreListByType(String storeType);

    List<StoreVO> selectEmail(String userEmail);
    int insertStore(StoreVO svo);

>>>>>>> origin/main
>>>>>>> 438cb7534a6da31bf915ee3515e3e405716f4d24
}
