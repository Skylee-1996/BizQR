package ezen.bizqr.store.repository;

import ezen.bizqr.store.domain.*;
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

    void updateStore(StoreVO svo);

    List<StoreVO> getStoreList();

    List<RegisterVO> getRegisterList();

    int alterRegisterInfo(@Param("registerNum") long registerNum, @Param("isRegistered") int isRegistered);

    List<StoreVO> getStoreListByType(String storeType);

    int insertTable(@Param("storeId") int storeId,@Param("tableId") String tableId);

    List<TablesVO> getTablesList(long storeId);

    List<OrderHistoryVO> getTableOrderHistory(@Param("storeId") long storeId, @Param("tableId") String tableId);

    int saveTablePay(tablePayHistoryVO tphvo);

    int deleteTableOrderHistory(@Param("storeId") long storeId, @Param("combinedTableId") String combinedTableId);
}
