package com.omitwa.tuto.arest

import akka.actor.Actor

/**
 * @author sunil

 */

case class USER_ID(userId:String)
case class ID(id:Int)
case class USER_JOBS()
case class UserJob(userName:String,numberOfReq:Int)
    
class MyActor extends Actor{
  var id:Int =0;
  def receive = {
    case USER_ID(userId:String) => id=id+1
                  sender ! ID(id)
    case USER_JOBS => 
                   val x:List[UserJob]=List(UserJob("tunu",1),UserJob("munu",2))
                  //sender ! ID(count)
                  sender ! x
    case _ => println("unknown request")
  }
}