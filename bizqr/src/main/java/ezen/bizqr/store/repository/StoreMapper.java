package ezen.bizqr.store.repository;

import ezen.bizqr.board.domain.PagingVO;
import ezen.bizqr.store.domain.MenuItemVO;
import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.domain.StoreVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {
    void storeRegister(RegisterVO rvo);

    void menuInsert(MenuItemVO mvo);

    RegisterVO getDetail(long registerNum);

    List<StoreVO> selectEmail(String userEmail);

    int insertStore(StoreVO svo);

    long getMenuId();
<<<<<<< HEAD
=======


    StoreVO getDetailFromStore(String storeId);
>>>>>>> bcbfd3b1af554e4f03e360a1dbf507f843d214b4
}
