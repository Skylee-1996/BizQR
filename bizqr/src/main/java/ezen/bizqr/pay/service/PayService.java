package ezen.bizqr.pay.service;

import ezen.bizqr.pay.domain.StorePaymentVO;
import org.springframework.stereotype.Service;

public interface PayService {
    int savePayment(StorePaymentVO spvo);
}
