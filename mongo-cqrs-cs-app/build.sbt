organization := Common.Organization

name := Common.NameMongoCqrsCsApp

scalaVersion := Common.ScalaVersion

crossScalaVersions := Common.CrossScalaVersions

version := "0.3.0-SNAPSHOT"

parallelExecution in Test := Common.ParallelExecutionInTest

scalacOptions ++= Common.ScalaCOptions

publishLocal := {}

publish := {}

publishArtifact := false

resolvers += "Sonatype OSS Releases" at "https://oss.sonatype.org/content/repositories/releases"

libraryDependencies ++= Seq(
  "com.typesafe.akka"      %% "akka-persistence-experimental"    % Common.AkkaVersion             % "compile",
  "com.github.ddevore"     %% "akka-persistence-mongo-casbah"    % Common.PluginVersion           % "compile",
  "org.scalaz"             %% "scalaz-core"                      % Common.ScalazVersion           % "compile",
  "com.novus"              %% "salat"                            % Common.SalatVersion            % "compile",
  "org.scala-stm"          %% "scala-stm"                        % Common.ScalaStmVersion         % "compile",
  "com.typesafe.akka"      %% "akka-slf4j"                       % Common.AkkaVersion             % "compile",
  "com.typesafe.akka"      %% "akka-testkit"                     % Common.AkkaVersion             % "test",
  "de.flapdoodle.embed"     % "de.flapdoodle.embed.mongo"        % Common.EmbeddedMongoVersion    % "test",
  "org.scalatest"          %% "scalatest"                        % Common.ScalatestVersion        % "test"
)
