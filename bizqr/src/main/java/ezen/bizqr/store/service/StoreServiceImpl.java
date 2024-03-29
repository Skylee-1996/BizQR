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
public class StoreServiceImpl implements StoreService {

    private final StoreMapper storeMapper;

    @Override
    public int insertRegister(RegisterVO rvo) {
        return storeMapper.storeRegister(rvo);
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
    public List<StoreVO> selectEmail(String userEmail) {
        return storeMapper.selectEmail(userEmail);
    }

    public int insertStore(StoreVO svo) {

        return storeMapper.insertStore(svo);
    }

    @Override
    public StoreVO getDetailFromStore(String storeId) {
        return storeMapper.getDetailFromStore(storeId);
    }

    @Override
    public List<RegisterVO> getRegisterList() {
        return storeMapper.getRegisterList();
    }

    @Override
    public int alterRegisterInfo(long registerNum, int isRegistered) {
        return storeMapper.alterRegisterInfo(registerNum, isRegistered);
    }

    public void updateStore(StoreVO svo) {
        storeMapper.updateStore(svo);
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD

=======
>>>>>>> origin/main
>>>>>>> 438cb7534a6da31bf915ee3515e3e405716f4d24
    @Override
    public List<StoreVO> getStoreList() {
        return storeMapper.getStoreList();
    }

    @Override
    public List<StoreVO> getStoreListByType(String storeType) {
        return storeMapper.getStoreListByType(storeType);
    }
<<<<<<< HEAD
}
=======
<<<<<<< HEAD
}
=======

}

>>>>>>> origin/main
>>>>>>> 438cb7534a6da31bf915ee3515e3e405716f4d24
