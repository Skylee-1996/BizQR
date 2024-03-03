package ezen.bizqr.store.repository;

import ezen.bizqr.board.domain.PagingVO;
import ezen.bizqr.store.domain.MenuItemVO;
import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.domain.StoreVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
=======
<<<<<<< HEAD

>>>>>>> eb551ee597500a941407327de654c94501c25018
    StoreVO getDetailFromStore(String storeId);

    void updateStore(StoreVO svo);

<<<<<<< HEAD
    List<StoreVO> getStoreList();

    List<RegisterVO> getRegisterList();

    int alterRegisterInfo(@Param("registerNum") long registerNum, @Param("isRegistered") int isRegistered);
=======
=======
    StoreVO getDetailFromStore(String storeId);

    List<RegisterVO> getRegisterList();

    void updateStore(StoreVO svo);

    List<StoreVO> getStoreList();

    List<StoreVO> getStoreListByType(String storeType);
>>>>>>> origin/main
>>>>>>> eb551ee597500a941407327de654c94501c25018
}
