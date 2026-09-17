import org.mdedetrich.apache.sonatype.{ApacheNexusCredentials, CredentialProvider}
import sbt.util.{Level, Logger}

ThisBuild / scalaVersion                   := "2.13.10"
ThisBuild / apacheSonatypeProjectProfile   := "project"
ThisBuild / apacheSonatypeUseCentralPortal := true
ThisBuild / version                        := "0.1.0-SNAPSHOT"
name                                       := "some-name"

ThisBuild / apacheSonatypeCredentialsProvider := new CredentialProvider {
  override def credentials(logger: Logger, level: Level.Value): Option[ApacheNexusCredentials] =
    Some(ApacheNexusCredentials("central-user", "central-token"))
}

TaskKey[Unit]("check-organization") := {
  val org = "org.apache.project"
  if (organization.value != org || (ThisBuild / organization).value != org)
    sys.error(s"expected organization to be $org, instead got ${organization.value}")
  ()
}

TaskKey[Unit]("check-sonatype-credential-host") := {
  val host = "central.sonatype.com"
  if (sonatypeCredentialHost.value != host || (ThisBuild / sonatypeCredentialHost).value != host)
    sys.error(s"expected sonatypeCredentialHost to be $host, instead got ${sonatypeCredentialHost.value}")
  ()
}

TaskKey[Unit]("check-credentials") := {
  val direct = credentials.value.collect { case d: DirectCredentials => d }
  if (!direct.exists(d => d.host == "central.sonatype.com" && d.userName == "central-user" && d.passwd == "central-token"))
    sys.error(s"expected credentials for central.sonatype.com, instead got ${direct.map(d => d.host -> d.userName)}")
  if (direct.exists(_.host == "repository.apache.org"))
    sys.error("credentials for repository.apache.org should not be registered when using the Central Portal")
  ()
}

TaskKey[Unit]("check-snapshot-publish-to") := {
  val centralSnapshots = "https://central.sonatype.com/repository/maven-snapshots/"
  val resolver         = publishTo.value
  if (!resolver.exists { case resolver: MavenRepository => resolver.root == centralSnapshots; case _ => false })
    sys.error(s"expected publishTo to be the central snapshots repository, instead got $resolver")
  ()
}

TaskKey[Unit]("check-release-publish-to") := {
  val staging = (ThisBuild / baseDirectory).value / "target" / "sona-staging"
  publishTo.value match {
    case Some(resolver: FileRepository) if resolver.name == "local-staging" =>
      val root = resolver.patterns.artifactPatterns.head
      if (!root.startsWith(staging.getAbsolutePath))
        sys.error(s"expected publishTo to stage under $staging, instead got $root")
    case other => sys.error(s"expected publishTo to be sbt's local-staging resolver, instead got $other")
  }
  ()
}
