organization := "com.github.jw3"
name := "play-akka-injects"
version := "1.0"
scalaVersion := "2.11.7"

resolvers += "jw3 at bintray" at "https://dl.bintray.com/jw3/maven"

libraryDependencies ++= {
  Seq(
    "com.rxthings" %% "akka-injects" % "0.3",
    "com.typesafe" % "config" % "1.3.0",
    "org.scalatest" %% "scalatest" % "2.2.5" % Test
  )
}

// play configuration
enablePlugins(PlayScala)
routesGenerator := InjectedRoutesGenerator
sourceDirectories in TwirlKeys.compileTemplates := (unmanagedSourceDirectories in Compile).value
