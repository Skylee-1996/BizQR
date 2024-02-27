package ezen.bizqr.pay.service;

import ezen.bizqr.pay.domain.StorePaymentVO;
import ezen.bizqr.pay.repository.PayMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PayServiceImpl implements PayService {

    private final PayMapper payMapper;

    @Override
    public int savePayment(StorePaymentVO spvo) {
        return payMapper.savePayment(spvo);
    }
}
