package ezen.bizqr.store.repository;

import ezen.bizqr.board.domain.PagingVO;
import ezen.bizqr.store.domain.MenuItemVO;
import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.domain.StoreVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {
    int storeRegister(RegisterVO rvo);

    void menuInsert(MenuItemVO mvo);

    RegisterVO getDetail(long registerNum);

    List<StoreVO> selectEmail(String userEmail);

    int insertStore(StoreVO svo);

    long getMenuId();

<<<<<<< HEAD
    StoreVO getDetailFromStore(String storeId);

    void updateStore(StoreVO svo);

    List<StoreVO> getStoreList();
=======

    StoreVO getDetailFromStore(String storeId);

    List<RegisterVO> getRegisterList();

    void updateStore(StoreVO svo);

    List<StoreVO> getStoreList();

<<<<<<< HEAD
=======
>>>>>>> 8ef0bd2f7ce085aa44ec78da0b13e0c073407d02
>>>>>>> origin/main
>>>>>>> bc5c99e711f6fcd142e56671feb9eda1f772b49a
}
