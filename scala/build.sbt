ThisBuild / scalaVersion := "3.2.0"

lazy val root = (project in file("."))
    .settings(
        name := "Guess the Number",
        libraryDependencies ++= Seq(
            "org.scalatest" %% "scalatest"   % "3.2.13" % Test,
            "org.typelevel" %% "cats-core"   % "2.8.0",
            "org.typelevel" %% "cats-effect" % "3.3.14"
        )
    )
