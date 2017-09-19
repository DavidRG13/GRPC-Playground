package com.williamhill.gatling

import com.williamhill.gatling.actions.GrpcExecutableAction
import com.williamhill.gatling.grpc.GrpcCheck
import io.gatling.core.action.builder.ActionBuilder

case class GrpcProcessBuilder(action: GrpcExecutableAction, checks: List[GrpcCheck] = Nil) extends GrpcCheckSupport{

  def check(grpcCheck: GrpcCheck*) = copy(checks = checks ::: grpcCheck.toList)

  def build(): ActionBuilder = new GrpcActionBuilder(action, checks)
}