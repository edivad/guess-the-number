package com.example.guessthenumber

import com.example.guessthenumber.programs.Program
import com.example.guessthenumber.services.{Console, Random}

import scala.annotation.tailrec
import scala.io.StdIn

object Main {
  class StdConsole extends Console {
    def printLine(s: String): Unit = println(s)
    def readLine(): String         = StdIn.readLine()
  }

  def main(args: Array[String]): Unit = {
    new Program(new Random, new StdConsole).run()
  }
}
