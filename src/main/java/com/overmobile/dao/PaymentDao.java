package com.overmobile.dao;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.overmobile.model.Payment;

import static com.mongodb.client.model.Filters.eq;

public class PaymentDao {
    private static final String FIELD_PAYMENTID = "paymentId";

    private final MongoCollection<Payment> mongoCollection;

    public PaymentDao(MongoCollection<Payment> mongoCollection) {
        this.mongoCollection = mongoCollection;
    }

    public void insert(Payment payment) {
        mongoCollection.insertOne(payment);
    }
    public FindIterable<Payment> findByPaymentId(String paymentId) {
        return mongoCollection.find(eq(FIELD_PAYMENTID, paymentId));
    }
}
