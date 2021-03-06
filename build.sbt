// Project name (artifact name in Maven)
name := "example-java-sbt"

// orgnization name (e.g., the package name of the project)
organization := "eu.christophburmeister.playground"

version := "0.0.1-SNAPSHOT"

// project description
description := "example project for scala build of java sources"

// Enables publishing to maven repo
publishMavenStyle := true

// Define location of publishing target
// local deployment (similar to "mvn install")
// publishTo := Some(Resolver.file("file",  new File( "/data/local-maven-repo" )))
// remote deployment (similar to "mvn deploy")
publishTo := {
  val nexus = "http://localhost:8081"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "/repository/sbt-snapshots")
  else
    Some("releases"  at nexus + "/repository/sbt-releases")
}

credentials += Credentials(Path.userHome / ".sbt" / "nexus-credentials.properties")

// Do not append Scala versions to the generated artifacts
crossPaths := false

// This forbids including Scala related libraries into the dependency
// If you do not want to include scala-library.jar (Scala’s core library) within the generated pacakge, set autoScalaLibrary to false. 
// This enables building pure-java project with sbt.
autoScalaLibrary := false

// library dependencies. (orginization name) % (project name) % (version) % (scope)
libraryDependencies ++= Seq(
   "junit" % "junit" % "3.8.1" % "test"  // Test-only dependency
)

