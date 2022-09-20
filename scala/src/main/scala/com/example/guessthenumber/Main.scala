package com.example.guessthenumber

import cats.Id
import cats.effect.std.Console as CatsConsole
import cats.effect.{IO, IOApp}
import com.example.guessthenumber.programs.Program
import com.example.guessthenumber.services.{Console, Random}

import scala.annotation.tailrec
import scala.io.StdIn

object Main extends IOApp.Simple {

  private val random = Random.make[IO]

  private val console = new Console[IO] {
    private val catsConsole = CatsConsole[IO]

    def printLine(s: String): IO[Unit] = catsConsole.println(s)
    def readLine(): IO[String]         = catsConsole.readLine
  }

  private val program = new Program(random, console)

  def run: IO[Unit] = program.run()
}
