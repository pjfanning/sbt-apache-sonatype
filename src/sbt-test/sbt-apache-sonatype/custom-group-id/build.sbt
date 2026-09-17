ThisBuild / scalaVersion                 := "2.13.10"
ThisBuild / apacheSonatypeProjectProfile := "project"
ThisBuild / apacheSonatypeGroupId        := "org.apache.project.custom"
name                                     := "some-name"

TaskKey[Unit]("check-organization") := {
  val org = "org.apache.project.custom"
  if (organization.value != org || (ThisBuild / organization).value != org)
    sys.error(s"expected organization to be $org, instead got ${organization.value}")
  ()
}

// the Apache Nexus staging profile is still derived from apacheSonatypeProjectProfile
TaskKey[Unit]("check-sonatype-profile-name") := {
  val profile = "org.apache.project"
  if (sonatypeProfileName.value != profile || (ThisBuild / sonatypeProfileName).value != profile)
    sys.error(s"expected sonatypeProfileName to be $profile, instead got ${sonatypeProfileName.value}")
  ()
}

TaskKey[Unit]("check-pom-group-id") := {
  val file    = makePom.value
  val xml     = scala.xml.XML.loadFile(file)
  val groupId = (xml \ "groupId").text
  if (groupId != "org.apache.project.custom")
    sys.error(s"expected pom groupId to be org.apache.project.custom, instead got $groupId")
}
