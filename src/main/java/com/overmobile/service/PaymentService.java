package com.overmobile.service;

import com.mongodb.client.FindIterable;
import com.overmobile.dao.PaymentDao;
import com.overmobile.model.Payment;

public class PaymentService {
    private final PaymentDao paymentDao;

    public PaymentService(PaymentDao paymentDao) {
        this.paymentDao = paymentDao;
    }

    public int insertIfNotExist(Payment payment) {
        FindIterable<Payment> iterable = paymentDao.findByPaymentId(payment.getPaymentId());
        if (iterable.first() != null) {
            return 1;
        }
        paymentDao.insert(payment);
        return 0;
    }

}
