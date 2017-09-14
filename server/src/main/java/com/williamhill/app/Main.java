package com.williamhill.app;

import com.williamhill.protobuf.EmployeeServer;
import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(Main.class, args);

        EmployeeServer server = new EmployeeServer(8980);
        server.start();
        server.blockUntilShutdown();
    }
}
