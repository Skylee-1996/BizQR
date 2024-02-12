package ezen.bizqr.store.service;

import ezen.bizqr.store.domain.RegisterVO;
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
    public void storeRegister(RegisterVO rvo) {
        storeMapper.storeRegister(rvo);
    }
}