package ezen.bizqr.admin.service;
import java.util.List;
import ezen.bizqr.store.domain.RegisterVO;

public interface AdminService {
    List<RegisterVO> getList();

    int post(RegisterVO rvo);
}
