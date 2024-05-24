val scala3Version = "3.4.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "$name$",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test
  )
