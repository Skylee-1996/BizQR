package ezen.bizqr.store.repository;

import ezen.bizqr.store.domain.MenuItemVO;
import ezen.bizqr.store.domain.RegisterVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StoreMapper {
    void storeRegister(RegisterVO rvo);

    void menuInsert(MenuItemVO mvo);

    RegisterVO getDetail(long registerNum);
}
