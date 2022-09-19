package com.example.guessthenumber

import cats.Id
import com.example.guessthenumber.programs.Program
import com.example.guessthenumber.services.{Console, Random}

import scala.annotation.tailrec
import scala.io.StdIn

object Main {
  class StdConsole extends Console[Id] {
    def printLine(s: String): Id[Unit] = println(s)
    def readLine(): Id[String]         = StdIn.readLine()
  }

  def main(args: Array[String]): Unit = {
    new Program(new Random, new StdConsole).run()
  }
}
