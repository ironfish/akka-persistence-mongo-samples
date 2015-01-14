/**
 *  Copyright (C) 2013-2014 Duncan DeVore. <https://github.com/ironfish/>
 */
import sbt._
import Keys._

object Common {
  def Organization = "com.github.ironfish"
  def NameMongoCqrsCsApp = "mongo-cqrs-cs-app"
  def NameMongoCqrsEsApp = "mongo-cqrs-es-app"
  def AkkaVersion = "2.3.6"
  def CrossScalaVersions = Seq("2.10.4", "2.11.4")
  def EmbeddedMongoVersion = "1.46.1"
  def PluginVersion = "0.7.5"
  def SalatVersion = "1.9.9"
  def ScalaStmVersion = "0.7"
  def ScalaVersion = "2.11.4"
  def ScalatestVersion = "2.2.2"
  def ScalazVersion = "7.1.0"
  def ParallelExecutionInTest = false
  def ScalaCOptions = Seq( "-deprecation", "-unchecked", "-feature", "-language:postfixOps" )
  def TestCompile = "test->test;compile->compile"
}
