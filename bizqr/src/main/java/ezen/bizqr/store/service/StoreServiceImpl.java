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

<<<<<<< HEAD

=======
>>>>>>> origin/main
    @Override
    public List<RegisterVO> getRegisterList() {
        return storeMapper.getRegisterList();
    }

<<<<<<< HEAD
=======
    @Override
    public int alterRegisterInfo(long registerNum, int isRegistered) {
        return storeMapper.alterRegisterInfo(registerNum, isRegistered);
    }

>>>>>>> origin/main
    public void updateStore(StoreVO svo) {
        storeMapper.updateStore(svo);
    }

<<<<<<< HEAD

=======
<<<<<<< HEAD
=======

>>>>>>> 3ba1bda8cea9d55069c672bc9bde2f3e8c18b4be
>>>>>>> origin/main
    @Override
    public List<StoreVO> getStoreList() {
        return storeMapper.getStoreList();
    }

    @Override
    public List<StoreVO> getStoreListByType(String storeType) {
        return storeMapper.getStoreListByType(storeType);
    }
<<<<<<< HEAD

=======
>>>>>>> 3ba1bda8cea9d55069c672bc9bde2f3e8c18b4be
}
<<<<<<< HEAD



=======
>>>>>>> origin/main
