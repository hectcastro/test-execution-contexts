lazy val akkaHttpVersion = "10.0.9"
lazy val akkaVersion    = "2.5.3"

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "com.azavea",
      scalaVersion    := "2.12.2"
    )),
    name := "test-execution-contexts",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"         % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"       % akkaVersion
    )
  )
