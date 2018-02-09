name := "Scala AIML"

version := "1.1.0"

scalaVersion := "2.12.4"

EclipseKeys.withSource := true

resolvers ++= Seq(
                  "snapshots"            at "http://oss.sonatype.org/content/repositories/snapshots",
                  "staging"              at "http://oss.sonatype.org/content/repositories/staging"  ,
                  "releases"             at "http://oss.sonatype.org/content/repositories/releases"
                 )
                 
scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  Seq(
    "org.scala-lang.modules"     %% "scala-xml"                  % "1.0.6",
    "org.scala-lang.modules"     %% "scala-parser-combinators"   % "1.0.4",
    "org.scalatest"              %% "scalatest"                  % "3.0.4"                % "test",
    "org.mockito"                % "mockito-core"                % "2.13.0"               % "test"
  )
}

logBuffered in Test := false

// because of mockito parallel issue
parallelExecution in Test := false
