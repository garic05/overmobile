package com.overmobile.dao.codec;

import com.overmobile.model.Payment;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

public class PaymentCodec implements Codec<Payment> {
    private CodecRegistry codecRegistry;

    public PaymentCodec(CodecRegistry codecRegistry) {
        this.codecRegistry = codecRegistry;
    }

    public PaymentCodec() {

    }

    @Override
    public Payment decode(BsonReader reader, DecoderContext decoderContext) {
        Payment payment = new Payment();
        reader.readStartDocument();
        payment.setObjectId(reader.readObjectId());
        payment.setPaymentId(reader.readString("paymentId"));
        payment.setUserId(reader.readInt64("userId"));
        payment.setSum(reader.readInt64("sum"));
        reader.readEndDocument();

        return payment;
    }

    @Override
    public void encode(BsonWriter writer, Payment value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeString("paymentId", value.getPaymentId());
        writer.writeInt64("userId", value.getUserId());
        writer.writeInt64("sum", value.getSum());
        writer.writeEndDocument();
    }

    @Override
    public Class<Payment> getEncoderClass() {
        return Payment.class;
    }
}
