val dottyVersion = "0.3.0-RC2"

lazy val root = (project in file(".")).
  settings(
    name := "dotty-simple",
    version := "0.1",

    scalaVersion := dottyVersion,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )
