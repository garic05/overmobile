package com.overmobile.dao;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.overmobile.dao.codec.PaymentCodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

class DaoUtils {
    private static final CodecRegistry codecRegistry;
    private static final MongoClientOptions options;
    private static final String host;
    private static final MongoClient mongoClient;

    /*...For most applications, you should have one MongoClient instance for the entire JVM...*/
    public static MongoClient getMongoClient() {
        return mongoClient;
    }

    static {
        codecRegistry = CodecRegistries.fromRegistries(
                CodecRegistries.fromProviders(new PaymentCodecProvider()),
                MongoClient.getDefaultCodecRegistry());
        options = MongoClientOptions
                .builder()
                .codecRegistry(codecRegistry)
                .build();
        //fix
        host = "localhost:27017";
        mongoClient = new MongoClient(host,  options);
    }
}
