package gatling.simulations

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import scala.util.Random

class ProtoGetEmployeeSimulation extends Simulation {
  val feeder = Iterator.continually(Map("id" -> Random.nextInt()))

  val httpProtocol = http
    .baseURL(s"http://localhost:8080")

  val scn = scenario("getEmployee proto load test")
      .feed(feeder)
      .exec(http("proto").get("/employee/${id}").header("Accept", "application/x-protobuf").check(status.is(200)))

  setUp(
    scn.inject(
      rampUsers(5000) over (5 seconds)
    )
  ).protocols(httpProtocol)
}
