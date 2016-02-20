package modules

import api.SomeThing
import net.codingwell.scalaguice.ScalaModule

/**
 *
 */
class BasicModule extends ScalaModule {
  def configure(): Unit = {
    println("configuring BasicModule")
    bind[SomeThing].to[DefaultThing]
  }
}

class DefaultThing extends SomeThing {
  def doit(): String = "DefaultThing done"
}