#### Example of using Akka Injects in a Play2 App
-

##### Modules
  The modules are loaded with SPI from `conf/META-INF/services/`

##### Run 
  `$ sbt run`

##### Dockerize
The play plugin has the `sbt-native-packager` included, so

  `$ sbt docker:publishLocal`
  
##### Run in Docker
  `$ docker run -p 8080:9000 play-template:1.0`
