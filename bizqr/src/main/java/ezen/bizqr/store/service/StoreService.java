package ezen.bizqr.store.service;

import ezen.bizqr.store.domain.MenuItemVO;
import ezen.bizqr.store.domain.RegisterVO;

public interface StoreService {
    void storeRegister(RegisterVO rvo);

    void insertMenu(MenuItemVO mvo);
}
