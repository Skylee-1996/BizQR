package ezen.bizqr.store.service;

import ezen.bizqr.store.domain.MenuItemVO;
import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.domain.StoreVO;

import java.util.List;

public interface StoreService {
    void insertRegister(RegisterVO rvo);

    long insertMenu(MenuItemVO mvo);

    RegisterVO getDetail(long registerNum);

<<<<<<< HEAD
=======

>>>>>>> bcbfd3b1af554e4f03e360a1dbf507f843d214b4

    List<StoreVO> selectEmail(String userEmail);
    int insertStore(StoreVO svo);
<<<<<<< HEAD
=======

    StoreVO getDetailFromStore(String storeId);
>>>>>>> bcbfd3b1af554e4f03e360a1dbf507f843d214b4
}
