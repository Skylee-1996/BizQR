package ezen.bizqr.store.service;

import ezen.bizqr.board.domain.BoardVO;
import ezen.bizqr.board.domain.PagingVO;
import ezen.bizqr.store.domain.MenuItemVO;
import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.domain.StoreVO;
<<<<<<< HEAD
import ezen.bizqr.user.security.UserVO;

import java.util.List;
=======
>>>>>>> origin/main

public interface StoreService {
    void insertRegister(RegisterVO rvo);

    long insertMenu(MenuItemVO mvo);

    RegisterVO getDetail(long registerNum);

<<<<<<< HEAD
    List<StoreVO> selectEmail(String userEmail);
=======
    int insertStore(StoreVO svo);
>>>>>>> origin/main
}
