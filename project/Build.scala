import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

val appName         = "ReactiveAuth"

val appVersion      = "0.0.1"

  val mandubianRepo = Seq(
    "Mandubian repository snapshots" at "https://github.com/mandubian/mandubian-mvn/raw/master/snapshots/",
    "Mandubian repository releases" at "https://github.com/mandubian/mandubian-mvn/raw/master/releases/"
  )
  
  val localIvy2Repo = Seq (
    //Resolver.url("Local IVY2 Repository", url("file://"+Path.userHome.absolutePath+"/.ivy2/local"))(Resolver.ivyStylePatterns)	
	Resolver.url("Local IVY2 Repository", url("file://bv3248/c$/Users/sgireddy/.ivy2/local"))(Resolver.ivyStylePatterns)
  )

  val sonatypeRepo = Seq(
    "Sonatype Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/"
  )
  val appDependencies = Seq()

  val main = play.Project(appName, appVersion, appDependencies).settings(
  
    resolvers ++= sonatypeRepo ++ mandubianRepo ++ localIvy2Repo,
    libraryDependencies ++= Seq(
      "play-autosource"   %% "reactivemongo"       % "2.0-SNAPSHOT",
      "org.specs2"        %% "specs2"              % "1.13"        % "test",
      "junit"              % "junit"               % "4.8"         % "test"
    )
  ) 
	  
	  lazy val reactiveMongo = ProjectRef( base = file("C:\\git\\play-autosource\\reactivemongo"), id = "reactivemongo")
	  lazy val autoSource = ProjectRef( base = file("C:\\git\\play-autosource"), id = "play-autosource")
}
