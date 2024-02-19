package ezen.bizqr.store.service;

import ezen.bizqr.store.domain.MenuItemVO;
import ezen.bizqr.store.domain.RegisterVO;
import ezen.bizqr.store.domain.StoreVO;
import ezen.bizqr.store.repository.StoreMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{

    private final StoreMapper storeMapper;

    @Override
    public void insertRegister(RegisterVO rvo) {
        storeMapper.storeRegister(rvo);
    }

    @Override
    public long insertMenu(MenuItemVO mvo) {

        storeMapper.menuInsert(mvo);
        long menuid = storeMapper.getMenuId();
        return menuid;
    }

    @Override
    public RegisterVO getDetail(long registerNum) {
        return storeMapper.getDetail(registerNum);
    }

    @Override
    public int insertStore(StoreVO svo) {
        return storeMapper.insertStore(svo);
    }
}
