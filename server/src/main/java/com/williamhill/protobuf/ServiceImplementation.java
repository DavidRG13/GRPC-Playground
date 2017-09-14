package com.williamhill.protobuf;

import io.grpc.stub.StreamObserver;

// Bad named to make it clearer
public class ServiceImplementation extends EmployeesServiceGrpc.EmployeesServiceImplBase {

    @Override
    public void getEmployeeById(final EmployeeService.GetEmployeeByIdRequest request, final StreamObserver<EmployeeService.Employee> responseObserver) {
        int employeeId = request.getId();
        EmployeeService.Employee employee = EmployeeService.Employee.newBuilder().setFirstname("Pepe").setLastname("Pepe").setId(employeeId).setSalary(1000.0).build();

        responseObserver.onNext(employee);
        responseObserver.onCompleted();
    }
}
