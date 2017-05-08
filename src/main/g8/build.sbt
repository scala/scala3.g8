
val defaultDottyVersion = "0.1.1-bin-20170502-df22149-NIGHTLY" // TODO: put technical release version

val useLatestDottyNightly = settingKey[Boolean]("Use the latest Dotty nightly build.")

lazy val root = (project in file(".")).
  settings(
    name := "dotty-cross-template",
    description := "Template sbt project that is cross compiled using Dotty and Scala 2",
    version := "0.1",

    useLatestDottyNightly := false,

    // To make the compiler and REPL use Dotty
    scalaVersion := {
      if (useLatestDottyNightly.value) dottyLatestNightlyBuild.get
      else defaultDottyVersion
    }
  )
