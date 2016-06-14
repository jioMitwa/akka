resolvers += "sbt-idea-repo" at "http://mpeltonen.github.com/maven/"

addSbtPlugin("io.spray" % "sbt-revolver" % "0.7.1")

addSbtPlugin("com.github.mpeltonen" % "sbt-idea" % "1.5.2")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")

// Scala Style Plugin
addSbtPlugin("org.scalastyle" %% "scalastyle-sbt-plugin" % "0.3.2")

// Enable the sbt eclipse plugin
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % "4.0.0")

//Sbt assenbly plugin
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.11.2")
