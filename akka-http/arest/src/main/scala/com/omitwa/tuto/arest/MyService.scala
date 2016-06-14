package com.omitwa.tuto.arest

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.ContentTypes
import akka.actor.Props
import akka.pattern.ask
import akka.dispatch.OnSuccess
import akka.dispatch.OnComplete
import scala.util.Failure
import scala.util.Success
import scala.concurrent.Future
import akka.util.Timeout
import scala.concurrent.duration._
import akka.http.scaladsl.model._
import akka.http.scaladsl.marshalling.ToResponseMarshallable
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import akka.http.scaladsl.server.RouteResult
import akka.http.scaladsl.server.RouteResult.Complete
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives.parameter
import akka.http.scaladsl.server.Directives.path
import akka.http.scaladsl.server.Directives.authenticateBasic
import akka.http.scaladsl.server.directives.Credentials
import akka.http.scaladsl.server.Directives._

/**
 * @author sunil
 */

trait MyService {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  // formats for unmarshalling and marshalling
  implicit val asupFormat = jsonFormat2(UserJob)

  implicit val timeout = Timeout(Duration(5000, MILLISECONDS))
  val myActor = system.actorOf(Props[MyActor], name = "myactor")

  implicit val bidsFormat = jsonFormat1(ID)

  def myUserPassAuthenticator(credentials: Credentials): Option[String] =
    credentials match {
      /* case p @ Credentials.Provided(id) => 
                                     println("ghot userId"+id)
                                     if(p.verify("mands@123")) Some(id)
                                     else None*/
      case p @ Credentials.Provided(id) if p.verify("test@123") => Some(id)
      case _ => None
    }

  val route: Route =
    path("id") {
      println("in id path")
      get {
        authenticateBasic("wrong password", myUserPassAuthenticator(_)) { u =>
          parameter("userId".as[String]) { (userId) =>
            println("get")
            complete {
              println("complete")
              (myActor ? USER_ID(userId)).mapTo[ID]
            }
          }
        }
      }
    } ~
      path("userJob") {
        get {
          authenticateBasic("wrong password", myUserPassAuthenticator(_)) { u =>
            complete {
              (myActor ? USER_JOBS).mapTo[List[UserJob]]
            }
          }
        }
      }
  
  
}

