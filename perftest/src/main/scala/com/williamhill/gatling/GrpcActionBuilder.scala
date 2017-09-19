package com.williamhill.gatling

import com.williamhill.gatling.actions.{GrpcAction, GrpcExecutableAction}
import com.williamhill.gatling.grpc.GrpcCheck
import io.gatling.core.action.Action
import io.gatling.core.action.builder.ActionBuilder
import io.gatling.core.protocol.Protocols
import io.gatling.core.structure.ScenarioContext

/**
  * Responsible to create specific action. Note in our case it uses functionName as criteria to decide if action will
  * execute sync or async call. In some other cases different criteria can be used.
  * @param action - grpc action used for testing
  * @param checks - The way how response can be validated.
  */
case class GrpcActionBuilder(action: GrpcExecutableAction, checks: List[GrpcCheck]) extends ActionBuilder{

  def grpcProtocol(protocols: Protocols) = {
    protocols.protocol[GrpcProtocol].getOrElse(throw new UnsupportedOperationException("gRPC protocol wasn't registered"))
  }

  override def build(ctx: ScenarioContext, next: Action): Action = {
    import ctx._
    val statsEngine = coreComponents.statsEngine
    GrpcAction(action, checks, new GrpcProtocol, ctx.system, statsEngine, next)
//    functionName match {
//      case "async" => GrpcAction(GrpcAsyncCallAction(functionName, host, port, requestMessage = payload.get), checks, new GrpcProtocol, ctx.system, statsEngine, next)
//      case "sync"  => GrpcAction(GrpcSyncCallAction(functionName, host, port, requestMessage = payload.get), checks, new GrpcProtocol, ctx.system, statsEngine, next)
//      case       _ => throw new UnsupportedOperationException(s"Operation $functionName is not supported")
//    }
  }
}
