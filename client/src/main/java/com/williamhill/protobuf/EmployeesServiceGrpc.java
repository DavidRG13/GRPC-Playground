package com.williamhill.protobuf;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: MyService.proto")
public final class EmployeesServiceGrpc {

  private EmployeesServiceGrpc() {}

  public static final String SERVICE_NAME = "token.EmployeesService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.williamhill.protobuf.MyService.GetEmployeeByIdRequest,
      com.williamhill.protobuf.MyService.Employee> METHOD_GET_BOOK_BY_ISBN =
      io.grpc.MethodDescriptor.<com.williamhill.protobuf.MyService.GetEmployeeByIdRequest, com.williamhill.protobuf.MyService.Employee>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "token.EmployeesService", "GetBookByIsbn"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.williamhill.protobuf.MyService.GetEmployeeByIdRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              com.williamhill.protobuf.MyService.Employee.getDefaultInstance()))
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
    public void getBookByIsbn(com.williamhill.protobuf.MyService.GetEmployeeByIdRequest request,
        io.grpc.stub.StreamObserver<com.williamhill.protobuf.MyService.Employee> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_GET_BOOK_BY_ISBN, responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_GET_BOOK_BY_ISBN,
            asyncUnaryCall(
              new MethodHandlers<
                com.williamhill.protobuf.MyService.GetEmployeeByIdRequest,
                com.williamhill.protobuf.MyService.Employee>(
                  this, METHODID_GET_BOOK_BY_ISBN)))
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
    public void getBookByIsbn(com.williamhill.protobuf.MyService.GetEmployeeByIdRequest request,
        io.grpc.stub.StreamObserver<com.williamhill.protobuf.MyService.Employee> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_GET_BOOK_BY_ISBN, getCallOptions()), request, responseObserver);
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
    public com.williamhill.protobuf.MyService.Employee getBookByIsbn(com.williamhill.protobuf.MyService.GetEmployeeByIdRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_GET_BOOK_BY_ISBN, getCallOptions(), request);
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
    public com.google.common.util.concurrent.ListenableFuture<com.williamhill.protobuf.MyService.Employee> getBookByIsbn(
        com.williamhill.protobuf.MyService.GetEmployeeByIdRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_GET_BOOK_BY_ISBN, getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BOOK_BY_ISBN = 0;

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
        case METHODID_GET_BOOK_BY_ISBN:
          serviceImpl.getBookByIsbn((com.williamhill.protobuf.MyService.GetEmployeeByIdRequest) request,
              (io.grpc.stub.StreamObserver<com.williamhill.protobuf.MyService.Employee>) responseObserver);
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
      return com.williamhill.protobuf.MyService.getDescriptor();
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
              .addMethod(METHOD_GET_BOOK_BY_ISBN)
              .build();
        }
      }
    }
    return result;
  }
}
