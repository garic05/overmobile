package com.overmobile.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.overmobile.dao.codec.PaymentCodecProvider;
import com.overmobile.util.PropertyUtil;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

public class DaoUtils {
    private static final CodecRegistry codecRegistry = CodecRegistries.fromRegistries(
            CodecRegistries.fromProviders(new PaymentCodecProvider()),
            MongoClient.getDefaultCodecRegistry());
    private static final MongoClientOptions options = MongoClientOptions
            .builder()
            .codecRegistry(codecRegistry)
            .build();
    private static final String host = PropertyUtil.getProperty("mongodb.host");
    private static final MongoClient  mongoClient = new MongoClient(host,  options);

    /*"...For most applications, you should have one MongoClient instance for the entire JVM..."*/
    public static MongoClient getMongoClient() {
        return mongoClient;
    }
}
