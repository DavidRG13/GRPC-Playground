package com.williamhill.protobuf;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

public class EmployeeClient {

    private final ManagedChannel channel;
    private final EmployeesServiceGrpc.EmployeesServiceBlockingStub blockingStub;
    private final EmployeesServiceGrpc.EmployeesServiceStub asyncStub;

    public EmployeeClient(String host, int port) {
        this(ManagedChannelBuilder.forAddress(host, port).usePlaintext(true));
    }

    /** Construct client for accessing RouteGuide server using the existing channel. */
    public EmployeeClient(ManagedChannelBuilder<?> channelBuilder) {
        channel = channelBuilder.build();
        blockingStub = EmployeesServiceGrpc.newBlockingStub(channel);
        asyncStub = EmployeesServiceGrpc.newStub(channel);
    }

    public EmployeeService.Employee getEmployeeWithId(int id) {
        EmployeeService.GetEmployeeByIdRequest request = EmployeeService.GetEmployeeByIdRequest.newBuilder().setId(id).build();

        try {
            return blockingStub.getEmployeeById(request);
        } catch (StatusRuntimeException e) {
            System.out.println("RPC failed: " + e.getStatus());
            return null;
        }
    }
}
