package ezen.bizqr.store.service;

import ezen.bizqr.board.domain.BoardVO;
import ezen.bizqr.board.domain.PagingVO;
import ezen.bizqr.store.domain.MenuItemVO;
import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.domain.StoreVO;
import ezen.bizqr.user.security.UserVO;

import java.util.List;

public interface StoreService {
    void insertRegister(RegisterVO rvo);

    long insertMenu(MenuItemVO mvo);

    RegisterVO getDetail(long registerNum);


    List<StoreVO> selectEmail(String userEmail);

    int insertStore(StoreVO svo);

}
