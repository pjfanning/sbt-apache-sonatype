addSbtPlugin("org.xerial.sbt" % "sbt-sonatype" % "3.12.2")
addSbtPlugin("com.github.sbt" % "sbt-pgp"      % "2.3.1")

addSbtPlugin("com.github.sbt" % "sbt-ci-release"     % "1.12.0")
addSbtPlugin("org.scalameta"  % "sbt-scalafmt"       % "2.5.2")
addSbtPlugin("com.github.sbt" % "sbt-github-actions" % "0.31.0")
addSbtPlugin("ch.epfl.scala"  % "sbt-scalafix"       % "0.14.7")

libraryDependencies += "org.scala-sbt" %% "scripted-plugin" % sbtVersion.value
