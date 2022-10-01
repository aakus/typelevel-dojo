ThisBuild / scalaVersion := "3.2.0"

ThisBuild / resolvers ++= Resolver.sonatypeOssRepos("snapshots")

val V /* Versions */ = new {
  val fs2 = "3.3.0"
  val weaver = "0.8.0"
  val catsEffect = "3.3.14"
  val tapir = "1.1.2"
}

val commonSettings = Seq(
  scalacOptions ++= Seq(
    "-source:future"
  ),
  libraryDependencies ++= Seq(
    "org.typelevel" %% "cats-effect" % V.catsEffect,
    "com.disneystreaming" %% "weaver-cats" % V.weaver % Test,
    "com.disneystreaming" %% "weaver-scalacheck" % V.weaver % Test
  ),
  testFrameworks += new TestFramework("weaver.framework.CatsEffect")
)

lazy val experiments = (project in file("experiments"))
  .settings(commonSettings)

lazy val server = (project in file("server"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      "com.softwaremill.sttp.tapir" %% "tapir-json-circe" % V.tapir,
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-server" % V.tapir,
      "com.softwaremill.sttp.tapir" %% "tapir-http4s-client" % V.tapir
    )
  )

lazy val streams = (project in file("streams"))
  .settings(
    commonSettings,
    libraryDependencies ++= Seq(
      "co.fs2" %% "fs2-core" % V.fs2,
      "co.fs2" %% "fs2-io" % V.fs2,
      "co.fs2" %% "fs2-reactive-streams" % V.fs2,
      "co.fs2" %% "fs2-scodec" % V.fs2
    )
  )
