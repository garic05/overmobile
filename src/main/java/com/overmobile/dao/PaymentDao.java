package com.overmobile.dao;

import com.mongodb.MongoClient;

public class PaymentDao {
    private final MongoClient mongoClient;

    public PaymentDao(MongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }
}
