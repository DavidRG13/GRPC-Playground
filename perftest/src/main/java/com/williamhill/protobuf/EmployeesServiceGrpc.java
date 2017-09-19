package com.williamhill.protobuf;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: EmployeeService.proto")
public final class EmployeesServiceGrpc {

  private EmployeesServiceGrpc() {}

  public static final String SERVICE_NAME = "token.EmployeesService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<EmployeeService.GetEmployeeByIdRequest,
      EmployeeService.Employee> METHOD_GET_EMPLOYEE_BY_ID =
      io.grpc.MethodDescriptor.<EmployeeService.GetEmployeeByIdRequest, EmployeeService.Employee>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "token.EmployeesService", "GetEmployeeById"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              EmployeeService.GetEmployeeByIdRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              EmployeeService.Employee.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static EmployeesServiceStub newStub(io.grpc.Channel channel) {
    return new EmployeesServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static EmployeesServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new EmployeesServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static EmployeesServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new EmployeesServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class EmployeesServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void getEmployeeById(EmployeeService.GetEmployeeByIdRequest request,
        io.grpc.stub.StreamObserver<EmployeeService.Employee> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_EMPLOYEE_BY_ID, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_EMPLOYEE_BY_ID,
            asyncUnaryCall(
              new MethodHandlers<
                EmployeeService.GetEmployeeByIdRequest,
                EmployeeService.Employee>(
                  this, METHODID_GET_EMPLOYEE_BY_ID)))
          .build();
    }
  }

  /**
   */
  public static final class EmployeesServiceStub extends io.grpc.stub.AbstractStub<EmployeesServiceStub> {
    private EmployeesServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EmployeesServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected EmployeesServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EmployeesServiceStub(channel, callOptions);
    }

    /**
     */
    public void getEmployeeById(EmployeeService.GetEmployeeByIdRequest request,
        io.grpc.stub.StreamObserver<EmployeeService.Employee> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_EMPLOYEE_BY_ID, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class EmployeesServiceBlockingStub extends io.grpc.stub.AbstractStub<EmployeesServiceBlockingStub> {
    private EmployeesServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EmployeesServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected EmployeesServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EmployeesServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public EmployeeService.Employee getEmployeeById(EmployeeService.GetEmployeeByIdRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_EMPLOYEE_BY_ID, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class EmployeesServiceFutureStub extends io.grpc.stub.AbstractStub<EmployeesServiceFutureStub> {
    private EmployeesServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private EmployeesServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected EmployeesServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new EmployeesServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<EmployeeService.Employee> getEmployeeById(
        EmployeeService.GetEmployeeByIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_EMPLOYEE_BY_ID, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_EMPLOYEE_BY_ID = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final EmployeesServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(EmployeesServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_EMPLOYEE_BY_ID:
          serviceImpl.getEmployeeById((EmployeeService.GetEmployeeByIdRequest) request,
              (io.grpc.stub.StreamObserver<EmployeeService.Employee>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class EmployeesServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return EmployeeService.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (EmployeesServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new EmployeesServiceDescriptorSupplier())
              .addMethod(METHOD_GET_EMPLOYEE_BY_ID)
              .build();
        }
      }
    }
    return result;
  }
}
