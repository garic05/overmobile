package com.overmobile.dao.codec;

import com.overmobile.model.Payment;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class PaymentCodecProvider implements CodecProvider {
    @Override
    public <T> Codec<T> get(Class<T> clazz, CodecRegistry registry) {
        if (clazz == Payment.class) {
            return (Codec<T>) new PaymentCodec(registry);
        }
        return null;
    }
}
