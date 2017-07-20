package com.azavea

import akka.actor.ActorSystem
import akka.http.scaladsl.server.{ HttpApp, Route }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.concurrent.blocking

object WebServerHttpApp extends HttpApp with App {
  val system = ActorSystem("test-execution-contexts")

  def blockingOperation: String = {
    Thread.sleep(5000)
    System.currentTimeMillis().toString
  }

  def routes: Route =
    pathEndOrSingleSlash {
      complete("Cool story, bro")
    } ~
      path("global") {
        get {
          complete {
            Future {
              blockingOperation
            }
          }
        }
      } ~
      path("default-dispatcher") {
        get {
          complete {
            blocking {
              blockingOperation
            }
          }
        }
      } ~
      path("blocking-dispatcher") {
        implicit val blockingDispatcher = system.dispatchers.lookup("blocking-dispatcher")

        get {
          complete {
            Future {
              blockingOperation
            }
          }
        }
      }

  // This will start the server until the return key is pressed
  startServer("localhost", 8080)
}
