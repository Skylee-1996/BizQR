package ezen.bizqr.admin.repository;

import ezen.bizqr.store.domain.RegisterVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface AdminMapper {
    List<RegisterVO> selectAdimRegisterList();

    int registeredUpdate(RegisterVO rvo);
}
