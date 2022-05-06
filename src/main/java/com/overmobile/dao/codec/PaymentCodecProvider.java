package com.overmobile.dao.codec;

import com.overmobile.model.Payment;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class PaymentCodecProvider implements CodecProvider {
    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz == Payment.class) {
            // construct DocumentCodec with a CodecRegistry
            return (Codec<T>) new PaymentCodec(registry);
        }

        // CodecProvider returns null if it's not a provider for the requresed Class
        return null;
    }
}
