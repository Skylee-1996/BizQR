package ezen.bizqr.pay.repository;

import ezen.bizqr.pay.domain.StorePaymentVO;
import ezen.bizqr.store.domain.RegisterVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayMapper {
    int savePayment(StorePaymentVO spvo);

    StorePaymentVO getImpUidWithMerchantUid(String merchantUid);
}
