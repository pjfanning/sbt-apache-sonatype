addSbtPlugin("com.github.sbt" % "sbt-ci-release"     % "1.12.1")
addSbtPlugin("org.scalameta"  % "sbt-scalafmt"       % "2.6.2")
addSbtPlugin("com.github.sbt" % "sbt-github-actions" % "0.32.1")
addSbtPlugin("ch.epfl.scala"  % "sbt-scalafix"       % "0.14.9")

libraryDependencies += "org.scala-sbt" %% "scripted-plugin" % sbtVersion.value
