package ru.nicshal.protobuf;

import io.grpc.stub.StreamObserver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nicshal.protobuf.InitialMessage;
import ru.nicshal.protobuf.RemoteSequenceServiceGrpc;
import ru.nicshal.protobuf.ValueMessage;

import java.util.concurrent.TimeUnit;

public class RemoteSequenceServiceImpl extends RemoteSequenceServiceGrpc.RemoteSequenceServiceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(RemoteSequenceServiceImpl.class);
    private static final int SERVER_SLEEP_MILLISECONDS = 2000;

    @Override
    public void generateSequence(InitialMessage request, StreamObserver<ValueMessage> responseObserver) {

        int firstValue = request.getFirstValue();
        int lastValue = request.getLastValue();

        logger.info("method generateSequence() starts.");
        logger.info("generate sequence from {} to {}", firstValue, lastValue);

        for (int idx = firstValue + 1; idx <= lastValue; idx++) {
            responseObserver.onNext(ValueMessage.newBuilder().setValue(idx).build());
            logger.info("generate value: {}", idx);
            try {
                TimeUnit.MILLISECONDS.sleep(SERVER_SLEEP_MILLISECONDS);
            } catch (InterruptedException e) {
                logger.info(e.getMessage(), e);
            }
        }

        responseObserver.onCompleted();
    }

}
