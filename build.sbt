name := "AIML To XML"

version := "0.15.0"

scalaVersion := "2.10.2"

EclipseKeys.withSource := true

resolvers ++= Seq(
                  "snapshots"            at "http://oss.sonatype.org/content/repositories/snapshots",
                  "staging"              at "http://oss.sonatype.org/content/repositories/staging"  ,
                  "releases"             at "http://oss.sonatype.org/content/repositories/releases" 
                 )
                 
scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  Seq(
    "org.scala-lang"           % "scala-reflect"               % "2.10.2",
    "org.scalatest"            % "scalatest_2.10"              % "2.0.M8"              % "test",
    "org.mockito"              % "mockito-all"                 % "1.9.5"               % "test"
  )
}
 