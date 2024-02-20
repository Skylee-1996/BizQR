package ezen.bizqr.store.service;

import ezen.bizqr.board.domain.PagingVO;
import ezen.bizqr.store.domain.MenuItemVO;
import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.domain.StoreVO;
import ezen.bizqr.store.repository.StoreMapper;
import ezen.bizqr.user.security.UserVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{

    private final StoreMapper storeMapper;

    @Override
    public void storeRegister(RegisterVO rvo) {
        storeMapper.storeRegister(rvo);
    }

    @Override
    public void insertMenu(MenuItemVO mvo) {
        storeMapper.menuInsert(mvo);
    }

    @Override
    public RegisterVO getDetail(long registerNum) {
        return storeMapper.getDetail(registerNum);
    }

    @Override
    public  List<StoreVO> selectEmail(String userEmail) {
        return storeMapper.selectEmail(userEmail);
    }


}
