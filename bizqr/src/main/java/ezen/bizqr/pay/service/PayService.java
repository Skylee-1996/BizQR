package ezen.bizqr.pay.service;

import ezen.bizqr.pay.domain.StorePaymentVO;
import ezen.bizqr.store.domain.RegisterVO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PayService {
    int savePayment(StorePaymentVO spvo);

}
