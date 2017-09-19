package com.williamhill.gatling.actions.impl

import com.williamhill.gatling.actions.GrpcExecutableAction
import com.williamhill.protobuf.EmployeeService.GetEmployeeByIdRequest
import com.williamhill.protobuf.{EmployeeService, EmployeesServiceGrpc}
import io.grpc.{ManagedChannelBuilder, StatusRuntimeException}

/**
  * Sync call action
  */
object GrpcSyncCallAction {
  /**
    *
    * Constructor that needs couple of params in order to create valid gRPC connection
    * @param name           - function name
    * @param host           - server host
    * @param port           - server port
    * @param id             - message to be send as request
    * @return               - GrpcSyncCallAction
    */
  def apply(name: String, host: String, port: Int, id: Int): GrpcSyncCallAction = {
    println("id: " + id)
    new GrpcSyncCallAction(name, host, port, id)
  }
}

class GrpcSyncCallAction(val name: String, host: String, port: Int, id: Int) extends GrpcExecutableAction {

  var channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build
  val blockingCall = EmployeesServiceGrpc.newBlockingStub(channel)

  /**
    * Create sync call to the server
    * @return Option[GeneratedMessage]
    */
  override def executeSync = {
    val employee = GetEmployeeByIdRequest.newBuilder.setId(id).build
    println("eee: " + employee.toString)
//    Some(blockingCall.getEmployeeById(employee))
    try
      Some(blockingCall.getEmployeeById(employee))
    catch {
      case e: StatusRuntimeException =>
        System.out.println("RPC failed: " + e.getStatus)
        null
    }
  }
}
