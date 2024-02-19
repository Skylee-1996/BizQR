package ezen.bizqr.admin.service;

import ezen.bizqr.admin.repository.AdminMapper;
import ezen.bizqr.store.domain.RegisterVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminMapper adminMapper;

    @Override
    public List<RegisterVO> getList() {
        return adminMapper.selectAdimRegisterList();
    }

    @Override
    public int update(RegisterVO rvo) {
        return adminMapper.registeredUpdate(rvo);
    }



}
