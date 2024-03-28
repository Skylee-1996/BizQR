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

    StoreVO getDetailFromStore(String storeId);

<<<<<<< HEAD
    List<RegisterVO> getRegisterList();

=======
>>>>>>> origin/main
    void updateStore(StoreVO svo);

    List<StoreVO> getStoreList();

    List<RegisterVO> getRegisterList();

    int alterRegisterInfo(@Param("registerNum") long registerNum, @Param("isRegistered") int isRegistered);

    List<StoreVO> getStoreListByType(String storeType);

}
<<<<<<< HEAD
=======
=======
}
>>>>>>> origin/main
>>>>>>> 438cb7534a6da31bf915ee3515e3e405716f4d24
