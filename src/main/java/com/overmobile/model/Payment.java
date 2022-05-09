package com.overmobile.model;

import org.bson.types.ObjectId;

import java.io.Serializable;

public class Payment implements Serializable {
    private ObjectId objectId;
    private String paymentId;
    private Long userId;
    private Long sum;

    public Payment() {
    }

    public Payment(ObjectId objectId, String paymentId, Long userId, Long sum) {
        this.objectId = objectId;
        this.paymentId = paymentId;
        this.userId = userId;
        this.sum = sum;
    }

    public ObjectId getObjectId() {
        return objectId;
    }

    public void setObjectId(ObjectId objectId) {
        this.objectId = objectId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }
}
