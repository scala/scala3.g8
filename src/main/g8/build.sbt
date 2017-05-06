val dottyVersion = "0.1.1-bin-20170502-df22149-NIGHTLY"
// or latest Nightly build
// val dottyVersion = dottyLatestNightlyBuild.get

lazy val root = (project in file(".")).
  settings(
    name := "dotty-hello-world",
    description := "Hello world sbt project that compiles using Dotty",
    version := "0.1",

    scalaVersion := dottyVersion
  )
