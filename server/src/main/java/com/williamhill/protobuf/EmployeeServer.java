package com.williamhill.protobuf;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;
import org.apache.log4j.Logger;

public class EmployeeServer {

    private static final Logger logger = Logger.getLogger(EmployeeServer.class);
    private final int port;
    private final Server server;

    public EmployeeServer(int port) {
        this.port = port;
        server = ServerBuilder.forPort(port)
            .addService(new ServiceImplementation())
            .build();
    }

    /** Start serving requests. */
    public void start() throws IOException {
        server.start();
        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may has been reset by its JVM shutdown hook.
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            EmployeeServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    /** Stop serving requests and shutdown resources. */
    public void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
