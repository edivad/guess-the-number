package com.example.guessthenumber

import cats.Id
import cats.effect.{IO, IOApp}
import com.example.guessthenumber.programs.Program
import com.example.guessthenumber.services.{Console, Random}

import scala.annotation.tailrec
import scala.io.StdIn

object Main extends IOApp.Simple {

  private val random = Random.make[IO]

  private val console = new Console[IO] {
    def printLine(s: String): IO[Unit] = IO.println(s)
    def readLine(): IO[String]         = IO.readLine
  }

  private val program = new Program(random, console)

  def run: IO[Unit] = program.run()
}
