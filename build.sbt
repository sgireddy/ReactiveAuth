import sbt._
import sbt.Keys._
import play.Project._
import java.io.File

play.Project.playScalaSettings

name         := "ReactiveAuth"

version      := "0.0.1"

scalaVersion := "2.10.3"

//offline := "true".equalsIgnoreCase(sys.props("sbt.offline"))
offline      := "false".equalsIgnoreCase(sys.props("sbt.offline"))

libraryDependencies ++= Seq(
   "com.typesafe" %% "play-plugins-mailer" % "2.2.0",
    "org.apache.commons"      %  "commons-email" % "1.3",
    "com.github.nscala-time"  %% "nscala-time"   % "0.2.0" withSources,
    //"org.scalatest"           %% "scalatest"   % "2.0.M5b" % "test",
    //"com.typesafe.play"       %% "play"        % "2.2.0" withSources,
    //"com.typesafe.play"       %% "play-json"                   % "2.2.0" withSources,
    //"com.micronautics"        % "securesocial"                % "2.2.0" withSources, 
    //"com.micronautics"        % "securesocial"                % "2.2.0" exclude("org.scala-stm", "scala-stm_2.10.0") withSources,
    "com.typesafe.play"       %% "anorm"         % "2.2.0" exclude("org.scala-stm", "scala-stm_2.10.0") exclude("play", "*"),
    "com.typesafe.play"       %% "play-jdbc"     % "2.2.0" exclude("org.scala-stm", "scala-stm_2.10.0") exclude("play", "*"),
    "com.typesafe.play"       %% "play-json"     % "2.2.0" exclude("org.scala-stm", "scala-stm_2.10.0") exclude("play", "*"),
    "com.micronautics"        % "securesocial"   % "2.2.0" exclude("org.scala-stm", "scala-stm_2.10.0") exclude("play", "*") withSources,
    "com.github.kevinsawicki" % "timeago" % "1.0.1" withSources,
    "org.scala-tools.time" % "time_2.9.1" % "0.5",
    "commons-io" % "commons-io" % "1.3.2" withSources,
    "org.apache.commons" % "commons-lang3" % "3.1",
    "com.github.scala-incubator.io" %% "scala-io-core" % "0.4.0" withSources,
    "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.0" withSources,
    //"securesocial" %% "securesocial" % "master-SNAPSHOT" exclude("org.scala-stm", "scala-stm_2.10.0"),
    //"securesocial" %% "securesocial" % "master-pawan" exclude("org.scala-stm", "scala-stm_2.10.0") withSources,
    //"be.objectify" % "deadbolt-scala_2.10" % "2.1-RC2" exclude("org.scala-stm", "scala-stm_2.10.0") exclude("play", "*") withSources,
    "be.objectify" %% "deadbolt-scala" % "2.2-RC1" exclude("org.scala-stm", "scala-stm_2.10.0") exclude("play", "*") withSources,
    //"org.reactivemongo" %% "play2-reactivemongo" % "0.10.0-SNAPSHOT" exclude("org.scala-stm", "scala-stm_2.10.0") exclude("play", "*") withSources,
    //"play-autosource"   %% "reactivemongo"       % "1.0-SNAPSHOT",
    "com.novus" %% "salat" % "1.9.2",
    //"se.radley" %% "play-plugins-salat" % "1.3.0",
    "org.scalaz" %% "scalaz-core" % "7.0.3" withSources
    )

    organization := "SriBhag Labs"

    resolvers ++= Seq(
        "webjars" at "http://webjars.github.com/m2",
        Resolver.url("play-plugin-releases", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns),
        //Resolver.file("Local Repository", file(sys.env.get("PLAY_HOME").map(_ + "/repository/local").getOrElse("")))(Resolver.ivyStylePatterns),
        Resolver.url("play-plugin-releases", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-releases/"))(Resolver.ivyStylePatterns),
        Resolver.url("play-plugin-snapshots", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots/"))(Resolver.ivyStylePatterns),
        Resolver.url("sbt-plugin-snapshots", new URL("http://repo.scala-sbt.org/scalasbt/sbt-plugin-snapshots/"))(Resolver.ivyStylePatterns),
        Resolver.url("Sonatype Snapshots",url("http://oss.sonatype.org/content/repositories/snapshots/"))(Resolver.ivyStylePatterns),
        Resolver.url("Objectify Play Repository", url("http://schaloner.github.com/releases/"))(Resolver.ivyStylePatterns),
        Resolver.url("Objectify Play Snapshot Repository", url("http://schaloner.github.com/snapshots/"))(Resolver.ivyStylePatterns)
        )

    scalacOptions ++= Seq("-deprecation", "-encoding", "UTF-8", "-feature", "-target:jvm-1.6", "-unchecked",
        "-Ywarn-adapted-args", "-Ywarn-value-discard", "-Xlint")

