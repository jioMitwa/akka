package com.omitwa.tuto.arest

import scala.io.StdIn
import akka.http.scaladsl.Http

/**
 * @author sunil
 */
object WebServer extends App with MyService {
  

 val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

 bindingFuture.onFailure {
  case ex: Exception =>
    println("Exception"+ex.getMessage)
    system.terminate()
}
 println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")
  StdIn.readLine() // let it run until user presses return
  
  bindingFuture
    .flatMap(_.unbind()) // trigger unbinding from the port
    .onComplete(_ ⇒ system.terminate()) // and shutdown when done
  
}
