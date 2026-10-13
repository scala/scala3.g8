// This build is for this Giter8 template.
// To test the template run `g8` or `g8Test` from the sbt session.
// See http://www.foundweekends.org/giter8/testing.html#Using+the+Giter8Plugin for more details.
import sbt.protocol.testing.TestResult

// sbt 2.0.10 publishes scripted-sbt for Scala 3.8.4, so ++ 2.12.21 cannot resolve it.
lazy val scalaVersions = Seq("3.8.4")
lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-template",
    // sbt 2 types test as TestResult. g8Test still returns Unit, so report Passed when it succeeds.
    Test / test := {
      val _ = (Test / g8Test).toTask("").value
      TestResult.Passed
    },
    scriptedLaunchOpts ++= List("-Xms1024m", "-Xmx1024m", "-XX:ReservedCodeCacheSize=128m", "-Xss2m", "-Dfile.encoding=UTF-8"),
    // sbt 2's scripted task reads this key, but sbt-giter8 does not enable ScriptedPlugin's global settings.
    scriptedKeepTempDirectory := false,
    scalaVersion := scalaVersions.head,
    crossScalaVersions := scalaVersions
  )

ThisBuild / githubWorkflowJavaVersions := Seq("17", "21", "25").map(JavaSpec.temurin)
ThisBuild / githubWorkflowScalaVersions := scalaVersions
ThisBuild / githubWorkflowBuildPostamble := Seq(
  // This runs the template with the default parameters, and runs test within the templated app.
  WorkflowStep.Run(List("sbt -Dfile.encoding=UTF8 -J-XX:ReservedCodeCacheSize=256M test")),
  WorkflowStep.Run(List(
    s"pushd target/out/jvm/scala-${scalaVersions.head}/scala3-template/sbt-test/scala3-template/scripted && sbt run test && popd"
  )),
)
ThisBuild / githubWorkflowPublishTargetBranches := Nil
Global / onChangedBuildSource := ReloadOnSourceChanges