name := "AIML To XML"

version := "0.0.1"

scalaVersion := "2.10.0"

EclipseKeys.withSource := true

resolvers ++= Seq(
                  "snapshots"            at "http://oss.sonatype.org/content/repositories/snapshots",
                  "staging"              at "http://oss.sonatype.org/content/repositories/staging"  ,
                  "releases"             at "http://oss.sonatype.org/content/repositories/releases" 
                 )
                 
scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  val liftVersion = "2.5.1"
  Seq(
    "org.scalatest"            % "scalatest_2.10"              % "2.0.M8"              % "test",
    "org.mockito"              % "mockito-all"                 % "1.9.5"               % "test"
  )
}
