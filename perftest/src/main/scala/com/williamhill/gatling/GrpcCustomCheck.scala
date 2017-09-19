package com.williamhill.gatling

import com.williamhill.gatling.grpc.GrpcCheck
import com.williamhill.protobuf.EmployeeService.Employee
import io.gatling.commons.validation.{Failure, Validation}
import io.gatling.core.check.CheckResult
import io.gatling.core.session.Session

import scala.collection.mutable

/**
  * Simple match class, checking if response message (GeneratedMessage) satisfy checker function.
  * It is possible to write more complex checkers in case they are needed.
  * @param func
  */
case class GrpcCustomCheck(func: Employee => Boolean) extends GrpcCheck {
  override def check(response: Employee, session: Session)(implicit cache: mutable.Map[Any, Any]): Validation[CheckResult] = {
    func(response) match {
      case true => CheckResult.NoopCheckResultSuccess
      case _ => Failure("Grpc check failed")
    }
  }
}
