package com.williamhill.gatling.actions

import com.williamhill.protobuf.EmployeeService.Employee

/**
  * Base trait which provide two type of calls sync and async one. It require to have name set.
  * Can be implemented as abstract class too.
  * In current version of code it has two implementations in GrpcExecutableSyncAction
  */
trait GrpcExecutableAction {
  require(name.nonEmpty)
  def name: String
  def executeSync: Option[Employee]
}
