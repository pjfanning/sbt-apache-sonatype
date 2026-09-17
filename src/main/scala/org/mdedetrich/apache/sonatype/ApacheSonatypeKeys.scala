package org.mdedetrich.apache.sonatype

import sbt.util.Level
import sbt.{File, SettingKey, settingKey}

trait ApacheSonatypeKeys {
  lazy val apacheSonatypeBaseRepo: SettingKey[String] =
    settingKey[String]("The base host for the Apache Nexus repository")
  lazy val apacheSonatypeProjectProfile: SettingKey[String] =
    settingKey[String](
      "The projects name which becomes the ending part of the sonatype profile, typically this is the Apache project name"
    )
  lazy val apacheSonatypeGroupId: SettingKey[String] =
    settingKey[String](
      "The Maven groupId (sbt organization) used for published artifacts, defaults to org.apache.<apacheSonatypeProjectProfile>"
    )
  lazy val apacheSonatypeUseCentralPortal: SettingKey[Boolean] =
    settingKey[Boolean](
      "Whether to publish to the Sonatype Central Portal (central.sonatype.com) using sbt's built-in support instead of the Apache Nexus repository"
    )
  lazy val apacheSonatypeCredentialsProvider: SettingKey[CredentialProvider] =
    settingKey[CredentialProvider]("How to provide credentials to Apaches Nexus repository")
  lazy val apacheSonatypeCredentialsLogLevel: SettingKey[Level.Value] =
    settingKey[Level.Value]("The log level to use when logging about potential problems in resolving credentials")
  lazy val apacheSonatypeCredentialsHost: SettingKey[String] =
    settingKey[String]("The host that is used specifying the credentials to the Apache Nexus repo")
  lazy val apacheSonatypeCredentialsUserEnvVar: SettingKey[String] =
    settingKey[String]("The user variable used when resolving credentials via environment variables")
  lazy val apacheSonatypeCredentialsPasswordEnvVar: SettingKey[String] =
    settingKey[String]("The password variable used when resolving credentials via environment variables")
  lazy val apacheSonatypeOrganizationName: SettingKey[String] =
    settingKey[String]("The organization name that is used in the Maven POM")
  lazy val apacheSonatypeOrganizationHomePage: SettingKey[sbt.URL] =
    settingKey[sbt.URL]("The organization home page that is used in the Maven POM")
  lazy val apacheSonatypeLicenseFile: SettingKey[File] =
    settingKey[File]("The LICENSE file which needs to be included in published artifact")
  lazy val apacheSonatypeNoticeFile: SettingKey[File] =
    settingKey[File]("The NOTICE file which needs to be included in published artifact")
  lazy val apacheSonatypeDisclaimerFile: SettingKey[Option[File]] =
    settingKey[Option[File]]("The DISCLAIMER file which can optionally be included in published artifact")
  lazy val apacheSonatypeArtifactNameProcessor: SettingKey[String => String] =
    settingKey[String => String](
      "A function that turns the name setting into an ASF complaint human readable form for the pom.xml"
    )
}
