package ezen.bizqr.pay.repository;

import ezen.bizqr.pay.domain.StorePaymentVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PayMapper {
    int savePayment(StorePaymentVO spvo);
}
