package gatling.simulations

import com.williamhill.gatling.GrpcCustomCheck
import com.williamhill.gatling.actions.impl.GrpcSyncCallAction
import com.williamhill.protobuf.EmployeeService.Employee
import io.gatling.core.Predef._

import scala.concurrent.duration._
import scala.util.Random

class GrpcGetEmployeeSimulation extends Simulation {
  import com.williamhill.gatling.Predef._

  val host = "localhost"
  val port = 8980

  val grpcConfig = GRPC()

  val grpcScenario = scenario("Test GRPC server")
    .exec(grpcCall(GrpcSyncCallAction("sync", host, port, Random.nextInt())).check(GrpcCustomCheck((s: Employee) => {
      s.getFirstname.equals("Pepe")
    })))

  setUp(
    grpcScenario.inject(
      rampUsers(5000) over (5 seconds)
    )
  ).protocols(grpcConfig)
}
