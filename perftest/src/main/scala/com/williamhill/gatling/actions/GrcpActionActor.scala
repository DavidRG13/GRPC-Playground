package com.williamhill.gatling.actions

import akka.actor.Props
import com.williamhill.gatling.GrpcProtocol
import com.williamhill.gatling.grpc.GrpcCheck
import com.williamhill.protobuf.EmployeeService.Employee
import io.gatling.commons.stats.{KO, OK}
import io.gatling.commons.validation.Failure
import io.gatling.core.action.{Action, ActionActor}
import io.gatling.core.check.Check
import io.gatling.core.session.Session
import io.gatling.core.stats.StatsEngine
import io.gatling.core.stats.message.ResponseTimings

/**
  * Execute tests and write results in stats engine
  */
object GrpcActionActor {
  def props(action: GrpcExecutableAction, checks: List[GrpcCheck], protocol: GrpcProtocol, statsEngine: StatsEngine, next: Action): Props = {
    Props(new GrpcActionActor(action, checks, protocol, statsEngine, next))
  }
}

class GrpcActionActor(action: GrpcExecutableAction,
                      checks: List[GrpcCheck],
                      protocol: GrpcProtocol,
                      val statsEngine: StatsEngine,
                      val next: Action) extends ActionActor {

  override def execute(session: Session): Unit = {
    val startTime = System.currentTimeMillis()
    var optionalResult: Option[Employee] = None
    var optionalThrowable : Option[Throwable] = None

    try {
      action match {
        case act: GrpcExecutableAction =>
          optionalResult = act.executeSync
          logResult(optionalResult)
        case _ => throw new UnsupportedOperationException("Action is not supported")
      }
    }
    catch {
      case t: Throwable =>
        optionalThrowable = Some(t)
        logResult(None, optionalThrowable)
    }

    /**
      * Write results or log errors
      * @param maybeResult - response from the server that will be checked
      * @param error       - error in case it exists
      */
    def logResult(maybeResult: Option[Employee], error: Option[Throwable] = None) = {
      val endTime = System.currentTimeMillis()
      val timings = ResponseTimings(startTime, endTime)

      if (error.isEmpty) {
        val result = maybeResult.get
        if (Option(result).nonEmpty) {
          val (newSession, error) = Check.check(result, session, checks)
          error match {
            case None =>
              statsEngine.logResponse(session, action.name, timings, OK, None, None)
              next ! newSession(session)
            case Some(Failure(errorMessage)) =>
              statsEngine.logResponse(session, action.name, timings, KO, None, Some(errorMessage))
              next ! newSession(session).markAsFailed
          }
        }
        else {
          statsEngine.logResponse(session, action.name, timings, KO, None, Some(s"Error during the call!"))
          next ! session.markAsFailed
        }
      }
      else {
        val throwable = error.get
        statsEngine.logResponse(session, action.name, timings, KO, None, Some(throwable.getMessage))
        next ! session.markAsFailed
      }
    }
  }
}