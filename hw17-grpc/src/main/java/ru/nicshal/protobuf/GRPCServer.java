package ru.nicshal.protobuf;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class GRPCServer {

    private static final Logger logger = LoggerFactory.getLogger(GRPCServer.class);

    public static final int SERVER_PORT = 8082;

    public static void main(String[] args) throws IOException, InterruptedException {
        RemoteSequenceServiceImpl remoteSequenceService = new RemoteSequenceServiceImpl();
        Server server = ServerBuilder.forPort(SERVER_PORT).addService(remoteSequenceService).build();
        server.start();
        logger.info("Waiting for connections...");
        server.awaitTermination();
    }

}
