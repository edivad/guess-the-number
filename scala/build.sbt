name         := "Guess the Number"
scalaVersion := "3.2.0"
libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest"   % "3.2.13" % Test,
  "org.typelevel" %% "cats-core"   % "2.8.0",
  "org.typelevel" %% "cats-effect" % "3.3.14"
)
