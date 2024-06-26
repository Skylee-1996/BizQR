package ezen.bizqr.store.service;

import ezen.bizqr.board.domain.PagingVO;
import ezen.bizqr.store.domain.*;
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
    public int insertTables(int storeId, int tableNum) {
        String tableId = storeId + "_" + tableNum;
        return storeMapper.insertTable(storeId, tableId);
    }

    @Override
    public List<TablesVO> getTablesList(long storeId) {
        return storeMapper.getTablesList(storeId);
    }

    @Override
    public List<OrderHistoryVO> getTableOrderHistory(long storeId, String tableId) {
        return storeMapper.getTableOrderHistory(storeId, tableId);
    }

    @Override
    public int saveTablePay(tablePayHistoryVO tphvo) {
        return storeMapper.saveTablePay(tphvo);
    }

    @Override
    public int deleteTableOrderHistory(long storeId, String combinedTableId) {
        return storeMapper.deleteTableOrderHistory(storeId, combinedTableId);
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

    @Override
    public List<StoreVO> getStoreList() {
        return storeMapper.getStoreList();
    }

    @Override
    public List<StoreVO> getStoreListByType(String storeType) {
        return storeMapper.getStoreListByType(storeType);
    }
}