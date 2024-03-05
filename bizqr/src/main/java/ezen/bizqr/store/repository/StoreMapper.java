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
=======
<<<<<<< HEAD


=======
<<<<<<< HEAD
>>>>>>> origin/main
>>>>>>> ef545580000efe1070f540661582351283e5f263
>>>>>>> origin/main
    StoreVO getDetailFromStore(String storeId);

    void updateStore(StoreVO svo);

    List<StoreVO> getStoreList();

    List<RegisterVO> getRegisterList();

    int alterRegisterInfo(@Param("registerNum") long registerNum, @Param("isRegistered") int isRegistered);

    List<StoreVO> getStoreListByType(String storeType);
<<<<<<< HEAD
}
=======
}




<<<<<<< HEAD
=======
    StoreVO getDetailFromStore(String storeId);
>>>>>>> origin/main

    List<RegisterVO> getRegisterList();


    List<StoreVO> getStoreList();

    List<StoreVO> getStoreListByType(String storeType);
<<<<<<< HEAD

=======
>>>>>>> origin/main
}
>>>>>>> 3ba1bda8cea9d55069c672bc9bde2f3e8c18b4be
>>>>>>> ef545580000efe1070f540661582351283e5f263
>>>>>>> origin/main
