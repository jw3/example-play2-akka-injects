package modules

import akka.actor.{Props, ActorRef, Actor, ActorSystem}
import api.SomeActor
import com.google.inject.{Inject, Injector, Provider}
import net.codingwell.scalaguice.InjectorExtensions._
import net.codingwell.scalaguice.ScalaModule

import scala.util.Random

/**
 *
 */
class ModuleWithProvider extends ScalaModule {
  def configure(): Unit = {
    bind[ActorRef]
    .toProvider(new SingletonActorProvider[SomeKindOfActor])
    .asEagerSingleton()
  }
}

class SingletonActorProvider[T <: Actor : Manifest] extends Provider[ActorRef] {
  @Inject() private[this] val injector: Injector = null

  override def get(): ActorRef = {
    implicit val sys = injector.instance[ActorSystem]
    sys.actorOf(SomeKindOfActor.props())
  }
}

object SomeKindOfActor {
  def props() = Props(new SomeKindOfActor(Random.alphanumeric.take(6).mkString))
}

class SomeKindOfActor(id: String) extends SomeActor {
  def receive: Receive = {
    case _ => sender() ! s"im $id, the actor!"
  }
}