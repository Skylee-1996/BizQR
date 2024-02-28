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


    StoreVO getDetailFromStore(String storeId);

<<<<<<< HEAD
    List<RegisterVO> getRegisterList();
=======
    void updateStore(StoreVO svo);

    List<StoreVO> getStoreList();

>>>>>>> 8ef0bd2f7ce085aa44ec78da0b13e0c073407d02
}
