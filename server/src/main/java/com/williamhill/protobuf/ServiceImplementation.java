package com.williamhill.protobuf;

import io.grpc.Status;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;

// Bad named to make it clearer
public class ServiceImplementation extends EmployeesServiceGrpc.EmployeesServiceImplBase {

    @Override
    public void getEmployeeById(final EmployeeService.GetEmployeeByIdRequest request, final StreamObserver<EmployeeService.Employee> responseObserver) {
        int employeeId = request.getId();
        System.out.println("employeeId = " + employeeId);
        EmployeeService.Employee employee = EmployeeService.Employee.newBuilder().setFirstname("Pepe").setLastname("Pepe").setId(employeeId).setSalary(1000.0).build();

        if (employeeId < 0) {
            StatusRuntimeException e = Status.INVALID_ARGUMENT.withDescription("employeeId should be positive").asRuntimeException();
            responseObserver.onError(e);
            //throw e;
        }

        responseObserver.onNext(employee);
        responseObserver.onCompleted();
    }
}
