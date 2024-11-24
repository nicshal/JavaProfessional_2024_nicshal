package ru.nicshal.protobuf;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.nicshal.protobuf.InitialMessage;
import ru.nicshal.protobuf.RemoteSequenceServiceGrpc;

import java.util.concurrent.TimeUnit;

public class GRPCClient {

    private static final Logger logger = LoggerFactory.getLogger(GRPCClient.class);

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8082;
    private static final int FIRST_VALUE = 0;
    private static final int LAST_VALUE = 30;
    private static final int MAX_CYCLE_VALUE = 50;
    private static final int CLIENT_SLEEP_SECONDS = 1;

    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(SERVER_HOST, SERVER_PORT)
                .usePlaintext()
                .build();
        logger.info("Channel created...");

        RemoteSequenceServiceGrpc.RemoteSequenceServiceStub stub = RemoteSequenceServiceGrpc.newStub(channel);
        InitialMessage message = InitialMessage.newBuilder().setFirstValue(FIRST_VALUE).setLastValue(LAST_VALUE).build();
        logger.info("Initial message formed...");

        int currentValue = 0;

        StreamObserverImpl responseObserver = new StreamObserverImpl();
        stub.generateSequence(message, responseObserver);

        for (int i = 0; i < MAX_CYCLE_VALUE; i++) {
            currentValue = currentValue + responseObserver.getValueAndReset() + 1;
            logger.info("currentValue: {}", currentValue);
            TimeUnit.SECONDS.sleep(CLIENT_SLEEP_SECONDS);
        }

        channel.shutdown();
        logger.info("Client completed work");
    }

}
