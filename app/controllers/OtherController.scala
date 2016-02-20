package controllers

import javax.inject.{Inject, Singleton}

import akka.actor.{ActorRef, ActorSystem}
import akka.pattern.ask
import akka.util.Timeout
import com.rxthings.di._
import play.api.mvc.{Action, Controller}

import scala.concurrent.duration.DurationInt


/**
 *
 */
@Singleton
class OtherController @Inject()(implicit sys: ActorSystem) extends Controller {
  val actor: ActorRef = inject[ActorRef]

  val actorSays = Action.async {
    implicit val timeout = Timeout(5.seconds)
    implicit val execctx = sys.dispatcher

    (actor ? "Hi").mapTo[String].map(Ok(_))
  }
}
