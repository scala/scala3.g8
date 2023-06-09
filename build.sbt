// This build is for this Giter8 template.
// To test the template run `g8` or `g8Test` from the sbt session.
// See http://www.foundweekends.org/giter8/testing.html#Using+the+Giter8Plugin for more details.
lazy val scalaVersions = Seq("2.12.18")
lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-template",
    Test / test := {
      val _ = (Test / g8Test).toTask("").value
    },
    scriptedLaunchOpts ++= List("-Xms1024m", "-Xmx1024m", "-XX:ReservedCodeCacheSize=128m", "-Xss2m", "-Dfile.encoding=UTF-8"),
    resolvers += Resolver.url("typesafe", url("https://repo.typesafe.com/typesafe/ivy-releases/"))(Resolver.ivyStylePatterns),
    crossScalaVersions := scalaVersions
  )

ThisBuild / githubWorkflowJavaVersions := Seq("8", "11", "17").map(JavaSpec.temurin)
ThisBuild / githubWorkflowScalaVersions := scalaVersions
ThisBuild / githubWorkflowBuildPostamble := Seq(
  // This runs the template with the default parameters, and runs test within the templated app.
  WorkflowStep.Run(List("sbt -Dfile.encoding=UTF8 -J-XX:ReservedCodeCacheSize=256M test")),
  WorkflowStep.Run(List("pushd target/sbt-test/scala3-template/scripted && sbt run test && popd")),
)
ThisBuild / githubWorkflowPublishTargetBranches := Nil
Global / onChangedBuildSource := ReloadOnSourceChanges