ThisBuild / scalaVersion          := "2.13.10"
ThisBuild / apacheSonatypeGroupId := "org.apache.project"
name                              := "some-name"

TaskKey[Unit]("check-organization") := {
  val org = "org.apache.project"
  if (organization.value != org || (ThisBuild / organization).value != org)
    sys.error(s"expected organization to be $org, instead got ${organization.value}")
  ()
}

// without apacheSonatypeProjectProfile the sonatype profile name falls back to the groupId
TaskKey[Unit]("check-sonatype-profile-name") := {
  val profile = "org.apache.project"
  if (sonatypeProfileName.value != profile || (ThisBuild / sonatypeProfileName).value != profile)
    sys.error(s"expected sonatypeProfileName to be $profile, instead got ${sonatypeProfileName.value}")
  ()
}
