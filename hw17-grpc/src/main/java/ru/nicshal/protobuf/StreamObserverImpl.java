package ru.nicshal.protobuf;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nicshal.protobuf.ValueMessage;

public class StreamObserverImpl implements StreamObserver<ValueMessage> {

    private static final Logger logger = LoggerFactory.getLogger(StreamObserverImpl.class);

    private int value = 0;

    @Override
    public void onNext(ValueMessage message) {
        setValue(message.getValue());
        logger.info("new value: {}", this.value);
    }

    @Override
    public void onError(Throwable t) {
        logger.error(t.getMessage(), t);
    }

    @Override
    public void onCompleted() {
        logger.info("request completed");
    }

    public synchronized void setValue(int value) {
        this.value = value;
    }

    public synchronized int getValueAndReset() {
        int value = this.value;
        this.value = 0;
        return value;
    }

}
