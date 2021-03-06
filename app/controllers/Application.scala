package controllers

import javax.inject.{Inject, Singleton}

import akka.actor.{ActorRef, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout
import api.SomeThing
import com.rxthings.di._
import play.api.mvc.{Action, Controller}

import scala.concurrent.duration.DurationInt

@Singleton
class Application @Inject()(implicit sys: ActorSystem) extends Controller {
  val thing: SomeThing = inject[SomeThing]
  val actor: ActorRef = inject[ActorRef]

  val index = Action {
    Ok("Index")
  }

  val thingSays = Action {
    Ok(thing.doit())
  }

  val actorSays = Action.async {
    implicit val timeout = Timeout(5.seconds)
    implicit val execctx = sys.dispatcher

    (actor ? "Hi").mapTo[String].map(Ok(_))
  }
}
