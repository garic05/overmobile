package com.overmobile.dao.codec;

import com.overmobile.model.Payment;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

public class PaymentCodec implements Codec<Payment> {
    private static final String FIELD_PAYMENTID = "paymentId";
    private static final String FIELD_USERID = "userId";
    private static final String FIELD_SUM = "sum";

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
        payment.setPaymentId(reader.readString(FIELD_PAYMENTID));
        payment.setUserId(reader.readInt64(FIELD_USERID));
        payment.setSum(reader.readInt64(FIELD_SUM));
        reader.readEndDocument();

        return payment;
    }

    @Override
    public void encode(BsonWriter writer, Payment value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeString(FIELD_PAYMENTID, value.getPaymentId());
        writer.writeInt64(FIELD_USERID, value.getUserId());
        writer.writeInt64(FIELD_SUM, value.getSum());
        writer.writeEndDocument();
    }

    @Override
    public Class<Payment> getEncoderClass() {
        return Payment.class;
    }
}
