// This build is for this Giter8 template.
// To test the template run `g8` or `g8Test` from the sbt session.
// See http://www.foundweekends.org/giter8/testing.html#Using+the+Giter8Plugin for more details.
import sbt.protocol.testing.TestResult

lazy val scala3Version = "3.8.4"
lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-template",
    Test / test := {
      val _ = (Test / g8Test).toTask("").value
      TestResult.Passed
    },
    scriptedLaunchOpts ++= List("-Xms1024m", "-Xmx1024m", "-XX:ReservedCodeCacheSize=128m", "-Xss2m", "-Dfile.encoding=UTF-8"),
    scriptedKeepTempDirectory := false,
    scalaVersion := scala3Version
  )

ThisBuild / githubWorkflowJavaVersions := Seq("17", "21", "25").map(JavaSpec.temurin)
ThisBuild / githubWorkflowScalaVersions := Seq(scala3Version)
ThisBuild / githubWorkflowBuildPostamble := Seq(
  // This runs the template with the default parameters, and runs test within the templated app.
  WorkflowStep.Run(List("sbt -Dfile.encoding=UTF8 -J-XX:ReservedCodeCacheSize=256M test")),
  WorkflowStep.Run(List(
    s"pushd target/out/jvm/scala-$scala3Version/scala3-template/sbt-test/scala3-template/scripted && sbt run test && popd"
  )),
)
ThisBuild / githubWorkflowPublishTargetBranches := Nil
Global / onChangedBuildSource := ReloadOnSourceChanges