package com.williamhill.gatling.actions.impl

import com.williamhill.gatling.actions.GrpcExecutableAction
import com.williamhill.protobuf.EmployeeService.GetEmployeeByIdRequest
import com.williamhill.protobuf.EmployeesServiceGrpc
import io.grpc.{ManagedChannelBuilder, StatusRuntimeException}

object GrpcSyncCallAction {
  /**
    *
    * Constructor that needs couple of params in order to create valid gRPC connection
    * @param name           - function name
    * @param host           - server host
    * @param port           - server port
    * @param toBeSend       - message to be send as request
    * @return               - GrpcSyncCallAction
    */
  def apply(name: String, host: String, port: Int, toBeSend: GetEmployeeByIdRequest): GrpcSyncCallAction = new GrpcSyncCallAction(name, host, port, toBeSend)
}

class GrpcSyncCallAction(val name: String, host: String, port: Int, toBeSend: GetEmployeeByIdRequest) extends GrpcExecutableAction {

  var channel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build
  val blockingCall = EmployeesServiceGrpc.newBlockingStub(channel)

  override def executeSync = {
    try
      Some(blockingCall.getEmployeeById(toBeSend))
    catch {
      case e: StatusRuntimeException =>
        System.out.println("RPC failed: " + e.getStatus)
        null
    }
  }
}
